---
title: Git提交小技巧
subtitle: Git提交小技巧
tags:
  - git
categories: Git
banner_img: 'https://gitee.com/ispong/my-images/raw/master/blog-react/page.png'
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/git/git.png'
excerpt: Git提交小技巧
date: 2020-05-20 14:18:12
---

## 🙋 问题

1. 如何用最少的命令提交代码
2. 总是不知道写什么内容
3. 查看git历史记录不够直观 

## 💡 方法

- 将emoji图标放进提交命令中

| emoji | 对应命令 | 代表的含义 | 提交内容示例 |
| --- | --- | --- |--- |
| ✨ | Add | 新增文件 | 新增:xxx |
| 📝 | Update | 修改文件 | 更新: xxx |
| 🐛 | Fix | 修复bug | 修复: xxx (#12345 issue编码) |
| 🔥 | Delete | 移除文件 | 移除:xxx  |
| ⚡ | Test | 添加测试 | 测试: xxx |
| 🎨 | Better | 优化代码 | 优化: xxx | 
| 🗍 | Init | 初始化项目 | 初始化: xxx | 

- 提交代码建议

1. 写代码最好一个功能一个功能去写,多commit代码，让提交的动作变得清晰。
2. 一定要配置好.gitignore文件，使用 `git add . `命令也不是不可以

> 配置.gitignore文件,以gradle项目为案例
```.gitignore
# gradle缓存文件不提交
.gradle

# idea缓存文件不提交
.idea

# 所有的build文件夹都不提交
**/build
**/out

# 日志文件夹不提交
logs

# 配置文件不能提交
gradle.properties
```

> 使用简单的代码提交命令
```shell script
# 撸代码中...
git add .
git commit -m "✨ 新增: 用户登录接口"
# 撸代码中...
git add .
git commit -m "📝 修改: 禁用超级用户账号"
git push origin master
```

## 📝 总结

🎈🎈 使用规范提交代码，同一个世界同一个git日志格式,为了能够更好的管理项目，更早的完成任务,少受点'福报'。 🎉🎉🎉

## 🔍 参考

1. 参考开源项目 hexo-theme-fluid