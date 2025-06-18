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
import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
//        String body = "{\n" +
//                "  \"messages\": [\n" +
//                "    {\n" +
//                "      \"content\": \"中小学周一到周五的营养餐的菜谱\",\n" +
//                "      \"role\": \"user\"\n" +
//                "    }\n" +
//                "  ],\n" +
//                "  \"model\": \"deepseek-chat\",\n" +
//                "  \"stream\": false\n" +
//                "}";
//        String result = HttpRequest.post("https://api.deepseek.com/chat/completions")
//                .body(body)
//                .header("Authorization", "Bearer sk-c9c70505fdf041318c37a86179f4dae0")
//                .execute()
//                .body();
//        System.out.println(result);

//        String result = "{\"id\":\"19e21192-e146-4638-8453-c9cfd4b407d0\",\"object\":\"chat.completion\",\"created\":1744680917,\"model\":\"deepseek-chat\",\"choices\":[{\"index\":0,\"message\":{\"role\":\"assistant\",\"content\":\"以下是针对中小学生 **周一到周五营养餐** 的菜谱设计，兼顾 **营养均衡、食材多样、口味适宜**，并符合学生生长发育需求。每日包含 **主食、荤菜、素菜、汤/饮品**，供参考：\\n\\n---\\n\\n### **周一：能量开启日**\\n- **主食**：杂粮米饭（大米+小米/燕麦）  \\n- **荤菜**：红烧鸡腿（去皮，少油版）  \\n- **素菜**：清炒西兰花胡萝卜（橄榄油快炒）  \\n- **汤类**：番茄豆腐蛋花汤（补钙、维生素C）  \\n- **加餐**：酸奶1杯（或水果：苹果1个）  \\n\\n**营养重点**：优质蛋白（鸡肉）、膳食纤维（杂粮+蔬菜）、维生素A（胡萝卜）。\\n\\n---\\n\\n### **周二：补铁增强日**\\n- **主食**：紫薯馒头（或南瓜馒头）  \\n- **荤菜**：番茄炖牛肉（牛肉切小块易消化）  \\n- **素菜**：蒜蓉菠菜（补铁）+ 玉米粒  \\n- **汤类**：海带排骨汤（低盐，补碘）  \\n- **加餐**：香蕉1根（或坚果一小把）  \\n\\n**营养重点**：铁元素（牛肉、菠菜）、B族维生素（紫薯）、钾（香蕉）。\\n\\n---\\n\\n### **周三：清淡易消化日**\\n- **主食**：红豆米饭（大米+红豆）  \\n- **荤菜**：清蒸鲈鱼（低脂高蛋白）  \\n- **素菜**：木耳炒黄瓜（润肠通便）  \\n- **汤类**：冬瓜虾皮汤（低热量、补钙）  \\n- **加餐**：梨1个（润燥）  \\n\\n**营养重点**：Omega-3（鱼类）、植物蛋白（红豆）、膳食纤维（木耳）。\\n\\n---\\n\\n### **周四：活力全谷日**\\n- **主食**：糙米饭（或全麦面条）  \\n- **荤菜**：彩椒炒鸡胸肉（少油少盐）  \\n- **素菜**：蒸南瓜+ 清炒豌豆苗  \\n- **汤类**：紫菜虾仁汤（补碘、锌）  \\n- **加餐**：橙子1个（维生素C）  \\n\\n**营养重点**：全谷物（糙米）、维生素C（彩椒、橙子）、锌（虾仁）。\\n\\n---\\n\\n### **周五：多彩蔬菜日**\\n- **主食**：红薯米饭（大米+红薯丁）  \\n- **荤菜**：鸡蛋虾仁豆腐羹（高钙高蛋白）  \\n- **素菜**：什锦时蔬（荷兰豆、玉米笋、香菇）  \\n- **汤类**：萝卜丝鲫鱼汤（助消化）  \\n- **加餐**：葡萄一小串（抗氧化）  \\n\\n**营养重点**：维生素D（鸡蛋）、钙（豆腐）、多种矿物质（时蔬）。\\n\\n---\\n\\n### **注意事项**：\\n1. **少油少盐**：控制烹饪用油和盐分，避免重口味。  \\n2. **过敏替代**：如对海鲜/坚果过敏，可替换为猪肉、豆制品等。  \\n3. **季节性调整**：冬季可增加根茎类蔬菜（如土豆、山药），夏季增加瓜类（如丝瓜、苦瓜）。  \\n4. **份量控制**：根据学生年龄调整，小学生减少主食量，中学生可适当增加蛋白质。  \\n\\n希望这份菜谱能帮助学校或家长科学搭配营养餐！ \uD83C\uDF1F\"},\"logprobs\":null,\"finish_reason\":\"stop\"}],\"usage\":{\"prompt_tokens\":12,\"completion_tokens\":750,\"total_tokens\":762,\"prompt_tokens_details\":{\"cached_tokens\":0},\"prompt_cache_hit_tokens\":0,\"prompt_cache_miss_tokens\":12},\"system_fingerprint\":\"fp_3d5141a69a_prod0225\"}";
//        JSONObject jsonObject = JSONUtil.parseObj(result);
//        System.out.println(jsonObject.toStringPretty());
//        String content = jsonObject.getJSONArray("choices").getJSONObject(0)
//                .getJSONObject("message").getStr("content");
//        System.out.println(content);

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
