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
服务器地址(URL) -- http://${localhost}:8080/${context}/wechatgo/wechatServer
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

实现WechatgoEventHandler可自定义实现各种提供好的接口,比如关注接口,取消关注接口。还可以结合WechatgoTemplate实例去直接实现模板发送之类的方法。请结合官网文档进行使用！
```java
@Service
public class WechatService implements WechatgoEventHandler {

    @Resource
    private WechatgoTemplate wechatgoTemplate;

    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {
               
        String data = "{\n" +
                    "           \"touser\":\"OPENID\",\n" +
                    "           \"template_id\":\"ngqIpbwh8bUfcSsECmogfXcV14J0tQlEpBO27izEYtY\",\n" +
                    "           \"url\":\"http://weixin.qq.com/download\",  \n" +
                    "           \"miniprogram\":{\n" +
                    "             \"appid\":\"xiaochengxuappid12345\",\n" +
                    "             \"pagepath\":\"index?foo=bar\"\n" +
                    "           },          \n" +
                    "           \"data\":{\n" +
                    "                   \"first\": {\n" +
                    "                       \"value\":\"恭喜你购买成功！\",\n" +
                    "                       \"color\":\"#173177\"\n" +
                    "                   },\n" +
                    "                   \"keyword1\":{\n" +
                    "                       \"value\":\"巧克力\",\n" +
                    "                       \"color\":\"#173177\"\n" +
                    "                   },\n" +
                    "                   \"keyword2\": {\n" +
                    "                       \"value\":\"39.8元\",\n" +
                    "                       \"color\":\"#173177\"\n" +
                    "                   },\n" +
                    "                   \"keyword3\": {\n" +
                    "                       \"value\":\"2014年9月22日\",\n" +
                    "                       \"color\":\"#173177\"\n" +
                    "                   },\n" +
                    "                   \"remark\":{\n" +
                    "                       \"value\":\"欢迎再次购买！\",\n" +
                    "                       \"color\":\"#173177\"\n" +
                    "                   }\n" +
                    "           }\n" +
                    "       }";
            wechatgoTemplate.sendMsgTemplate(data);
    }
    
    @Override
    public void unsubscribeEvent(WeChatEventBody weChatEventBody) {
        // 取消关注事件       
    }
    
    @Override
    public void sendMsgTemplateResponse(WeChatEventBody weChatEventBody) {
        // 发送模板返回事件        
    }

}
```


