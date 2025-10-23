package com.wubin.test.z;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

public class TestBeauty3 {

    //chrome://version
    //https://googlechromelabs.github.io/chrome-for-testing/
    //https://storage.googleapis.com/chrome-for-testing-public/133.0.6943.99/win64/chromedriver-win64.zip

    public static void test0() {
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.baidu.com/");
    }

    //https://kwa.kbuu78.cc/lf.html?dlx=1&lx=1
    //https://kwb.kwuu19.icu/lf.html?dlx=1&lx=2
    //https://hsa1.yeyepa4.sbs/lf.html?dlx=1&lx=2&gg=1
    private static final String BASE_URL = "https://tt.sfm1.icu";

    //www.ludouciba.icu
    //https://tt.sfm1.icu/index.php/vod/search.html?wd=ipx
    //https://tt.sfm1.icu/index.php/vod/search/page/1/wd/IPX-344.html
    //https://tt.sfm1.icu/vodhtml/315201.html
    //https://tt.sfm1.icu/play/315201_1_1.html
    //https://player.huangguam3u.com/20230630/9ReLBl5W/index.m3u8
    public static void main(String[] args) throws Exception {
        test0();

//        read();
//        test();
//        list();
//        info("/play/317022_1_1.html");

        //108,082
//        read2();
//        test2();
//        list2();
//        info2(Arrays.asList("192015"));
    }

    public static void read2() throws Exception {
        List<String> data = FileUtils.readLines(new File("D:\\test\\m3u8\\test3_3.txt"), "UTF-8");
        List<String> list = new ArrayList<>();
        for (String str : data) {
            String[] arr = str.split("@");
            if (arr[1].toUpperCase().contains("VEMA")) {//IPX-482 IPX-491
                System.out.println(str);
                list.add(arr[0]);
            }
        }
        System.out.println(list.size());
        if (list.size() > 0) {
            info2(list);
        }
    }

    private static final String API_URL = "https://x99.womeinaozi.icu";
//    private static final String API_URL = "https://houduan.ggm5.icu";

    public static void test2() throws Exception {
        String url = API_URL + "//api.php/mei/getShiPinHuangList?currentPage=1&tid=&ztid=&wd=VEMA-184";
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get(url);
        String data = driver.findElementByTagName("pre").getText();
        driver.quit();
        String result = decrypt(data.replace("\"", "").replace("\\", ""));
        JSONObject jsonObject = JSONUtil.parseObj(result);
        System.out.println(jsonObject.toStringPretty());
        JSONArray jsonArray = jsonObject.getJSONArray("list");
        for (JSONObject object : jsonArray.jsonIter()) {
            System.out.println(object.getInt("vod_id") + "@" + object.getStr("vod_blurb"));
        }
    }

    public static void list2() throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        for (int i = 1; i <= 7; ++i) {
            String url = API_URL + "//api.php/mei/getShiPinHuangList?currentPage=" + i + "&tid=&ztid=&wd=mtall";
            driver.get(url);
            String data = driver.findElementByTagName("pre").getText();
            String result = decrypt(data.replace("\"", "").replace("\\", ""));
            JSONObject jsonObject = JSONUtil.parseObj(result);
            JSONArray jsonArray = jsonObject.getJSONArray("list");
            for (JSONObject object : jsonArray.jsonIter()) {
                System.out.println(object.getInt("vod_id") + "@" + object.getStr("vod_blurb"));
            }
        }
        driver.quit();
    }

    public static void info2(List<String> list) throws Exception {
        System.setProperty("webdriver.chrome.driver", "D:\\tools\\chromedriver-win64\\chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        ChromeDriver driver = new ChromeDriver(options);
        for (String str : list) {
            String url = API_URL + "//api.php/mei/getDetail?id=" + str;
            driver.get(url);
            String data = driver.findElementByTagName("pre").getText();
            String result = decrypt(data.replace("\"", "").replace("\\", ""));
            JSONObject jsonObject = JSONUtil.parseObj(result);
            System.out.println(jsonObject.getJSONObject("detail").getStr("vod_name"));
            System.out.println(jsonObject.getJSONObject("detail").getStr("vod_play_url"));
        }
        driver.quit();
    }

    public static void read() throws IOException {
        List<String> list = FileUtils.readLines(new File("D:\\test\\m3u8\\test3_2_old.txt"), "UTF-8");
        for (String str : list) {
            String[] arr = str.split("@");
            if (arr[0].toUpperCase().contains("IPX")
                    || arr[0].toUpperCase().contains("相泽南")
                    || arr[0].toUpperCase().contains("相沢")) {
                System.out.println(arr[0] + ":" + arr[1]);
                info(arr[1].replace("vodhtml", "play")
                        .replace(".html", "_1_1.html"));
                System.out.println();
            }
        }
    }

    public static void test() throws IOException {
//        String url = BASE_URL + "/index.php/vod/search.html?wd=ipx";
        String url = BASE_URL + "/index.php/vod/search/page/1/wd/无码破解.html";
        Document document = Jsoup.connect(url).get();
        System.out.println(document.body().html());
        Elements elements = document.select("div.video-info > h5 > a");
        for (Element element : elements) {
            String title = element.html();
            String href = element.attr("href");
            System.out.println(title + "@" + href);
        }
    }

    public static void list() throws IOException {
        for (int i = 1; i <= 48; ++i) {
            String url = BASE_URL + "/index.php/vod/search/page/" + i + "/wd/无码破解.html";
            Document document = Jsoup.connect(url).get();
            Elements elements = document.select("div.video-info > h5 > a");
            for (Element element : elements) {
                String title = element.html();
                String href = element.attr("href");
                System.out.println(title + "@" + href);
//                FileUtils.write(new File("D:\\test\\m3u8\\test3_2.txt"), title + "@" + href + "\n", "UTF-8", true);
            }
        }
    }

    public static void info(String str) throws IOException {
        String url = BASE_URL + str;
        Document document = Jsoup.connect(url).get();
//        System.out.println(document.body().html());
        String title = document.select("div.container > div > b").get(0).html();
        System.out.println(title);
        for (Element element : document.getElementsByTag("script")) {
            if (element.html().contains("player_aaaa")) {
                JSONObject jsonObject = JSONUtil.parseObj(element.html().split("=")[1]);
                String path = jsonObject.getStr("url");
                System.out.println(path);
//                FileUtils.write(new File("D:\\test\\m3u8\\download.txt"), path + "\n", "UTF-8", true);
            }
        }
    }

    private static final SecretKeySpec KEY = new SecretKeySpec("gFzviOY0zOxVq1cu".getBytes(), "AES");
    private static final IvParameterSpec IV = new IvParameterSpec("ZmA0Osl677UdSrl0".getBytes());
    private static final String CIPHER_ALGORITHM = "AES/CBC/PKCS5Padding";

    public static String decrypt(String str) throws Exception {
        Cipher cipher = Cipher.getInstance(CIPHER_ALGORITHM);
        cipher.init(Cipher.DECRYPT_MODE, KEY, IV);
        byte[] data = cipher.doFinal(Base64.getDecoder().decode(str));
        return new String(data);
    }

}
