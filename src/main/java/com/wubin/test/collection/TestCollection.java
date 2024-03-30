package com.wubin.test.collection;

import com.google.common.collect.Lists;
import com.wubin.test.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TestCollection {

    public static void main(String[] args) {
//        list.toArray();
//        Arrays.asList(arr);

        // Set根据hashCode和equals去重

        // 通过Map.keySet()遍历key和value
//        Set<Object> keySet = map.keySet();
//        for (Object key : keySet) {
//            Object value = map.get(key);
//        }

        // 通过Map.values()遍历所有的value，但不能遍历key
//        Collection<Object> collection = map.values();
//        for (Object object : collection) {
//        }

        // 通过Map.entrySet()遍历key和value
//        Set<Map.Entry<Object, Object>> entrySet = map.entrySet();
//        for (Map.Entry<Object, Object> entry : entrySet) {
//            Object key = entry.getKey();
//            Object value = entry.getValue();
//        }


        Date date = new Date();
        List<User> list1 = new ArrayList<User>() {{
            add(new User(1, "tom", date));
            add(new User(2, "mary", date));
            add(new User(3, "jack", date));
            add(new User(1, "eric", date));
            add(new User(2, "leo", date));
            add(new User(3, "baptiste", date));
        }};
        List<User> list2 = Lists.newArrayList(
                new User(1, "tom", date),
                new User(2, "mary", date),
                new User(3, "jack", date),
                new User(1, "eric", date),
                new User(2, "baptiste", date)
        );
        System.out.println(list1.subList(0, 6));

    }

}
