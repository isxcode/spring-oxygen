---
title: 如何使用Gradle发布自己的Jar到中央仓库
subtitle: 如何使用Gradle发布自己的Jar到中央仓库
tags:
  - gradle
categories: Gradle
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/gradle/gradle.png'
excerpt: 如何使用Gradle发布自己的Jar到中央仓库
date: 2020-05-23 18:41:33
---

## 🙋 问题

1. 发布自己的开源项目到中央仓库

## 💡 方法

- 重要的网址

1. [发布项目地址](https://oss.sonatype.org/)
2. [管理项目地址](https://issues.sonatype.org/)
3. [查询项目地址](https://search.maven.org/)
4. [Gradle官方参考文档](https://docs.gradle.org/current/userguide/signing_plugin.html#header)

- 安装GPG加密协议

生成GPG密钥

```shell script
gpg --full-generate-key # 初始化一对密钥
gpg --list-key # 查看已生成key 后8位为keyId
gpg --export-secret-keys B794F8D1 > secret.gpg  
gpg --keyring secret.gpg --export-secret-keys > secring.gpg
```

上传到GPG密钥仓库
```shell script
gpg --keyserver http://keys.openpgp.org:11371/ --send-keys B794F8D1
gpg --keyserver http://keyserver.ubuntu.com:11371/ --send-keys B794F8D1
gpg --keyserver http://pool.sks-keyservers.net:11371/ --send-keys B794F8D1
```

修改gradle.properties文件

```properties
signing.keyId=B794F8D1
signing.password=song151617
signing.secretKeyRingFile=C:/Users/ispon/.gnupg/secring.gpg
```


## 📝 总结

🎈🎈 xxx  🎉🎉🎉

## 🔍 参考

- 无