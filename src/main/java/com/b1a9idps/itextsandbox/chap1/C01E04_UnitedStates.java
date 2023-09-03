package com.b1a9idps.itextsandbox.chap1;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import com.itextpdf.io.font.constants.StandardFonts;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class C01E04_UnitedStates {
    public static final String DATA = "src/main/resources/data/united_states.csv";
    public static final String DEST = "results/chapter01/united_states.pdf";

    public static void main(String args[]) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf();
    }

    private static void createPdf() throws IOException {
        try (PdfWriter writer = new PdfWriter(DEST)) {
            PdfDocument pdf = new PdfDocument(writer);

            // A4サイズで縦横変更
            Document document = new Document(pdf, PageSize.A4.rotate());
            // マージンを調整
            document.setMargins(20, 20, 20, 20);

            // テーブルの行幅の割合を指定
            Table table = new Table(UnitValue.createPercentArray(new float[]{4, 1, 3, 4, 3, 3, 3, 3, 1}))
                    .useAllAvailableWidth();

            BufferedReader bufferedReader = new BufferedReader(new FileReader(DATA));
            String line = bufferedReader.readLine();
            PdfFont font = PdfFontFactory.createFont(StandardFonts.HELVETICA);
            PdfFont bold = PdfFontFactory.createFont(StandardFonts.HELVETICA_BOLD);
            process(table, line, bold, true);
            while ((line = bufferedReader.readLine()) != null) {
                process(table, line, font, false);
            }
            bufferedReader.close();

            document.add(table);
            document.close();
        }
    }

    private static void process(Table table, String line, PdfFont font, boolean isHeader) {
        StringTokenizer tokenizer = new StringTokenizer(line, ";");
        while (tokenizer.hasMoreTokens()) {
            var cell = new Cell();
            var paragraph = new Paragraph(tokenizer.nextToken());
            cell.add(paragraph.setFont(font));

            if (isHeader) {
                table.addHeaderCell(cell);
            } else {
                table.addCell(cell);
            }
        }
    }
}
