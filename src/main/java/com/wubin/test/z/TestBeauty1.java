package com.wubin.test.z;

import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBeauty1 {

    public static void main(String[] args) throws IOException {
        search2_1();
//        search2_2();
//        search();
//        get("/videoplay/?vid=34275");
    }

    //tai9tai99@gmail.com
    //https://t91951.xyz:9388
    //https://t91651.xyz:9388/
    private static final String BASE_URL = "https://t91403.xyz:9388";

    //https://t91536.xyz:9388/app/#/search/result?keyword=森泽佳奈
    public static void search2_1() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("keyword", "一乃");//白木
        paramMap.put("limit", 10);
        paramMap.put("page", 2);
        String result = HttpUtil.post(BASE_URL + "/v2/home/search", paramMap);

        JSONObject jsonObject = JSONUtil.parseObj(result);
        JSONArray jsonArray = jsonObject.getJSONArray("data");
        for (JSONObject data : jsonArray.jsonIter()) {
            System.out.println(data.getStr("title") + ":" + data.getInt("id"));
            System.out.println(data.getStr("sl")
                    .replace("http://23.226.188.186:2100", "https://al1.zabiul.com")
                    .replace("https://oss.tstdjoiajojkla.com", "https://p1.zacaig.com"));
            System.out.println();
//            https://al1.zabiul.com
//            https://al1.zacuin.com
//            https://t1.zacuin.com
//            https://p1.zacaig.com
        }
    }

    //https://t91536.xyz:9388/app/#/category?id=172&child=&name=枫富爱
    public static void search2_2() {
        //日韩4 女优专区135
        //相泽南164 河北彩花167 明里紬176 枫富爱172 伊藤舞雪168 岬奈奈美174 天海翼163
        String body = "{" +
                "\"category_child_id\":\"\"," +
                "\"category_id\":\"164\"," +
                "\"limit\":10," +
                "\"page\":1" +
                "}";
        String result = HttpRequest.put(BASE_URL + "/v2/category").body(body).execute().body();

        JSONObject jsonObject = JSONUtil.parseObj(result);
        JSONArray jsonArray = jsonObject.getJSONObject("data").getJSONArray("data");
        for (JSONObject data : jsonArray.jsonIter()) {
            System.out.println(data.getStr("title") + ":" + data.getInt("id"));
            System.out.println(data.getStr("sl")
                    .replace("http://23.226.188.186:2100", "https://al1.zabiul.com")
                    .replace("https://oss.tstdjoiajojkla.com", "https://p1.zacaig.com"));
            System.out.println();
        }
    }

    public static void search() throws IOException {
//        String url = BASE_URL + "/category/?category_id=176";
        String url = BASE_URL + "/index/search/?keyword=枫富爱";
//        String url = BASE_URL + "/index/search/?keyword=枫富爱&page=2&limit=30";
        Document document = Jsoup.connect(url).get();
        System.out.println(document.body().html());
        Elements elements = document.select("div.rank-title");
        for (Element element : elements) {
            System.out.println(element.html());
            System.out.println(BASE_URL + element.parent().attr("href"));
//            get(element.parent().attr("href"));
        }
    }

    public static void get(String str) throws IOException {
        String url = BASE_URL + str;
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.body().html());

        Pattern pattern = Pattern.compile("atob\\(\"(.*)\"\\)");
        Matcher matcher = pattern.matcher(document.body().html());
        if (matcher.find()) {
            String s = new String(Base64Utils.decodeFromString(matcher.group(1)));
            JSONObject jsonObject = JSONUtil.parseObj(s);
            System.out.println(jsonObject.getStr("sl"));
            System.out.println();
        }
    }

    public static void findAll() throws IOException {
        for (int i = 1; i <= 38; ++i) {
            System.out.println(i);
            String url = "https://cpc002.com/vodsearch/julia----------" + i + "---.html";
            Document document = Jsoup.connect(url).get();
//            System.out.println(document.body().html());
            Elements elements = document.select("div.pic > ul > li");
            for (Element element : elements) {
                if (element.html().contains("https://img.bttimg.com")) {
                    System.out.println(element.html());
                }
            }
        }
    }

    public static void find(String wd) throws IOException {
//        String url = "https://cpc002.com/vodsearch/-------------.html?wd=" + wd;
        String url = "https://cpc002.com/vodsearch/julia----------1---.html";
//        String url = "http://ww12.cpc002.com/vodsearch/julia----------1---.html";
        Document document = Jsoup.connect(url).get();
        Elements elements = document.select("div.pic > ul > li");
        for (Element element : elements) {
            System.out.println(element.html());
        }
    }

    public static void download() throws IOException {
//        String url = "https://cpc002.com/voddetail/QIbSCS.html";
        //J1DSCS
        String[] arr = new String[]{"J1DSCS", "NYYSCS", "CmDSCS", "xJDSCS"};

//        FileUtils.write(new File("D:\\test\\m3u8\\download.txt"), "\n", "UTF-8", true);
        for (String str : arr) {
            String url = "https://cpc002.com/vodplay/" + str + "-1-1.html";
            Document document = Jsoup.connect(url).get();
            String title = document.select("div.title > li").get(0).html();
            System.out.println(title);
//            FileUtils.write(new File("D:\\test\\m3u8\\download.txt"), title + "\n", "UTF-8", true);
            for (Element element : document.getElementsByTag("script")) {
                if (element.html().contains("player_aaaa")) {
                    JSONObject jsonObject = JSONUtil.parseObj(element.html().split("=")[1]);
                    String path = jsonObject.getStr("url");
                    System.out.println(path);
//                    FileUtils.write(new File("D:\\test\\m3u8\\download.txt"), path + "\n", "UTF-8", true);
                }
            }
        }
    }

}
