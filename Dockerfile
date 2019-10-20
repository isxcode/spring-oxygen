# 指定基础镜像
FROM openjdk
# 维护者信息
MAINTAINER ispong ispong@outlook.com
# 执行命令
RUN mkdir /file-data
# 当前主机的工作目录
WORKDIR /root/isxcode-spring
# 复制文件到容器中（依赖于上面的主机地址）
ADD /build/libs/isxcode-spring-0.0.1.jar isxcode-spring.jar
# 挂载主机目录
VOLUME /file-data
# 配置上海时区
ENV TZ=Asia/Shanghai
# 打开端口
EXPOSE 8080
# 容器启动后执行命令
CMD java -jar isxcode-spring.jar