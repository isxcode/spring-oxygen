package com.ispong.oxygen.java.collections.list;

import java.util.*;
import java.util.stream.Collectors;

public class DemoList {

    public static void main(String[] args) {

        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("tom", 12, 1));
        dogList.add(new Dog("chandler", 12, 1));
        dogList.add(new Dog("lun", 14, 2));
        dogList.add(new Dog("gen", 15, 2));
        dogList.add(new Dog("two", 16, 6));

        // 降序
//        List<Dog> dogResult = dogList.stream()
//            .sorted(Comparator.comparing(Dog::getAge, Collections.reverseOrder()))
//            .collect(Collectors.toList());

        // 升序
//        dogResult = dogList.stream()
//            .sorted(Comparator.comparing(Dog::getAge))
//            .collect(Collectors.toList());

//         分组求和
//        Map<Integer, Object> collect = dogList.stream()
//            .collect(Collectors.groupingBy(Dog::getOrder, Collectors.collectingAndThen(
//                Collectors.toList(), list -> list.stream().mapToInt(Dog::getAge).sum()
//            )));

        // 分组
        Map<Integer, List<Dog>> collect = dogList.stream().collect(Collectors.groupingBy(Dog::getOrder));

        System.out.print(collect.toString());

    }
}
