#!/bin/bash

# 拉取master代码
cd /root/isxcode-spring
git pull

# gradle打包
bash gradlew clean build -x test

# maven打包
#mvn clean package -Dmaven.test.skip=true

# 删除旧的镜像
docker stop isxcode-spring
docker rm isxcode-spring
docker rmi spring:isxcode-spring

# 制作新的镜像
docker build -t spring:isxcode-spring .

# 启动镜像
docker run --name isxcode-spring --restart=always -v /root/file-data:/file-data -p 8080:8080 -d spring:isxcode-spring

# 释放缓存
sync
echo 1 > /proc/sys/vm/drop_caches
echo 2 > /proc/sys/vm/drop_caches
echo 3 > /proc/sys/vm/drop_caches

# 主机执行脚本
# ssh root@106.15.189.6 "bash /root/isxcode-spring/deploy.sh"
# isxcode123!