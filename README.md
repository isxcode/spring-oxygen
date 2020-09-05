<p align="center">
  <a href="https://github.com/ispong/spring-oxygen">
    <img alt="spring-oxygen" width="500" src="https://gitee.com/ispong/blog-images/raw/master/design/oxygen.png">
  </a>
</p>

<h1 align="center">
    Spring Oxygen
</h1>

<h4 align="center">
Spring rapid development integration framework
</h4>

<div align="center">

[![github commit][commit-image]][commit-url] [![github license][license-image]][license-url] [![github workflow][workflow-image]][workflow-url] [![Language grade: Java][lgtm-image]][lgtm-url] [![Coverage Status][coveralls-image]][coveralls-url]

[commit-image]: https://img.shields.io/github/last-commit/ispong/spring-oxygen?style=flat
[commit-url]: https://github.com/ispong/spring-oxygen/graphs/commit-activity
[license-image]: https://img.shields.io/github/license/ispong/spring-oxygen?style=flat
[license-url]: https://github.com/ispong/spring-oxygen/blob/master/LICENSE
[workflow-image]: https://img.shields.io/github/workflow/status/ispong/spring-oxygen/release%20ci?style=flat
[workflow-url]: https://github.com/ispong/spring-oxygen/actions?query=workflow%3A%22release+ci%22
[lgtm-image]: https://img.shields.io/lgtm/grade/java/g/ispong/spring-oxygen.svg?logo=lgtm&logoWidth=18
[lgtm-url]: https://lgtm.com/projects/g/ispong/spring-oxygen/context:java
[coveralls-image]: https://coveralls.io/repos/github/ispong/spring-oxygen/badge.svg?branch=latest
[coveralls-url]: https://coveralls.io/github/ispong/spring-oxygen?branch=latest
</div>

<div align="center">

[![github watch][github-watch-image]][github-watch-url] [![github star][github-star-image]][github-star-url] [![github fork][github-fork-image]][github-fork-url]

[github-watch-image]: https://img.shields.io/github/watchers/ispong/spring-oxygen?style=flat
[github-watch-url]: https://github.com/ispong/spring-oxygen/watchers
[github-star-image]: https://img.shields.io/github/stars/ispong/spring-oxygen?style=flat
[github-star-url]: https://github.com/ispong/spring-oxygen/stargazers
[github-fork-image]: https://img.shields.io/github/forks/ispong/spring-oxygen?style=flat
[github-fork-url]: https://github.com/ispong/spring-oxygen/network/members
</div>

## Not

After 1.0.0 , will be not publish in center mvn,will only publish in github repository
and after only publish oxygen-spring-boot-starter ,will not publish any jar,we will use simply

## 📦 Installation

Note: version > 1.0.0,please add Github package repository
```
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/ispong/spring-oxygen")
        credentials {
            username = ispong ?: System.getenv("USERNAME")
            password = f4bf5205a7526d316ebb17617bd1c67aa9fe25e6 ?: System.getenv("TOKEN")
        }
    }
}
```

Maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-boot-starter</artifactId>
  <version>1.0.0</version>
</dependency>
```

Gradle

```groovy
implementation 'com.github.ispong:spring-oxygen-boot-starter:1.0.0'
```

## 🔨 Start Up

```java
import com.ispong.oxygen.annotation.EnableOxygen;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableOxygen
public class AppConfig {

}
```

## 📄 Documentation

You can find the spring-oxygen documentation [on the website](https://ispong.gitee.io).

## Modules

There are a number of modules in spring-oxygen, here is a quick overview:

### ✅ [oxygen-flysql](https://github.com/ispong/spring-oxygen/blob/master/spring-oxygen-flysql/README.md)

- Integrate spring jdbc rapid development

- Example

```java
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.core.Flysql;
import com.ispong.oxygen.flysql.pojo.enums.OrderType;

@Repository
public class UserDao {

    public List<UserEntity> queryUser() {

        return Flysql.select(UserEntity.class)
                .select("userName", "sex", "account", "age")
                .eq("userName", "ispong")
                .between("point", 100, 200)
                .gt("age", "18")
                .like("sex", "M")
                .in("userPower", "ADMIN", "USER", "MANAGER")
                .orderBy("userIndex", OrderType.DESC)
                .query();
    }
}
```

### ✅ [oxygen-wechatgo](https://github.com/ispong/spring-oxygen/blob/master/spring-oxygen-wechatgo/README.md)

- Integrate WeChat platform rapid development

- Example

```yaml
oxygen:
  wechatgo:
    app-id: xxxxx # appId
    app-secret: xxxxx # appSecret
    token: xxxxx # custom server token
```

```java
import com.ispong.oxygen.wechatgo.handler.WechatgoEventHandler;
import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;
import org.springframework.stereotype.Service;

@Service
public class WechatgoService implements WechatgoEventHandler {

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {

        // do subscribe event
    }
}
```

### ✅ [oxygen-freecode](https://github.com/ispong/spring-oxygen/blob/master/spring-oxygen-freecode/READEME.md)

- Fast generate java code

- Example

```yaml
oxygen:
  freecode:
    author: ispong
    version: 0.0.2
    module-path: com.ispong.app.module
```

```http request
GET http://localhost:8080/freecode/generate?tableName=user_table
```

```text
📂 com
    📂 ispong
        📂 app
            📂 module
                📄 userTableController
                📄 userTableEntity
                📄 userTableRepository
                📄 userTableService
```

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://gitee.com/ispong/blog-images/raw/master/idea/jetbrains-3.png" height="100" alt="jetbrains"/></a>
