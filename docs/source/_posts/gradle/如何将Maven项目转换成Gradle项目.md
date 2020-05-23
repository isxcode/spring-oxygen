---
title: 如何将Maven项目转换成Gradle项目
subtitle: 如何将Maven项目转换成Gradle项目
tags:
  - gradle
categories: Gradle
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/gradle/gradle.png'
excerpt: 如何将Maven项目转换成Gradle项目
date: 2020-05-23 16:51:33
---

## 🙋 问题

1. 想将Maven项目转成Gradle

## 💡 方法

- 添加仓库地址

> 因为默认是http协议访问不安全,现在全是https访问
```xml
<repositories>
    <repository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2/</url>
    </repository>
</repositories>
```

注释标签 `<relativePath/>`

执行命令
```shell script
gradle init
```


## 📝 总结

🎈🎈 xxx  🎉🎉🎉

## 🔍 参考

- [gradle参考文档](https://docs.spring.io/dependency-management-plugin/docs/current-SNAPSHOT/reference/html/)