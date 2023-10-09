package com.b1a9idps.itextsandbox;

import java.io.IOException;
import java.util.List;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.draw.ILineDrawer;
import com.itextpdf.kernel.pdf.canvas.draw.SolidLine;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Tab;
import com.itextpdf.layout.element.TabStop;
import com.itextpdf.layout.properties.TabAlignment;

public class CenterText {

    public static void main(String[] args) throws IOException {
        var pdfCreator = new CenterTextPdfCreator("/sandbox/objects/center_text.pdf");
        pdfCreator.generatePdf();
    }

    private static class CenterTextPdfCreator extends AbstractPdfCreator {

        public CenterTextPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));
            Document document = new Document(pdfDocument);
            Rectangle pageSize = pdfDocument.getDefaultPageSize();
            // ページ幅から左右のマージンを引く
            float width = pageSize.getWidth() - document.getLeftMargin() - document.getRightMargin();

            SolidLine line = new SolidLine();
            addParagraphWithTabs(document, line, width);

            MyLine customLine = new MyLine();
            addParagraphWithTabs(document, customLine, width);

            document.close();
        }

        private void addParagraphWithTabs(Document document, ILineDrawer line, float width) {
            List<TabStop> tabStops = List.of(
                    new TabStop(width / 2, TabAlignment.CENTER, line),
                    new TabStop(width, TabAlignment.LEFT, line)
            );
            Paragraph paragraph = new Paragraph();
            paragraph.addTabStops(tabStops)
                    .add(new Tab())
                    .add("Text in the middle")
                    .add(new Tab());
            document.add(paragraph);
        }

        private static class MyLine implements ILineDrawer {
            private float lineWidth = 1;
            private float offset = 2.02f;
            private Color color = ColorConstants.RED;

            @Override
            public void draw(PdfCanvas canvas, Rectangle drawArea) {
                float coordY = drawArea.getY() + lineWidth / 2 + offset;
                canvas
                        .saveState()
                        .setStrokeColor(color)
                        .setLineWidth(lineWidth)
                        .moveTo(drawArea.getX(), coordY)
                        .lineTo(drawArea.getX() + drawArea.getWidth(), coordY)
                        .stroke()
                        .restoreState();
            }

            @Override
            public float getLineWidth() {
                return lineWidth;
            }

            @Override
            public void setLineWidth(float lineWidth) {
                this.lineWidth = lineWidth;
            }

            @Override
            public Color getColor() {
                return color;
            }

            @Override
            public void setColor(Color color) {
                this.color = color;
            }

            public float offset() {
                return offset;
            }

            public void setOffset(float offset) {
                this.offset = offset;
            }
        }
    }
}
