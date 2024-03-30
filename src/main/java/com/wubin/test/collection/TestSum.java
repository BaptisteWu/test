package com.wubin.test.collection;

import com.wubin.test.model.User;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class TestSum {

    public static void main(String[] args) {
        Date date = new Date();
        List<User> list = new ArrayList<User>() {{
            add(new User(1, "tom", date));
            add(new User(2, "mary", date));
            add(new User(3, "jack", date));
            add(new User(4, "eric", date));
            add(new User(5, "baptiste", date));
        }};

        System.out.println(list.stream().collect(Collectors.summingInt(User::getId)));
        System.out.println(list.stream().mapToInt(User::getId).sum());
    }

}
