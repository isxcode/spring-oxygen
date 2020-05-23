---
title: SpringCloudAlibaba之Nacos
subtitle: SpringCloudAlibaba之Nacos
tags:
  - spring
categories: Spring
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/spring.png'
excerpt: SpringCloudAlibaba之Nacos
date: 2020-05-23 12:08:49
---

## 🙋 问题

1. 安装注册服务发现

## 💡 方法

- 创建初始项目

- 导入pom 加注解 启动项目
```xml
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0" xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
	xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 https://maven.apache.org/xsd/maven-4.0.0.xsd">
	<modelVersion>4.0.0</modelVersion>
	<parent>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-parent</artifactId>
		<version>2.2.7.RELEASE</version>
		<relativePath/> <!-- lookup parent from repository -->
	</parent>
	<groupId>com.ispong</groupId>
	<artifactId>spring-oxygen-nacos</artifactId>
	<version>0.0.1-SNAPSHOT</version>
	<name>spring-oxygen-nacos</name>
	<description>Demo project for Spring Boot</description>

	<properties>
		<java.version>1.8</java.version>
	</properties>
	
	<dependencyManagement>
		<dependencies>
			<dependency>
				<groupId>com.alibaba.cloud</groupId>
				<artifactId>spring-cloud-alibaba-dependencies</artifactId>
				<version>2.1.0.RELEASE</version>
				<type>pom</type>
				<scope>import</scope>
			</dependency>
		</dependencies>
	</dependencyManagement>

	<dependencies>
		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter</artifactId>
		</dependency>

		<!-- nacos-discovery -->
		<dependency>
			<groupId>com.alibaba.cloud</groupId>
			<artifactId>spring-cloud-starter-alibaba-nacos-discovery</artifactId>
		</dependency>

		<dependency>
			<groupId>org.springframework.boot</groupId>
			<artifactId>spring-boot-starter-test</artifactId>
			<scope>test</scope>
			<exclusions>
				<exclusion>
					<groupId>org.junit.vintage</groupId>
					<artifactId>junit-vintage-engine</artifactId>
				</exclusion>
			</exclusions>
		</dependency>
	</dependencies>

	<build>
		<plugins>
			<plugin>
				<groupId>org.springframework.boot</groupId>
				<artifactId>spring-boot-maven-plugin</artifactId>
			</plugin>
		</plugins>
	</build>

</project>
```

```java
package com.ispong.springoxygennacos;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class SpringOxygenNacosApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringOxygenNacosApplication.class, args);
	}

}
```

- 下载源码 打开控制界面

```shell script
git clone https://gitee.com/ispong/nacos.git
cd nacos/
mvn -Prelease-nacos -Dmaven.test.skip=true clean install -U  
cd distribution/target/nacos-server-1.3.0-BETA/nacos/bin
# 配置环境变量JAVA_HOME
# 启动应用
startup.cmd
# 关闭应用
shutdown.cmd
```

访问地址 http://127.0.0.1:8848/nacos/index.html#/login
账号密码 nacos / nacos

- 注册服务发现 走界面上配置
```shell script
# 服务注册
curl -X POST 'http://127.0.0.1:8848/nacos/v1/ns/instance?serviceName=nacos.naming.serviceName&ip=20.18.7.10&port=8080'

# 服务发现
curl -X GET 'http://127.0.0.1:8848/nacos/v1/ns/instance/list?serviceName=nacos.naming.serviceName'

# 发布配置
curl -X POST "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test&content=HelloWorld"

# 获取配置
curl -X GET "http://127.0.0.1:8848/nacos/v1/cs/configs?dataId=nacos.cfg.dataId&group=test"
```

## 📝 总结

🎈🎈 xxx  🎉🎉🎉

## 🔍 参考

- 无