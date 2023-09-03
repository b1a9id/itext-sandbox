package com.b1a9idps.itextsandbox.chap1;

import java.io.File;
import java.io.IOException;

import com.itextpdf.io.image.ImageDataFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import com.itextpdf.layout.element.Paragraph;

public class C01E03_QuickBrownFox {
    public static final String DOG = "src/main/resources/img/dog.bmp";
    public static final String FOX = "src/main/resources/img/fox.bmp";

    public static final String DEST = "results/chapter01/quick_brown_fox.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf();
    }

    private static void createPdf() throws IOException {
        try (PdfWriter writer = new PdfWriter(DEST)) {
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);

            Image fox = new Image(ImageDataFactory.create(FOX));
            Image dog = new Image(ImageDataFactory.create(DOG));
            Paragraph paragraph = new Paragraph("The quick brown")
                    .add(fox)
                    .add(" jumps over the lazy")
                    .add(dog);
            document.add(paragraph);

            document.close();
        }
    }
}
