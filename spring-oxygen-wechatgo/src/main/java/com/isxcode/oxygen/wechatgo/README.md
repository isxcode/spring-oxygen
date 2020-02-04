# wechatgo
- 快速使用

maven
```xml
<!-- https://github.com/ispong/spring-oxygen/oxygen-wechatgo-spring-boot-starter -->
<dependency>
    <groupId>com.github.ispong</groupId>
    <artifactId>oxygen-wechatgo-spring-boot-starter</artifactId>
    <version>0.0.1</version>
</dependency>
```
gradle
```groovy
// https://github.com/ispong/spring-oxygen/oxygen-wechatgo-spring-boot-starter
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

