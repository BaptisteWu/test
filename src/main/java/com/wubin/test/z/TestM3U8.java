package com.wubin.test.z;

import cn.hutool.core.io.FileUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestM3U8 {

    public static void main(String[] args) throws Exception {
//        String result = HttpRequest.post("https://gaodengkijhsnbg.md14d3fdfw.cc/Web/VideoDetail")
//                .form("id", "33062")
//                .execute()
//                .body();
//        System.out.println(decrypt(result));
        //MGTV/20250407/bd42e6e6001bbab2fd476c0389ad8a45/index.m3u8

        String baseUrl = "https://sdfdssecef929.gxyhcm.com";
        String path = "MGTV/20250407/bd42e6e6001bbab2fd476c0389ad8a45/index.m3u8";
        String path2 = "MGTV/20250407/bd42e6e6001bbab2fd476c0389ad8a45";
        String secretKey = "wB760Vqpk76oRSVA1TNz";
        String fileName = "";
        long t = new Date().getTime() / 1000;
        String m3u8 = HttpRequest.get(baseUrl + "/" + path)
                .form("sign", SecureUtil.md5(secretKey + "/" + path + t))
                .form("t", t)
                .execute()
                .body();
        List<String> list = extractUrl(m3u8);
        byte[] key = HttpRequest.get(baseUrl + "/" + path2 + "/encrypt.key").execute().bodyBytes();
        for (String ts : list) {
            String newUrl = HttpRequest.get(baseUrl + "/" + path2 + "/" + ts)
                    .execute().header("Location");
            System.out.println(newUrl);
            byte[] bytes = HttpRequest.get(newUrl).execute().bodyBytes();
            FileUtil.writeBytes(decrypt(bytes, key, key), "");
        }

    }

    public static List<String> extractUrl(String m3u8) {
        Pattern pattern = Pattern.compile(".*\\.ts");
        Matcher matcher = pattern.matcher(m3u8);
        List<String> list = new ArrayList<>();
        while (matcher.find()) {
            String ts = matcher.group(0);
            list.add(ts);
            System.out.println(ts);
        }
        return list;
    }

    public static byte[] decrypt(byte[] bytes, byte[] key, byte[] iv) throws Exception {
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec secretKeySpec = new SecretKeySpec(key, "AES");
        if (iv != null) {
            IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec, ivParameterSpec);
        } else {
            cipher.init(Cipher.DECRYPT_MODE, secretKeySpec);
        }
        return cipher.doFinal(bytes);
    }

}
