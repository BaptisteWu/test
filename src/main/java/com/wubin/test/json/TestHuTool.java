package com.wubin.test.json;

import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONConfig;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.wubin.test.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestHuTool {

    public static void objToStr() {
        User user = User.builder().name("张三").birth(new Date()).build();
        System.out.println(JSONUtil.toJsonStr(user));
        System.out.println(JSONUtil.parse(user, JSONConfig.create().setDateFormat("yyyy-MM-dd HH:mm:ss"))
                .toJSONString(0));
    }

    public static void strToObj() {
        String str = "{" +
                "id:1," +
                "name:'张三'," +
                "birth:'2020-01-09 15:17:01'" +
                "}";
        System.out.println(JSONUtil.toBean(str, User.class));
    }

    public static void listToStr() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", new Date()));
        list.add(new User(2, "李四", new Date()));
        System.out.println(JSONUtil.toJsonStr(list));
        System.out.println(JSONUtil.parse(list, JSONConfig.create().setDateFormat("yyyy-MM-dd HH:mm:ss"))
                .toJSONString(0));
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
        List<User> list = JSONUtil.toList(str, User.class);
        list.forEach(System.out::println);
    }

    public static void strToJSONObject() {
        String str = "{" +
                "id:1," +
                "name:'张三'," +
                "birth:'2020-01-09 15:17:01'" +
                "}";
        JSONObject jsonObject = JSONUtil.parseObj(str);
        System.out.println(jsonObject.getDate("birth"));
    }

    public static void strToJSONArray() {
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
        JSONArray jsonArray = JSONUtil.parseArray(str);
        System.out.println(jsonArray.get(0));
        System.out.println(jsonArray.toString());
    }

    public static void main(String[] args) {
        objToStr();
//        strToObj();

//        listToStr();
//        strToList();

//        strToJSONObject();
//        strToJSONArray();
    }

}
