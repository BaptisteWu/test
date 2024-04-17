package com.wubin.test;

import cn.hutool.core.io.FileUtil;
import com.benjaminwan.ocrlibrary.OcrResult;
import com.benjaminwan.ocrlibrary.Point;
import com.benjaminwan.ocrlibrary.TextBlock;
import io.github.mymonstercat.Model;
import io.github.mymonstercat.ocr.InferenceEngine;
import io.github.mymonstercat.ocr.config.ParamConfig;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

public class TestOcr {

    public static void main(String[] args) throws IOException {
        System.out.println(new Date());
        InferenceEngine engine = InferenceEngine.getInstance(Model.ONNX_PPOCR_V4);

//        ParamConfig paramConfig = ParamConfig.getDefaultConfig();
//        paramConfig.setDoAngle(true);
//        paramConfig.setMostAngle(true);
        System.out.println(new Date());
        OcrResult ocrResult = engine.runOcr("D:\\test\\888.jpg");
        System.out.println(new Date());
        List<TextBlock> list = ocrResult.getTextBlocks();
        for (TextBlock textBlock : list) {
            System.out.println(textBlock.toString());
        }
        System.out.println(ocrResult.getStrRes().trim());
//        outputPic(ocrResult, "D:\\test\\222.jpg", "D:\\test\\999.jpg");
    }

    public static void outputPic(OcrResult ocrResult, String path1, String path2) throws IOException {
        BufferedImage image = ImageIO.read(new File(path1));
        Graphics2D g2d = image.createGraphics();
        List<TextBlock> list = ocrResult.getTextBlocks();
        for (TextBlock textBlock : list) {
            List<Point> boxPoint = textBlock.getBoxPoint();
            String text = textBlock.getText();
            // 1. 算出矩形框
            Rectangle box = calcRectangle(boxPoint);
            int x = box.x;
            int y = box.y;
            int width = box.width;
            int height = box.height;
            // 2. 绘制矩形框
            g2d.setColor(Color.RED);
            g2d.drawRect(x, y, width, height);
            // 3. 在矩形框的左下角添加文字
            g2d.setColor(Color.BLACK);
            g2d.drawString(text, x, y + height + 15);
        }
        ImageIO.write(image, "jpg", new File(path2));
    }

    public static Rectangle calcRectangle(List<Point> list) {
        int minX = Integer.MAX_VALUE;
        int minY = Integer.MAX_VALUE;
        int maxX = Integer.MIN_VALUE;
        int maxY = Integer.MIN_VALUE;
        // 找到最小和最大的 x、y 坐标
        for (Point point : list) {
            int x = point.getX();
            int y = point.getY();
            if (x < minX) {
                minX = x;
            }
            if (y < minY) {
                minY = y;
            }
            if (x > maxX) {
                maxX = x;
            }
            if (y > maxY) {
                maxY = y;
            }
        }
        // 矩形的左上角坐标即是最小x、y
        int width = maxX - minX;
        int height = maxY - minY;
        return new Rectangle(minX, minY, width, height);
    }

}
