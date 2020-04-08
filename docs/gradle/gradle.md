## gradle

### gradle 打包忽略某些文件
```groovy
jar {
    exclude('**/application.properties')
}
```

### gradle 如何导入外部 jar

```
repositories {
    mavenLocal()
    maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
    flatDir { dirs 'libs' }
}
sourceCompatibility = JavaVersion.VERSION_11
```

### maven项目转gradle项目

1- add repository
> 因为默认是http协议访问不安全,现在全是https访问
```xml
<repositories>
    <repository>
        <id>central</id>
        <url>https://repo.maven.apache.org/maven2/</url>
    </repository>
</repositories>
```

2- 注释标签 <relativePath/>

3- 初始化脚本执行
```shell script
gradle init
```

4- 添加.gitignore
```text
.gradle
gradle
gradlew
build.gradle
gradlew.bat
settings.gradle
build
libs
```

5- 修改lombok
```groovy
compileOnly 'org.projectlombok:lombok:latest.integration'
annotationProcessor 'org.projectlombok:lombok:latest.integration'
```

6- 添加阿里镜像
```groovy
repositories {
		maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
		jcenter()
		mavenCentral()
	}
```
## 调试命令
gradle processDebugResources --debug

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
