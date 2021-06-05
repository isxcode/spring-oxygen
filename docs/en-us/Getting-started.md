#### Start a new Spring Boot project

?> Recommend to add dependency `Lombok` and `Spring Web`

- [https://spring.io/quickstart](https://spring.io/quickstart)

#### Add spring-oxygen dependency

> edit `pom.xml` <br/>
> Recommend to use `https://repo1.maven.org/maven2/` repository

```xml

<dependencies>

    <dependency>
        <groupId>com.isxcode.oxygen</groupId>
        <artifactId>oxygen-spring-boot-starter</artifactId>
        <version>0.0.3</version>
    </dependency>

    <!-- for demo (optional)   -->
    <dependency>
        <groupId>com.h2database</groupId>
        <artifactId>h2</artifactId>
    </dependency>

</dependencies>
```

#### Edit database init schema

> edit schema file `src/main/resources/db/schema.sql`

```sql
DROP TABLE IF EXISTS DOGS_T;

CREATE TABLE DOGS_T
(
    DOG_ID    VARCHAR(100) PRIMARY KEY,
    DOG_NAME  VARCHAR(100) NOT NULL,
    DOG_AGE   INT          NOT NULL,
    DOG_COLOR VARCHAR(100) NOT NULL
);
```

#### Edit project config file

> edit spring config file `src/main/resources/application.properties`

```properties
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.url=jdbc:h2:~/h2
spring.datasource.username=root
spring.datasource.password=root
spring.sql.init.enabled=true
spring.sql.init.schema-locations=classpath:db/schema.sql
```

#### Demo

##### Entity

```java
package com.example.demo.dogs;

import com.isxcode.oxygen.flysql.annotation.ColumnName;
import com.isxcode.oxygen.flysql.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@TableName("DOGS_T")
@Data
public class DogEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @ColumnName("DOG_ID")
    private String id;

    @ColumnName("DOG_NAME")
    private String name;

    @ColumnName("DOG_AGE")
    private Integer age;

    @ColumnName("DOG_COLOR")
    private String color;
}
```

##### Controller

```java
package com.example.demo;

import com.example.demo.dogs.DogEntity;
import com.isxcode.oxygen.flysql.core.Flysql;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.mongo.MongoAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/dogs")
@SpringBootApplication(exclude = {MongoAutoConfiguration.class})
public class DemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private final Flysql flysql;

    public DemoApplication(Flysql flysql) {
        this.flysql = flysql;
    }

    @GetMapping("/query")
    public List<DogEntity> queryDogs() {

        // add dogs
        DogEntity.DogEntityBuilder dog = DogEntity.builder()
            .id("1")
            .name("li")
            .age(12)
            .color("black");

        flysql.build().insert(DogEntity.class).save(dog);

        // query dogs
        return flysql.build().select(DogEntity.class).query();
    }
}
```

#### Run and Test Project

```log
Connected to the target VM, address: '127.0.0.1:49967', transport: 'socket'

  .   ____          _            __ _ _
 /\\ / ___'_ __ _ _(_)_ __  __ _ \ \ \ \
( ( )\___ | '_ | '_| | '_ \/ _` | \ \ \ \
 \\/  ___)| |_)| | | | | || (_| |  ) ) ) )
  '  |____| .__|_| |_|_| |_\__, | / / / /
 =========|_|==============|___/=/_/_/_/
 :: Spring Boot ::                (v2.5.0)

2021-06-03 14:51:07.430  INFO 15432 --- [           main] com.example.demo.DemoApplication         : Starting DemoApplication using Java 11.0.10 on ispong-pc with PID 15432 (C:\Users\ispon\Desktop\demo\target\classes started by ispon in C:\Users\ispon\Desktop\demo)
2021-06-03 14:51:07.434  INFO 15432 --- [           main] com.example.demo.DemoApplication         : No active profile set, falling back to default profiles: default
2021-06-03 14:51:07.964  INFO 15432 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Bootstrapping Spring Data MongoDB repositories in DEFAULT mode.
2021-06-03 14:51:07.971  INFO 15432 --- [           main] .s.d.r.c.RepositoryConfigurationDelegate : Finished Spring Data repository scanning in 4 ms. Found 0 MongoDB repository interfaces.
2021-06-03 14:51:08.424  INFO 15432 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat initialized with port(s): 8080 (http)
2021-06-03 14:51:08.432  INFO 15432 --- [           main] o.apache.catalina.core.StandardService   : Starting service [Tomcat]
2021-06-03 14:51:08.432  INFO 15432 --- [           main] org.apache.catalina.core.StandardEngine  : Starting Servlet engine: [Apache Tomcat/9.0.46]
2021-06-03 14:51:08.528  INFO 15432 --- [           main] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring embedded WebApplicationContext
2021-06-03 14:51:08.528  INFO 15432 --- [           main] w.s.c.ServletWebServerApplicationContext : Root WebApplicationContext: initialization completed in 1036 ms
2021-06-03 14:51:08.596  INFO 15432 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Starting...
2021-06-03 14:51:08.708  INFO 15432 --- [           main] com.zaxxer.hikari.HikariDataSource       : HikariPool-1 - Start completed.
   _____            _                   ____                            
  / ___/____  _____(_)___  ____ _      / __ \_  ____  ______ ____  ____ 
  \__ \/ __ \/ ___/ / __ \/ __ `/_____/ / / / |/_/ / / / __ `/ _ \/ __ \
 ___/ / /_/ / /  / / / / / /_/ /_____/ /_/ />  </ /_/ / /_/ /  __/ / / /
/____/ .___/_/  /_/_/ /_/\__, /      \____/_/|_|\__, /\__, /\___/_/ /_/ 
    /_/                 /____/                 /____//____/             
 Github: https://github.com/isxcode/spring-oxygen
  Docs : https://oxygen.isxcode.com
2021-06-03 14:51:09.194  WARN 15432 --- [           main] o.s.b.a.f.FreeMarkerAutoConfiguration    : Cannot find template location(s): [classpath:/templates/] (please add some templates, check your FreeMarker configuration, or set spring.freemarker.checkTemplateLocation=false)
2021-06-03 14:51:09.245  INFO 15432 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2021-06-03 14:51:09.252  INFO 15432 --- [           main] com.example.demo.DemoApplication         : Started DemoApplication in 2.319 seconds (JVM running for 3.168)
2021-06-03 14:51:09.253  INFO 15432 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state LivenessState changed to CORRECT
2021-06-03 14:51:09.254  INFO 15432 --- [           main] o.s.b.a.ApplicationAvailabilityBean      : Application availability state ReadinessState changed to ACCEPTING_TRAFFIC
2021-06-03 14:51:57.215  INFO 15432 --- [nio-8080-exec-1] o.a.c.c.C.[Tomcat].[localhost].[/]       : Initializing Spring DispatcherServlet 'dispatcherServlet'
2021-06-03 14:51:57.216  INFO 15432 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Initializing Servlet 'dispatcherServlet'
2021-06-03 14:51:57.217  INFO 15432 --- [nio-8080-exec-1] o.s.web.servlet.DispatcherServlet        : Completed initialization in 1 ms
```

```log
GET http://localhost:8080/dogs/query

HTTP/1.1 200 
Content-Type: application/json
Transfer-Encoding: chunked
Date: Thu, 03 Jun 2021 07:05:18 GMT
Keep-Alive: timeout=60
Connection: keep-alive

[
  {
    "id": "1",
    "name": "li",
    "age": 12,
    "color": "black"
  }
]

Response code: 200; Time: 5569ms; Content length: 49 bytes
```
