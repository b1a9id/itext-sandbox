package com.b1a9idps.itextsandbox.chap1;

import java.io.IOException;

import com.b1a9idps.itextsandbox.AbstractPdfCreator;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class C01E01_HelloWorld {
    public static final String DEST = "/chapter01/hello_world.pdf";

    public static void main(String[] args) throws IOException {
        var pdfCreator = new HelloWorldPdfCreator(DEST);
        pdfCreator.createPdf();
    }

    private static class HelloWorldPdfCreator extends AbstractPdfCreator {

        public HelloWorldPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            try (PdfWriter writer = new PdfWriter(destPath())) {
                PdfDocument pdf = new PdfDocument(writer);

                Document document = new Document(pdf);
                document.add(new Paragraph("Hello World!"));

                document.close();
            }
        }
    }
}
