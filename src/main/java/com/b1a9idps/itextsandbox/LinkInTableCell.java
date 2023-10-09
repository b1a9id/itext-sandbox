package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.action.PdfAction;
import com.itextpdf.kernel.pdf.annot.PdfAnnotation;
import com.itextpdf.kernel.pdf.annot.PdfLinkAnnotation;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Link;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;
import com.itextpdf.layout.renderer.CellRenderer;
import com.itextpdf.layout.renderer.DrawContext;
import com.itextpdf.layout.renderer.IRenderer;

public class LinkInTableCell {

    public static void main(String[] args) throws IOException {
        var pdfCreator = new LinkInTableCellPdfCreator("/sandbox/tables/link_in_table_cell.pdf");
        pdfCreator.generatePdf();
    }

    private static class LinkInTableCellPdfCreator extends AbstractPdfCreator {

        public LinkInTableCellPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));
            Document document = new Document(pdfDocument);

            Table table = new Table(UnitValue.createPercentArray(1))
                    .useAllAvailableWidth();

            // コンテンツの一部がリンク
            Link chunk = new Link(
                    "European Business Awards",
                    PdfAction.createURI("https://itextpdf.com/en/events/itext-european-business-awards-gala-milan")
            );
            Paragraph paragraph = new Paragraph();
            paragraph.add("iText at the ");
            paragraph.add(chunk);
            paragraph.add(" gala in Milan");
            table.addCell(paragraph);

            // セル全体がリンク
            Cell cell = new Cell();
            cell.add(new Paragraph("Help us win a European Business Award!"));
            cell.setNextRenderer(new LinkInCellRenderer(
                            cell,
                            "http://itextpdf.com/blog/help-us-win-european-business-award"
                    )
            );
            table.addCell(cell);

            document.add(table);
            document.close();
        }

        private static class LinkInCellRenderer extends CellRenderer {
            protected String url;

            public LinkInCellRenderer(Cell modelElement, String url) {
                super(modelElement);
                this.url = url;
            }

            @Override
            public IRenderer getNextRenderer() {
                return new LinkInCellRenderer((Cell) modelElement, url);
            }

            @Override
            public void draw(DrawContext drawContext) {
                super.draw(drawContext);

                PdfLinkAnnotation linkAnnotation = new PdfLinkAnnotation(getOccupiedAreaBBox());
                linkAnnotation.setHighlightMode(PdfAnnotation.HIGHLIGHT_INVERT);
                linkAnnotation.setAction(PdfAction.createURI(url));
                drawContext.getDocument().getLastPage().addAnnotation(linkAnnotation);
            }
        }
    }
}
