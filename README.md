# [spring-oxygen](https://github.com/ispong/spring-oxygen) &middot; [![Status](https://img.shields.io/badge/status-developing-ff69b4?style=flat-square)](https://github.com/ispong/spring-oxygen) [![Spring](https://img.shields.io/badge/spring-2.2.x-blue?style=flat-square)](https://spring.io/) [![Maven Central](https://img.shields.io/maven-central/v/com.github.ispong/spring-oxygen-flysql?style=flat-square)](https://search.maven.org/search?q=g:com.github.ispong) [![GitHub last commit](https://img.shields.io/github/last-commit/ispong/spring-oxygen?style=flat-square)](https://github.com/ispong/spring-oxygen) [![GitHub](https://img.shields.io/github/license/ispong/spring-oxygen?style=flat-square)](https://github.com/ispong/spring-oxygen/blob/master/LICENSE)

Spring rapid development integration framework

## Installation

Maven
```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen</artifactId>
  <version>0.0.3</version>
</dependency>
```

Gradle
```groovy
implementation 'com.github.ispong:spring-oxygen:0.0.3'
```

Start Up
```java
import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFlysql
@EnableWechatgo
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

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://github.com/ispong/spring-oxygen/blob/master/docs/jetbrains/jetbrains.png?raw=true" height="150" alt="jetbrains"/></a>
