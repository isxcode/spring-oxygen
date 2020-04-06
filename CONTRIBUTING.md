how to contrubite

准备 java 11 
gradle-6.1.1


java 安装 配置
gradle 安装 配置


1 git clone https://github.com/ispong/spring-oxygen.git
2 安装java11 
win+R sysdm.cpl	
删除system path C:\Program Files (x86)\Common Files\Oracle\Java\javapath
添加user path D:\developer\Java\jdk-11.0.6\bin
java -version

3 安装gradle
win+R sysdm.cpl	
配置环境
本地运行
gradle -version
GRADLE_USER_HOME D:\developer\gradle-6.1.1\repository


4 配置idea开发环境
gradle clean build -x test
gradle bootRun


5- 发布mvn 到远程