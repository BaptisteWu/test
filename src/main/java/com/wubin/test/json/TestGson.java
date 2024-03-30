package com.wubin.test.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.wubin.test.model.User;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestGson {

    public static void objToStr() {
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd HH:mm:ss")
                .serializeNulls()
                .setPrettyPrinting()
                .create();
        System.out.println(gson.toJson(User.builder().name("张三").birth(new Date()).build()));
    }

    public static void strToObj() {
        String str = "{" +
                "\"id\":1," +
                "\"name\":\"张三\"," +
                "\"birth\":\"2020-01-09 14:47:48\"" +
                "}";
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        User user = gson.fromJson(str, User.class);
        System.out.println(user);
    }

    public static void listToStr() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", new Date()));
        list.add(new User(2, "李四", new Date()));
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        System.out.println(gson.toJson(list));
    }

    public static void strToList() {
        String str = "[" +
                "{" +
                "\"id\":1," +
                "\"name\":\"张三\"," +
                "\"birth\":\"2020-01-09 14:48:41\"" +
                "}," +
                "{" +
                "\"id\":2," +
                "\"name\":\"李四\"," +
                "\"birth\":\"2020-01-09 14:48:41\"" +
                "}" +
                "]";
        Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
        Type type = new TypeToken<List<User>>() {
        }.getType();
        List<User> list = gson.fromJson(str, type);
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        objToStr();
//        strToObj();

//        listToStr();
        strToList();
    }

}
