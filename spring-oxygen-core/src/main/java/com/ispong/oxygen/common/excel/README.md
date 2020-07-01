### excel 工具使用说明

- 思路
导出excel
data -->  目标对象加注解 --> ExcelUtils.generateExcel -->  file

导入excel
file -->  ExcelUtils.parseExcel -->  目标对象加注解 --> data

### 使用说明文档

- 储存对象
```java
package com.ispong.oxygen.module.test.excel;

import com.ispong.oxygen.common.excel.ExcelType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Log {

    @ExcelType(cellName = "接口名称", cellWidth = 4000, cellIndex = 1, cellColorR = 230, cellColorG = 10, cellColorB = 10)
    private String apiName;

    @ExcelType(cellName = "请求体", cellWidth = 10001, cellIndex = 2)
    private String requestBody;

    @ExcelType(cellName = "响应体", cellWidth = 10001, cellIndex = 3)
    private String responseBody;

    @ExcelType(cellName = "执行日期", cellWidth = 4000, cellIndex = 4 , cellDateFormat = "yy/mm/d")
    private LocalDateTime executeDate;
}
```

- 工具调用
```java
public class Demo{
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
```