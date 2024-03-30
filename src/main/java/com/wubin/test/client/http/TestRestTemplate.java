package com.wubin.test.client.http;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.HashMap;
import java.util.Map;

public class TestRestTemplate {

    private static final String BASE_URL = "http://localhost:8081/test";

    public static void select1() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "张三");
        map.put("password", "");
        map.put("createTime", "");

        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<JSONObject> responseEntity = restTemplate.getForEntity(
                BASE_URL + "/select?username={username}&password={password}&createTime={createTime}",
                JSONObject.class, map);
        JSONObject jsonObject = responseEntity.getBody();
        System.out.println(jsonObject);

//        JSONObject jsonObject = restTemplate.getForObject(
//                BASE_URL + "/select?username={username}&password={password}&createTime={createTime}",
//                JSONObject.class, map);
//        System.out.println(jsonObject);
    }

    public static void select2() {
        UriComponents uri = UriComponentsBuilder.fromHttpUrl(BASE_URL + "/select")
                .queryParam("username", "张三")
                .queryParam("password", "")
                .queryParam("createTime", "")
                .build();

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Hello World!!!");
        HttpEntity<Object> httpEntity = new HttpEntity<>(httpHeaders);

        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(
                uri.toString(),
                HttpMethod.GET,
                httpEntity,
                JSONObject.class
        );
        JSONObject jsonObject = responseEntity.getBody();
        System.out.println(jsonObject);
    }

    public static void insert() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("username", "张三");
        map.add("password", "");
        map.add("createTime", "");

//        RestTemplate restTemplate = getRestTemplate();
//        JSONObject jsonObject = restTemplate.postForObject(BASE_URL + "/insert", map, JSONObject.class);
//        System.out.println(jsonObject);

        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Authorization", "Hello World!!!");
        HttpEntity<Map> httpEntity = new HttpEntity<>(map, httpHeaders);
        RestTemplate restTemplate = getRestTemplate();
        JSONObject jsonObject = restTemplate.postForObject(BASE_URL + "/insert", httpEntity, JSONObject.class);
        System.out.println(jsonObject);
    }

    public static void update() {
        Map<String, String> map = new HashMap<>();
        map.put("username", "张三");
        map.put("password", "");
        map.put("createTime", "");
        String body = JSON.toJSONString(map);

        HttpHeaders httpHeaders = new HttpHeaders();
        // 默认 application/x-www-form-urlencoded
//        httpHeaders.add("Content-Type", "application/json");
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Authorization", "Hello World!!!");
        HttpEntity<String> httpEntity = new HttpEntity<>(body, httpHeaders);

        RestTemplate restTemplate = getRestTemplate();
        ResponseEntity<JSONObject> responseEntity = restTemplate.exchange(
                BASE_URL + "/update",
                HttpMethod.PUT,
                httpEntity,
                JSONObject.class
        );
        JSONObject jsonObject = responseEntity.getBody();
        System.out.println(jsonObject);
    }

    public static RestTemplate getRestTemplate() {
        SimpleClientHttpRequestFactory factory = new SimpleClientHttpRequestFactory();
        factory.setConnectTimeout(1000);
        factory.setReadTimeout(1000);
        return new RestTemplate(factory);
    }

    public static void main(String[] args) {
//        select1();
//        select2();
//        insert();
        update();
    }

}
