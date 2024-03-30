package com.wubin.test.collection;

import com.wubin.test.model.User;

import java.util.*;

public class TestMax {

    public static void main(String[] args) {
        Date date = new Date();
        List<User> list = new ArrayList<User>() {{
            add(new User(1, "tom", date));
            add(new User(2, "mary", date));
            add(new User(3, "jack", date));
            add(new User(4, "eric", date));
            add(new User(5, "baptiste", date));
        }};

//        User user = Collections.max(list, Comparator.comparingInt(User::getId));

        User user = list.stream().max(Comparator.comparingInt(User::getId)).get();

        System.out.println(user);
    }

}
