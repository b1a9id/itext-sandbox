package com.b1a9idps.itextsandbox.chap1;

import java.io.File;
import java.io.IOException;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.List;
import com.itextpdf.layout.element.ListItem;
import com.itextpdf.layout.element.Paragraph;

public class C01E02_RickAstley {
    public static final String DEST = "results/chapter01/rick_astley.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf();
    }

    private static void createPdf() throws IOException {
        try (PdfWriter writer = new PdfWriter(DEST)) {
            PdfDocument pdf = new PdfDocument(writer);

            Document document = new Document(pdf);

            PdfFont font = PdfFontFactory.createFont(StandardFonts.TIMES_ROMAN);
            document.add(new Paragraph("iText is:").setFont(font));

            List list = new List()
                    .setSymbolIndent(12)
                    .setListSymbol("•")
                    .setFont(font);
            list.add(new ListItem("Never gonna give you up"))
                    .add(new ListItem("Never gonna let you down"))
                    .add(new ListItem("Never gonna run around and desert you"))
                    .add(new ListItem("Never gonna make you cry"))
                    .add(new ListItem("Never gonna say goodbye"))
                    .add(new ListItem("Never gonna tell a lie and hurt you"));
            document.add(list);

            document.close();
        }
    }

}
