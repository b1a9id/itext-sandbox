package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;

import static com.itextpdf.io.font.constants.StandardFonts.TIMES_BOLD;
import static com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN;

public class C01E01TextParagraph {

    private static final String DEST = "/chapter01/text_paragraph.pdf";

    public static void main(String[] args) throws Exception {
        var pdfCreator = new C01E01TextParagraphPdfCreator(DEST);
        pdfCreator.generatePdf();
    }

    private static class C01E01TextParagraphPdfCreator extends AbstractPdfCreator {
        public C01E01TextParagraphPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));

            Document document = new Document(pdfDocument);

            PdfFont font = PdfFontFactory.createFont(TIMES_ROMAN);
            PdfFont bold = PdfFontFactory.createFont(TIMES_BOLD);
            Text title = new Text("The Storage Case of Dr. Jekyll and Mr. Hyde")
                    .setFont(bold);
            Text author = new Text("Robert Louis Stevenson")
                    .setFont(font);
            Paragraph paragraph = new Paragraph()
                    .add(title)
                    .add(" by ")
                    .add(author);
            document.add(paragraph);

            document.close();
        }
    }
}
