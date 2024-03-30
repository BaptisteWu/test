package com.wubin.test.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.wubin.test.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestFastJson {

    public static void objToStr() {
        String str = JSON.toJSONString(
                User.builder().name("张三").birth(new Date()).build(),
                SerializerFeature.WriteDateUseDateFormat,
                SerializerFeature.WriteMapNullValue,
                SerializerFeature.PrettyFormat
        );
        System.out.println(str);
    }

    public static void strToObj() {
        String str = "{" +
                "\"birth\":\"2020-01-09 14:56:48\"," +
                "\"id\":1," +
                "\"name\":\"张三\"" +
                "}";
        User user = JSON.parseObject(str, User.class);
        System.out.println(user);
    }

    public static void listToStr() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", new Date()));
        list.add(new User(2, "李四", new Date()));
        String str = JSONObject.toJSONString(list, SerializerFeature.WriteDateUseDateFormat);
        System.out.println(str);
    }

    public static void strToList() {
        String str = "[" +
                "{" +
                "\"birth\":\"2020-01-09 15:00:42\"," +
                "\"id\":1," +
                "\"name\":\"张三\"" +
                "}," +
                "{" +
                "\"birth\":\"2020-01-09 15:00:42\"," +
                "\"id\":2," +
                "\"name\":\"李四\"" +
                "}" +
                "]";
        List<User> list = JSONObject.parseArray(str, User.class);
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
//        objToStr();
//        strToObj();

//        listToStr();
        strToList();
    }

}
