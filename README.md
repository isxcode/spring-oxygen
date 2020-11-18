<p align="center">
  <a href="https://github.com/ispong/spring-oxygen">
    <img alt="spring-oxygen" width="500" src="https://gitee.com/ispong/blog-images/raw/master/design/oxygen.png">
  </a>
</p>

<h1 align="center">
    Spring Oxygen
</h1>

<h4 align="center">
    Spring rapid development integration framework.
</h4>

<div align="center">

[![Github Build](https://github.com/ispong/spring-oxygen/workflows/build/badge.svg?branch=ispong)](https://github.com/ispong/spring-oxygen/actions?query=workflow%3A%22build%22)
[![Maven Version](https://img.shields.io/maven-central/v/com.github.ispong/spring-oxygen-boot-starter)](https://search.maven.org/artifact/com.github.ispong/spring-oxygen-boot-starter)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/ispong/spring-oxygen.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/ispong/spring-oxygen/context:java)
[![Coverage Status](https://coveralls.io/repos/github/ispong/spring-oxygen/badge.svg?branch=ispong)](https://coveralls.io/github/ispong/spring-oxygen?branch=ispong)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fispong%2Fspring-oxygen.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fispong%2Fspring-oxygen?ref=badge_shield)

</div>

<div align="center">

[![Wiki](https://img.shields.io/badge/Wiki-docs-important)](https://github.com/ispong/spring-oxygen/wiki)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/ispong/spring-oxygen/blob/main/CONTRIBUTING.md)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/ispong/spring-oxygen)

</div>

<div align="center">

[![Github Watch](https://img.shields.io/github/watchers/ispong/spring-oxygen?style=social)](https://github.com/ispong/spring-oxygen/watchers)
[![Github Star](https://img.shields.io/github/stars/ispong/spring-oxygen?style=social)](https://github.com/ispong/spring-oxygen/stargazers)
[![Github Fork](https://img.shields.io/github/forks/ispong/spring-oxygen?style=social)](https://github.com/ispong/spring-oxygen/network/members)

</div>

## 🐣 Intro

[Spring Oxygen](https://github.com/ispong/spring-oxygen) is rapid development integration framework for [Spring](https://spring.io/).
**Important statement, enterprise-level development is recommended to be used with caution!**
For instructions on use, please check the [Wiki](https://github.com/ispong/spring-oxygen/wiki) carefully.
Welcome to develop and maintain together, please follow the [github development](https://github.com/ispong/spring-oxygen/blob/main/CONTRIBUTING.md) specification.

## 📦 Installation

- for Gradle

```groovy
dependencies {
    implementation 'com.github.ispong:spring-oxygen-boot-starter:1.1.2'
}
```

- for Maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-boot-starter</artifactId>
  <version>1.1.2</version>
</dependency>
```

## 🔨 Usage

```java
package com.isxcode.leoday.config;

import org.springframework.context.annotation.Configuration;
import com.ispong.oxygen.freecode.annotation.EnableFreecode;
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;

@EnableWechatgo
@EnableFreecode
@Configuration
public class AppConfig {

}
```

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://gitee.com/ispong/blog-images/raw/master/idea/jetbrains-3.png" height="100" alt="jetbrains"/></a>