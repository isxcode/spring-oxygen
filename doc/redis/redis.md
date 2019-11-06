# redis

```bash
docker run \
  --name redis \
  --privileged=true \
  --restart=always \
  -d \
  -p 8088:6379 \
  -v /root/redis-data:/data \
  -v /etc/redis.conf:/usr/local/etc/redis/redis.conf \
  redis redis-server /usr/local/etc/redis/redis.conf
```

sudo chmod 777 /etc/redis.conf

修改配置 
requirepass isxcode
logfile


# 远程连接redis
redis-cli -h 47.105.33.169 -p 8088 -a isxcode