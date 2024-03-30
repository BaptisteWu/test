package com.wubin.test.collection;

import com.wubin.test.model.User;

import java.util.*;
import java.util.stream.Collectors;

public class TestSort {

    public static void arraySort() {
        Date date = new Date();
        User[] arr = {
                new User(1, "tom", date),
                new User(2, "mary", date),
                new User(3, "jack", date),
                new User(4, "eric", date),
                new User(5, "baptiste", date)
        };

//        Arrays.sort(arr, new Comparator<User>() {
//            @Override
//            public int compare(User user1, User user2) {
//                return user2.getId() - user1.getId();
//            }
//        });
//        System.out.println(Arrays.toString(arr));

        Comparator<User> sortById = (User user1, User user2) -> (user2.getId() - user1.getId());
        Arrays.sort(arr, sortById);
        System.out.println(Arrays.toString(arr));
    }

    public static void listSort() {
        Date date = new Date();
        List<User> list = new ArrayList<User>() {{
            add(new User(1, "tom", date));
            add(new User(2, "mary", date));
            add(new User(3, "jack", date));
            add(new User(4, "eric", date));
            add(new User(5, "baptiste", date));
        }};

//        list.sort(new Comparator<User>() {
//            @Override
//            public int compare(User user1, User user2) {
//                return user2.getId() - user1.getId();
//            }
//        });
//        System.out.println(list);

//        Comparator<User> sortById = (User user1, User user2) -> (user2.getId() - user1.getId());
//        list.sort(sortById);
//        System.out.println(list);

        // 从大到小
        list.stream()
                .sorted(Comparator.comparing(User::getId).reversed())
                .collect(Collectors.toList())
                .forEach(System.out::println);
    }

    public static void main(String[] args) {
//        arraySort();
        listSort();
    }

}
