---
title: Git命令使用大全
subtitle: Git命令使用大全
tags:
  - git
categories: Git
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/git/git.png'
excerpt: Git命令使用大全
date: 2020-05-20 16:22:32
---

## 🙋 问题

1. 总是忘记常用的git命令

## 💡 方法

- 配置Git全局用户信息

```shell script
git config --global user.email "ispong@outlook.com"
git config --global user.name "ispong"
```

- 初始化项目

```shell script
git init
git remote remove origin
git remote add origin https://github.com/ispong/spring-oxygen
git add .
git commit -m "🗍 初始化: spring-oxygen项目"
git push origin master
```

- 删除本地缓存文件

```shell script
git rm -rf --cached xxx
```

- 储存用户登录密码

```shell script
git config --global credential.helper cache
```

- 分支处理

```shell script
git branch # 查看当前分支
git branch dev # 从当前分支创建分支
git checkout dev # 切换分支
git push origin dev # 推送分支
git branch -d dev # 删除本地分支
git push origin -d dev # 删除远程分支
```

- 回滚操作

```shell script
set LESSCHARSET=utf-8 # 解决乱码问题
git log # 查询git提交的所有日志
git reset --soft 0874fbf3e8349d0b4a72567410da54ce79113642 # 复制下图红线部分,进行commit回滚
```

![img](https://gitee.com/ispong/my-images/raw/master/blog-spring/git/164243.png)

| mode | 说明 |
| --- | --- |
| soft | 撤回提交，不需要重新add |
| mixed | 撤回暂存文件,需要重新add  |
| hard | 撤回全部,撤回stage区和工作区的文件 |

- Git 打标签

```shell script
git tag # 查看所有的标签
git tag v1.0.0 # 当前分支打标签
git push origin v1.0.0 # 推送标签
git push origin -d v1.0.0 # 删除远程标签
git tag -d v1.0.0 # 删除本地标签
```

## 📝 总结

🎈🎈 Git管理项目天下第一  🎉🎉🎉

## 🔍 参考

- [Git官方](https://git-scm.com/docs/git-reset)