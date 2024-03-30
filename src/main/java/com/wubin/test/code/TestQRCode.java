package com.wubin.test.code;

import com.google.zxing.*;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class TestQRCode {

    public static void generate(String value) throws WriterException, IOException {
        int width = 400;
        int height = 400;

        Map<EncodeHintType, Object> map = new HashMap<>();
        map.put(EncodeHintType.CHARACTER_SET, "UTF-8");

        Writer writer = new MultiFormatWriter();
        BitMatrix bitMatrix = writer.encode(value, BarcodeFormat.QR_CODE, width, height, map);

        FileOutputStream out = new FileOutputStream("D:/test/qr.jpg");
        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", out);

//        ByteArrayOutputStream out = new ByteArrayOutputStream();
//        String base64 = Base64.encodeBase64String(out.toByteArray());
//        MatrixToImageWriter.writeToStream(bitMatrix, "jpg", out);
//        new ByteArrayInputStream(out.toByteArray());

//        addLogo(bitMatrix, width, height);
    }

    public static void addLogo(BitMatrix bitMatrix, int width, int height) throws IOException {
        BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        for (int i = 0; i < width; ++i) {
            for (int j = 0; j < height; ++j) {
                bufferedImage.setRGB(i, j, bitMatrix.get(i, j) ? 0xFF000000 : 0xFFFFFFFF);
            }
        }

        File logo = new File("D:/test/test.jpg");
        Graphics2D graphics2D = bufferedImage.createGraphics();
        BufferedImage logoImage = ImageIO.read(logo);
        int logoWidth = logoImage.getWidth() > width * 2 / 10 ? (width * 2 / 10) : logoImage.getWidth();
        int logoHeight = logoImage.getHeight() > height * 2 / 10 ? (height * 2 / 10) : logoImage.getHeight();
        int x = (width - logoWidth) / 2;
        int y = (height - logoHeight) / 2;
        graphics2D.drawImage(logoImage, x, y, logoWidth, logoHeight, null);
        graphics2D.drawRoundRect(x, y, logoWidth, logoHeight, 15, 15);
        graphics2D.setStroke(new BasicStroke(2));
        graphics2D.setColor(Color.WHITE);
        graphics2D.drawRect(x, y, logoWidth, logoHeight);
        graphics2D.dispose();
        logoImage.flush();
        bufferedImage.flush();

        FileOutputStream out = new FileOutputStream("D:/test/qr.jpg");
        ImageIO.write(bufferedImage, "jpg", out);
    }

    public static void analysis() throws IOException, NotFoundException, FormatException, ChecksumException {
        File file = new File("D:/test/qr.jpg");
        BufferedImage bufferedImage = ImageIO.read(file);
        LuminanceSource luminanceSource = new BufferedImageLuminanceSource(bufferedImage);
        Binarizer binarizer = new HybridBinarizer(luminanceSource);
        BinaryBitmap binaryBitmap = new BinaryBitmap(binarizer);

        Map<DecodeHintType, Object> map = new HashMap<>();
        map.put(DecodeHintType.CHARACTER_SET, "UTF-8");

        Reader reader = new MultiFormatReader();
        Result result = reader.decode(binaryBitmap, map);
        System.out.println(result.toString());
    }

    public static void main(String[] args) throws IOException, WriterException, NotFoundException, FormatException, ChecksumException {
        generate("你好！");
//        analysis();
    }

}
