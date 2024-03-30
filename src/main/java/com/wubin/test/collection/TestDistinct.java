package com.wubin.test.collection;

import com.wubin.test.model.User;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TestDistinct {

    public static void main(String[] args) {
        Date date = new Date();
        List<User> list = new ArrayList<User>() {{
            add(new User(1, "tom", date));
            add(new User(2, "mary", date));
            add(new User(3, "jack", date));
            add(new User(1, "eric", date));
            add(new User(2, "leo", date));
            add(new User(3, "baptiste", date));
        }};

//        list.stream()
//                .filter(distinctByKey(User::getId))
//                .collect(Collectors.toList())
//                .forEach(System.out::println);

        list.stream()
                .collect(
                        Collectors.collectingAndThen(
                                Collectors.toCollection(
                                        () -> new TreeSet<>(Comparator.comparing(User::getId))
                                ),
                                ArrayList::new
                        )
                )
                .forEach(System.out::println);

//        Set<Object> set = new HashSet<>();
//        list.removeIf(user -> !set.add(user.getId()));
//        list.forEach(System.out::println);
    }

    public static <T> Predicate<T> distinctByKey(Function<? super T, Object> keyExtractor) {
        Map<Object, Boolean> map = new ConcurrentHashMap<>();
        return t -> map.putIfAbsent(keyExtractor.apply(t), Boolean.TRUE) == null;
    }

}
