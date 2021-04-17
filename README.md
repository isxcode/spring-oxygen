<p align="center">
  <a href="https://github.com/isxcode/spring-oxygen">
    <img alt="spring-oxygen" width="500" src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen/oxygen.png">
  </a>
</p>

<h1 align="center">
    Spring Oxygen
</h1>

<h4 align="center">
    🦄 Spring rapid development integration framework.
</h4>

<div align="center">

[![Github Build](https://github.com/isxcode/spring-oxygen/workflows/build/badge.svg?branch=latest)](https://github.com/isxcode/spring-oxygen/actions?query=workflow%3Abuild)
[![Maven Version](https://img.shields.io/maven-central/v/com.isxcode.oxygen/oxygen-spring-boot-starter)](https://search.maven.org/artifact/com.isxcode.oxygen/oxygen-spring-boot-starter)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/isxcode/spring-oxygen.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/isxcode/spring-oxygen/context:java)
[![Coverage Status](https://coveralls.io/repos/github/isxcode/spring-oxygen/badge.svg?branch=latest)](https://coveralls.io/github/isxcode/spring-oxygen?branch=latest)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fisxcode%2Fspring-oxygen.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fisxcode%2Fspring-oxygen?ref=badge_shield)

</div>

<div align="center">

[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/isxcode/spring-oxygen/blob/main/CONTRIBUTING.md)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/isxcode/spring-oxygen)
[![Discussions on github](https://img.shields.io/badge/Discussions-on%20github-blueviolet)](https://github.com/isxcode/spring-oxygen/discussions)

</div>

<div align="center">

[![Github Watch](https://img.shields.io/github/watchers/isxcode/spring-oxygen?style=social)](https://github.com/isxcode/spring-oxygen/watchers)
[![Github Star](https://img.shields.io/github/stars/isxcode/spring-oxygen?style=social)](https://github.com/isxcode/spring-oxygen/stargazers)
[![Github Fork](https://img.shields.io/github/forks/isxcode/spring-oxygen?style=social)](https://github.com/isxcode/spring-oxygen/network/members)

</div>


## 🐣 Introduce

[Spring Oxygen](https://github.com/isxcode/spring-oxygen) is rapid development integration framework for [Spring](https://spring.io/) framework.
**Important statement, enterprise-level development is recommended to be used with caution!**
For instructions on use, please check the [Docs](https://spring-oxygen.isxcode.com) carefully.
Welcome to develop and maintain together, please follow the [github development](https://github.com/isxcode/spring-oxygen/blob/latest/CONTRIBUTING.md) specification.

## 📦 Installation

#### For Gradle

```groovy
dependencies {
    implementation 'com.isxcode.oxygen:oxygen-spring-boot-starter:0.0.1'
}
```

#### For Maven

```xml
<dependency>
  <groupId>com.isxcode.oxygen</groupId>
  <artifactId>oxygen-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```

## 🔨 Usage

```java
import com.isxcode.oxygen.flysql.pojo.enums.OrderType;
import org.springframework.stereotype.Repository;
import com.isxcode.oxygen.flysql.core.Flysql;

import java.util.List;

@Repository
public class DogsRepository {

    public List<DogsEntity> queryDogsEntity() {

        return Flysql.build().select(DogsEntity.class)
            .select("name", "age", "color")
            .eq("name", "alen")
            .between("age", 1, 2)
            .like("color", "red")
            .orderBy("index", OrderType.DESC)
            .query();
    }
}
```

## 📒 Documentation

Check out the [Getting Started](https://spring-oxygen.isxcode.com) page for a quick overview.

##  👏 Contributing

Read our [contributing guide](https://github.com/isxcode/spring-oxygen/blob/main/CONTRIBUTING.md) to learn about our development process, how to propose bugfixes and improvements, and how to build and test your changes to React.

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank" style="border-bottom: none !important">
    <img src="https://gitee.com/isxcode/blogs-galaxy-images/raw/master/jetbrains/jetbrains-3.png" height="100" alt="jetbrains"/>
</a>
