package com.wubin.test;

import cn.hutool.cache.CacheUtil;
import cn.hutool.cache.impl.TimedCache;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.date.*;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.IoUtil;
import cn.hutool.core.thread.ThreadUtil;
import cn.hutool.core.util.*;
import cn.hutool.extra.emoji.EmojiUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.github.houbb.heaven.util.lang.StringUtil;
import org.springframework.http.HttpEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Base64Utils;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.*;

public class TestHutool {

    public static void main(String[] args) throws IOException {
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

//        String body = "{" +
//                "  \"inputs\": {" +
//                "      \"query\": \"你哪里不舒服。我肚子痛\" " +
//                "  }," +
//                "  \"response_mode\": \"blocking\"," +
//                "  \"user\": \"abc-123\"" +
//                "}";
//        String result = HttpRequest.post("http://192.168.1.110/v1/workflows/run")
//                .body(body)
//                .header("Authorization", "Bearer app-XR0N4jBangtBaaI5czdiK1Nm")
//                .execute()
//                .body();
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        System.out.println(jsonObject.toStringPretty());
//        System.out.println(jsonObject.getJSONObject("data").getJSONObject("outputs").getStr("text"));

        String result = HttpRequest.post("http://122.224.39.188:8980/file_parse")
                .form("files", new File("D:\\work\\test\\pdf\\1599727.pdf"))
//                .form("backend", "pipeline")
                .form("backend", "vlm-http-client")
                .form("server_url", "http://192.168.33.50:8001")
                .execute()
                .body();
        System.out.println(result);

//        System.out.println(RandomUtil.randomNumbers(6));
//        System.out.println(RandomUtil.randomInt(-1, 2));

        //URLUtil +号不行
//        System.out.println(URLUtil.encode("https://apis.map.qq.com?a=3 3+"));
//        System.out.println(URLUtil.decode("https://apis.map.qq.com%3Fa=3%203"));
//        try {
//            System.out.println(URLEncoder.encode("https://apis.map.qq.com?a=3 3", "UTF-8"));
//            System.out.println(URLDecoder.decode("https%3A%2F%2Fapis.map.qq.com%3Fa%3D3+3", "UTF-8"));
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }

//        String phone = "183 6852 7925";
//        System.out.println(PhoneUtil.isMobile(phone));

//        String alias = EmojiUtil.toAlias("多多😄");
//        System.out.println(alias);
//        String emoji = EmojiUtil.toUnicode("多多:smile:");
//        System.out.println(emoji);
//        System.out.println(EmojiUtil.isEmoji("😄"));
//        System.out.println(EmojiUtil.isEmoji("😄多多"));
//        System.out.println(EmojiUtil.removeAllEmojis("😄多多"));

//        System.out.println(IdcardUtil.isValidCard("330621199307224239"));
//        System.out.println(IdcardUtil.getGenderByIdCard("330621199307224239"));
//        System.out.println(IdcardUtil.getBirth("330621199307224239"));
//        System.out.println(IdcardUtil.getBirthDate("330621199307224239"));
//        System.out.println(IdcardUtil.getAgeByIdCard("330621199307224239"));

        //4  4  20 16
        //16 10 20
        //64 24 24
//        System.out.println(DesensitizedUtil.chineseName("钱多多"));
//        System.out.println(DesensitizedUtil.mobilePhone("18717758202"));
//        System.out.println(DesensitizedUtil.idCardNum("330621199307224239", 6, 4));
//        System.out.println(DesensitizedUtil.address("浙江省绍兴市越城区中兴北路568号", 4));
    }

}
