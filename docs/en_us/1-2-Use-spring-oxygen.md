### Use spring-oxygen

#### Install

- For maven

```
<dependency>
  <groupId>com.isxcode.oxygen</groupId>
  <artifactId>oxygen-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```

- For gradle

```
dependencies {
    implementation 'com.isxcode.oxygen:oxygen-spring-boot-starter:0.0.1'
}
```

#### Start up and Test

```
cd demo
gradle bootRun
```

```
2020-11-25 16:35:27.948  INFO 12064 --- [         task-1] o.hibernate.jpa.internal.util.LogHelper  : HHH000204: Processing PersistenceUnitInfo [name: default]
2020-11-25 16:35:27.986  INFO 12064 --- [         task-1] org.hibernate.Version                    : HHH000412: Hibernate ORM core version 5.4.23.Final
2020-11-25 16:35:28.090  INFO 12064 --- [         task-1] o.hibernate.annotations.common.Version   : HCANN000001: Hibernate Commons Annotations {5.1.2.Final}
2020-11-25 16:35:28.179  INFO 12064 --- [         task-1] org.hibernate.dialect.Dialect            : HHH000400: Using dialect: org.hibernate.dialect.H2Dialect
   _____            _                   ____
  / ___/____  _____(_)___  ____ _      / __ \_  ____  ______ ____  ____
  \__ \/ __ \/ ___/ / __ \/ __ `/_____/ / / / |/_/ / / / __ `/ _ \/ __ \
 ___/ / /_/ / /  / / / / / /_/ /_____/ /_/ />  </ /_/ / /_/ /  __/ / / /
/____/ .___/_/  /_/_/ /_/\__, /      \____/_/|_|\__, /\__, /\___/_/ /_/
    /_/                 /____/                 /____//____/
2020-11-25 16:35:28.342  INFO 12064 --- [         task-1] o.h.e.t.j.p.i.JtaPlatformInitiator       : HHH000490: Using JtaPlatform implementation: [org.hibernate.engine.transaction.jta.platform.internal.NoJtaPlatform]
2020-11-25 16:35:28.351  INFO 12064 --- [         task-1] j.LocalContainerEntityManagerFactoryBean : Initialized JPA EntityManagerFactory for persistence unit 'default'
2020-11-25 16:35:28.416  INFO 12064 --- [           main] o.s.b.w.embedded.tomcat.TomcatWebServer  : Tomcat started on port(s): 8080 (http) with context path ''
2020-11-25 16:35:28.417  INFO 12064 --- [           main] DeferredRepositoryInitializationListener : Triggering deferred initialization of Spring Data repositoriesà
2020-11-25 16:35:28.417  INFO 12064 --- [           main] DeferredRepositoryInitializationListener : Spring Data repositories initialized!
2020-11-25 16:35:28.423  INFO 12064 --- [           main] c.isxcode.demo.oxygen.OxygenApplication  : Started OxygenApplication in 2.199 seconds (JVM running for 2.503)
<==========---> 80% EXECUTING [1m 35s]
> :bootRun
```