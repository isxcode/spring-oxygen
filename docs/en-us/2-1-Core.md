### Config Core

```yaml
spring:
  mail:
    host: smtp.qq.com                          # 必填 邮件服务器地址
    port: 465                                  # 必填 邮件服务器端口
    protocol: smtp                             # 可选 默认smtp 邮件协议 
    test-connection: false                     # 可选 默认false 是否进行连接检测
    username: xxx                              # 必填 用户账号
    password: xxx                              # 必填 用户密码
    default-encoding: UTF-8                    # 可选 默认UTF-8 邮件编码
    properties:                                # 整体可选
      sender: a leo day                        # 默认空 邮件发件人名称
      mail.smtp.ssl.enable: true               # 默认true 是否支持https
      mail.smtp.connectiontimeout: 10000       # 默认10000 链接超时时间
      mail.smtp.timeout: 10000                 # 默认10000 超时时间
      mail.smtp.writetimeout: 10000            # 默认10000 默认写入超时时间
```

### Send Email

```
package com.isxcode.demo.oxygen;

import com.isxcode.oxygen.core.email.EmailUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/email")
public class EmailController {

    @GetMapping("/send")
    public void sendEmail() {

        String toEmail = "ispong@outlook.com";
        String emailContent = "hello world";
        String emailTitle = "welcome to use spring-oxygen";
        EmailUtils.sendNormalEmail(toEmail, emailContent, emailTitle);
    }
}
```

### Export Excel

1. create dog entity

```
package com.isxcode.demo.oxygen;

import com.isxcode.oxygen.core.excel.ExcelType;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Dog {

    @ExcelType(cellName = "狗狗名称")
    private String name;

    @ExcelType(cellName = "狗狗年龄")
    private Integer age;

    @ExcelType(cellName = "狗狗爱好")
    private String joy;
}

```


2. 编写接口

```
package com.isxcode.demo.oxygen;

import com.isxcode.oxygen.core.excel.ExcelUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/excel")
public class ExcelController {

    @GetMapping("/write")
    public void writeExcel(HttpServletResponse response) throws UnsupportedEncodingException {

        List<Dog> dogList = Arrays.asList(
                new Dog("Tom", 12, "swim"),
                new Dog("Gar", 16, "eat"));

        String fileName = "狗狗Excel";
        ExcelUtils.generate(dogList, fileName, response);
    }

    @GetMapping("/read")
    public void readExcel(@RequestParam("excel") MultipartFile excel) throws IOException {

        List<Dog> dogs = ExcelUtils.parse(excel.getInputStream(), Dog.class);
        System.out.println(dogs);
    }

}
```
