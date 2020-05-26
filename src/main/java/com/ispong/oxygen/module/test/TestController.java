package com.ispong.oxygen.module.test;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.ispong.oxygen.exception.OxygenException;
import com.ispong.oxygen.flysql.Flysql;
import com.ispong.oxygen.flysql.common.BaseController;
import com.ispong.oxygen.flysql.common.BaseResponse;
import com.ispong.oxygen.module.log.LogEntity;
import com.ispong.oxygen.module.log.LogRepository;
import com.ispong.oxygen.module.test.excel.Dog;
import com.ispong.oxygen.module.test.excel.Log;
import com.ispong.oxygen.module.test.jackson.People;
import com.ispong.oxygen.module.test.user.Users;
import com.ispong.oxygen.utils.email.EmailUtils;
import com.ispong.oxygen.utils.excel.ExcelUtils;
import com.ispong.oxygen.utils.validation.PhoneCodeReq;
import com.ispong.oxygen.utils.validation.PhoneCodeRes;
import com.ispong.oxygen.utils.validation.ValidationUtils;
import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.*;

/**
 * 测试类中心
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
@RestController
@RequestMapping("test")
public class TestController extends BaseController {

    private final LogRepository logRepository;

    public TestController(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     * 查询所有的日志
     *
     * @since 0.0.1
     */
    @GetMapping("/users")
    public ResponseEntity<BaseResponse<List<Users>>> users() {

        return successResponse("查询日志成功", Flysql.select(Users.class,"pluto").query());
    }

    /**
     * 查询所有的日志
     *
     * @since 0.0.1
     */
    @GetMapping("/logs")
    public ResponseEntity<BaseResponse<List<LogEntity>>> logs() {

        return successResponse("查询日志成功", Flysql.select(LogEntity.class).query());
    }

    /**
     * 解析excel文件,并储存数据
     *
     * @param file excel文件
     * @since 0.0.1
     */
    @PostMapping("/parseExcel")
    public List<Dog> parseExcel(@RequestParam("file") MultipartFile file) {

        try {
            return ExcelUtils.parseExcel(file.getInputStream(), Dog.class);
        } catch (IOException e) {
            throw new OxygenException("[excelUtils] 文件读取失败");
        }

    }

    /**
     * 取出数据 并导出excel文件
     *
     * @param response 响应体
     * @since 0.0.1
     */
    @GetMapping("/generateExcel")
    public void generateExcel(HttpServletResponse response) {

        List<LogEntity> logEntities = logRepository.queryLog();
        List<Log> logs = new ArrayList<>();
        for (LogEntity metaLog : logEntities) {
            Log log = new Log();
            BeanUtils.copyProperties(metaLog, log);
            logs.add(log);
        }

        ExcelUtils.generateExcel(Log.class, logs, "测试文件", response);
    }


    @SneakyThrows
    @GetMapping("/jackson")
    public People jackson() {

        String value = "{\n" +
            "            \"startDate\":\"2020-12-01 12:00:00\",\n" +
            "            \"userId\":\"hhhh433adsfasdadsfasfdf3asdfasdf33a\"\n" +
            "        }";

        // string 转 json
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
        return objectMapper.readValue(value, People.class);
    }

    /**
     * 发送短信接口测试接口
     *
     * @since 0.0.1
     */
    @GetMapping("sendPhoneCode")
    public void sendPhoneCode() {

        PhoneCodeReq phoneCodeReq = new PhoneCodeReq();
        phoneCodeReq.setPhone("15161739808");
        phoneCodeReq.setMsg("测试发送短信接口");
        PhoneCodeRes phoneCodeRes = ValidationUtils.sendPhoneCode(phoneCodeReq);
        log.info("发送成功" + phoneCodeRes.toString());

    }

    /**
     * 发送邮件接口测试接口
     *
     * @since 0.0.1
     */
    @GetMapping("sendEmail")
    public void sendEmail() {

        EmailUtils.sendNormalEmail("ispong@outlook.com", "邮件内容", "邮件主题");
    }

    @SneakyThrows
    @GetMapping("freemarker")
    public String freemarker() {

        Configuration configuration = new Configuration(Configuration.VERSION_2_3_27);
        configuration.setTemplateLoader(new StringTemplateLoader());

        configuration.setDefaultEncoding("UTF-8");
        String templateName = "messageTemplate";
        String templateValue = "hello,${name},我今年,${age}岁.";
        Template template = new Template("", templateValue, configuration);
        Map<String, Object> root = new HashMap<>(4);
        root.put("name", "你好");
        root.put("age", 25);

        return FreeMarkerTemplateUtils.processTemplateIntoString(template, root);
    }

}