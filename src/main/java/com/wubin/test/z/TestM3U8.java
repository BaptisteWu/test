package com.wubin.test.z;

import cn.hutool.core.codec.Base64;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.CharsetUtil;
import cn.hutool.core.util.NumberUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestM3U8 {

    public static void main(String[] args) throws Exception {
        hx();
//        md();
    }

    //allow pasting
    //_0x18d8f1(163)
    ///poster.html?viewkey=&spm=
    public static void hx() throws Exception {
        String baseUrl1 = "https://2saccf315uii.su8gotifhx.cc";
        String viewKey = "f66418529289ab71b6851c07b7497999";
        String spm = "1792240371687.1789695710";
        String result = HttpRequest.get(baseUrl1 + "/app/movie/getDetail")
                .form("viewKey", viewKey)
                .execute()
                .body();
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        String key = "NHboMHZerxFQ401E";
        String iv = "i7JeCEIMVrj2W9xN";
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key.getBytes(), "AES"), new IvParameterSpec(iv.getBytes()));
        JSONObject jsonObject = JSONUtil.parseObj(new String(cipher.doFinal(Base64.decode(result))));
        System.out.println(jsonObject.toStringPretty());
        String playUrl = jsonObject.getJSONObject("data").getStr("playUrl");
        System.out.println(playUrl);

        String baseUrl2 = "https://mhxvmjsr01.dajfalb.com";
        String referer = baseUrl1 + "/poster.html?viewkey=" + viewKey + "&spm=" + spm;
        byte[] m3u8 = HttpRequest.get(baseUrl2 + playUrl)
                .header("referer", referer)
                .execute().bodyBytes();
        FileUtil.writeBytes(m3u8, "D:\\test\\m3u8\\Downloads\\index.m3u8");
        System.out.println("请求头：" + "referer:" + referer);
        System.out.println("BASEURL：" + baseUrl2 + playUrl);
    }

    public static void md() {
//        String result = HttpRequest.post("https://gaodengkijhsnbg.md14d3fdfw.cc/Web/VideoDetail")
//                .form("id", "33062")
//                .execute()
//                .body();
//        System.out.println(decrypt(result));
        //MGTV/20250407/bd42e6e6001bbab2fd476c0389ad8a45/index.m3u8

        //referer:https://gaodengkijhsnbg.md14d3fdfw.cc:2025/
        //https://zhouyu921.rj2345.com/MGTV/20250617/fb671c6661ef381b6163da6dbdffa055/index.m3u8?sign=8d527b7c34f2ae80582a5d856d2cef37&t=1761638624
        String baseUrl = "https://zhouyu921.rj2345.com";
        String path = "MGTV/20250407/39e3f550d4acdf7d535d4515cc68ea45";
        String referer = "https://gaodengkijhsnbg.md14d3fdfw.cc:2025/";
        String secretKey = "wB760Vqpk76oRSVA1TNz";
        long t = new Date().getTime() / 1000;
        byte[] m3u8 = HttpRequest.get(baseUrl + "/" + path + "/index.m3u8")
                .form("sign", SecureUtil.md5(secretKey + "/" + path + "/index.m3u8" + t))
                .form("t", t)
                .header("referer", referer)
                .execute().bodyBytes();
        FileUtil.writeBytes(m3u8, "D:\\test\\m3u8\\Downloads\\index.m3u8");
        System.out.println("请求头：" + "referer:" + referer);
        System.out.println("BASEURL：" + baseUrl + "/" + path + "/");

//        byte[] key = HttpRequest.get(baseUrl + "/" + path + "/encrypt.key")
//                .header("referer", referer)
//                .execute().bodyBytes();
//        FileUtil.writeBytes(key, "D:\\test\\m3u8\\Downloads\\encrypt.key");


//        String baseUrl = "https://sdfdssecef929.gxyhcm.com";
//        String path = "MGTV/20250407/bd42e6e6001bbab2fd476c0389ad8a45/index.m3u8";
//        String path2 = "MGTV/20250407/bd42e6e6001bbab2fd476c0389ad8a45";
//        String secretKey = "wB760Vqpk76oRSVA1TNz";
//        String fileName = "D:\\test\\m3u8\\Downloads\\test\\";
//        long t = new Date().getTime() / 1000;
//        String m3u8 = HttpRequest.get(baseUrl + "/" + path)
//                .form("sign", SecureUtil.md5(secretKey + "/" + path + t))
//                .form("t", t)
//                .execute()
//                .body();
//        List<String> list = extractUrl(m3u8);
//        byte[] key = HttpRequest.get(baseUrl + "/" + path2 + "/encrypt.key").execute().bodyBytes();
//        int i = 1;
//        for (String ts : list) {
//            String url = baseUrl + "/" + path2 + "/" + ts;
//            System.out.println(url);
//            String newUrl = HttpRequest.get(baseUrl + "/" + path2 + "/" + ts)
//                    .execute().header("Location");
//            System.out.println(newUrl);
//            byte[] bytes = HttpRequest.get(newUrl).execute().bodyBytes();
//            FileUtil.writeBytes(decrypt(bytes, key, key), fileName + "index" + NumberUtil.decimalFormat("000000", i++) + ".ts");
//        }
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
        cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(key, "AES"), new IvParameterSpec(iv));
        return cipher.doFinal(bytes);
    }

}
