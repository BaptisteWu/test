package com.wubin.test;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TestReg {

    public static boolean isNumber(String str) {
//        return str.matches("-?[0-9]+.?[0-9]*");
        return str.matches("-?[0-9]*\\.?[0-9]*");
    }

    public static boolean hasDate(String str) {
        return str.matches(".*\\d{8}_\\d{6}.*");
    }

    public static boolean password(String str) {
        return str.matches("^(?=.*[A-Z])(?=.*[a-z])(?=.*[0-9])(?=.*[?!@#$%^&*-.]).{8,}$");
    }

    public static void matches() {
        System.out.println(Pattern.compile(".*：(.*)（.*").matcher("二级指挥长：陈欢（管辖三级单元5个）").matches());
        System.out.println(Pattern.matches(".*：.*（.*", "二级指挥长：陈欢（管辖三级单元5个）"));
    }

    public static void substringBetween() {
        Pattern pattern = Pattern.compile("：(.*)（");
        Matcher matcher = pattern.matcher("二级指挥长：陈欢（管辖三级单元5个）");
        if (matcher.find()) {
            System.out.println(matcher.groupCount());
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
        }
    }

    public static void substringBetween2() {
        String script = "var a = ${262};" +
                "var b = ${269};" +
                "var result = a + b;";
        Pattern pattern = Pattern.compile("\\$\\{([^}]+)}");
        Matcher matcher = pattern.matcher(script);
        while (matcher.find()) {
            System.out.println(matcher.group(0));
            System.out.println(matcher.group(1));
            System.out.println("------");
        }
    }

    public static void main(String[] args) {
//        System.out.println(isNumber("1a"));
//        System.out.println(hasDate("abc_20200202_181818_cba"));

//        matches();
//        substringBetween();
//        substringBetween2();

//        System.out.println("a88888888c".replaceAll("(\\w)\\w*(\\w)", "$1*$2"));
//        System.out.println(Pattern.compile("(\\w)\\w*(\\w)").matcher("a88888888c").replaceAll("$1*$2"));

//        String str = "mz123!@#啊";
//        System.out.println(str.matches(".*[a-zA-Z].*"));
//        System.out.println(str.matches(".*[0-9].*"));
//        System.out.println(str.matches(".*[!@#$%^&*].*"));
//        System.out.println(str.matches(".*[\u4e00-\u9fa5].*"));

//        String password = "123ABCabc!@#111111";
//        System.out.println(password(password));

//        String input = "广东省深圳市南山区";
//        Pattern pattern = Pattern.compile("([\\u4e00-\\u9fa5]+?[省|自治区])([\\u4e00-\\u9fa5]+?[市|自治州|盟])([\\u4e00-\\u9fa5]+?[区|县])");
//        Matcher provinceMatcher = pattern.matcher(input);
//        if (provinceMatcher.find()) {
//            System.out.println(provinceMatcher.group(1));
//            System.out.println(provinceMatcher.group(2));
//            System.out.println(provinceMatcher.group(3));
//        }

    }

}
