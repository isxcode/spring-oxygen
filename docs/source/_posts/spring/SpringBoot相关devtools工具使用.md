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
dependencies {
    developmentOnly("org.springframework.boot:spring-boot-devtools")
}
```

2. 修改Spring项目配置文件
```yaml
  # spring-devtools  idea需要配置
  devtools:
    add-properties: false # 禁用默认配置
    restart: # 配置自动部署
      enabled: true # 开启自动重启
      log-condition-evaluation-delta: false # 不启用重启日志
      additional-paths: src # 指定监控的文件夹
      additional-exclude: spring-oxygen # 指定不监控的文件夹
      exclude: docs/**,**/*Test.class # 指定不监控文件
      poll-interval: 1s # 轮询间隔
      quiet-period: 100ms # 触发重启等待时间 一定要比PollInterval小
      trigger-file: .reloadtrigger # 触发器文件,只有修改触发器文件才会触发重启
    livereload: # 触发chrome浏览器自动刷新 需要下载插件livereload (基于websocket实现)
      enabled: true # 启用livereload
      port: 35729 # 端口写死
    remote: # 远程部署
        restart:
          enabled: true # 开启远程热部署
        proxy:
          host: localhost # 远程ip地址
          port: 8888 # 远程端口号
        context-path: /.~~spring-boot!~  # 远程访问上下文 可自定义
        secret: secrectKey # 密钥 必要参数 可自定义
        secret-header-name: X-AUTH-TOKEN # 请求头标识
```

3. 创建触发器文件,内容自定义 `spring-oxygen\src\main\resources\.reloadtrigger`

4. 配置idea

组合键 `ctrl+alt+s` 搜索 `compiler` 勾选`Build project automatically`

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/174016.png)

组合键 `ctrl+shift+alt+/` 选择 `Registry` 勾选 `compiler.automake.allow.when.app.running` 

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/173637.png)

5. 体验自动加载

修改项目内容,再修改触发器文件. **Note**: 使用触发器时,需要触发两次,才可能生效。

## 📝 总结
🎈🎈 个人觉得这个没啥用,Spring项目本地重新跑也是很快的,远程热部署的话,有点不安全,万一手抖一下,远程就重新部署了,感觉与公司的缘分也就到头了,哈哈哈  🎉🎉🎉

## 🔍 参考

- [Spring-DevTool](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/html/using-spring-boot.html#using-boot-devtools)