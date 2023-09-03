package com.b1a9idps.itextsandbox.chap2;

import java.io.File;
import java.io.IOException;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.kernel.pdf.canvas.PdfCanvasConstants;

public class C02E01_Axes {

    public static final String DEST = "results/chapter02/axes.pdf";

    public static void main(String[] args) throws IOException {
        File file = new File(DEST);
        file.getParentFile().mkdirs();
        createPdf();
    }

    private static void createPdf() throws IOException {
        PdfDocument pdf = new PdfDocument(new PdfWriter(DEST));

        PageSize pageSize = PageSize.A4.rotate();
        PdfPage page = pdf.addNewPage(pageSize);

        PdfCanvas canvas = new PdfCanvas(page);
        // 座標系の始まりをページの中心に置き換える
        canvas.concatMatrix(1, 0, 0, 1, pageSize.getWidth() / 2, pageSize.getHeight() / 2);

        drawAxes(canvas, pageSize);

        pdf.close();
    }

    public static void drawAxes(PdfCanvas canvas, PageSize pageSize) {
        // X軸を描画
        canvas.moveTo(-(pageSize.getWidth() / 2 -15), 0)
                .lineTo(pageSize.getWidth() / 2 - 15, 0)
                .stroke();

        // X軸矢印を描画
        canvas.setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
                .moveTo(pageSize.getWidth() / 2 - 25, -10)
                .lineTo(pageSize.getWidth() / 2 - 15, 0)
                .lineTo(pageSize.getWidth() / 2 - 25, 10)
                .stroke()
                .setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.MITER);

        // Y軸を描画
        canvas.moveTo(0, -(pageSize.getHeight() / 2 - 15))
                .lineTo(0, pageSize.getHeight() / 2 - 15)
                .stroke();

        // Y軸矢印を描画
        canvas.saveState()
                .setLineJoinStyle(PdfCanvasConstants.LineJoinStyle.ROUND)
                .moveTo(-10, pageSize.getHeight() / 2 - 25)
                .lineTo(0, pageSize.getHeight() / 2 - 15)
                .lineTo(10, pageSize.getHeight() / 2 - 25)
                .stroke()
                .restoreState();

        // X軸のメモリを描画
        for (int i = -((int) pageSize.getWidth() / 2 - 61); i < ((int) pageSize.getWidth() / 2 - 60); i += 40) {
            canvas.moveTo(i, 5)
                    .lineTo(i, -5);
        }
        // Y軸のメモリを描画
        for (int j = -((int) pageSize.getHeight() / 2 - 57); j < ((int) pageSize.getHeight() / 2 - 56); j += 40) {
            canvas.moveTo(5, j)
                    .lineTo(-5, j);
        }
        canvas.stroke();
    }
}
