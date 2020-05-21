---
title: SpringBoot相关devtools工具使用
subtitle: SpringBoot相关devtools工具使用
tags:
  - spring
categories: Spring
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/spring.png'
excerpt: SpringBoot相关devtools工具使用
date: 2020-05-21 14:15:24
---

## 🙋 问题

1. 不想每次都去重新运行项目

## 💡 方法

- 使用Spring的Developer Tools工具

1. gradle导入依赖
```groovy
configurations {
    developmentOnly
    runtimeClasspath {
        extendsFrom developmentOnly
    }
}
dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
```

2. 修改Spring项目配置文件
```yaml
  # spring-devtools  idea需要配置
  devtools:
    add-properties: false # 使用false会出现 部署无法立即生效
    restart: # 配置重新启动
      enabled: true # 开启自动重启
      log-condition-evaluation-delta: false # 是否重启日志
      additional-paths: src # 监控重启的文件夹
      additional-exclude: spring-oxygen # 不监控文件夹
      exclude: docs/**,**/*Test.class # 不监控文件
      poll-interval: 1s # 轮询间隔
      quiet-period: 100ms # 触发重启等待时间 不能比PollInterval大
      trigger-file: .reloadtrigger #触发器文件,只有修改触发器文件才会触发
    livereload: # 触发chrome浏览器自动加载 需要下载插件livereload
      enabled: true # 启用livereload
      port: 35729 # 端口写死
    remote: # 远程部署
        restart:
          enabled: true # 开启远程热部署
        proxy:
          host: localhost # ip地址
          port: 8888 # 端口号
        context-path: /.~~spring-boot!~  # 远程访问上下文
        secret: secrectKey # 密钥
        secret-header-name: X-AUTH-TOKEN # 请求头标识
```

## 📝 总结
🎈🎈 xxx  🎉🎉🎉

## 🔍 参考

- [Spring-DevTool](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/html/using-spring-boot.html#using-boot-devtools)