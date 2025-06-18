package com.wubin.test.client.http;

import cn.hutool.core.io.FileUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import org.springframework.util.Base64Utils;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TestHuTool {

    private static final String BASE_URL = "http://localhost:8081/test";

    public static void get() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "张三");
        paramMap.put("password", "");
        paramMap.put("createTime", "");

//        String result = HttpUtil.get(BASE_URL + "/select", paramMap);
        String result = HttpRequest.get(BASE_URL + "/select")
                .form(paramMap)
                .header("Authorization", "Hello World!!!")
                .execute()
                .body();
        System.out.println(result);
    }

    public static void post() {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("username", "张三");
        paramMap.put("password", "");
        paramMap.put("createTime", "");

//        String result = HttpUtil.post(BASE_URL + "/insert", paramMap);
        String result = HttpRequest.post(BASE_URL + "/insert")
                .form(paramMap)
                .header("Authorization", "Hello World!!!")
                .execute()
                .body();
        System.out.println(result);
    }

    public static void body() {
        String body = "{" +
                "\"username\":\"张三\"," +
                "\"password\":\"123\"," +
                "\"createTime\":\"2020-01-09 14:47:48\"" +
                "}";
        String result = HttpRequest.put(BASE_URL + "/update")
                .body(body)
                .header("Authorization", "Hello World!!!")
                .execute()
                .body();
        System.out.println(result);
    }

    public static void upload() {
        //http://medical-record.zpsoftware.cn/api/file/upload
        String result = HttpRequest.post("http://localhost:8075/file/upload")
                .form("file", new File("D:\\project\\medical-record\\file\\6074381_20250512162852.jpg"))
                .execute()
                .body();
        System.out.println(result);
    }

    public static void uploadBase() {
        //http://medical-record.zpsoftware.cn/api/file/upload/base64
        String result = HttpRequest.post("http://localhost:8075/file/upload/base64")
                .header("content-type", "multipart/form-data")
                .form("filename", "80712_20250529173454.jpg")
                .form("file", Base64Utils.encodeToString(FileUtil.readBytes(new File("D:\\work\\medical-record\\80712_20250529173454.jpg"))))
                .execute()
                .body();
        System.out.println(result);
    }

    public static void download() {
        HttpUtil.downloadFile(
                "http://medical-record.zpsoftware.cn/api/files/20250519/0e16ddace5d64dec9d253d3a0c408d35.jpg",
                "D:/test/000/000.jpg"
        );
    }

    public static void main(String[] args) {
//        get();
//        post();
//        body();
    }

}
