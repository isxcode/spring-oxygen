---
title: Gradle打包Spring项目管理
subtitle: Gradle打包Spring项目管理
tags:
  - gradle
categories: Gradle
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/gradle/gradle.png'
date: 2020-05-25 14:48:09
---

## 🙋 问题

1.Gradle使用MavenPlugin打包Spring项目 

## 💡 方法

#### Spring Developer Tool 打包问题

- maven源码

```xml
<build>
    <plugins>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <configuration>
                <excludeDevtools>false</excludeDevtools>
            </configuration>
        </plugin>
    </plugins>
</build>
```

1. 使用maven的插件打包将DevTools包打进项目中

```groovy
apply plugin: 'org.springframework.boot'

bootJar {
    excludeDevtools false
}
```

## 📝 结论

🎉🎉🎉 

## 🔍 参考

- [gradle plugin](https://docs.spring.io/spring-boot/docs/1.5.14.RELEASE/reference/html/build-tool-plugins-gradle-plugin.html)
