# oxygen-wechatgo

### Quickly Start

1- import starter jar

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

2- add @EnableWechatgo
```java
@EnableWechatgo
public class AppConfig {

}
```

3- config yaml
```yaml
oxygen:
  wechatgo:
    app-id: xxx # appId
    app-secret: xxx # appSecret
    token: xxx # custom server token
```

4- config wechat server
```text
服务器地址(URL) -- http://localhost:port/context/wechatgo/wechatServer
令牌(Token) -- yaml配置的custom server token
消息加解密密钥 -- 随机数
消息加解密方式 -- 明文模式
```

5- start application
```text
                                                             __          __            
  ____  _  ____  ______ ____  ____       _      _____  _____/ /_  ____ _/ /_____ _____ 
 / __ \| |/_/ / / / __ `/ _ \/ __ \_____| | /| / / _ \/ ___/ __ \/ __ `/ __/ __ `/ __ \
/ /_/ />  </ /_/ / /_/ /  __/ / / /_____/ |/ |/ /  __/ /__/ / / / /_/ / /_/ /_/ / /_/ /
\____/_/|_|\__, /\__, /\___/_/ /_/      |__/|__/\___/\___/_/ /_/\__,_/\__/\__, /\____/ 
          /____//____/                                                   /____/        
```

### Advance Use

- wechat event
```java
public class WechatService implements WechatgoEventHandler {

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {
        // do something           
    }
    
    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {
        // do something       
    }

}
```

- wechatgo utils
```java
class demo {
    public void test(){
        WechatgoUtils.sendMsgTemplate("openId", "templateId", "data");
    }
}
```

