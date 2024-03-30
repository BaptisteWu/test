package com.wubin.test.client.http;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class TestJavaNet {

    private static final String BASE_URL = "http://localhost:8081/test";

    public static void select() throws IOException {
        URL url = new URL(BASE_URL + "/select?username=" + URLEncoder.encode("张三", "UTF-8"));
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Authorization", "Hello World!!!");

        // connection调用getInputStream()、getResponseCode()、connect()会发起请求
        try (InputStream is = connection.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
        }
    }

    public static void insert() throws IOException {
        URL url = new URL(BASE_URL + "/insert");
        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("POST");
        connection.setRequestProperty("Authorization", "Hello World!!!");
        connection.setDoOutput(true);

        try (OutputStream os = connection.getOutputStream()) {
            os.write("username=张三".getBytes());
            os.flush();
        }

        try (InputStream is = connection.getInputStream();
             InputStreamReader isr = new InputStreamReader(is);
             BufferedReader br = new BufferedReader(isr)) {
            String line;
            StringBuilder result = new StringBuilder();
            while ((line = br.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result.toString());
        }
    }

    public static void main(String[] args) throws IOException {
//        select();
        insert();
    }

}
