package com.wubin.test.z;

import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestBeauty99 {

    public static void test() throws IOException {
        String url = BASE_URL + "/dolan/index2.html";
//        Document document = Jsoup.connect(url).get();
        Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);
        System.out.println(document.body().html());
    }

    public static void get5() throws IOException {
        String[] fileNames = new String[]{
                "D:\\test\\m3u8\\beauty2.txt",
                "D:\\test\\m3u8\\beauty4.txt",
                "D:\\test\\m3u8\\beauty5.txt",
                "D:\\test\\m3u8\\beauty8.txt",
        };
        String[] targets = new String[]{
                "801", "499", "476", "390", "412", "469", "491", "509",

                "536", "566", "583", "598", "610", "691", "747", "759",

                "653", "686", "719",

                "156", "175", "183",
                "204", "226", "239", "244", "264", "272", "280", "297",
                "326", "356", "367", "378", "389",
                "414", "436", "451", "463", "468", "482", "490", "495", "498",
                "522", "531", "540", "551", "563", "579", "591",
                "613", "620", "624", "660", "664", "675", "680", "687", "696",
                "714", "726", "742", "746", "758", "776", "781", "817", "837"
        };
        for (String fileName : fileNames) {
            System.out.println(fileName);
            List<String> list = FileUtils.readLines(new File(fileName), "UTF-8");
            List<String> list2 = new ArrayList<>();
            for (String str : list) {
                String[] arr = str.split(":");
                if (arr.length > 4) {
                    String title = arr[4];
                    String path = arr[2] + ":" + arr[3];
                    if (title.toUpperCase().contains("WANZ-0")/* && !title.toUpperCase().contains("JULIA")*/) {
                        System.out.println(path + ":" + title + ":" + arr[1]);

//                        for (String target : targets) {
//                            if (title.contains(target)) {
//                                System.out.println(path + ":" + title);
//                            }
//                        }

//                        if (path.startsWith("http://baidu.kankandv.xyz")/* && !title.contains("MIDE-063")*/) {
//                            System.out.println(path + ":" + title);
//                            try {
//                                get4(path);
//                            } catch (IOException e) {
//                                list2.add(path + ":" + title);
//                            }
//                        } else {
//                            list2.add(path + ":" + title);
//                        }
                    }
                }
            }
            System.out.println("------------");
            list2.forEach(System.out::println);
        }
    }

    public static void main(String[] args) throws IOException {
//        test();
//        get();
//        get2("index2");
//        get3("/donei/index66930.html");
//        get4("http://baidu.kankandv.xyz/share/P7tB6n1EgWKu0fpA");
        get5();
    }

    private static final File FILE = new File("D:\\test\\m3u8\\beauty_new.txt");
    private static final String BASE_URL = "http://www.avzz16.top";
//    private static final String BASE_URL = "http://www.anzzcomsjdffdjgj.top:1238";
//    private static final String BASE_URL = "http://www.anzzcomsjl23gj.top:1238";

    //亚洲电影：index2.html、index2_2.html
    //制服丝袜：index4.html
    //强奸群交：index5.html
    //亚洲有码：index8.html
    public static void get() throws IOException {
        String index = "index2";
        //index2_370
        for (int i = 1; i <= 58; i++) {
            if (i == 1) {
                //第一页
                get2(index);
            } else {
                System.out.println(index + "_" + i);
                get2(index + "_" + i);
            }
        }
    }

    public static void get2(String index) throws IOException {
        String url = BASE_URL + "/dolan/" + index + ".html";
        Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);
//        System.out.println(document.body().html());

        Elements elements = document.select("ul.movieList > li > a");
        for (Element element : elements) {
            String href = element.attr("href");
            String h3 = element.select("h3").first().text();
            String time = element.select("span").first().text();
            String str = time + ":" + href + ":";
            System.out.println(str);
            FileUtils.write(FILE, str, "UTF-8", true);
            get3(href);
        }
    }

    public static void get3(String index) throws IOException {
        String url = BASE_URL + index;
        Document document = Jsoup.parse(new URL(url).openStream(), "GBK", url);
//        System.out.println(document.body().html());

        String title = document.select("li.title").first().text();
        String path = document.select("input#copy_yah").first().attr("value");
        String str = path + ":" + title + "\n";
        System.out.println(str);
        FileUtils.write(FILE, str, "UTF-8", true);
    }

    public static void get4(String url) throws IOException {
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.body().html());

        Elements elements = document.select("script");
        Pattern pattern = Pattern.compile("var main = \"(.*index.m3u8)\"");
        Matcher matcher = pattern.matcher(elements.html());
        if (matcher.find()) {
//            System.out.println(matcher.group(0));
            System.out.println("http://baidu.kankandv.xyz" + matcher.group(1));
            System.out.println();
        }
    }

}
