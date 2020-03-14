# [spring-oxygen](https://github.com/ispong/spring-oxygen) &middot; [![Status](https://img.shields.io/badge/status-developing-ff69b4?style=flat-square)](https://github.com/ispong/spring-oxygen) [![Spring](https://img.shields.io/badge/spring-2.2.x-blue?style=flat-square)](https://spring.io/) [![Maven Central](https://img.shields.io/maven-central/v/com.github.ispong/spring-oxygen-flysql?style=flat-square)](https://search.maven.org/search?q=g:com.github.ispong) [![GitHub last commit](https://img.shields.io/github/last-commit/ispong/spring-oxygen?style=flat-square)](https://github.com/ispong/spring-oxygen) [![GitHub](https://img.shields.io/github/license/ispong/spring-oxygen?style=flat-square)](https://github.com/ispong/spring-oxygen/blob/master/LICENSE)

Spring rapid development integration framework

## Installation

Maven
```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen</artifactId>
  <version>0.0.1</version>
</dependency>
```

Gradle
```groovy
implementation 'com.github.ispong:spring-oxygen:0.0.1'
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

There are a number of modules in Spring Oxygen, here is a quick overview:

### [oxygen-flysql](https://github.com/ispong/spring-oxygen/tree/master/spring-oxygen-flysql/src/main/java/com/ispong/oxygen/flysql)

- Integrate spring jdbc rapid development

- Example
```java
import org.springframework.stereotype.Repository;
import com.ispong.oxygen.flysql.Flysql;

@Repository
public class DemoDao {

    public List<DemoEntity> queryDemo() {

        return Flysql.select(Demo.class)
                .select("column", "column1", "column2", "column3")
                .eq("column", "v")
                .between("column", "v1", "v2")
                .gt("column", "v")
                .ltEq("column", "v")
                .like("column", "v")
                .in("column", "v1", "v2", "v3")
                .orderBy("column", "desc")
                .query();
    }
}
```

### [oxygen-wechatgo](https://github.com/ispong/spring-oxygen/tree/master/spring-oxygen-wechatgo/src/main/java/com/ispong/oxygen/wechatgo)

- Integrate WeChat platform rapid development

- Example
```yaml
oxygen:
  wechatgo:
    app-id: xxxxx # appId
    app-secret: xxxxx # appSecret
    token: xxxxx # custom server token
```

***

#### Thanks for free JetBrains Open Source license

<a href="https://www.jetbrains.com/?from=spring-oxygen" target="_blank"><img src="https://github.com/ispong/spring-oxygen/blob/master/doc/jetbrains.png?raw=true" height="80" alt="jetbrains"/></a>
