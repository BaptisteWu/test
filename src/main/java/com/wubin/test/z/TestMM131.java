package com.wubin.test.z;

import com.sun.deploy.net.URLEncoder;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;

public class TestMM131 {

    private static final String BASE_URL = "https://www.mmm131.com";
    private static final String KEY = "";
    private static String dir;

    public static void main(String[] args) throws IOException {
//        start();
//        detail("/xinggan/5775_58.html");
//        download("https://img1.hnllsy.com/pic/5775/60.jpg", "D:\\1.jpg");
        search("?key=" + URLEncoder.encode(KEY, "GBK") + "&page=1");
    }

    public static void search(String page) throws IOException {
        String url = BASE_URL + "/search/" + page;
        Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);
//        System.out.println(document.body().html());

        Elements detail = document.select("ul.e2 > li > a:not(.preview)");
        for (Element element : detail) {
            System.out.println(element.attr("href") + ":" + element.html());

            dir = "D:\\test\\beauty\\" + KEY + "\\" + element.html();
            if (!Files.exists(Paths.get(dir))) {
                detail(element.attr("href").replace(BASE_URL, ""));
            } else {
                System.out.println("已存在：" + dir);
            }
        }

        String next = document.select("a.next").attr("href");
        if (!next.equals("")) {
            search(next);
        }
    }

    public static void start() throws IOException {
        String url = BASE_URL + "/xinggan/";
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.body().html());

        Elements detail = document.select("div.main > dl > dd > a:not(.page-en)");
        for (Element element : detail) {
            Element img = element.select("img").first();
            System.out.println(element.attr("href") + ":" + img.attr("alt"));
        }

        Elements next = document.select("a.page-en:nth-last-child(2)");
        System.out.println(next.attr("href"));
    }

    public static void detail(String page) throws IOException {
        String url = BASE_URL + page;
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.body().html());

        String imageTitle = document.select("div.content > h5").text();
        String imageUrl = document.select("div.content-pic > a > img").attr("src");
        System.out.println(imageTitle + ":" + imageUrl);

        download(imageUrl, dir + "\\" + imageUrl.substring(imageUrl.lastIndexOf("/") + 1));

        String next = document.select("a.page-ch:nth-last-child(1)").attr("href");
        if (!next.equals("")) {
            detail("/xinggan/" + next);
        }
    }

    public static void download(String url, String fileName) throws IOException {
        byte[] bytes = Jsoup.connect(url)
                .header("referer", BASE_URL)
                .ignoreContentType(true)
                .execute()
                .bodyAsBytes();
        FileUtils.writeByteArrayToFile(new File(fileName), bytes);
    }

}
