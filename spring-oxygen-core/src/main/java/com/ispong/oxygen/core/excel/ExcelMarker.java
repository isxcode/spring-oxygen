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
package com.ispong.oxygen.core.excel;

import com.ispong.oxygen.core.exception.OxygenException;
import com.ispong.oxygen.core.reflect.ClassNameConstants;
import com.ispong.oxygen.core.reflect.FieldBody;
import com.ispong.oxygen.core.reflect.ReflectMarker;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.apache.poi.xssf.usermodel.XSSFWorkbookType.XLSX;

/**
 * Excel文件解析Marker
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class ExcelMarker {

    /**
     * 解析excel文件生成数组
     *
     * @param inputStream 输入文件流
     * @param targetClass 目标类型
     * @param <A>         泛型
     * @return List[A]
     * @since 0.0.1
     */
    public static <A> List<A> parse(InputStream inputStream, Class<A> targetClass) throws OxygenException {

        // 初始化返回对象
        List<A> result = new ArrayList<>();

        try {
            Workbook workbook = XSSFWorkbookFactory.create(inputStream);

            // 解析首行的对应字段
            Sheet sheet = workbook.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            int firstColumnNum = firstRow.getFirstCellNum();
            int lastColumnNum = firstRow.getLastCellNum();
            List<String> rowColumnList = new ArrayList<>();
            for (int i = firstColumnNum; i < lastColumnNum; i++) {
                rowColumnList.add(firstRow.getCell(i).getStringCellValue());
            }

            // 匹配首行对应的属性的write函数
            List<FieldBody> fieldList = ReflectMarker.queryFields(targetClass);
            Map<Integer, FieldBody> columnMap = new HashMap<>(fieldList.size());
            for (String metaRowName : rowColumnList) {
                for (FieldBody metaFieldBody : fieldList) {
                    Field metaField = metaFieldBody.getField();
                    if (metaField.isAnnotationPresent(ExcelType.class)) {
                        if (metaRowName.equals(metaField.getAnnotation(ExcelType.class).cellName())) {
                            columnMap.put(rowColumnList.indexOf(metaRowName) + firstColumnNum, metaFieldBody);
                            break;
                        }
                    }
                }
            }

            // 无限解析下面的数据
            firstRowNum++;
            while (sheet.getRow(firstRowNum) != null) {
                // 反射实例
                A target = ReflectMarker.newInstance(targetClass);

                Row nextRow = sheet.getRow(firstRowNum);
                for (int i = firstColumnNum; i < lastColumnNum; i++) {

                    Cell metaCell = nextRow.getCell(i);
                    if (metaCell == null) {
                        continue;
                    }

                    FieldBody fieldBody = columnMap.get(i);
                    if (fieldBody == null) {
                        continue;
                    }

                    // 写入数据
                    Method writeMethod = fieldBody.getWriteMethod();
                    switch (fieldBody.getClassName()) {
                        case ClassNameConstants.STRING:
                            writeMethod.invoke(target, metaCell.getStringCellValue());
                            break;
                        case ClassNameConstants.LOCAL_DATE_TIME:
                            writeMethod.invoke(target, metaCell.getLocalDateTimeCellValue());
                            break;
                        case ClassNameConstants.DOUBLE:
                            writeMethod.invoke(target, metaCell.getNumericCellValue());
                            break;
                        case ClassNameConstants.DATE:
                            writeMethod.invoke(target, metaCell.getDateCellValue());
                            break;
                        default:
                            throw new OxygenException("not support properties class");
                    }
                }
                result.add(target);
                firstRowNum++;
            }
            return result;
        } catch (IOException | IllegalAccessException | InvocationTargetException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * 通过传入的数据生成excel文件
     *
     * @param data     数据
     * @param fileName 文件名
     * @param response 返回
     * @since 0.0.1
     */
    public static void generate(List<?> data, String fileName, HttpServletResponse response) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        try {

            if (data == null || data.isEmpty()) {
                throw new OxygenException("data is empty");
            }

            List<FieldBody> fieldBodies = ReflectMarker.queryFields(data.get(0).getClass());

            // 绘制表头
            XSSFRow row = sheet.createRow(0);
            for (FieldBody metaFieldBody : fieldBodies) {

                Field metaField = metaFieldBody.getField();
                if (metaField.isAnnotationPresent(ExcelType.class)) {

                    ExcelType annotation = metaField.getAnnotation(ExcelType.class);
                    int cellIndex = annotation.cellIndex() == -1 ? fieldBodies.indexOf(metaFieldBody) : annotation.cellIndex();
                    sheet.setColumnWidth(cellIndex, annotation.cellWidth());
                    XSSFCell cell = row.createCell(cellIndex, CellType.STRING);
                    cell.setCellValue(annotation.cellName());

                    // 设置背景颜色
                    XSSFCellStyle colorStyle = workbook.createCellStyle();
                    colorStyle.setFillForegroundColor(new XSSFColor(new Color(annotation.cellColor()[0], annotation.cellColor()[1], annotation.cellColor()[2]), new DefaultIndexedColorMap()));
                    colorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                    cell.setCellStyle(colorStyle);
                }
            }

            // 装载数据
            for (Object metaData : data) {
                XSSFRow metaRow = sheet.createRow(data.indexOf(metaData) + 1);
                for (FieldBody metaFieldBody : fieldBodies) {
                    Field metaField = metaFieldBody.getField();
                    if (metaField.isAnnotationPresent(ExcelType.class)) {
                        ExcelType annotation = metaField.getAnnotation(ExcelType.class);
                        int cellIndex = annotation.cellIndex() == -1 ? fieldBodies.indexOf(metaFieldBody) : annotation.cellIndex();
                        // 读取数据填入
                        if (metaFieldBody.getReadMethod().invoke(metaData) == null) {
                            continue;
                        }

                        XSSFCell cell = metaRow.createCell(cellIndex);

                        // 时间格式
                        XSSFCellStyle dateStyle = workbook.createCellStyle();
                        dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(annotation.cellDateFormat()));

                        // 数字格式
                        XSSFCellStyle numberStyle = workbook.createCellStyle();
                        numberStyle.setDataFormat((workbook.createDataFormat().getFormat(annotation.cellDoubleFormat())));

                        Method readMethod = metaFieldBody.getReadMethod();
                        String dataStr = String.valueOf(readMethod.invoke(metaData));
                        switch (metaFieldBody.getClassName()) {
                            case ClassNameConstants.STRING:
                                cell.setCellValue(dataStr);
                                break;
                            case ClassNameConstants.DATE:
                                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                cell.setCellValue(dateFormat.parse(dataStr));
                                cell.setCellStyle(dateStyle);
                                break;
                            case ClassNameConstants.LOCAL_DATE_TIME:
                                cell.setCellValue(LocalDateTime.parse(dataStr.substring(0, dataStr.indexOf('.')), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                                cell.setCellStyle(dateStyle);
                                break;
                            case ClassNameConstants.LOCAL_DATE:
                                cell.setCellValue(LocalDate.parse(dataStr.substring(0, dataStr.indexOf('.')), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                cell.setCellStyle(dateStyle);
                                break;
                            case ClassNameConstants.DOUBLE:
                            case ClassNameConstants.INTEGER:
                                cell.setCellValue(Double.parseDouble(dataStr));
                                cell.setCellStyle(numberStyle);
                                break;
                            default:
                                throw new OxygenException("not support class properties");
                        }
                    }
                }
            }

            // 下载文件
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)) + "." + XLSX.getExtension());
            response.setHeader(HttpHeaders.CONTENT_TYPE, XLSX.getContentType());
            try (OutputStream fileOut = response.getOutputStream()) {
                workbook.write(fileOut);
            }

        } catch (Exception e) {
            throw new OxygenException(e.getMessage());
        }
    }
}
