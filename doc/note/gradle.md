#  配置gradle环境

GRADLE_USER_HOME 配置本地仓库

在本地项目中加入

 repositories {
        maven { url 'http://maven.aliyun.com/nexus/content/groups/public/' }
                maven{ url 'http://maven.aliyun.com/nexus/content/repositories/jcenter'}
    }