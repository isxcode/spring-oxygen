<p align="center">
  <a href="https://github.com/isxcode/spring-oxygen">
    <img alt="spring-oxygen" width="500" src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen/oxygen.png">
  </a>
</p>

<h1 align="center">
    Spring Oxygen
</h1>

<h4 align="center">
    基于Spring的快速开发框架
</h4>

<div align="center">

[![Github Build](https://github.com/isxcode/spring-oxygen/workflows/build/badge.svg?branch=latest)](https://github.com/isxcode/spring-oxygen/actions?query=workflow%3Abuild)
[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.oxygen/oxygen-spring-boot-starter)](https://search.maven.org/artifact/com.isxcode.oxygen/oxygen-spring-boot-starter)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/isxcode/spring-oxygen.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/isxcode/spring-oxygen/context:java)
[![Coverage Status](https://coveralls.io/repos/github/isxcode/spring-oxygen/badge.svg?branch=latest)](https://coveralls.io/github/isxcode/spring-oxygen?branch=latest)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fisxcode%2Fspring-oxygen.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fisxcode%2Fspring-oxygen?ref=badge_shield)

</div>

<div align="center">

[![Wiki](https://img.shields.io/badge/Wiki-docs-important)](https://github.com/isxcode/spring-oxygen/wiki)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/isxcode/spring-oxygen/blob/main/CONTRIBUTING.md)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/isxcode/spring-oxygen)

</div>

<div align="center">

[![Github Watch](https://img.shields.io/github/watchers/isxcode/spring-oxygen?style=social)](https://github.com/isxcode/spring-oxygen/watchers)
[![Github Star](https://img.shields.io/github/stars/isxcode/spring-oxygen?style=social)](https://github.com/isxcode/spring-oxygen/stargazers)
[![Github Fork](https://img.shields.io/github/forks/isxcode/spring-oxygen?style=social)](https://github.com/isxcode/spring-oxygen/network/members)

</div>

## 📔 目录

- 🐣 [简介](#简介)
- 🚨 [紧急公告](#紧急公告)
- 📦 [安装](#安装)
- 🔨 [使用说明](#使用说明)
- 👏 [开发维护](#开发维护)
- 🏅 [贡献者勋章](#贡献者勋章)

## 🐣 简介

[Spring Oxygen](https://github.com/isxcode/spring-oxygen) 是基于 [Spring](https://spring.io/) 二次集成的快速开发框架。
解放生产力，提高业务实现速度。
- `core` -- 提供常用加解密，随机数，excel读写，电子邮件，模板生成等一系列核心工具。
- `flysql` -- 告别Mybatis xml编写，轻量级sql的快速实现。
- `freecode` -- 自动化基础代码生成，加速开发。
- `tencentgo` -- 腾讯全家桶，集成小程序、企业微信、微信公众号等工具。
- `aligo` -- 阿里全家桶，集成短信发送、sso文件管理等工具。

## 🚨 紧急公告

此项目代码简洁，结构清晰，注释全面。
基于`Git`协同开发，项目周期稳定，管理科学。
非常适合初学者一起学习、讨论、成长。
有兴趣的小伙伴热烈欢迎加入一同开发。
维护者，将给予贡献者勋章。

## 📦 安装

- for Gradle

```groovy
dependencies {
    implementation 'com.isxcode.oxygen:oxygen-spring-boot-starter:0.0.1'
}
```

- for Maven

```xml
<dependency>
  <groupId>com.isxcode.oxygen</groupId>
  <artifactId>oxygen-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```

## 🔨 使用说明

```
    public List<Dog> queryDogs() {

        return Flysql.select(Dog.class)
            .select("name", "age", "color")
            .eq("name", "alen")
            .between("age", 12, 20)
            .like("color", "red")
            .orderBy("index", OrderType.DESC)
            .query();
    }
```

## 👏 开发维护

1. 前提

- git 2.22+
- java 1.8+
- gradle 6.7+

2. Fork 源代码库到自己的账号下 （例如账号: xiao）

3. 克隆代码到本地

```
git clone https://github.com/xiao/spring-oxygen.git
cd spring-oxygen
gradle build
```

> 分支说明：维护者需要按照模块进行开发

- 切出自己的模块分支

```
git checkout -b feature-core-xiao origin/feature-core
```

4. 开发测试

```
gradle publishToMavenLocal
OR
mvn install
```

5. 合并冲突

```
git remote add upstream https://github.com/isxcode/spring-oxygen.git
git fetch upstream
git checkout -b feature-core upstream/feature-core
git merge feature-core
```

6. 提交pull request

- https://github.com/isxcode/spring-oxygen/compare

```
xiao/spring-oxygen/feature-core-xiao  --> isxcode/spring-oxygen/feature-core
```

## 🏅 贡献者a

<a href="https://github.com/ispong" target="_blank"><img alt="ispong" border-radius="50%" height="40" src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen/team/avatar.png"></a>

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/jetbrains/jetbrains-3.png" height="100" alt="jetbrains"/></a>
