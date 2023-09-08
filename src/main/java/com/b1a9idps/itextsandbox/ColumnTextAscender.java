package com.b1a9idps.itextsandbox;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.ColumnDocumentRenderer;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;

public class ColumnTextAscender {

    public static final String DEST = "results/column_text_ascender.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf();
    }

    private static void createPdf() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));
        Document document = new Document(pdf);

        Rectangle[] areas = new Rectangle[] {
                new Rectangle(50, 750, 200, 50),
                new Rectangle(300, 750, 200, 50)
        };

        pdf.addNewPage();
        for (Rectangle rectangle : areas) {
            new PdfCanvas(pdf.getFirstPage())
                    .setLineWidth(0.5f)
                    .setStrokeColor(ColorConstants.RED)
                    .rectangle(rectangle)
                    .stroke();
        }

        document.setRenderer(new ColumnDocumentRenderer(document, areas));
        addColumn(document, false);
        addColumn(document, true);
        document.close();
    }

    private static void addColumn(Document document, boolean useAscender) {
        Paragraph paragraph = new Paragraph("This text is added at the top of the column.");
        if (useAscender) {
            paragraph.setMargin(0)
                    .setMultipliedLeading(1);
        }
        document.add(paragraph);
    }
}
