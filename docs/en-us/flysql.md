### Config database

```
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:~/h2
    username: root
    password: root
```

### 多数据源配置

```
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:~/h2
    username: root
    password: root

oxygen:
  flysql:
    datasource:
      db1:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://xxx
        username: xxx
        password: xxx
      db2:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://xxx
        username: xxx
        password: xxx  
      db3:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://xxx
        username: xxx
        password: xxx  
```

### Init database （目前只支持spring单数据源初始化）

```yml
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:~/h2
    username: root
    password: root
    initialization-mode: always
    schema: classpath:db/schema_h2.sql
    data: classpath:db/data_h2.sql
```

- db/schema_h2.sql

```sql
drop table if exists dogs;

create table dogs
(
    name               varchar(32)  primary key ,
    age                integer      not null ,
    is_delete          integer      not null ,
    created_by         varchar(50)  not null ,
    created_date       datetime     not null ,
    last_modified_by   varchar(50)  not null ,
    last_modified_date datetime     not null ,
    version            int          not null
);
```

- db/data_h2.sql

```sql
insert into dogs values('john',12,0,'ispong','2020-11-10','ispong','2020-11-11',1);
insert into dogs values('tom',13,0,'ispong','2020-11-10','ispong','2020-11-11',1);
```

###  Start Flysql

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

### Save

```
	@GetMapping("/saveDogs")
    public List<DogsEntity> saveDogs() {

        DogsEntity dogsEntity = DogsEntity.builder().age(12).name("li").build();
        Flysql.insert(DogsEntity.class).save(dogsEntity);
        return Flysql.select(DogsEntity.class).query();
    }
```

### Delete

```
    @GetMapping("/deleteDogs")
    public List<DogsEntity> deleteDogs() {

        Flysql.delete(DogsEntity.class)
                .eq("name", "li")
                .doDelete();
        return Flysql.select(DogsEntity.class).query();
    }
```

### Update

```
    @GetMapping("/updateDogs")
    public List<DogsEntity> updateDogs() {

        Flysql.update(DogsEntity.class)
                .eq("name", "li")
                .update("age", 100)
                .doUpdate();
        return Flysql.select(DogsEntity.class).query();
    }
```

### Select

```
package com.isxcode.demo.oxygen.dogs;

import com.isxcode.oxygen.flysql.core.Flysql;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import lombok.extern.slf4j.Slf4j;

/**
 * Controller - 
 *
 * @author ispong
 * @since v0.0.1
 */
@Slf4j
@RestController
@RequestMapping("/dogs")
public class DogsController {

    /**
     * query DogsEntity
     *
     * @return String
     */
    @GetMapping("/queryDogs")
    public List<DogsEntity> queryDogs() {
        
        return Flysql.select(DogsEntity.class).query();
    }

}
```
