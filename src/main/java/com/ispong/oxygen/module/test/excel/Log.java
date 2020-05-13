package com.ispong.oxygen.module.test.excel;

import com.ispong.oxygen.utils.excel.ExcelType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class Log {

    @ExcelType(cellName = "接口名称", cellWidth = 4000, cellIndex = 1, cellColorR = 230, cellColorG = 10, cellColorB = 10)
    private String apiName;

    @ExcelType(cellName = "请求体", cellWidth = 10001, cellIndex = 2)
    private String requestBody;

    @ExcelType(cellName = "响应体", cellWidth = 10001, cellIndex = 3)
    private String responseBody;

    @ExcelType(cellName = "执行日期", cellWidth = 4000, cellIndex = 4, cellDateFormat = "yy/mm/d")
    private LocalDateTime executeDate;
}
