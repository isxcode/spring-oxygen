### Init database （目前只支持spring单数据源初始化）

```
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

```
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

```
insert into dogs values('john',12,0,'ispong','2020-11-10','ispong','2020-11-11',1);
insert into dogs values('tom',13,0,'ispong','2020-11-10','ispong','2020-11-11',1);
```