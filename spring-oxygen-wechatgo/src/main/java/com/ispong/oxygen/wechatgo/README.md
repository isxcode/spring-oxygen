# oxygen-wechatgo

### Quickly Start

1- import starter jar

maven

```xml
<dependency>
    <groupId>com.github.ispong</groupId>
    <artifactId>spring-oxygen-wechatgo</artifactId>
    <version>1.0.0</version>
</dependency>
```

gradle

```groovy
compile group: 'com.github.ispong', name: 'spring-oxygen-wechatgo', version: '1.0.0'
```

2- add @EnableWechatgo

```java
@Configuration
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

5- Note: 关闭接口安全拦截

6- start application console

```text

2020-07-02 18:40:21.978  INFO 3376 --- [  restartedMain] c.i.o.w.c.WechatgoAutoConfiguration      : welcome to use oxygen-wechatgo

```

### Advance Use

实现WechatgoEventHandler可自定义实现各种提供好的接口,比如关注接口,取消关注接口。还可以结合WechatgoTemplate实例去直接实现模板发送之类的方法。请结合官网文档进行使用！

```java
package com.isxcode.leoserver.service;

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


