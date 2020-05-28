---
title: Java-List之Stream使用
subtitle: Java-List之Stream使用
tags:
  - java
categories: Java
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/java/java.png'
date: 2020-05-28 17:10:50
---
## 🙋 Question

- List排序
- List拦截
- List分组
- List分组求和

## 💡 Idea

#### List排序

1. Bean

```java
package com.ispong.oxygen.java.collections.list;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog {

    private String name;

    private Integer age;

    private Integer order;
}
```

#### 拦截器使用 

```java

package com.ispong.oxygen.java.collections.list;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class DemoList {

    public static void main(String[] args) {

        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("tom", 12, 0));
        dogList.add(new Dog("chandler", 13, 2));
        dogList.add(new Dog("lun", 14, 4));
        dogList.add(new Dog("gen", 15, 5));
        dogList.add(new Dog("two", 16, 6));

        List<Dog> dogResult = dogList.stream()
            .filter(v -> v.getAge() > 14)
            .filter(v -> v.getOrder() > 5)
            .collect(Collectors.toList());

        System.out.print(dogResult.toString());

    }
}
```

#### 排序使用

1. 排序使用

```java
package com.ispong.oxygen.java.collections.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class DemoList {

    public static void main(String[] args) {

        List<Dog> dogList = new ArrayList<>();
        dogList.add(new Dog("tom", 12, 0));
        dogList.add(new Dog("chandler", 13, 2));
        dogList.add(new Dog("lun", 14, 4));
        dogList.add(new Dog("gen", 15, 5));
        dogList.add(new Dog("two", 16, 6));

        // 降序
        List<Dog> dogResult = dogList.stream()
            .sorted(Comparator.comparing(Dog::getAge, Collections.reverseOrder()))
            .collect(Collectors.toList());

        // 升序
//        dogResult = dogList.stream()
//            .sorted(Comparator.comparing(Dog::getAge))
//            .collect(Collectors.toList());

        System.out.print(dogResult.toString());

    }
}
```

#### 分组
 
 ```java
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

        // 分组
        Map<Integer, List<Dog>> collect = dogList.stream().collect(Collectors.groupingBy(Dog::getOrder));

        System.out.print(collect.toString());
    }
}
```

#### 分组求和

```java
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

        Map<Integer, Object> collect = dogList.stream()
            .collect(Collectors.groupingBy(Dog::getOrder, Collectors.collectingAndThen(
                Collectors.toList(), list -> list.stream().mapToInt(Dog::getAge).sum()
            )));

        System.out.print(collect.toString());

    }
}
```

## 📝 Solution

🏳️‍🌈🏳️‍🌈🏳️‍🌈 xxxx

## 🔍 Reference

- [Reference1](http://localhost:8080)
- [Reference2](http://localhost:8080)