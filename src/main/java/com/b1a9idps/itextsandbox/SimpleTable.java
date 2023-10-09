package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.properties.UnitValue;

public class SimpleTable {

    public static void main(String[] args) throws IOException {
        var pdfCreator = new SimpleTablePdfCreator("/sandbox/tables/simple_table.pdf");
        pdfCreator.generatePdf();
    }

    private static class SimpleTablePdfCreator extends AbstractPdfCreator {

        public SimpleTablePdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));
            Document document = new Document(pdfDocument);

            // 列数を8に指定
            Table table = new Table(UnitValue.createPercentArray(8))
                    .useAllAvailableWidth();

            for (int i = 0; i < 16; i++) {
                table.addCell("hi" + i);
            }

            document.add(table);

            document.close();
        }
    }
}
