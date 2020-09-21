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

[![Github Build](https://github.com/ispong/spring-oxygen/workflows/build/badge.svg?branch=latest)](https://github.com/ispong/spring-oxygen/actions?query=workflow%3A%22build%22)
[![Maven Version](https://img.shields.io/maven-central/v/com.github.ispong/spring-oxygen-boot-starter)](https://search.maven.org/artifact/com.github.ispong/spring-oxygen-boot-starter)
[![Language grade: Java](https://img.shields.io/lgtm/grade/java/g/ispong/spring-oxygen.svg?logo=lgtm&logoWidth=18)](https://lgtm.com/projects/g/ispong/spring-oxygen/context:java)
[![Coverage Status](https://coveralls.io/repos/github/ispong/spring-oxygen/badge.svg?branch=latest)](https://coveralls.io/github/ispong/spring-oxygen?branch=latest)
[![FOSSA Status](https://app.fossa.com/api/projects/git%2Bgithub.com%2Fispong%2Fspring-oxygen.svg?type=shield)](https://app.fossa.com/projects/git%2Bgithub.com%2Fispong%2Fspring-oxygen?ref=badge_shield)

</div>

<div align="center">

[![Wiki](https://img.shields.io/badge/Wiki-docs-ff69b4)](https://github.com/ispong/spring-oxygen/wiki)
[![PRs Welcome](https://img.shields.io/badge/PRs-welcome-brightgreen.svg)](https://github.com/ispong/spring-oxygen/blob/main/CONTRIBUTING.md)
[![Gitpod ready-to-code](https://img.shields.io/badge/Gitpod-ready--to--code-blue?logo=gitpod)](https://gitpod.io/#https://github.com/ispong/spring-oxygen)

</div>

<div align="center">

[![Github Watch](https://img.shields.io/github/watchers/ispong/spring-oxygen?style=social)](https://github.com/ispong/spring-oxygen/watchers)
[![Github Star](https://img.shields.io/github/stars/ispong/spring-oxygen?style=social)](https://github.com/ispong/spring-oxygen/stargazers)
[![Github Fork](https://img.shields.io/github/forks/ispong/spring-oxygen?style=social)](https://github.com/ispong/spring-oxygen/network/members)

</div>

## 🐣 Intro

[Spring Oxygen](https://github.com/ispong/spring-oxygen) is integration framework for [Spring](https://spring.io/).
It is important to state that this project is personally developed and maintained, and enterprise projects are recommended to be used with caution.
Welcome to develop together, hope to become an enterprise-level integration framework.

## 📦 Installation

- for Gradle

```groovy
dependencies {
    implementation 'com.github.ispong:spring-oxygen-boot-starter:1.1.1'
}
```

- for Maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-boot-starter</artifactId>
  <version>1.1.1</version>
</dependency>
```

## 🔨 Usage

There are a number of modules in spring-oxygen, here is a quick overview:

### ✅ [oxygen-freecode]()

- Fast generate java code

- Example

```yaml
oxygen:
  freecode:
    author: ispong
    version: 0.0.1
    table-prefix: leo_
    module-path: com.isxcode.leoday.module
```

```http request
GET http://localhost:8080/freecode/generate?tableName=leo_dogs
```

```text
📂 com
    📂 isxcode
        📂 leoday
            📂 module
                📂 dogs
                    📄 LeoDogsController
                    📄 LeoDogsEntity
                    📄 LeoDogsRepository
                    📄 LeoDogsService
```

### ✅ [oxygen-flysql]()

- Integrate spring jdbc rapid development

- Example

```java
import com.ispong.oxygen.flysql.pojo.enums.OrderType;
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.core.Flysql;

import java.util.List;

@Repository
public class LeoDogsRepository {

    public List<LeoDogsEntity> customQuery() {

        return Flysql.select(LeoDogsEntity.class)
            .select("name", "age", "color")
            .eq("name", "alen")
            .between("age", 12, 20)
            .like("color", "red")
            .orderBy("userIndex", OrderType.DESC)
            .query();
    }
}
```

### ✅ [oxygen-wechatgo]()

- Integrate WeChat platform rapid development

- Example

- wechat callback url: `https://108516f880de.ngrok.io/wechatgo/wechatServer`

```yaml
oxygen:
  wechatgo:
    app-id: wx5ada926e5489824f
    app-secret: d49e9eaac5633e661ff207353a9c86b1
    token: custom_token
```

```java
import com.ispong.oxygen.wechatgo.handler.WechatgoEventHandler;
import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;
import org.springframework.stereotype.Service;

@Service
class WechatService implements WechatgoEventHandler {

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {
        // do subscribe
    }

    @Override
    public void unsubscribeEvent(WeChatEventBody weChatEventBody) {
        // do unsubscribe
    }
}
```

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://gitee.com/ispong/blog-images/raw/master/idea/jetbrains-3.png" height="100" alt="jetbrains"/></a>