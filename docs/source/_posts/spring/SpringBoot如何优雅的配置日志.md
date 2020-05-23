---
title: SpringBoot如何优雅的配置日志
subtitle: SpringBoot如何优雅的配置日志
tags:
  - spring
categories: Spring
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/spring.png'
excerpt: SpringBoot如何优雅的配置日志
date: 2020-05-21 10:10:05
---

## 🙋 问题

1. 不想写logback.xml配置文件

## 💡 方法

- 使用application.yaml文件进行配置 (Note:每个不同的版本配置文件格式可能不同，以下是2.2.4.RELEASE版本)
```yaml
logging:
  group:
    ispong:  # 建立的自己日志组
      - com.ispong.oxygen.websockets
      - com.ispong.oxygen.wechatgo
      - com.ispong.oxygen.flysql
      - com.ispong.oxygen.freecode
  level:
    root: info # 打印所有的info信息
    ispong : debug # 设置日志组打印的等级
  file:
    name: logs/spring.log  # 保存到项目目录下logs文件夹中,且日志文件为spring.log
    max-size: 10MB # 文件最大大小为10MB
    max-history: 30 # 日志最多保留30天
    total-size-cap: 100MB # 所有附件的大小上限
    clean-history-on-start: false # 开机清理日志历史
  exception-conversion-word: '%wEx' # 异常形式
  pattern:
    level: '%5p' # 日志等级
    dateformat: 'yyyy-MM-dd HH:mm:ss.SSS' # 日志时间格式
    console: '%clr(%d{${LOG_DATEFORMAT_PATTERN}}){faint} %clr(${LOG_LEVEL_PATTERN}) %clr(${PID:- }){magenta} %clr(---){faint} %clr([%15.15t]){faint} %clr(%-40.40logger{39}){cyan} %clr(:){faint} %m%n${LOG_EXCEPTION_CONVERSION_WORD}' # 控制台打印格式
    file: '%d{${LOG_DATEFORMAT_PATTERN}} ${LOG_LEVEL_PATTERN} %-5(${PID:- }) --- [%15.15t] %-40.40logger{39} : %m%n${LOG_EXCEPTION_CONVERSION_WORD}}' # 日志文件输出格式
    rolling-file-name: '${LOG_FILE}.%d{yyyy-MM-dd}.%i.gz' # 压缩的文件名
  register-shutdown-hook: false # 是否注册一个关机hook给日志系统
```
- 日志文件保存格式

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/104913.png)

- 控制台日志显示格式

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/105915.png)

- 保存日志文件日志显示格式

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/spring/110107.png)

## 📝 总结

🎈🎈 正确规范的使用日志,可以更快的定位错误  🎉🎉🎉

## 🔍 参考

- [Spring Boot文档](https://docs.spring.io/spring-boot/docs/2.2.7.RELEASE/reference/html/appendix-application-properties.html#common-application-properties)