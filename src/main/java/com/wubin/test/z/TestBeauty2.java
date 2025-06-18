package com.wubin.test.z;

import cn.hutool.http.HttpRequest;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class TestBeauty2 {

    //https://1SSS.APP
    //https://1SSS.XYZ
    //https://1SSS.ORG
    //1ssstv18@gmail.com
    //https://www.gg5522.com
    private static final String BASE_URL = "https://www.tt9922.com";

    public static void main(String[] args) throws IOException {
        read();
//        list();
    }

    public static void read() throws IOException {
        List<String> list = FileUtils.readLines(new File("D:\\test\\m3u8\\test2.txt"), "UTF-8");
        for (String str : list) {
            String[] arr = str.split("@");
            if (arr[0].toUpperCase().contains("MEYD")) {
                System.out.println(arr[0] + ":" + arr[1]);
                System.out.println(arr[2]
//                        .replace("hweu3.sjzsq.com", "eu3.xm141.com")
                        .replace("1.jpg", "playlist.m3u8"));
                System.out.println();
            }
        }
    }

    //https://hweu3.sjzsq.com/video/m3u8/202404/16/f81f0a711828/1.jpg
    //https://eu3.xm141.com/video/m3u8/202404/16/f81f0a711828/playlist.m3u8
    public static void list() {
        String body = "{" +
                "\"RecordsPage\":1000," +
                "\"command\":\"WEB_GET_INFO\"," +
                "\"content\":\"\"," +
                "\"languageType\":\"CN\"," +
                "\"pageNumber\":1," +
                "\"typeId\":\"22\"," +
                "\"typeMid\":\"1\"" +
                "}";
        String result = HttpRequest.post("https://ccdata.7wzx9.com/forward").body(body).execute().body();
        JSONObject jsonObject = JSONUtil.parseObj(result);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("resultList");
        for (JSONObject data : jsonArray.jsonIter()) {
            System.out.println(data.getStr("vod_name") + "@" + data.getInt("id") + "@"
                    + data.getStr("vod_pic"));
        }
    }

    public static void info() {
        String body = "{" +
                "\"command\":\"WEB_GET_INFO_DETAIL\"," +
                "\"id\":\"16648\"," +
                "\"languageType\":\"CN\"," +
                "\"type_Mid\":\"1\"" +
                "}";
        String result = HttpRequest.post("https://ccdata.7wzx9.com/forward").body(body).execute().body();
        System.out.println(result);
    }

}
