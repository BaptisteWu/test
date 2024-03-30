package com.wubin.test.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.type.CollectionLikeType;
import com.wubin.test.model.User;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestJackson {

    public static void objToStr() throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        System.out.println(objectMapper.writerWithDefaultPrettyPrinter()
                .writeValueAsString(User.builder().name("张三").birth(new Date()).build()));
    }

    public static void strToObj() throws IOException {
        String str = "{" +
                "id:1," +
                "age:1," +
                "name:'张三'," +
                "birth:'2020-01-09 15:17:01'" +
                "}";
        ObjectMapper objectMapper = new ObjectMapper();
        // 设置识别单引号
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 设置字段可以不用双引号包括
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // 忽略实体类没有的字段
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        User user = objectMapper.readValue(str, User.class);
        System.out.println(user);
    }

    public static void listToStr() throws JsonProcessingException {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", new Date()));
        list.add(new User(2, "李四", new Date()));
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        System.out.println(objectMapper.writeValueAsString(list));
    }

    public static void strToList() throws IOException {
        String str = "[" +
                "{" +
                "id:1," +
                "name:'张三'," +
                "birth:'2020-01-09 14:48:41'" +
                "}," +
                "{" +
                "id:2," +
                "name:'李四'," +
                "birth:'2020-01-09 14:48:41'" +
                "}" +
                "]";
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

//        CollectionLikeType type = objectMapper.getTypeFactory().constructCollectionLikeType(List.class, User.class);
//        List<User> list = objectMapper.readValue(str, type);
        List<User> list = objectMapper.readValue(str, new TypeReference<List<User>>() {
        });

        list.forEach(System.out::println);
    }

    public static void test() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object o, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                if (o instanceof String) {
                    jsonGenerator.writeString("");
                } else if (o instanceof List) {
                    jsonGenerator.writeStartArray();
                    jsonGenerator.writeEndArray();
                } else {
                    jsonGenerator.writeStartObject();
                    jsonGenerator.writeEndObject();
                }
                jsonGenerator.writeNull();
            }
        });
    }

    public static void main(String[] args) throws IOException {
//        objToStr();
//        strToObj();

//        listToStr();
//        strToList();
    }

}
