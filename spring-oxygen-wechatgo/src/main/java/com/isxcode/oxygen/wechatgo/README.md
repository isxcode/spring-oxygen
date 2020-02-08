# wechatgo
- 快速使用

> 提供功能

1- 快速配置服务器地址

2- 自动刷新access_token

3- 关注/取消关注接口开放

4- 推送模板接口开放

maven
```xml
<dependency>
    <groupId>com.github.ispong</groupId>
    <artifactId>oxygen-wechatgo-spring-boot-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```
gradle
```groovy
compile group: 'com.github.ispong', name: 'oxygen-wechatgo-spring-boot-starter', version: '0.0.1'
```

- yaml配置
```yaml
oxygen:
  wechatgo:
    app-id: xxx # appId
    app-secret: xxx # appSecret
    token: xxx # custom server token
```

- 微信服务器配置地址
```http request
GET http://localhost:port/context/wechatgo/wechatServer
```

- 快捷获取access_token
```java
class demo{
    public void useToken(){
        System.out.println("access_token",WE_CHAT_ACCESS_TOKENS);
    }   
}
```

- 提供扩展接口 (订阅接口/取消订阅接口/模板推送成功接口/模板推送失败接口)
```java
class demo extends WechatgoService{ 
    
        
    
}
```

