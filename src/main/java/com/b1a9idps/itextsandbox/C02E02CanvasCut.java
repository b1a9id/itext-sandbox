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

public class C02E02CanvasCut {

    private static final String DEST = "/chapter02/canvas_cut.pdf";

    public static void main(String[] args) throws Exception {
        var pdfCreator = new C02E02CanvasCutPdfCreator(DEST);
        pdfCreator.generatePdf();
    }

    private static class C02E02CanvasCutPdfCreator extends AbstractPdfCreator {
        public C02E02CanvasCutPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));

            PdfPage page = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Rectangle rectangle = new Rectangle(36, 750, 100, 50);
            pdfCanvas.rectangle(rectangle);
            pdfCanvas.stroke();

            Canvas canvas = new Canvas(pdfCanvas, rectangle);
            PdfFont font = PdfFontFactory.createFont(TIMES_ROMAN);
            PdfFont bold = PdfFontFactory.createFont(TIMES_BOLD);
            Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
            Text author = new Text("Robert Louis Stevenson").setFont(font);
            Paragraph p = new Paragraph().add(title).add(" by ").add(author);
            canvas.add(p);
            canvas.close();
            //Close document
            pdfDocument.close();
        }
    }
}
