package com.wubin.test.code;

import org.krysalis.barcode4j.impl.code128.Code128Bean;
import org.krysalis.barcode4j.impl.code39.Code39Bean;
import org.krysalis.barcode4j.output.bitmap.BitmapCanvasProvider;
import org.krysalis.barcode4j.tools.UnitConv;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class TestBarCode {

    public static void generate1() throws IOException {
        Code128Bean bean = new Code128Bean();

        final int dpi = 150;

        bean.setModuleWidth(UnitConv.in2mm(2.0f / dpi));

//        bean.setWideFactor(3);
        bean.doQuietZone(false);

        FileOutputStream out = new FileOutputStream("D:/test/组织标本.png");
        BitmapCanvasProvider canvas = new BitmapCanvasProvider(
                out, "image/x-png", dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        bean.generateBarcode(canvas, "86096");
        canvas.finish();
    }

    public static void generate2() throws IOException {
        Code39Bean bean = new Code39Bean();

        final int dpi = 150;

        bean.setModuleWidth(UnitConv.in2mm(1.0f / dpi));

        bean.setWideFactor(3);
        bean.doQuietZone(false);

        BitmapCanvasProvider canvas =
                new BitmapCanvasProvider(dpi, BufferedImage.TYPE_BYTE_BINARY, false, 0);

        // 不能有_
        bean.generateBarcode(canvas, "P13794730-0");
        canvas.finish();

        BufferedImage bufferedImage = canvas.getBufferedImage();
        ImageIO.write(bufferedImage, "png", new File("D:/test/Code39.png"));
    }

    public static void main(String[] args) throws IOException {
        generate1();
//        generate2();
    }

}
