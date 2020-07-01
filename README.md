<p align="center">
  <a href="https://github.com/ispong/spring-oxygen">
    <img alt="spring-oxygen" width="300" src="https://gitee.com/ispong/blog-images/raw/master/design/design.png">
  </a>
</p>

<h2 align="center">Spring Oxygen</h2>

<div align="center">

Spring rapid development integration framework.

[![Status][Status-image]][Status-url] [![Spring][Spring-image]][Spring-url] [![Maven Central][Maven-image]][Maven-url] [![GitHub last commit][commit-image]][commit-url] [![GitHub][license-image]][license-url]

[Status-image]: https://img.shields.io/badge/status-developing-ff69b4?style=flat-square
[Status-url]: https://github.com/ispong/spring-oxygen
[Spring-image]: https://img.shields.io/badge/spring-2.2.x-blue?style=flat-square
[Spring-url]: https://spring.io/
[Maven-image]: https://img.shields.io/maven-central/v/com.github.ispong/spring-oxygen-flysql?style=flat-square
[Maven-url]: https://search.maven.org/search?q=g:com.github.ispong
[commit-image]: https://img.shields.io/github/last-commit/ispong/spring-oxygen?style=flat-square
[commit-url]: https://github.com/ispong/spring-oxygen
[license-image]: https://img.shields.io/github/license/ispong/spring-oxygen?style=flat-square
[license-url]: https://github.com/ispong/spring-oxygen/blob/master/LICENSE

</div>

## Installation

Maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifaectId>spring-oxygen-boot-starter</artifaectId>
  <version>0.0.3</version>
</dependency>
```

Gradle

```groovy
implementation 'com.github.ispong:spring-oxygen-boot-starter:0.0.3'
```

Start Up

```java
import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;
import com.ispong.oxygen.freecode.annotation.EnableFreecode;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFlysql
@EnableWechatgo
@EnableFreecode
public class AppConfig {

}
```

## Documentation

You can find the spring-oxygen documentation [on the website](https://ispong.gitee.io/spring-oxygen).  

## Modules

There are a number of modules in spring-oxygen, here is a quick overview:

### [oxygen-flysql](https://ispong.gitee.io/spring-oxygen)

- Integrate spring jdbc rapid development

- Example

```java
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.Flysql;
import com.ispong.oxygen.flysql.enums.OrderType;

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

### [oxygen-wechatgo](https://ispong.gitee.io/spring-oxygen)

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
import com.ispong.oxygen.wechatgo.WechatgoEventHandler;
import com.ispong.oxygen.wechatgo.model.WeChatEventBody;
import org.springframework.stereotype.Service;

@Service
public class WechatgoService implements WechatgoEventHandler {

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {
        
        // do subscribe event
    }
}
```

### [oxygen-freecode](https://ispong.gitee.io/spring-oxygen)

- Fast generate java code

- Example

```yaml
oxygen:
  freecode:
    module-path: com.ispong.oxygen 
    author: ispong 
    version: 0.0.1 
    base-entity-class: com.ispong.oxygen.flysql.common.BaseEntity
    base-controller-class: com.ispong.oxygen.flysql.common.BaseController 
    ignore-columns: 
      - uuid
      - version
      - created_date
      - created_by
      - last_modified_date
      - last_modified_by
    file-types: 
      - controller
      - entity
      - service
      - repository
```

```http 

POST http://${localhost}:${port}/${context}/freecode
Content-Type: application/json

{
    "tableName": "${tableName}"
}
```

 generate file like below
 
```text
-- module
   -- ${tableName}
      -- ${tableName}Controller
      -- ${tableName}Entity
      -- ${tableName}Repository
      -- ${tableName}Service
```

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://gitee.com/ispong/blog-images/raw/master/idea/idea-logo.png" height="150" alt="jetbrains"/></a>
