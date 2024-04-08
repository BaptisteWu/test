package com.wubin.test;

import cn.hutool.core.util.StrUtil;
import org.apache.commons.lang3.StringUtils;

import java.util.UUID;

public class TestString {

    // String:适用于少量字符串操作的情况
    // StringBuilder:适用于单线程下在字符缓冲区进行大量操作的情况
    // StringBuffer:适用于多线程下在字符缓冲区进行大量操作的情况

    public static void equalTest() {
        String a = "abc";
        String a1 = "abc";
        System.out.println(a == a1); // true

        String b = new String("abc");
        String b1 = new String("abc");
        System.out.println(b == b1); // false

        System.out.println(a == b); // false
    }

    public static void main(String[] args) {
        // [0, 8)
//        String str = "苏械注准20232020101";
//        System.out.println(str.substring(0, 8));
//        System.out.println(str.substring(8));
//        System.out.println(StrUtil.sub(str, 0, 8));

//        System.out.println(StringUtils.defaultIfEmpty(null, ""));

//        String[] arr = new String[]{"a", "b", "c"};
//        System.out.println(StringUtils.join(arr, ","));

//        String str = " ";
//        System.out.println(StringUtils.isNotBlank(str));
//        System.out.println(StringUtils.isNotEmpty(str));
//        System.out.println(StringUtils.isBlank(str));
//        System.out.println(StringUtils.isEmpty(str));

//        System.out.println(StringUtils.left("abc", 2));
//        System.out.println(StringUtils.center("a", 3, "*"));
//        System.out.println(StringUtils.rightPad("a", 3, "*"));

    }

}
