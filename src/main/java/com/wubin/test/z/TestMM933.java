package com.wubin.test.z;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestMM933 {

    private static final String BASE_URL = "https://mm933.com";
    private static final String KEY = "曼苏拉娜";
    private static String dir;

    public static void main(String[] args) throws IOException {
//        start();
//        detail("/xinggan/6776.html");
//        download("https://imgapi.saws.ac.cn/6770/2fbbe1bfeb407df7abafa4849d371983.jpg", "D:\\1.jpg");
        search(KEY, "search.html");
    }

    public static void search(String keyword, String page) throws IOException {
        String url = BASE_URL + "/" + page;
        Document document = Jsoup.connect(url).data("keyword", keyword).post();
//        System.out.println(document.body().html());

        Elements detail = document.select("div.main > dl > dd > a:not(.page-en)");
        for (Element element : detail) {
            Element img = element.select("img").first();
            System.out.println(element.attr("href") + ":" + img.attr("alt"));

            dir = "D:\\test\\beauty\\" + KEY + "\\" + img.attr("alt");
            if (!Files.exists(Paths.get(dir))) {
                detail(element.attr("href"));
            } else {
                System.out.println("已存在：" + dir);
            }
        }

        Elements pages = document.select("a.page-en");
        for (Element element : pages) {
            if (element.ownText().equals("下一页")) {
                String next = element.attr("href");
                Pattern pattern = Pattern.compile(",'(.*)'\\)");
                Matcher matcher = pattern.matcher(next);
                if (matcher.find()) {
                    search(keyword, matcher.group(1));
                }
            }
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

        Pattern pattern = Pattern.compile("\\((.*)\\)");
        Matcher matcher = pattern.matcher(imageTitle);
        if (matcher.find()) {
            download(imageUrl, dir + "\\" + matcher.group(1) + ".jpg");
        }

        String next = document.select("div.content-pic > a").attr("href");
        if (!next.equals("#")) {
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
