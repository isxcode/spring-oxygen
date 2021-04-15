1. Init from [spring website](https://start.spring.io/)

> Project: Gradle Project </br>
> Language: Java </br>
> Spring Boot: 2.4.4 </br>
> Package: Jar </br>
> Java: 11 </br>
> Dependencies: Spring Web+Lombok </br>

![img](https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen-docs/8821090bd75e877d12bfa0dfe4d1e06.png)

2. Unzip and Run Project

```bash
unzip demo
cd demo
gradle bootRun
```

![img](https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen-docs/be9d9e3c363ead6c79c51bd9a8521fe.png)


3. add Dependency

- For maven

```xml
<dependency>
  <groupId>com.isxcode.oxygen</groupId>
  <artifactId>oxygen-spring-boot-starter</artifactId>
  <version>0.0.1</version>
</dependency>
```

- For gradle

```groovy
dependencies {
    implementation 'com.isxcode.oxygen:oxygen-spring-boot-starter:0.0.1'
}
```

4. Run Project

```bash
#cd demo
gradle bootRun
```

![img](https://gitee.com/isxcode/blogs-galaxy-images/raw/master/oxygen-docs/d25b7b4e6e9e95696d2a7ebc71e439f.png)
