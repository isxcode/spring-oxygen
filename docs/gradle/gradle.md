## gradle

### gradle 如何导入外部 jar

flatDir { dirs 'lib' }

### 如何将 maven 项目 gradle 项目

// 添加 repo 不然会报 501

```xml
<repositories>
    <repository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2/</url>
    </repository>
</repositories>
```

```shell script
gradle init
```

```
repositories {
    mavenLocal()
    maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
    flatDir { dirs 'lib' }
}
sourceCompatibility = JavaVersion.VERSION_11
```

```shell script
gradle build
```
