package com.wubin.test;

import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class TestPic {

    public static void main(String[] args) throws IOException {
//        Thumbnails.of("D:\\work\\quye\\test.jpg")
//                .size(640, 480)
//                .toFile("D:\\work\\quye\\test2.jpg");
//        Thumbnails.of("D:\\work\\quye\\test1.png")
//                .size(640,480)
//                .scale(0.2)
//                .outputQuality(0.5)
//                .toFiles(Rename.PREFIX_DOT_THUMBNAIL);

//        cut1();
//        cut2();
//        cut3();
//        cutAndScale1();
//        cutAndScale2();

        compress();
    }

    public static void compress() throws IOException {
//        BufferedImage image = ImageIO.read(new File("D:\\work\\quye\\test1.png"));
//        BufferedImage bufferedImage = new BufferedImage(640, 320, BufferedImage.TYPE_INT_RGB);
//        Graphics2D graphics2D = bufferedImage.createGraphics();
//        graphics2D.drawImage(image, 0, 0, null);
//        graphics2D.dispose();
//        ImageIO.write(bufferedImage, "png", new File("D:/test/test11.png"));
    }

    public static void cut1() throws IOException {
        String inFileName = "D:/test/test.jpg";
        FileInputStream fis = new FileInputStream(inFileName);
        ImageInputStream iis = ImageIO.createImageInputStream(fis);
        ImageReader imageReader = ImageIO.getImageReadersBySuffix("jpg").next();
        imageReader.setInput(iis, true);

        ImageReadParam param = imageReader.getDefaultReadParam();
        Rectangle rect = new Rectangle(240, 0, 240, 150);
        param.setSourceRegion(rect);
        BufferedImage bufferedImage = imageReader.read(0, param);
        ImageIO.write(bufferedImage, "jpg", new File("D:/test/test1.jpg"));
    }

    public static void cut2() throws IOException {
        String inFileName = "D:/test/test.jpg";
        FileInputStream fis = new FileInputStream(inFileName);
        BufferedImage image = ImageIO.read(fis);

        int width = 240;
        int height = 150;
        BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, width, height,
                240, 0, 480, 150, null);
        graphics2D.dispose();
        ImageIO.write(bufferedImage, "jpg", new File("D:/test/test2.jpg"));
    }

    public static void cut3() throws IOException {
        String inFileName = "D:/wubin/html/image/amour.jpg";
        FileInputStream fis = new FileInputStream(inFileName);
        BufferedImage image = ImageIO.read(fis);
        int imageWidth = image.getWidth();
        int imageHeight = image.getHeight();

        int width = imageWidth / 3;
        int height = imageHeight / 3;
        int count = 0;
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 3; ++j) {
                BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());
                Graphics2D graphics2D = bufferedImage.createGraphics();
                graphics2D.drawImage(image, 0, 0, width, height,
                        width * j, height * i, width * j + width, height * i + height,
                        null);
                graphics2D.dispose();
                ImageIO.write(bufferedImage, "jpg", new File("D:/wubin/html/image/amour" + count++ + ".jpg"));
            }
        }
    }

    public static void cutAndScale1() throws IOException {
        String inFileName = "D:/test/test.jpg";
        FileInputStream fis = new FileInputStream(inFileName);
        ImageInputStream iis = ImageIO.createImageInputStream(fis);
        ImageReader imageReader = ImageIO.getImageReadersBySuffix("jpg").next();
        imageReader.setInput(iis, true);

        ImageReadParam param = imageReader.getDefaultReadParam();
        Rectangle rect = new Rectangle(240, 0, 240, 150);
        param.setSourceRegion(rect);
        BufferedImage bufferedImage = imageReader.read(0, param);

        int width = 480;
        int height = 300;
        Image image = bufferedImage.getScaledInstance(width, height, Image.SCALE_DEFAULT);
        BufferedImage bufferedImage2 = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = bufferedImage2.createGraphics();
        graphics2D.drawImage(image, 0, 0, null);
        graphics2D.dispose();
        ImageIO.write(bufferedImage2, "jpg", new File("D:/test/test3.jpg"));
    }

    public static void cutAndScale2() throws IOException {
        String inFileName = "D:/test/test.jpg";
        FileInputStream fis = new FileInputStream(inFileName);
        BufferedImage image = ImageIO.read(fis);

        int width = 240;
        int height = 150;
        BufferedImage bufferedImage = new BufferedImage(width, height, image.getType());
        Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.drawImage(image, 0, 0, width, height,
                240, 0, 480, 150, null);
        graphics2D.dispose();

        int width2 = 480;
        int height2 = 300;
        Image image2 = bufferedImage.getScaledInstance(width2, height2, Image.SCALE_DEFAULT);
        BufferedImage bufferedImage2 = new BufferedImage(width2, height2, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D2 = bufferedImage2.createGraphics();
        graphics2D2.drawImage(image2, 0, 0, null);
        graphics2D2.dispose();
        ImageIO.write(bufferedImage2, "jpg", new File("D:/test/test3.jpg"));
    }

    public static void check() {
        String inFileName = "";
        List<String> types = Arrays.asList(ImageIO.getReaderFormatNames());
        String suffix = inFileName.substring(inFileName.lastIndexOf(".") + 1);
        if (!types.contains(suffix)) {
            System.out.println("无效的图片名称");
        }
    }

}
