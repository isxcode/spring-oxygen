## maven init

> maven 初始化项目

-- 注意 **^** 后面不能有空格
```cmd
mvn archetype:generate ^
    -DgroupId=com.ispong ^
    -DartifactId=isx-java ^
    -DarchetypeArtifactId=maven-archetype-quickstart ^
    -DarchetypeVersion=1.4 ^
    -DinteractiveMode=false
```

> 修改maven镜像地址

-- D:\developer\maven\conf\settings.xml
```xml
<mirrors>

    <mirror>
      <id>alimaven</id>
      <mirrorOf>central</mirrorOf>
      <name>aliyun maven</name>
      <url>http://maven.aliyun.com/nexus/content/groups/public/</url>
    </mirror>

</mirrors>
```

> 无效的标记

-- mvn java的版本号要和pom.xml配置一致
```cmd
D:\isxcode\isx-java>mvn --version
Apache Maven 3.6.1 (d66c9c0b3152b2e69ee9bac180bb8fcc8e6af555; 2019-04-05T03:00:29+08:00)
Maven home: D:\developer\maven\bin\..
Java version: 1.8.0_111, vendor: Oracle Corporation, runtime: D:\developer\jdk1.8.0\jdk\jre
Default locale: zh_CN, platform encoding: GBK
OS name: "windows 10", version: "10.0", arch: "amd64", family: "windows"
```