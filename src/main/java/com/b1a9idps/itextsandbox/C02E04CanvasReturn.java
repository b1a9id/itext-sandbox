package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import static com.itextpdf.io.font.constants.StandardFonts.TIMES_BOLD;
import static com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN;
import static com.itextpdf.kernel.colors.DeviceCmyk.CYAN;

public class C02E04CanvasReturn {

    private static final String DEST = "/chapter02/canvas_return.pdf";

    public static void main(String[] args) throws Exception {
        var pdfCreator = new C02E04CanvasReturnPdfCreator(DEST);
        pdfCreator.generatePdf();
    }

    private static class C02E04CanvasReturnPdfCreator extends AbstractPdfCreator {
        public C02E04CanvasReturnPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));

            PdfPage page = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Rectangle rectangle = new Rectangle(36, 650, 100, 100);
            pdfCanvas.rectangle(rectangle);
            pdfCanvas.stroke();

            Canvas canvas1 = new Canvas(pdfCanvas, rectangle);
            PdfFont font = PdfFontFactory.createFont(TIMES_ROMAN);
            PdfFont bold = PdfFontFactory.createFont(TIMES_BOLD);
            Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
            Text author = new Text("Robert Louis Stevenson").setFont(font);
            Paragraph p = new Paragraph().add(title).add(" by ").add(author);
            canvas1.add(p);
            canvas1.close();

            PdfPage page2 = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas2 = new PdfCanvas(page2);
            Canvas canvas2 = new Canvas(pdfCanvas2, rectangle);
            canvas2.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
            canvas2.close();

            PdfPage page1 = pdfDocument.getFirstPage();
            PdfCanvas pdfCanvas1 = new PdfCanvas(
                    page1.newContentStreamBefore(), page1.getResources(), pdfDocument
            );
            rectangle = new Rectangle(100, 70, 100, 100);
            pdfCanvas1.saveState()
                    .setFillColor(CYAN)
                    .rectangle(rectangle)
                    .fill()
                    .restoreState();
            Canvas canvas = new Canvas(pdfCanvas1, rectangle);
            canvas.add(new Paragraph("Dr. Jekyll and Mr. Hyde"));
            canvas.close();

            //Close document
            pdfDocument.close();
        }
    }
}
