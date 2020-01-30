/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.isxcode.oxygen.core.excel;

import com.isxcode.oxygen.core.exception.FileException;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.xssf.usermodel.*;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

/**
 * Excel生成工具
 *
 * @author ispong
 * @date 2019-10-30
 * @version v0.1.0
 */
@Slf4j
public class ExcelUtils {

    /**
     * 生成Excel表,适用于一切
     *
     * @param data    需要显示的数据
     * @return 返回spring资源
     * @since 2019-10-29
     */
    public static XSSFWorkbook generateExcel(List<?> data) {

        // 判断数据是否为空
        if (data.isEmpty()) {
            throw new FileException("数据为空");
        }

        // 创建Excel对象
        XSSFWorkbook wb = new XSSFWorkbook();
        // 创建新的一页
        XSSFSheet sheet = wb.createSheet("new sheet");

        // 设置时间样式
        CreationHelper createHelper = wb.getCreationHelper();
        XSSFCellStyle dateStyle = wb.createCellStyle();
        dateStyle.setDataFormat(createHelper.createDataFormat().getFormat("yyyy/mm/dd/ hh:mm:ss"));

        // 设置背景颜色
        XSSFCellStyle cellStyle = wb.createCellStyle();
        cellStyle.setFillForegroundColor(new XSSFColor(new java.awt.Color(211, 206, 202), new DefaultIndexedColorMap()));
        cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);

        // 获取注解中的属性
        Object obj = data.get(0);
        List<ExcelType> fieldAnnotationList = ReflectUtils.getAnnotations(obj.getClass(), ExcelType.class);

        // 设置表头
        XSSFRow row = sheet.createRow(0);
        for (ExcelType type : fieldAnnotationList) {
            XSSFCell topCell = row.createCell(fieldAnnotationList.indexOf(type));
            topCell.setCellValue(type.cellName());
            topCell.setCellStyle(cellStyle);
            // 设置单元格宽度
            sheet.setColumnWidth(fieldAnnotationList.indexOf(type), type.cellWidth() * (1 << 8));
        }

        // 反射构建excel,支持String/LocalDate/long
        Field[] declaredFields = obj.getClass().getDeclaredFields();
        List<Field> fields = Arrays.asList(declaredFields);
        for (Object metaData : data) {
            XSSFRow metaRow = sheet.createRow(data.indexOf(metaData) + 1);
            for (Field metaField : fields) {
                try {
                    XSSFCell cell = metaRow.createCell(fields.indexOf(metaField));
                    // 开启获取元素
                    metaField.setAccessible(true);
                    // 反射获取对应属性值
                    switch (metaField.getType().getName()) {
                        case "long":
                            cell.setCellValue(metaField.getLong(metaData));
                            break;
                        case "java.lang.String":
                            cell.setCellValue(String.valueOf(metaField.get(metaData)));
                            if (metaField.get(metaData) == null) {
                                cell.setCellValue("");
                            }
                            break;
                        case "java.time.LocalDateTime":
                            if (metaField.get(metaData) != null) {
                                String dateStr = String.valueOf(metaField.get(metaData)).replace("T", " ");
                                DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
                                LocalDateTime date = LocalDateTime.parse(dateStr, timeFormatter);
                                cell.setCellValue(date);
                                cell.setCellStyle(dateStyle);
                            }
                            break;
                        default:
                            log.info("存在不支持格式");

                    }
                } catch (IllegalAccessException e) {
                    log.info("无法取出属性值");
                }
            }
        }

        return wb;
    }

}
