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