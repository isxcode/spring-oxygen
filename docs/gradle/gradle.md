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

注释 <relativePath/>
```

```shell script
gradle init
```

```
repositories {
    mavenLocal()
    maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
    flatDir { dirs 'libs' }
}
sourceCompatibility = JavaVersion.VERSION_11
```

```shell script
gradle build
```

### gradle环境
.gradle
gradle
gradlew
build.gradle
gradlew.bat
settings.gradle
build


compileOnly 'org.projectlombok:lombok:1.18.2'
annotationProcessor 'org.projectlombok:lombok:latest.integration'

# gradle 使用手册

## 调试命令
gradle processDebugResources --debug

## 通过gradle发布jar到中央仓库

DEFAULT_JVM_OPTS -Dfile.encoding=UTF-8 
    
### 使用gradle发布项目到中央仓库

发布项目- https://oss.sonatype.org/
管理项目- https://issues.sonatype.org/    
查询项目- https://search.maven.org/

参考 - https://docs.gradle.org/current/userguide/signing_plugin.html#header

1- 创建GPG密钥,上传到maven中心
```shell script
gpg --full-generate-key # 初始化一对密钥
gpg --list-key # 查看已生成key 后8位为keyId
gpg --export-secret-keys B794F8D1 > secret.gpg  
gpg --keyring secret.gpg --export-secret-keys > secring.gpg
```

2- 修改gradle.properties
```shell script
gpg --keyserver http://keys.openpgp.org:11371/ --send-keys B794F8D1
gpg --keyserver http://keyserver.ubuntu.com:11371/ --send-keys B794F8D1
gpg --keyserver http://pool.sks-keyservers.net:11371/ --send-keys B794F8D1
```

```text
signing.keyId=B794F8D1
signing.password=song151617
signing.secretKeyRingFile=C:/Users/ispon/.gnupg/secring.gpg
```
