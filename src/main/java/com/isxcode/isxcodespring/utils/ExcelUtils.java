package com.isxcode.isxcodespring.utils;

import com.isxcode.isxcodespring.exception.FileException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.streaming.SXSSFCell;
import org.apache.poi.xssf.streaming.SXSSFRow;
import org.apache.poi.xssf.streaming.SXSSFSheet;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import java.io.IOException;
import java.lang.reflect.Field;
import java.util.Arrays;
import java.util.List;

@Slf4j
public class ExcelUtils {

    /**
     * 生成Excel表,适用于一切
     *
     * @param columns 每列名称
     * @param data 需要显示的数据
     * @param meta 被认证对象
     * @return 返回spring资源
     * @since 2019-10-29
     */
    public static Resource generateExcel(String[] columns, List data,Class<?> meta) {

        SXSSFWorkbook wb = new SXSSFWorkbook();
        SXSSFSheet sheet = wb.createSheet("new sheet");
        SXSSFRow row = sheet.createRow(0);
        // 设置表头样式
        CellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(IndexedColors.ORANGE.getIndex());
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
        // 创建表头
        List<String> columnList = Arrays.asList(columns);
        for (String metaColumn : columnList) {
            SXSSFCell cell = row.createCell(columnList.indexOf(metaColumn));
            cell.setCellValue(metaColumn);
            cell.setCellStyle(cellStyle);
            sheet.setColumnWidth(columnList.indexOf(metaColumn), metaColumn.getBytes().length * 256);
        }
        // 解析构建表
        Field[] declaredFields = meta.getDeclaredFields();
        List<Field> fields = Arrays.asList(declaredFields);
        for (Object metaData : data) {
            SXSSFRow metaRow = sheet.createRow(data.indexOf(metaData)+1);
            for (Field metaField : fields) {
                try {
                    SXSSFCell cell = metaRow.createCell(fields.indexOf(metaField));
                    metaField.setAccessible(true);
                    Object metaValue = metaField.get(metaData);
                    if ("long".equals(metaField.getType().getName())) {
                        cell.setCellValue(Long.parseLong(String.valueOf(metaValue)));
                    }
                    if ("java.lang.String".equals(metaField.getType().getName())) {
                        cell.setCellValue(String.valueOf(metaValue));
                    }
                    if ("java.time.LocalDateTime".equals(metaField.getType().getName())) {
                        cell.setCellValue(String.valueOf(metaValue).replace("T"," "));
                    }
                } catch (IllegalAccessException e) {
                    log.info("无法取出属性值");
                }
            }
        }
        // 写入resource文件
        try {
            FileSystemResource resource = new FileSystemResource("ExcelTemp");
            wb.write(resource.getOutputStream());
            return resource;
        } catch (IOException e) {
            throw new FileException("文件读取异常");
        }

    }

}
