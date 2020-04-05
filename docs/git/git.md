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

