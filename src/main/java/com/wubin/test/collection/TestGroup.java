package com.wubin.test.collection;

import com.google.common.collect.LinkedListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimap;
import com.wubin.test.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestGroup {

    public static void self(List<User> list) {
        Map<Integer, List<User>> map = Maps.newHashMap();
        for (User user : list) {
//            List<User> tmp = map.get(user.getId());
//            if (tmp == null) {
//                tmp = Lists.newLinkedList();
//                map.put(user.getId(), tmp);
//            }
            List<User> tmp = map.computeIfAbsent(user.getId(), k -> Lists.newLinkedList());
            tmp.add(user);
        }
        map.forEach((k, v) -> System.out.println(k + ":" + v));
    }

    public static void guava(List<User> list) {
        Multimap<Integer, User> multimap = LinkedListMultimap.create();
        for (User user : list) {
            multimap.put(user.getId(), user);
        }
        multimap.asMap().forEach((k, v) -> System.out.println(k + ":" + v));
        multimap.get(2).forEach(System.out::println);
    }

    public static void lambda(List<User> list) {
        Map<Integer, List<User>> map = list.stream().collect(Collectors.groupingBy(User::getId));
        map.forEach((k, v) -> System.out.println(k + ":" + v));

//        Map<Integer, StockCode> map = list.stream().collect(
//                Collectors.groupingBy(
//                        StockCode::getGoodsId,
//                        Collectors.collectingAndThen(
//                                Collectors.toList(),
//                                result -> {
//                                    BigDecimal quantity = BigDecimal.ZERO;
//                                    for (StockCode stockCode : result) {
//                                        quantity = quantity.add(stockCode.getQuantity());
//                                    }
//                                    return StockCode.builder().goodsId(result.get(0).getGoodsId())
//                                            .quantity(quantity).build();
//                                }
//                        )
//                )
//        );
    }

    public static void main(String[] args) {
        Date date = new Date();
        List<User> list = new ArrayList<User>() {{
            add(new User(1, "tom", date));
            add(new User(2, "mary", date));
            add(new User(3, "jack", date));
            add(new User(1, "eric", date));
            add(new User(2, "baptiste", date));
        }};

        self(list);
//        guava(list);
//        lambda(list);
    }

}
