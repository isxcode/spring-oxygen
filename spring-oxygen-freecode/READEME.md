<h1 align="center">Oxygen Freecode</h1>

## 📦 Installation

gradle

```groovy
implementation 'com.github.ispong:spring-oxygen-freecode:1.0.0'
```

maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-freecode</artifactId>
  <version>1.0.0</version>
</dependency>
```

## 🔨 Start Up

```java
import com.ispong.oxygen.freecode.annotation.EnableFreecode;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFreecode
public class AppConfig {

}
```

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://mysql.xxx.com:3306/databaseName
    username: root
    password: root

oxygen:
  freecode:
    author: ispong
    version: 0.0.2
    module-path: com.isxcode.leoserver.module
```

```text
2020-07-05 11:54:54.348  INFO 12080 --- [  restartedMain] c.i.o.f.c.FreecodeAutoConfiguration      : welcome to use oxygen-freecode```
```

## ✏️usage

```http request
GET http://localhost:8080/freecode/generate?tableName=user_table
```

```text
📂 com
    📂 ispong
        📂 app
            📂 moudle
                📄 userTableController
                📄 userTableEntity
                📄 userTableRepository
                📄 userTableService
```