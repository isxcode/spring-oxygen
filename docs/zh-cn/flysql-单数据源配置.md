### Oracle

```yaml
spring:
    datasource:
        driver-class-name: oracle.jdbc.driver.OracleDriver
        url: jdbc:oracle:thin:@${host}:${port}:${db}
        username: ${username}
        password: ${password}
```

### Mysql

```yaml
spring:
    datasource:
        driver-class-name: com.mysql.cj.jdbc.Driver
        url: jdbc:mysql://${host}:${port}/${db}
        username: ${username}
        password: ${password}
```

### MongoDB

```yaml
spring:
    data:
        mongodb:
            host: ${host}
            port: ${port}
            username: ${username}
            password: ${password}
            database: ${db}
```

or

```yaml
spring:
    data:
        mongodb:
            uri: mongodb://${username}:${password}@${host}:${port}/${db}
```

### H2

```yaml
spring:
  datasource:
    driver-class-name: org.h2.Driver
    url: jdbc:h2:${host}:${port}/${db}
    username: ${username}
    password: ${password}
```
