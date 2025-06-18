package com.wubin.test;

import cn.hutool.core.img.ImgUtil;
import cn.hutool.core.io.FileUtil;
import com.jhlabs.image.ContrastFilter;
import net.coobird.thumbnailator.Thumbnails;
import net.coobird.thumbnailator.name.Rename;

import javax.imageio.ImageIO;
import javax.imageio.ImageReadParam;
import javax.imageio.ImageReader;
import javax.imageio.stream.ImageInputStream;
import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.awt.image.ConvolveOp;
import java.awt.image.Kernel;
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
//                .size(640, 480)
//                .scale(0.2)
//                .outputQuality(0.5)
//                .toFiles(Rename.PREFIX_DOT_THUMBNAIL);

//        cut99();
//        cutAndScale1();
//        cutAndScale2();

//        cut1();
//        cut2();
//        cut3();
//        rotate();
//        brightness();

//        testHutool();
//        testJhlabs();
    }

    public static void testHutool() {
        //旋转
//        ImgUtil.rotate(new File("D:\\test\\1111.jpg"), 90, new File("D:\\test\\8888.jpg"));
        //裁剪
//        ImgUtil.cut(new File("D:\\test\\1111.jpg"), new File("D:\\test\\2222.jpg"), new Rectangle(300, 0, 600, 200));
        //缩放
//        ImgUtil.scale(new File("D:\\test\\555.jpg"), new File("D:\\test\\666.jpg"), 0.1f);
        //压缩
//        ImgUtil.compress(new File("D:\\test\\1111.jpg"), new File("D:\\test\\2222.jpg"), 0.2f);
        //灰化（黑白）
//        ImgUtil.gray(new File("D:\\test\\1111.jpg"), new File("D:\\test\\9999.jpg"));
        //二进制化（黑白）
        ImgUtil.binary(new File("D:\\test\\1111.jpg"), new File("D:\\test\\9999.jpg"));
        //翻转镜像
//        ImgUtil.flip(new File("D:\\test\\1111.jpg"), new File("D:\\test\\9999.jpg"));
        //图片类型转换
//        ImgUtil.convert(new File("D:\\test\\1111.jpg"), new File("D:\\test\\9999.jpg"));
        //切片
//        ImgUtil.slice(new File("D:\\test\\1111.jpg"), new File("D:\\test\\9999"), 1000, 1000);
        //添加文字水印
//        ImgUtil.pressText();
        //添加图片水印
//        ImgUtil.pressImage();
    }

    public static void testJhlabs() throws IOException {
        ContrastFilter filter = new ContrastFilter();
        filter.setBrightness(1.75f);
//        filter.setContrast(1.75f);

        BufferedImage oldImage = ImageIO.read(new File("D:\\test\\1111.jpg"));
        BufferedImage newImage = new BufferedImage(oldImage.getWidth(), oldImage.getHeight(), oldImage.getType());
        filter.filter(oldImage, newImage);
        ImageIO.write(newImage, "jpg", new File("D:\\test\\6666.jpg"));
    }

    public static void cut1() throws IOException {
        BufferedImage oldImage = ImageIO.read(new File("D:\\test\\222.jpg"));
        BufferedImage newImage = oldImage.getSubimage(319, 488, 211, 48);
        ImageIO.write(newImage, "jpg", new File("D:\\test\\444.jpg"));
    }

    public static void cut2() throws IOException {
        BufferedImage oldImage = ImageIO.read(new File("D:\\test\\222.jpg"));
        int x = 319;
        int y = 488;
        int width = 211;
        int height = 48;
        BufferedImage newImage = new BufferedImage(width, height, oldImage.getType());
        Graphics2D graphics2D = newImage.createGraphics();
        graphics2D.drawImage(oldImage, 0, 0, width, height, x, y, x + width, y + height, null);
        graphics2D.dispose();
        ImageIO.write(newImage, "jpg", new File("D:\\test\\444.jpg"));
    }

    public static void cut3() throws IOException {
        FileInputStream fis = new FileInputStream("D:\\test\\222.jpg");
        ImageInputStream iis = ImageIO.createImageInputStream(fis);

        ImageReader imageReader = ImageIO.getImageReadersBySuffix("jpg").next();
        imageReader.setInput(iis, true);
        ImageReadParam param = imageReader.getDefaultReadParam();

        Rectangle rect = new Rectangle(319, 488, 211, 48);
        param.setSourceRegion(rect);
        BufferedImage bufferedImage = imageReader.read(0, param);
        ImageIO.write(bufferedImage, "jpg", new File("D:\\test\\444.jpg"));
    }

    public static void rotate() throws IOException {
        BufferedImage oldImage = ImageIO.read(new File("D:\\test\\111.jpg"));
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();

        double angle = 90;
        double sin = Math.abs(Math.sin(Math.toRadians(angle)));
        double cos = Math.abs(Math.cos(Math.toRadians(angle)));
        int newWidth = (int) Math.floor(height * sin + width * cos);
        int newHeight = (int) Math.floor(height * cos + width * sin);

//        AffineTransform.getRotateInstance();
        AffineTransform affineTransform = new AffineTransform();
        affineTransform.translate((newWidth - width) / 2.0, (newHeight - height) / 2.0);
        affineTransform.rotate(Math.toRadians(angle), width / 2.0, height / 2.0);
        AffineTransformOp affineTransformOp = new AffineTransformOp(affineTransform, AffineTransformOp.TYPE_BICUBIC);

        BufferedImage newImage = new BufferedImage(newWidth, newHeight, oldImage.getType());
        newImage = affineTransformOp.filter(oldImage, newImage);
        ImageIO.write(newImage, "jpg", new File("D:\\test\\444.jpg"));
    }

    public static void brightness() throws IOException {
        BufferedImage oldImage = ImageIO.read(new File("D:\\test\\1111.jpg"));
        int width = oldImage.getWidth();
        int height = oldImage.getHeight();
        BufferedImage newImage = new BufferedImage(width, height, oldImage.getType());
        double factor = 1.75d;
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int rgb = oldImage.getRGB(x, y);
                int red = (rgb >> 16) & 0xff;
                int green = (rgb >> 8) & 0xff;
                int blue = rgb & 0xff;
                red = Math.min(255, Math.max(0, (int) (red * factor)));
                green = Math.min(255, Math.max(0, (int) (green * factor)));
                blue = Math.min(255, Math.max(0, (int) (blue * factor)));
                newImage.setRGB(x, y, (red << 16) | (green << 8) | blue);
            }
        }
        ImageIO.write(newImage, "jpg", new File("D:\\test\\8888.jpg"));
    }

    public static void cut99() throws IOException {
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
