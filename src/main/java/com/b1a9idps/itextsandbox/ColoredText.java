package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

public class ColoredText {
    public static void main(String[] args) throws Exception {
        var pdfCreator = new ColoredTextPdfCreator("/sandbox/objects/colored_text.pdf");
        pdfCreator.generatePdf();
    }

    private static class ColoredTextPdfCreator extends AbstractPdfCreator {
        public ColoredTextPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));
            Document document = new Document(pdfDocument);

            Text redText = new Text("This text is red. ")
                    .setFontColor(ColorConstants.RED)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA));
            Text blueText = new Text("This text is blue and bold. ")
                    .setFontColor(ColorConstants.BLUE)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD));
            Text greenText = new Text("This text is green and italic. ")
                    .setFontColor(ColorConstants.GREEN)
                    .setFont(PdfFontFactory.createFont(StandardFonts.HELVETICA_OBLIQUE));

            Paragraph paragraph1 = new Paragraph(redText).setMargin(0);
            Paragraph paragraph2 = new Paragraph().setMargin(0);
            paragraph2.add(blueText)
                    .add(greenText);

            document.add(paragraph1);
            document.add(paragraph2);

            // 長方形の中にテキストを出力。はみ出る場合は、改行される。
            new Canvas(new PdfCanvas(pdfDocument.getLastPage()), new Rectangle(36, 600, 108, 160))
                    .add(paragraph1)
                    .add(paragraph2);

            document.close();
        }
    }
}
