# 集成spring-security

- 目前只集成 servlet security

- Authorization 权限控制
```
思路：
    解析token后,获取用户的数据库中记载的权限,构建几个静态的spring权限
附属权限,再使用@PreAuthorize 进行接口配置
```