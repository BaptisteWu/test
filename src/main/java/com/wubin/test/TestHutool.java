package com.wubin.test;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.date.*;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.*;
import cn.hutool.extra.emoji.EmojiUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TestHutool {

    public static void main(String[] args) {
//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("ak", "4BYA5llSmcptT5BvCFkjUcdO6NWfXLr0");
//        paramMap.put("address", "北京如壹科技有限公司");
//        String result = HttpUtil.get("https://api.map.baidu.com/address_analyzer/v1", paramMap);
//        System.out.println(result);

//        Map<String, Object> paramMap = new HashMap<>();
//        paramMap.put("key", "JF6BZ-Q6SRQ-KHI5Y-GXVWB-W4VXT-WDBY5");
//        paramMap.put("address", "北京如壹科技有限公司");
//        String result = HttpUtil.get("https://apis.map.qq.com/ws/geocoder/v1/", paramMap);
//        System.out.println(result);

//        System.out.println(RandomUtil.randomNumbers(6));
//        System.out.println(RandomUtil.randomInt(-1, 2));

        System.out.println(URLUtil.encode("https://apis.map.qq.com?a=3 3"));
        System.out.println(URLUtil.decode("https://apis.map.qq.com%3Fa=3%203"));
        try {
            System.out.println(URLEncoder.encode("https://apis.map.qq.com?a=3 3", "UTF-8"));
            System.out.println(URLDecoder.decode("https%3A%2F%2Fapis.map.qq.com%3Fa%3D3+3", "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

//        String alias = EmojiUtil.toAlias("多多😄");
//        System.out.println(alias);
//        String emoji = EmojiUtil.toUnicode("多多:smile:");
//        System.out.println(emoji);
//        System.out.println(EmojiUtil.isEmoji("😄"));
//        System.out.println(EmojiUtil.isEmoji("😄多多"));
//        System.out.println(EmojiUtil.removeAllEmojis("😄多多"));

//        System.out.println(IdcardUtil.getGenderByIdCard("330621199307224239"));
//        System.out.println(IdcardUtil.getBirthByIdCard("330621199307224239"));
//        System.out.println(IdcardUtil.getBirth("330621199307224239"));
//        System.out.println(IdcardUtil.getBirthDate("330621199307224239"));

//        System.out.println(DesensitizedUtil.chineseName("钱多多"));
//        System.out.println(DesensitizedUtil.mobilePhone("18717758202"));
//        System.out.println(DesensitizedUtil.idCardNum("330621199307224239", 6, 4));
//        System.out.println(DesensitizedUtil.address("浙江省绍兴市越城区中兴北路568号", 4));
    }

}
