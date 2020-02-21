# oxygen-flysql

### Quickly Start

1- import starter jar

maven
```xml
<dependency>
    <groupId>com.github.ispong</groupId>
    <artifactId>spring-oxygen-flysql</artifactId>
    <version>0.0.1</version>
</dependency>
```
gradle
```groovy
compile group: 'com.github.ispong', name: 'spring-oxygen-flysql', version: '0.0.1'
```

2- enable flysql
```java
import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFlysql
class AppConfig{

}
```

3- config dataBase info
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://host:port/dataBaseName
    username: root
    password: ENC@[R3ZE710H1U0nsxSr1x8vJKjD2rl83XEugZwVLOBkLIbrJekh0OE+7Vpsi2lvtpLP]
```

4- create entity dto demo
```java

@FlysqlView(type = DateBaseType.MYSQL, value = "select account name,password password,enabled_status status from user_info where password=:password")
@FlysqlView(type = DateBaseType.ORACLE, value = "select * from user_info")
@Data
class DemoDto{
        
    private String name;
    
    private String password;

    private String status;
}

```

5- create demo dao
```java

class DemoDtoDao{ 
    
    public DemoDto getDemoDto(){
                
        return FlySqlFactory.viewSql(DemoDto.class).setVar("password","demoName").getOne();  
    }
   
}
```

