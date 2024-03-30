package com.wubin.test.client.http;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class TestApache {

    private static final String BASE_URL = "http://localhost:8081/test";

    public static String execute(HttpUriRequest httpRequest) throws IOException {
        try (CloseableHttpClient httpClient = HttpClients.createDefault();
             CloseableHttpResponse httpResponse = httpClient.execute(httpRequest)) {
            HttpEntity httpEntity = httpResponse.getEntity();
//            String result = EntityUtils.toString(httpEntity, Charset.forName("UTF-8"));
            String result = EntityUtils.toString(httpEntity);
            EntityUtils.consume(httpEntity);
            return result;
        }
    }

    public static void get() throws URISyntaxException, IOException {
        URIBuilder uriBuilder = new URIBuilder(BASE_URL + "/select")
                .addParameter("username", "张三")
                .addParameter("password", "")
                .addParameter("createTime", "");

        HttpGet httpGet = new HttpGet(uriBuilder.toString());
        httpGet.setHeader("Authorization", "Hello World!!!");

        String result = execute(httpGet);
        System.out.println(result);
    }

    public static void post() throws IOException {
        List<NameValuePair> params = new ArrayList<>();
        params.add(new BasicNameValuePair("username", "张三"));
        params.add(new BasicNameValuePair("password", ""));
        params.add(new BasicNameValuePair("createTime", ""));
        UrlEncodedFormEntity urlEncodedFormEntity = new UrlEncodedFormEntity(params, "UTF-8");

        HttpPost httpPost = new HttpPost(BASE_URL + "/insert");
        httpPost.setEntity(urlEncodedFormEntity);
        httpPost.setHeader("Authorization", "Hello World!!!");

        String result = execute(httpPost);
        System.out.println(result);
    }

    public static void body() throws IOException {
        String body = "{" +
                "\"username\":\"张三\"," +
                "\"password\":\"123\"," +
                "\"createTime\":\"2020-01-09 14:47:48\"" +
                "}";
        StringEntity stringEntity = new StringEntity(body, ContentType.APPLICATION_JSON);

        HttpPut httpPut = new HttpPut(BASE_URL + "/update");
        httpPut.setEntity(stringEntity);
        httpPut.setHeader("Authorization", "Hello World!!!");

        String result = execute(httpPut);
        System.out.println(result);
    }

    public static void main(String[] args) throws URISyntaxException, IOException {
//        get();
//        post();
//        body();
    }

}
