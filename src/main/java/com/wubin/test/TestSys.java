package com.wubin.test;

import java.net.URL;

public class TestSys {

    public static void main(String[] args) {
//        System.out.println(System.getProperty("user.dir"));
//        System.out.println(System.getProperty("user.home"));
//        System.out.println(System.getProperty("os.name"));

        URL url1 = TestSys.class.getClass().getResource("/");
        URL url2 = TestSys.class.getClass().getResource("/testDES.html");
        System.out.println(url1 + "------" + url1.getPath());
        System.out.println(url2 + "------" + url2.getPath());

//        URL url = this.getClass().getResource("/");
//        URL url = this.getClass().getResource("/testDES.html");

        URL url3 = TestSys.class.getClassLoader().getResource("");
        URL url4 = TestSys.class.getClassLoader().getResource("testDES.html");
        System.out.println(url3 + "------" + url3.getPath());
        System.out.println(url4 + "------" + url4.getPath());

        URL url5 = ClassLoader.getSystemResource("");
        URL url6 = ClassLoader.getSystemResource("testDES.html");
        System.out.println(url5 + "------" + url5.getPath());
        System.out.println(url6 + "------" + url6.getPath());

        URL url7 = ClassLoader.getSystemClassLoader().getResource("");
        URL url8 = ClassLoader.getSystemClassLoader().getResource("testDES.html");
        System.out.println(url7 + "------" + url7.getPath());
        System.out.println(url8 + "------" + url8.getPath());

        URL url9 = Thread.currentThread().getContextClassLoader().getResource("");
        URL url0 = Thread.currentThread().getContextClassLoader().getResource("testDES.html");
        System.out.println(url9 + "------" + url9.getPath());
        System.out.println(url0 + "------" + url0.getPath());
    }

}
