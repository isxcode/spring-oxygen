## Oxygen Flysql

### install

gradle

```groovy
implementation 'com.github.ispong:spring-oxygen-flysql:1.0.0'
```

maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-flysql</artifactId>
  <version>1.0.0</version>
</dependency>
```

### start up

1. 配置spring的`application.yml`配置文件

```yml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://mysql.xxx.com:3306/databaseName
    username: root
    password: root
```

- mysql script 初始化测试数据表脚本

```mysql
create table test_table
(
    id         varchar(30)  not null
        primary key,
    first_name varchar(100) null,
    age        int          null,
    birth      date         null,
    start_date datetime     null,
    introduce  text         null,
    money      decimal      null,
    is_valid   tinyint(1)   null,
    tall       double       null,
    exe_long   mediumtext   null
);
```

- 启动flysql脚本

```java
package com.isxcode.leoserver.config;

import com.ispong.oxygen.flysql.annotation.EnableFlysql;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFlysql
public class AppConfig {

}
```

- 启动成功标识 `console`

```text
2020-07-03 15:18:28.352  INFO 11060 --- [  restartedMain] c.i.o.f.config.FlysqlAutoConfiguration   : welcome to use oxygen-flysql
```

### usage

- entity 类对象

```java
package com.isxcode.leoserver.pojo;

import com.ispong.oxygen.flysql.annotation.ColumnName;
import com.ispong.oxygen.flysql.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@TableName("test_table")
public class People{

    @ColumnName("id")
    private String peopleNum;

    @ColumnName("first_name")
    private String username;

    private Integer age;

    private LocalDate birth;

    @ColumnName("start_date")
    private LocalDateTime startDate;

    @ColumnName("introduce")
    private String introduce;

    private BigDecimal money;

    @ColumnName("is_valid")
    private Boolean valid;

    private Double tall;
}
```

- controller 测试类

```java
package com.isxcode.leoserver.controller;

import com.ispong.oxygen.flysql.core.Flysql;
import com.isxcode.leoserver.pojo.People;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/flysql")
public class TestController {

    /**
     * 插入操作
     *
     * @since 0.0.1
     */
    @GetMapping("/add")
    public void addPeople() {

        People people = new People(
                "001",
                "ispong",
                22,
                LocalDate.now(),
                LocalDateTime.now(),
                "love coding people",
                new BigDecimal("12.110"),
                false,
                170.111);

        Flysql.insert(People.class).save(people);
    }

    /**
     * 查询单个操作
     *
     * @since 0.0.1
     */
    @GetMapping("/get")
    public People getPeople() {

        return Flysql.select(People.class)
                .eq("peopleNum", "001")
                .eq("birth", LocalDate.now())
                .getOne();
    }

    /**
     * 查询批量操作
     *
     * @since 0.0.1
     */
    @GetMapping("/query")
    public List<People> queryPeople() {

        return Flysql.select(People.class)
                .eq("birth", LocalDate.now())
                .query();
    }

    /**
     * 更新操作
     *
     * @since 0.0.1
     */
    @GetMapping("/update")
    public void updatePeople() {

        Flysql.update(People.class)
                .update("age", 44)
                .eq("peopleNum", "001")
                .doUpdate();
    }

    /**
     * 删除操作
     *
     * @since 0.0.1
     */
    @GetMapping("/delete")
    public void deletePeople() {

        Flysql.delete(People.class)
                .eq("peopleNum", "001")
                .doDelete();
    }

    /**
     * 数量查询操作
     *
     * @since 0.0.1
     */
    @GetMapping("/count")
    public Integer countPeople() {

        return Flysql.select(People.class)
                .ltEq("age", 40)
                .count();
    }

}
```
