package com.b1a9idps.itextsandbox;

import java.io.IOException;

import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.Rectangle;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfPage;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.kernel.pdf.canvas.PdfCanvas;
import com.itextpdf.layout.Canvas;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Text;
import com.itextpdf.layout.properties.Property;
import com.itextpdf.layout.renderer.CanvasRenderer;
import com.itextpdf.layout.renderer.IRenderer;

import static com.itextpdf.io.font.constants.StandardFonts.TIMES_BOLD;
import static com.itextpdf.io.font.constants.StandardFonts.TIMES_ROMAN;

/**
 * 用意したRectangleが埋まるまでコンテンツを繰り返す
 */
public class C02E03CanvasRepeat {

    private static final String DEST = "/chapter02/canvas_repeat.pdf";

    public static void main(String[] args) throws Exception {
        var pdfCreator = new C02E03CanvasRepeatPdfCreator(DEST);
        pdfCreator.generatePdf();
    }

    private static class C02E03CanvasRepeatPdfCreator extends AbstractPdfCreator {
        public C02E03CanvasRepeatPdfCreator(String destPath) {
            super(destPath);
        }

        @Override
        protected void createPdf() throws IOException {
            PdfDocument pdfDocument = new PdfDocument(new PdfWriter(destPath()));

            PdfPage page = pdfDocument.addNewPage();
            PdfCanvas pdfCanvas = new PdfCanvas(page);
            Rectangle rectangle = new Rectangle(36, 500, 100, 250);
            pdfCanvas.rectangle(rectangle);
            pdfCanvas.stroke();

            // Canvasクラスは指定したPdfCanvas上にコンテンツを直接配置する。なお、Canvasクラスはページ構成は」知らない
            Canvas canvas = new Canvas(pdfCanvas, rectangle);
            MyCanvasRenderer renderer = new MyCanvasRenderer(canvas);
            canvas.setRenderer(renderer);
            PdfFont font = PdfFontFactory.createFont(TIMES_ROMAN);
            PdfFont bold = PdfFontFactory.createFont(TIMES_BOLD);
            Text title = new Text("The Strange Case of Dr. Jekyll and Mr. Hyde").setFont(bold);
            Text author = new Text("Robert Louis Stevenson").setFont(font);
            Paragraph p = new Paragraph().add(title).add(" by ").add(author);
            while (!renderer.isFull()) {
                canvas.add(p);
            }
            canvas.add(p);
            canvas.close();

            pdfDocument.close();
        }
    }

    private static class MyCanvasRenderer extends CanvasRenderer {
        protected boolean full = false;

        public MyCanvasRenderer(Canvas canvas) {
            super(canvas);
        }

        @Override
        public void addChild(IRenderer renderer) {
            super.addChild(renderer);
            full = Boolean.TRUE.equals(getPropertyAsBoolean(Property.FULL));
        }

        public boolean isFull() {
            return full;
        }
    }
}
