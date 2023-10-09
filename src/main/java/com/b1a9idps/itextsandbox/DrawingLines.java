package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.colors.Color;
import com.itextpdf.kernel.colors.DeviceCmyk;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;

public class DrawingLines {
    public static void main(String[] args) throws Exception {
        var pdfCreator = new DrawingLinesPdfCreator("/sandbox/graphics/drawing_lines.pdf");
        pdfCreator.generatePdf();
    }

    private static class DrawingLinesPdfCreator extends AbstractPdfCreator {
        public DrawingLinesPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));
            PdfCanvas pdfCanvas = new PdfCanvas(pdfDocument.addNewPage());

            Color magentaColor = new DeviceCmyk(0.f, 1.f, 0.f, 0.f);
            pdfCanvas
                    .setStrokeColor(magentaColor)
                    // 起点をx=0, y=0にする（左下がx=0, y=0)
                    .moveTo(36, 36)
                    .lineTo(36, 806)
                    .lineTo(559, 36)
                    // PdfCanvas#strokeは、指定した点を結ぶ。PdfCanvas#closePathStrokeは、指定した点を結んで囲む。
                    .closePathStroke();

            pdfDocument.close();
        }
    }
}
