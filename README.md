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

[![github commit][commit-image]][commit-url] [![github release][release-image]][release-url] [![github license][license-image]][license-url] [![github workflow][workflow-image]][workflow-url] [![lgtm][lgtm-image]][lgtm-url] [![coveralls][coveralls-image]][coveralls-url]

[commit-image]: https://img.shields.io/github/last-commit/ispong/spring-oxygen?style=flat-square
[commit-url]: https://github.com/ispong/spring-oxygen/graphs/commit-activity
[release-image]: https://img.shields.io/github/v/release/ispong/spring-oxygen?style=flat-square
[release-url]: https://github.com/ispong/spring-oxygen/releases
[license-image]: https://img.shields.io/github/license/ispong/spring-oxygen?style=flat-square
[license-url]: https://github.com/ispong/spring-oxygen/blob/master/LICENSE
[workflow-image]: https://img.shields.io/github/workflow/status/ispong/spring-oxygen/release%20ci?style=flat-square
[workflow-url]: https://github.com/ispong/spring-oxygen/actions?query=workflow%3A%22release+ci%22
[lgtm-image]: https://img.shields.io/lgtm/grade/java/github/ispong/spring-oxygen?style=flat-square
[lgtm-url]: https://lgtm.com/projects/g/ispong/spring-oxygen/latest/files/?sort=name&dir=ASC&mode=heatmap
[coveralls-image]: https://img.shields.io/coveralls/github/ispong/spring-oxygen?style=flat-square
[coveralls-url]: https://coveralls.io/github/ispong/spring-oxygen?branch=latest
</div>

<div align="center">

[![github watch][watch-image]][watch-url] [![github star][star-image]][star-url] [![github fork][fork-image]][fork-url]

[watch-image]: https://img.shields.io/github/watchers/ispong/spring-oxygen?style=social
[watch-url]: https://github.com/ispong/spring-oxygen/watchers
[star-image]: https://img.shields.io/github/stars/ispong/spring-oxygen?style=social
[star-url]: https://github.com/ispong/spring-oxygen/stargazers
[fork-image]: https://img.shields.io/github/forks/ispong/spring-oxygen?style=social
[fork-url]: https://github.com/ispong/spring-oxygen/network/members
</div>

## 🚨 Note

Since release `v.1.1.0` , Package will not update in mvn center repository.We will only publish package in [github package repository](https://github.com/ispong/spring-oxygen/packages).

## 📦 Installation

#### For Gradle

- edit `build.gradle` file
```groovy
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/ispong/spring-oxygen")
        credentials {
            username = 'ispong'
            password = '135ea292b89b891ac9d155ad2e87f1abdf1db1fa'
        }
    }
    mavenCentral()
}

dependencies {
    implementation 'com.github.ispong:spring-oxygen-boot-starter:1.1.0'
}
```

#### For Maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-boot-starter</artifactId>
  <version>1.1.0</version>
</dependency>
```

## 🔨 Start Up

- You can try demo from [leo-day-spring](https://github.com/ispong/leo-day-spring).
```text
git clone -b template https://github.com/ispong/leo-day-spring
cd leo-day-spring
gradle bootRun
```

- you can see console to make sure spring-oxygen status.
```text
2020-09-06 10:20:25.896  INFO 17284 --- [           main] c.i.o.s.config.OxygenAutoConfiguration   : welcome to use spring-oxygen
   _____            _                   ____
  / ___/____  _____(_)___  ____ _      / __ \_  ____  ______ ____  ____
  \__ \/ __ \/ ___/ / __ \/ __ `/_____/ / / / |/_/ / / / __ `/ _ \/ __ \
 ___/ / /_/ / /  / / / / / /_/ /_____/ /_/ />  </ /_/ / /_/ /  __/ / / /
/____/ .___/_/  /_/_/ /_/\__, /      \____/_/|_|\__, /\__, /\___/_/ /_/
    /_/                 /____/                 /____//____/
```

## 📄 Documentation

You can find the spring-oxygen documentation [on the website](https://ispong.gitee.io/tags/spring-oxygen/).

## Modules

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