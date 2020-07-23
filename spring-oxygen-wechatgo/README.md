<h1 align="center">Oxygen Wechatgo</h1>

## 📦 Installation

gradle

```groovy
implementation 'com.github.ispong:spring-oxygen-wechatgo:1.0.0'
```

maven

```xml
<dependency>
  <groupId>com.github.ispong</groupId>
  <artifactId>spring-oxygen-wechatgo</artifactId>
  <version>1.0.0</version>
</dependency>
```

## 🔨 Start Up

```java
import com.ispong.oxygen.wechatgo.annotation.EnableWechatgo;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableWechatgo
public class AppConfig {

}
```

```yaml
oxygen:
  wechatgo:
    app-id: xxx # appId
    app-secret: xxx # appSecret
    token: xxx # custom server token
```

🚨 Note:  配置微信公众号的属性,且要放行端口权限 
```text
服务器地址(URL) -- http://localhost:port/context/wechatgo/wechatServer
令牌(Token) -- yaml配置的custom server token
消息加解密密钥 -- 随机数
消息加解密方式 -- 明文模式
```

```text
2020-07-02 18:40:21.978  INFO 3376 --- [  restartedMain] c.i.o.w.c.WechatgoAutoConfiguration      : welcome to use oxygen-wechatgo
```

## ✏️usage
```java
import com.ispong.oxygen.wechatgo.handler.WechatgoEventHandler;
import com.ispong.oxygen.wechatgo.pojo.entity.WeChatEventBody;
import com.ispong.oxygen.wechatgo.template.WechatgoTemplate;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Slf4j
@Service
public class WechatService implements WechatgoEventHandler {

    @Resource
    private WechatgoTemplate wechatgoTemplate;
    
    @Override
    public void subscribeEvent(WeChatEventBody weChatEventBody) {

        log.info(weChatEventBody.getFromUserName() + "此用户订阅微信公众号");
    }

    @Override
    public void unsubscribeEvent(WeChatEventBody weChatEventBody) {

        log.info(weChatEventBody.getFromUserName() + "此用户取消订阅微信公众号");
    }

    @Override
    public void sendMsgTemplateResponse(WeChatEventBody weChatEventBody) {

        log.info(weChatEventBody.getStatus() + "此消息模板发送成功");
    }

    public void sendMsgTemplate() {

        String openId = "oLRflwyj79FUS7O7zARJsLq16k5k";
        String requestParam = "{\n" +
                "           \"touser\":\"" + openId + "\",\n" +
                "           \"template_id\":\"" + "KQwJbCIjdJ9wvktUBVxx2fVm44QdSsqNNyqU7kqnsHg" + "\",\n" +
                "           \"data\":{\n" +
                "                   \"first\": {\n" +
                "                       \"value\":\"你好！\",\n" +
                "                       \"color\":\"#000000\"\n" +
                "                   },\n" +
                "                   \"keyword1\":{\n" +
                "                       \"value\":\"新任务包发布\",\n" +
                "                       \"color\":\"#027AFF\"\n" +
                "                   },\n" +
                "                   \"keyword2\": {\n" +
                "                       \"value\":\"待你查看。\",\n" +
                "                       \"color\":\"#000000\"\n" +
                "                   },\n" +
                "                   \"remark\":{\n" +
                "                       \"value\":\"请登录pluto.definesys.com操作。\",\n" +
                "                       \"color\":\"#000000\"\n" +
                "                   }\n" +
                "           }\n" +
                "       }";

        wechatgoTemplate.sendMsgTemplate(requestParam);
    }
}

```