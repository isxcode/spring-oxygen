### Oracle/Mysql/H2

```yaml
oxygen:
    flysql:
        datasource:
            dbSource1:
                driver-class-name: oracle.jdbc.driver.OracleDriver
                url: jdbc:oracle:thin:@${host}:${port}:${db}
                username: ${username}
                password: ${password}
            dbSource2:
                driver-class-name: com.mysql.cj.jdbc.Driver
                url: jdbc:mysql://${host}:${port}/${db}
                username: ${username}
                password: ${password} 
            dbSource3:
                driver-class-name: org.h2.Driver
                url: jdbc:h2:${host}:${port}/${db}
                username: ${username}
                password: ${password}
            ${oxygen_db_name}:
                driver-class-name: org.h2.Driver
                url: jdbc:h2:${host}:${port}/${db}
                username: ${username}
                password: ${password}
```

### MongoDB

```yaml
oxygen:
    flysql:
        mongodb:
            dbSource1:
                uri: mongodb://${username}:${password}@${host}:${port}/${db}
            dbSource2:
                host: ${host}
                port: ${port}
                username: ${username}
                password: ${password}
                database: ${db}
            ${oxygen_db_name}:
                host: ${host}
                port: ${port}
                username: ${username}
                password: ${password}
                database: ${db}
```
