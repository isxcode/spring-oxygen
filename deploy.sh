#!/bin/bash

# 拉取master代码
echo "=========================> git pull master"
cd /root/isxcode-spring
git pull
echo "=========================> successful"

# gradle打包
echo "=========================> gradle package"
bash gradlew clean build -x test
echo "=========================> successful"

# maven打包
#echo "=========================> maven package"
#mvn clean package -Dmaven.test.skip=true
#echo "=========================> successful"

# 删除旧的镜像
echo "=========================> delete docker image"
docker stop isxcode-spring
docker rm isxcode-spring
docker rmi spring:isxcode-spring
echo "=========================> successful"

# 制作新的镜像
echo "=========================> build docker image"
docker build -t spring:isxcode-spring .
echo "=========================> successful"

# 启动镜像
echo "=========================> run docker image"
docker run --name isxcode-spring --restart=always -v /root/file-data:/file-data -p 8080:8080 -d spring:isxcode-spring
echo "=========================> successful"

# 释放缓存
echo "=========================> drop caches"
sync
echo 1 > /proc/sys/vm/drop_caches
echo 2 > /proc/sys/vm/drop_caches
echo 3 > /proc/sys/vm/drop_caches
echo "=========================> successful"

# 主机执行脚本
# ssh root@106.15.189.6 "bash /root/isxcode-spring/deploy.sh"
# isxcode123!