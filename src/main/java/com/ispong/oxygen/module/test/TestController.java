package com.ispong.oxygen.module.test;

import com.ispong.oxygen.exception.OxygenException;
import com.ispong.oxygen.module.log.LogEntity;
import com.ispong.oxygen.module.log.LogRepository;
import com.ispong.oxygen.module.test.excel.Dog;
import com.ispong.oxygen.module.test.excel.Log;
import com.ispong.oxygen.utils.excel.ExcelUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 测试类中心
 *
 * @author ispong
 * @since 0.0.1
 */
@RestController
@RequestMapping("test")
public class TestController {

    private final LogRepository logRepository;

    public TestController(LogRepository logRepository) {
        this.logRepository = logRepository;
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

}