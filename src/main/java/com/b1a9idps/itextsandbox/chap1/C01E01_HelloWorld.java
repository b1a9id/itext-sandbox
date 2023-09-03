package com.b1a9idps.itextsandbox.chap1;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class C01E01_HelloWorld {
    public static final String DEST = "results/chapter01/hello_world.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf(DEST);
    }

    public static void createPdf(String dest) throws IOException {
        try (PdfWriter writer = new PdfWriter(dest)) {
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);
            document.add(new Paragraph("Hello World!"));

            document.close();
        }
    }
}
