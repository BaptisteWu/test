package com.wubin.test.collection;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestJoinSplit {

    public static void join() {
        List<String> list = Lists.newArrayList("a", null, "b", "c");

        System.out.println(list.stream().collect(Collectors.joining(",", "[", "]")));

        System.out.println(Joiner.on(",").skipNulls().join(list));

        System.out.println(StringUtils.join(list, ","));
    }

    public static void splitToList() {
        String str = "a,,b, c";

        List<String> list1 = Arrays.asList(str.split(","));
        list1.forEach(System.out::println);

        List<String> list2 =
                Lists.newArrayList(Splitter.on(",").trimResults().omitEmptyStrings().split(str));
        list2.forEach(System.out::println);
    }

    public static void splitToMap() {
        String str = "a=123&b=456&c=789";
        Map<String, String> map = Splitter.on("&").withKeyValueSeparator("=").split(str);
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    public static void main(String[] args) {
        join();
//        splitToList();
//        splitToMap();
    }

}
