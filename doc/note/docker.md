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

