# 储存登录密码
```shell script
git config --global credential.helper cache
```

# 如何把一个仓库代码推到另一个仓库中

```shell script
git remote remove origin
git remote add origin http://git.definesys.com/DDMP/dap-doms-plugin.git
git add .
git commit -m "[init] 初始化项目"
git push origin master
```

# 创建分支
```shell script
git checkout -b uat
git checkout -b prod
git checkout -b dev
```

# 删除远程分支
```shell script
git push origin --delete add-code-of-conduct-1
```

# 撤销commit内容 soft只回退commit信息
```shell script
git reset --soft 97a58928febe82d5b7eceee1a9b92dfa54272c1c
```

## git log 中文乱码
```shell script
set LESSCHARSET=utf-8
git log
```