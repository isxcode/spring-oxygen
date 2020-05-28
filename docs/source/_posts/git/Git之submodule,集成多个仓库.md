---
title: Git之submodule,集成多个仓库
subtitle: Git之submodule,集成多个仓库
tags:
  - git
categories: Git
index_img: 'https://gitee.com/ispong/my-images/raw/master/blog-spring/git/git.png'
excerpt: Git集成多个项目
date: 2020-05-27 19:28:15
---

## 🙋 问题

1. 想通过一个项目文件夹 管理不同的git仓库
 
## 💡 方法

- 创建一个新的项目
git submodule  
git submodule init
git submodule update　　 
- 组装进入多个项目
git submodule add ssh://ip/[path]/xxx.git

git clone http://git.definesys.com/xdap/xdap_framework.git --recursive

550*324
288*288

- 执行分支的切换  提交多个

## 📝 总结

🎈🎈 xxx  🎉🎉🎉

## 🔍 参考

- 无