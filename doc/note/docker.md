# docker 部署镜像

ip:106.15.189.6 
port:22
user:root
passwd:isxcode123!

== 安装fedora源
yum install -y  https://dl.fedoraproject.org/pub/epel/epel-release-latest-7.noarch.rpm

== 安装docker//配置阿里云镜像地址
yum install -y docker
service docker start
vi /etc/docker/daemon.json

```cmd
{
	"registry-mirrors":["https://3fe1zqfu.mirror.aliyuncs.com"]
}
```
service docker restart
service docker status

== 安装git
yum install -y git 
yum install -y java-1.8.0-openjdk java-1.8.0-openjdk-devel

=== 配置git凭证
git config --global credential.helper store

== clone git
git clone https://github.com/ispong/isxcode-spring.git


## acs 服务器
ip : 47.105.33.160
user: root
pwd liu123456.


# 安装rabbitmq服务器

docker pull rabbitmq:management
docker run \
    --privileged=true \
    --restart=always \
    --name rabbitmq \
    -d \
    -p 5672:5672 \
    -p 15672:15672 \
    -e RABBITMQ_DEFAULT_USER=admin \
    -e RABBITMQ_DEFAULT_PASS=admin \
    -v /root/rabbitmq-data:/var/lib/rabbitmq \
    rabbitmq:3-management
       
docker run -d --hostname my-rabbit --name some-rabbit -e RABBITMQ_DEFAULT_USER=user -e RABBITMQ_DEFAULT_PASS=password rabbitmq:3-management