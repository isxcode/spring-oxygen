<p align="center">
  <a href="https://github.com/isxcode/spring-oxygen">
    <img alt="spring-oxygen" width="500" src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen/oxygen.png">
  </a>
</p>

<h1 align="center">
    Spring Oxygen
</h1>

<h4 align="center">
    Spring rapid development integration framework.
</h4>

<div align="center">

[![Github Build](https://github.com/isxcode/spring-oxygen/workflows/build/badge.svg)](https://github.com/isxcode/spring-oxygen/actions?query=workflow%3A%22build%22)
[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.oxygen/oxygen-spring-boot-starter)](https://search.maven.org/artifact/com.isxcode.oxygen/oxygen-spring-boot-starter)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/isxcode/spring-oxygen.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/isxcode/spring-oxygen/context:java)
[![Coverage Status](https://coveralls.io/repos/github/isxcode/spring-oxygen/badge.svg?branch=isxcode)](https://coveralls.io/github/isxcode/spring-oxygen?branch=isxcode)
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

## 🐣 Intro

[Spring Oxygen](https://github.com/isxcode/spring-oxygen) is rapid development integration framework for [Spring](https://spring.io/).
**Important statement, enterprise-level development is recommended to be used with caution!**
For instructions on use, please check the [Wiki](https://github.com/isxcode/spring-oxygen/wiki) carefully.
Welcome to develop and maintain together, please follow the [github development](https://github.com/isxcode/spring-oxygen/blob/main/CONTRIBUTING.md) specification.

## 📦 Installation

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

## 🔨 Usage

```java
package com.isxcode.leoday.config;

import org.springframework.context.annotation.Configuration;
import com.isxcode.oxygen.freecode.annotation.EnableFreecode;

@EnableFreecode
@Configuration
public class AppConfig {

}
```

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/jetbrains/jetbrains-3.png" height="100" alt="jetbrains"/></a>