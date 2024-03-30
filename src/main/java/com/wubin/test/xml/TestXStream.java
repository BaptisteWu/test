package com.wubin.test.xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.basic.DateConverter;
import com.wubin.test.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class TestXStream {

    public static void objToXml() {
        XStream xStream = new XStream();
        // 启用注解
        xStream.processAnnotations(User.class);
        DateConverter dateConverter = new DateConverter(
                "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00",
                new String[]{"yyyy-MM-dd'T'HH:mm:ss+08:00", "yyyy-MM-dd'T'HH:mm:ss.S+08:00", "yyyy-MM-dd'T'HH:mm:ss.SS+08:00"},
                TimeZone.getTimeZone("GMT+8")
        );
        xStream.registerConverter(dateConverter);
        System.out.println(xStream.toXML(User.builder().name("张三").birth(new Date()).build()));
    }

    public static void xmlToObj() {
        String xml = "<sUser>\n" +
                "  <sId>1</sId>\n" +
                "  <sName>张三</sName>\n" +
                "  <sBirth>2019-09-03T19:33:27.967+08:00</sBirth>\n" +
                "</sUser>";
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{User.class});
        DateConverter dateConverter = new DateConverter(
                "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00",
                new String[]{"yyyy-MM-dd'T'HH:mm:ss+08:00", "yyyy-MM-dd'T'HH:mm:ss.S+08:00", "yyyy-MM-dd'T'HH:mm:ss.SS+08:00"},
                TimeZone.getTimeZone("GMT+8")
        );
        xStream.registerConverter(dateConverter);
        User user = (User) xStream.fromXML(xml);
        System.out.println(user);
    }

    public static void listToXml() {
        List<User> list = new ArrayList<>();
        list.add(new User(1, "张三", new Date()));
        list.add(new User(2, "李四", new Date()));
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        DateConverter dateConverter = new DateConverter(
                "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00",
                new String[]{"yyyy-MM-dd'T'HH:mm:ss+08:00", "yyyy-MM-dd'T'HH:mm:ss.S+08:00", "yyyy-MM-dd'T'HH:mm:ss.SS+08:00"},
                TimeZone.getTimeZone("GMT+8")
        );
        xStream.registerConverter(dateConverter);
        xStream.alias("wubin", List.class);
        System.out.println(xStream.toXML(list));
    }

    public static void xmlToList() {
        String xml = "<wubin>\n" +
                "  <sUser>\n" +
                "    <sId>1</sId>\n" +
                "    <sName>张三</sName>\n" +
                "    <sBirth>2019-09-03T19:31:32.162+08:00</sBirth>\n" +
                "  </sUser>\n" +
                "  <sUser>\n" +
                "    <sId>2</sId>\n" +
                "    <sName>李四</sName>\n" +
                "    <sBirth>2019-09-03T19:31:32.162+08:00</sBirth>\n" +
                "  </sUser>\n" +
                "</wubin>";
        XStream xStream = new XStream();
        xStream.processAnnotations(User.class);
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{User.class});
        DateConverter dateConverter = new DateConverter(
                "yyyy-MM-dd'T'HH:mm:ss.SSS+08:00",
                new String[]{"yyyy-MM-dd'T'HH:mm:ss+08:00", "yyyy-MM-dd'T'HH:mm:ss.S+08:00", "yyyy-MM-dd'T'HH:mm:ss.SS+08:00"},
                TimeZone.getTimeZone("GMT+8")
        );
        xStream.registerConverter(dateConverter);
        xStream.alias("wubin", List.class);
        List<User> list = (List<User>) xStream.fromXML(xml);
        list.forEach(System.out::println);
    }

    public static void main(String[] args) {
        objToXml();
//        xmlToObj();

//        listToXml();
//        xmlToList();
    }
}
