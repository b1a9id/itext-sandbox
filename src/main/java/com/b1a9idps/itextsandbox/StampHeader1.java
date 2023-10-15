package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfReader;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

import static com.itextpdf.io.font.constants.StandardFonts.HELVETICA;
import static com.itextpdf.kernel.colors.ColorConstants.RED;
import static com.itextpdf.layout.properties.TextAlignment.LEFT;
import static com.itextpdf.layout.properties.VerticalAlignment.BOTTOM;

public class StampHeader1 {
    public static final String DEST = "/sandbox/stamper/stamp_header1.pdf";
    public static final String SRC = "./src/main/resources/pdf/Right.pdf";

    public static void main(String[] args) throws Exception {
        var pdfCreator = new StampHeader1PdfCreator(DEST);
        pdfCreator.generatePdf();
    }

    private static class StampHeader1PdfCreator extends AbstractPdfCreator {
        public StampHeader1PdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfReader(SRC), new PdfWriter(destPath()));
            Document document = new Document(pdfDocument);

            Paragraph header = new Paragraph("Copy")
                    .setFont(PdfFontFactory.createFont(HELVETICA))
                    .setFontSize(14)
                    .setFontColor(RED);

            for (int i = 1; i <= pdfDocument.getNumberOfPages(); i++) {
                // ページの座標を取得
                Rectangle pageSize = pdfDocument.getPage(i).getPageSize();
                // ページの半分の長さを取得
                float x = pageSize.getWidth() / 2;
                // ページトップの座標を取得
                float y = pageSize.getTop() - 20;
                document.showTextAligned(header, x, y, i, LEFT, BOTTOM, 0);
            }

            document.close();
        }
    }
}
