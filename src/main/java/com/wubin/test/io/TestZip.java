package com.wubin.test.io;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.GZIPInputStream;
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
        FileInputStream fileInputStream1 = new FileInputStream("D:\\test\\test.jpg");
        FileInputStream fileInputStream2 = new FileInputStream("D:\\test\\test.svg");
        ZipOutputStream zipOutputStream = new ZipOutputStream(fileOutputStream);

        ZipEntry zipEntry1 = new ZipEntry("test\\1.jpg");
        zipOutputStream.putNextEntry(zipEntry1);
        IOUtils.copy(fileInputStream1, zipOutputStream);
//        byte[] buffer1 = new byte[1024];
//        int len1;
//        while ((len1 = fileInputStream1.read(buffer1)) != -1) {
//            zipOutputStream.write(buffer1, 0, len1);
//        }

        ZipEntry zipEntry2 = new ZipEntry("2.svg");
        zipOutputStream.putNextEntry(zipEntry2);
        IOUtils.copy(fileInputStream2, zipOutputStream);
//        byte[] buffer2 = new byte[1024];
//        int len2;
//        while ((len2 = fileInputStream2.read(buffer2)) != -1) {
//            zipOutputStream.write(buffer2, 0, len2);
//        }

        zipOutputStream.closeEntry();
        zipOutputStream.close();
        fileOutputStream.close();
        fileInputStream1.close();
        fileInputStream2.close();
    }

}
