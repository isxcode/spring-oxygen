---
title: Maven-导入同事的Jar
subtitle: Maven-导入同事的Jar
tags:
  - maven
categories: Maven
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/maven/maven.png'
date: 2020-05-28 11:02:48
---
## 🙋 Question

- 同事写了一个Jar包，但是没有传入maven仓库，手动复制粘贴进入仓库太慢了

## 💡 Idea

#### 使用命令导入本地仓库

1. 修改`pom.xml`文件

```xml
<dependencies>
    <dependency>
    	<groupId>com.definesys.mpaas</groupId>
        <artifactId>query-mongodb</artifactId>
        <version>1.0.9-beta</version>
    </dependency>
</dependencies>
```
2. 将Jar复制到桌面

```text
C:\Users\ispon\Desktop\query-mongodb-1.0.9-beta.jar
```
3. 执行导入命令

```shell script
# mvn install:install-file -DgroupId=${groupId} -DartifactId=${query-mongodb} -Dversion=${version} -Dpackaging=jar -Dfile=${jarFile}

mvn install:install-file -DgroupId=com.definesys.mpaas -DartifactId=query-mongodb -Dversion=1.0.9-beta -Dpackaging=jar -Dfile=C:\Users\ispon\Desktop\query-mongodb-1.0.9-beta.jar
```

## 📝 Solution

🏳️‍🌈🏳️‍🌈🏳️‍🌈  有些隐私的Jar，不易上传到中央仓库的可以通过本地打包导入的方式.如果可以公开代码,也可以直接上传到本地仓库。

## 🔍 Reference

- [Maven]()
