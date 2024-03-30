package com.wubin.test.client.http;

import cn.hutool.http.HttpRequest;

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

    public static void main(String[] args) {
//        get();
//        post();
//        body();
    }

}
