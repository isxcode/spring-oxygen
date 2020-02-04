# spring oxygen

## oxygen-wechatgo

> 封装微信公众号开发工具,starter+yaml配置,快速上手简单易懂
- [wechatgo](https://github.com/ispong/spring-oxygen/tree/master/spring-oxygen-wechatgo/src/main/java/com/isxcode/oxygen/wechatgo)

* 集成 **spring-mail**
* 集成 **spring-websocket**
* 集成 **spring-security**
* 集成 **spring-jdbc**
* 集成 **spring-rabbitMq**
* 集成 **spring-redis**
* 集成 **jjwt**
* 集成 **jasypt**
* 集成 **httpClient**
* 集成 **poi**

## 项目的目的
> 为企业级开发提供标准，以java为主，spring全家桶为基础，idea为开发利器

## spring-mail 使用 
```groovy
dependencies {
	// spring-boot-starter-mail
	compile "org.springframework.boot:spring-boot-starter-mail"
}
```
```yaml
spring:
    mail:
        host: smtp.qq.com # 邮箱服务器地址
        port: 465 # 邮箱服务器端口号
        username: ENC@[4FmaVNBbiNyVZEF2cDBwGml4ufMnYB6wEMHrBTDPg8MdYWUPlgV0uRyqwfttlaIOA6jl8DpSABXJO7QBVBbkWQ==] # 邮箱账号
        password: ENC@[bc4ClJDP6nX+XWTtZkBbXPCBlOvTqaWIVogQebWagYJCAO1WBZE2qkTstMp859nCrumGTxboQpNhywo75hPIFQ==] # 邮箱密码
        default-encoding: UTF-8 # 邮件字符集
        protocol: smtp # 邮件发送协议
        test-connection: false # 是否进行连接测试
        properties:
          mail.smtp.ssl.enable: true # 启动ssl协议访问
          mail.smtp.connectiontimeout: 10000 # 设置连接超时
          mail.smtp.timeout: 10000 # 设置超时
          mail.smtp.writetimeout: 10000 # 设置写入超时
```
```java
class demo{
    public void demo(){
        EmailUtils.sendNormalEmail("toEmail","normalContext","subject");
    }
}
```

## spring-jdbc 使用  
```groovy
dependencies {
	// spring-boot-starter-data-jpa
	compile "org.springframework.boot:spring-boot-starter-data-jpa"    
}
```
```yaml
spring:
  # 数据库配置
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://47.105.33.160:3306/aicallsystem
    username: root
    password: ENC@[R3ZE710H1U0nsxSr1x8vJKjD2rl83XEugZwVLOBkLIbrJekh0OE+7Vpsi2lvtpLP] #liu123456.
   
```
```java
class demo{
    public void demo(){
        Demo demo = SqlFactory.selectSql(Demo.class)
                        .select("column1", "column2", "column3")
                        .eq("column1", "demo")
                        .ltEq("column2",new Date())
                        .getOne();
    }
}
```
## 自动生成代码
> 基于java nio + spring freemarker + spring Resource 自动生成代码
```http request
POST http://localhost:8888/isxcode/generateCode
Content-Type: application/json

{
  "tableName": "user"
}
```
```yaml
isxcode:
  code-generate:
    project-path: com.isxcode.oxygen.demo # 模块地址,可以完全自定义
    paths:
      'controller-path': controller # 相对模块地址的controller地址
      'service-path': service # 相对模块地址的service地址
      'dao-path': dao # 相对模块地址的dao地址
      'entity-path': model.entity # 相对模块地址的entity地址
```
```java
class demo{
    
    /**
     * 通过表名自动生成controller/service/dao/entity文件
     *
     * @param codeDto 对象请求
     * @since 2020-01-08
     */
    @PostMapping("generateCode")
    public ResponseEntity<BaseResponse> generateCode(@RequestBody CodeDto codeDto) {

        FormatUtils.checkEmptyStr(codeDto.getTableName(), "tableName 不能为空");
        codeService.generateCode(codeDto.getTableName());
        return successResponse("自动代码生成成功", "");
    }
}
```