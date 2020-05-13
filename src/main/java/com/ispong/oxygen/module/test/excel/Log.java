package com.ispong.oxygen.module.test.excel;

import com.ispong.oxygen.utils.excel.ExcelType;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

@Data
public class Log {

    @ExcelType(cellName = "接口名称", cellWidth = 4000, cellIndex = 1, cellColor={230,10,10})
    private String apiName;

    @ExcelType(cellName = "请求体", cellWidth = 10001, cellIndex = 2)
    private String requestBody;

    @ExcelType(cellName = "响应体", cellWidth = 10001, cellIndex = 3,cellDoubleFormat = "0.0000")
    private Double responseBody;

    @ExcelType(cellName = "执行日期", cellWidth = 4000, cellIndex = 4, cellDateFormat = "yy/mm/dd")
    private LocalDateTime executeDate;

    public Double getResponseBody() {
        return 12.00;
    }
}
