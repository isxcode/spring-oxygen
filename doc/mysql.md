# docker 初始化mysql

> git clone https://github.com/ispong/isxcode-spring.git
> mkdir -p /root/mysql-data

> docker初始启动命令
```
docker run \
  --name mysql \
  --privileged=true \
  --restart=always \
  -d \
  -p 3306:3306 \
  -e MYSQL_ROOT_PASSWORD=isxcode \
  -e MYSQL_DATABASE=isxcode \
  -v /root/mysql-data:/var/lib/mysql \
  -v /root/isxcode-spring/doc/mysql:/etc/mysql/conf.d \
  -v /root/isxcode-spring/doc/mysql:/docker-entrypoint-initdb.d \
  mysql
```

# 连接名

> jdbc:mysql://106.15.189.6:3306/isxcode

