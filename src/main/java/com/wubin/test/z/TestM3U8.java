package com.wubin.test.z;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestM3U8 {

    public static void main(String[] args) throws Exception {
        download2();
    }

    public static void download1() throws Exception {
        String baseUrl = "https://d2gwumourdj5u6.cloudfront.net/20180830/PPPD-526/1000kb/hls/";
        String name = "PPPD-526";

        String indexUrl = baseUrl + "index.m3u8";
        String index = Jsoup.connect(indexUrl).ignoreContentType(true).execute().body();

        String keyUrl = baseUrl + "key.key";
        byte[] key = Jsoup.connect(keyUrl).ignoreContentType(true).execute().bodyAsBytes();

        Pattern pattern = Pattern.compile(".*\\.ts");
        Matcher matcher = pattern.matcher(index);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            list.add(matcher.group(0));
        }

        // 4105
        for (int i = 1; i <= list.size(); ++i) {
            System.out.println(i + ":" + list.get(i - 1));
            String tsUrl = baseUrl + list.get(i - 1);
            byte[] bytes = Jsoup.connect(tsUrl).ignoreContentType(true).execute().bodyAsBytes();
            System.out.println(bytes.length);

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(key));
            byte[] data = cipher.doFinal(bytes);
            FileUtils.writeByteArrayToFile(
                    new File("D:\\test\\m3u8\\Downloads\\java\\" + name + "\\" + i + ".ts"), data);
        }
    }

    public static void download2() throws IOException {
        String tsUrl = "https://d26m4frfp45pk7.cloudfront.net/20171120/MIDE-301-C/550kb/hls/nuK4FtTc7361001.ts";
        byte[] bytes = Jsoup.connect(tsUrl).ignoreContentType(true).execute().bodyAsBytes();
        FileUtils.writeByteArrayToFile(new File("D:\\test2\\1.ts"), bytes);
    }

}
