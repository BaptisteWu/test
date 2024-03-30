package com.wubin.test.z;

import cn.hutool.core.date.DateUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class TestBeauty {

    public static void test() {
    }

    public static void main(String[] args) throws Exception {
//        find("PRED");
//        getDownloadTime();
//        getVideoInfo();
//        getPublishDate();
//        getNotExist();

        File parent = new File("G:\\Program Files\\IPX_2");
//        File parent = new File("D:\\test\\m3u8\\Downloads\\IPX_2");
        for (String str : parent.list()) {
            System.out.println(str.replace(".mp4", ""));
        }
    }

    public static void find(String f) {
        File parent = new File("D:\\test\\m3u8\\Downloads\\京香julia");
//        File parent = new File("D:\\test\\m3u8\\Downloads\\test");
        for (File child : parent.listFiles()) {
            if (child.isDirectory()) {
                for (String str : child.list()) {
                    if (str.contains(f)) {
                        System.out.println(child.getPath() + "\\" + str);
                    }
                }
            }
        }
    }

    public static void getDownloadTime() {
        File parent = new File("D:\\test\\m3u8\\Downloads\\京香julia\\6");
        for (File child : parent.listFiles()) {
            System.out.println(child.getName() + ":"
                    + DateUtil.format(new Date(child.lastModified()), "yyyy-MM-dd HH:mm:ss"));
        }
    }

    public static void getVideoInfo() throws IOException, InterruptedException {
        File parent = new File("D:\\test\\m3u8\\Downloads\\京香julia\\4_3");
        for (File child : parent.listFiles()) {
            String exe = "E:\\tools\\ffmpeg-20190426-4b7166c-win64-static\\bin\\ffprobe.exe";
            String command1 = "-v";
            String command2 = "quiet";
            String command3 = "-show_format";
            String command4 = "-show_streams";
            String command5 = "-print_format";
            String command6 = "json";
            String[] cmd = new String[]{exe, command1, command2, command3, command4, command5, command6, child.getPath()};

            Process process = Runtime.getRuntime().exec(cmd);
            process.waitFor();
            String result = String.join("", IOUtils.readLines(process.getInputStream(), "GBK"));
            JSONObject jsonObject = JSONUtil.parseObj(result);
            JSONObject stream = jsonObject.getJSONArray("streams").getJSONObject(0);
            int width = stream.getInt("width");
            int height = stream.getInt("height");
            int bitRate1 = stream.getInt("bit_rate");
            int bitRate2 = jsonObject.getJSONObject("format").getInt("bit_rate");
            System.out.println(width + ":" + height + ":" + bitRate1 / 1000 + ":" + bitRate2 / 1000 + ":" + child.getName());
        }
    }

    public static void getPublishDate() throws IOException {
        Map<String, String> map = new HashMap<>();
        List<String> all = FileUtils.readLines(new File("D:\\test\\m3u8\\julia.txt"), "UTF-8");
        for (String str : all) {
            String[] arr = str.split(",");
            map.put(arr[0], arr[1]);
        }

        File parent = new File("D:\\test\\m3u8\\Downloads\\京香julia\\1");
        for (String child : parent.list()) {
            if (child.endsWith(".mp4") || child.endsWith(".ts")) {
                Pattern pattern = Pattern.compile("[A-Z]{3,5}-[0-9]{3}");
                Matcher matcher = pattern.matcher(child);
                if (matcher.find()) {
                    String str = matcher.group(0);
                    String date = map.get(str);
                    System.out.println(child + ":" + date);
                }
            }
        }
    }

    public static void getNotExist() throws IOException {
        List<String> part = new ArrayList<>();
        File parent = new File("D:\\test\\m3u8\\Downloads\\京香julia");
        for (File child : parent.listFiles()) {
            if (child.isDirectory()) {
                for (String title : child.list()) {
                    if (title.endsWith(".mp4") || title.endsWith(".ts")) {
                        Pattern pattern = Pattern.compile("[A-Z]{3,5}-[0-9]{3}");
                        Matcher matcher = pattern.matcher(title);
                        if (matcher.find()) {
                            part.add(matcher.group(0));
                        }
                    }
                }
            }
        }
        System.out.println(part.size());

        List<String> all = FileUtils.readLines(new File("D:\\test\\m3u8\\julia.txt"), "UTF-8");
        System.out.println(all.size());

        for (String no : all) {
            boolean isExist = false;
            for (String title : part) {
                if (title.equals(no)) {
                    isExist = true;
                }
            }
            if (!isExist) {
                System.out.println(no);
            }
        }
//        for (String title : part) {
//            boolean isExist = false;
//            for (String no : all) {
//                if (title.equals(no)) {
//                    isExist = true;
//                }
//            }
//            if (!isExist) {
//                System.out.println(title);
//            }
//        }
    }

}
