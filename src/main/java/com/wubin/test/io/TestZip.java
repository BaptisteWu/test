package com.wubin.test.io;

import cn.hutool.core.io.IoUtil;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

public class TestZip {

    public static void main(String[] args) throws IOException {
        write();

//        GZIPInputStream gzip = new GZIPInputStream(new FileInputStream("D:/xx.zip"));
    }

    public static void read() {
    }

    public static void write() throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\test.zip");
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        ZipEntry zipEntry1 = new ZipEntry("test\\1.jpg");
        zipOutputStream.putNextEntry(zipEntry1);
        FileInputStream fileInputStream1 = new FileInputStream("D:\\test\\test.jpg");
        byte[] buffer = new byte[1024];
        int len;
        while ((len = fileInputStream1.read(buffer)) != -1) {
            zipOutputStream.write(buffer, 0, len);
        }
        fileInputStream1.close();
        zipOutputStream.closeEntry();

        ZipEntry zipEntry2 = new ZipEntry("2.svg");
        zipOutputStream.putNextEntry(zipEntry2);
        FileInputStream fileInputStream2 = new FileInputStream("D:\\test\\test.svg");
        IoUtil.copy(fileInputStream2, zipOutputStream);
        fileInputStream2.close();
        zipOutputStream.closeEntry();

        zipOutputStream.close();
        fileOutputStream.close();
    }

}
