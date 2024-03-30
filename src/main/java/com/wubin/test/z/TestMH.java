package com.wubin.test.z;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

public class TestMH {

    public static void test() {
    }

    public static void main(String[] args) throws Exception {
//        test();
        get();
    }

    public static void get() throws Exception {
        //getChapterContent
//        String dir = "D:/test/m3u8/mh//";
//        String str = decrypt("");
        String dir = "D:/test/m3u8/mh//";
        String str = decrypt("");

        JSONObject jsonObject = JSONUtil.parseObj(str);
        System.out.println(jsonObject.toStringPretty());

        String comicId = jsonObject.getStr("comicId");
        JSONArray chapterList = jsonObject.getJSONArray("chapterList");

        List<Map<String, Object>> list = new ArrayList<>();
        for (JSONObject object : chapterList.jsonIter()) {
            Map<String, Object> map = new HashMap<>();
            map.put("id", object.get("id"));
            map.put("title", object.get("title"));
            map.put("sort", object.get("sort"));
            list.add(map);
        }
        list.stream()
                .sorted(Comparator.comparing(map -> Integer.valueOf(map.get("sort").toString())))
                .collect(Collectors.toList())
                .forEach(item -> {
                    System.out.println(item);
                    try {
                        String chapterId = item.get("id").toString();
                        String sort = item.get("sort").toString();
                        String title = item.get("title").toString()
                                .replaceAll("\\?", "")
                                .replaceAll("\\.", "");
                        download(comicId, chapterId, dir + sort + "-" + title + "/");
//                        if (chapterId.equals("235486")) {
//                            download(comicId, chapterId, dir + sort + "-" + title + "/");
//                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                });
    }

    public static void download(String comicId, String chapterId, String title) throws IOException {
        //https://v2pics4.ruimouhzp.xyz/api/upload/img/comic/2147/256189/1.data
        String base = "http://v2pics4.ruimouhzp.xyz/api/upload/img/comic/" + comicId + "/" + chapterId + "/";
        for (int i = 1; i < 1000; ++i) {
//            try {
            String url = base + i + ".data";
            System.out.println(url);
            byte[] res = HttpUtil.downloadBytes(url);
            byte[] bytes = new byte[res.length - 3];
            System.arraycopy(res, 2, bytes, 0, res.length - 3);
            FileUtils.writeByteArrayToFile(new File(title + i + ".jpg"), bytes);
//            } catch (Exception e) {
//                e.printStackTrace();
//            }
        }
    }

    private static final SecretKeySpec KEY = new SecretKeySpec("56+0x4d8=8c56fd5".getBytes(), "AES");
    private static final String CIPHER_ALGORITHM = "AES/ECB/PKCS5Padding";

    public static String decrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, KEY);
        byte[] data = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(data);
    }

    public static String encrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.ENCRYPT_MODE, KEY);
        byte[] data = cipher.doFinal(str.getBytes());
        return Base64.getEncoder().encodeToString(data);
    }

}
