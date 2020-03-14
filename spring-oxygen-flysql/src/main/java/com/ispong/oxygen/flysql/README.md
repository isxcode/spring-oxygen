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

2- config dataBase info
```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://host:port/dataBase
    username: xxx
    password: xxx
  jpa:
    open-in-view: true
```

3- enable flysql
```java
import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFlysql
class AppConfig{

}
```

4- create table A and B
```sql
create table demo_table_a
(
    a_name varchar(100) null,
    a_age  int          null,
    a_desc mediumtext   null
);

create table demo_table_b
(
    b_name varchar(100) null,
    b_info varchar(200) null,
    b_date datetime     null
);
```

5- create DemoEntity 
```java
import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
import lombok.Data;

@Data
@TableName("demo_table_a")
public class DemoEntity {

    @ColumnName("a_name")
    private String customName;

    @ColumnName("a_age")
    private Integer customAge;

    @ColumnName("a_desc")
    private String customDesc;
}
```

6- create test controller 
```java
import com.ispong.oxygen.flysql.Flysql;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class HelloController {

    @GetMapping("saveDemo")
    public String saveDemo(){

        DemoEntity demoEntity = new DemoEntity();
        demoEntity.setCustomAge(20);
        demoEntity.setCustomDesc("a man");
        demoEntity.setCustomName("li");
        Flysql.insertSql(DemoEntity.class).save(demoEntity);

        return "success";
    }

    @GetMapping("getDemo")
    public String getDemo(){

        DemoEntity customAge = Flysql.selectSql(DemoEntity.class)
                .eq("customAge", 11)
                .getOne();

        return customAge.toString();
    }

    @GetMapping("queryDemo")
    public String queryDemo(){

        List<DemoEntity> customAge = Flysql.selectSql(DemoEntity.class)
                .gt("customAge", 1)
                .query();

        return customAge.toString();
    }

    @GetMapping("deleteDemo")
    public void deleteDemo(){

        Flysql.deleteSql(DemoEntity.class)
                .in("customAge",20)
                .doDelete();
    }

    @GetMapping("updateDemo")
    public void updateDemo(){

        Flysql.updateSql(DemoEntity.class)
                .like("customName", "w")
                .update("customDesc", "better man")
                .doUpdate();
    }

    @GetMapping("pageQueryDemo")
    public String pageQueryDemo(){

        List<DemoEntity> query = Flysql.selectSql(DemoEntity.class)
                .like("customName", "w")
                .query(2, 2);

        return query.toString();
    }

    @GetMapping("countDemo")
    public String countDemo(){
    
         Integer count = Flysql.countSql(DemoEntity.class)
                  .eq("customName","wang")
                  .count();
         return String.valueOf(count);
    }

}
```

7- two table

create DemoDto
```java
import com.ispong.oxygen.flysql.annotation.FlysqlView;
import com.ispong.oxygen.flysql.enums.DateBaseType;
import lombok.Data;

import java.time.LocalDateTime;

@FlysqlView(type = DateBaseType.MYSQL, value = "" +
        "select a.a_name name, a.a_age age, a_desc descInfo, b.b_info info, b.b_date dateTime\n" +
        "from demo_table_a a,\n" +
        "     demo_table_b b\n" +
        "where a.a_name = b.b_name and a.a_name=:name")
@Data
public class DemoDto {

    private String name;

    private String info;

    private String descInfo;

    private LocalDateTime dateTime;
    
    private Integer age;
}
```

create test controller
```java
import com.ispong.oxygen.flysql.Flysql;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;

@RestController
public class TestController {

    @GetMapping("queryDemoDto")
    public String queryDemo(){

        List<DemoDto> query = Flysql.viewSql(DemoDto.class)
                .setValue("name", "wang")
                .lt("dateTime", LocalDateTime.now())
                .query();

        return query.toString();
    }

}
```