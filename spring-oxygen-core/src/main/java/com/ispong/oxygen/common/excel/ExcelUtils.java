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
package com.ispong.oxygen.common.excel;

import com.ispong.oxygen.common.exception.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
 * excel文件 解析工具类
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class ExcelUtils {

    /**
     * 解析excel文件生成List
     *
     * @param inputStream 输入文件流
     * @param <A>         泛型
     * @param objClass    目标类型
     * @return A
     * @since 0.0.1
     */
    public static <A> List<A> parseExcel(InputStream inputStream, Class<A> objClass) {

        // 返回数据
        List<A> result = new ArrayList<>();

        try {
            Workbook workbook = XSSFWorkbookFactory.create(inputStream);
            Sheet sheet = workbook.getSheetAt(0);
            // 首行
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            int firstColumnNum = firstRow.getFirstCellNum();
            int lastColumnNum = firstRow.getLastCellNum();

            // 解析首行数据
            List<String> rowColumnList = new ArrayList<>();
            for (int i = firstColumnNum; i < lastColumnNum; i++) {
                rowColumnList.add(firstRow.getCell(i).getStringCellValue());
            }

            // 按照第一行的顺序储存属性
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(objClass);
            Map<Integer, PropertyDescriptor> columnMap = new HashMap<>(propertyDescriptors.length);
            for (String metaRowName : rowColumnList) {
                for (PropertyDescriptor propertyMeta : propertyDescriptors) {
                    if (!"class".equals(propertyMeta.getName())) {
                        Field metaField = propertyMeta.getReadMethod().getDeclaringClass().getDeclaredField(propertyMeta.getName());
                        if (metaField.isAnnotationPresent(ExcelType.class)) {
                            if (metaRowName.equals(metaField.getAnnotation(ExcelType.class).cellName())) {
                                columnMap.put(rowColumnList.indexOf(metaRowName) + firstColumnNum, propertyMeta);
                                break;
                            }
                        }
                    }
                }
            }

            // 无限解析下面的数据
            firstRowNum++;
            while (sheet.getRow(firstRowNum) != null) {

                // 反射实例
                A newInstance = objClass.getDeclaredConstructor().newInstance();
                Row nextRow = sheet.getRow(firstRowNum);
                for (int i = firstColumnNum; i < lastColumnNum; i++) {

                    Cell metaCell = nextRow.getCell(i);
                    if (metaCell == null) {
                        continue;
                    }
                    PropertyDescriptor propertyDescriptor = columnMap.get(i);
                    if (propertyDescriptor == null) {
                        continue;
                    }
                    Method writeMethod = propertyDescriptor.getWriteMethod();

                    // 读取数据
                    switch (propertyDescriptor.getPropertyType().getName()) {
                        case "java.lang.String":
                            writeMethod.invoke(newInstance, metaCell.getStringCellValue());
                            break;
                        case "java.time.LocalDateTime":
                            writeMethod.invoke(newInstance, metaCell.getLocalDateTimeCellValue());
                            break;
                        case "java.lang.Double":
                            writeMethod.invoke(newInstance, metaCell.getNumericCellValue());
                            break;
                        case "java.util.Date":
                            writeMethod.invoke(newInstance, metaCell.getDateCellValue());
                            break;
                        default:
                            throw new CoreException("[excelUtils]: 不支持此类型转换");
                    }

                }
                result.add(newInstance);
                firstRowNum++;
            }

            return result;
        } catch (IOException | NoSuchFieldException | NoSuchMethodException | InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new CoreException("excel文件解析异常");
        }
    }

    /**
     * 通过传入的数据生成excel文件
     *
     * @param <A>      泛型
     * @param data     数据
     * @param fileName 文件名
     * @param objClass 对象
     * @since 0.0.1
     */
    public static <A> void generateExcel(Class<A> objClass, List<A> data, String fileName, HttpServletResponse response) {

        try {
            XSSFWorkbook workbook = new XSSFWorkbook();
            XSSFSheet sheet = workbook.createSheet();

            // 画表头
            PropertyDescriptor[] propertyDescriptors = BeanUtils.getPropertyDescriptors(objClass);
            XSSFRow row = sheet.createRow(0);
            for (int i = 0; i < propertyDescriptors.length; i++) {
                if (!"class".equals(propertyDescriptors[i].getName())) {
                    Field metaField = propertyDescriptors[i].getReadMethod().getDeclaringClass().getDeclaredField(propertyDescriptors[i].getName());
                    if (metaField.isAnnotationPresent(ExcelType.class)) {
                        ExcelType annotation = metaField.getAnnotation(ExcelType.class);
                        int cellIndex = annotation.cellIndex() == -1 ? i : annotation.cellIndex();
                        sheet.setColumnWidth(cellIndex, annotation.cellWidth());
                        XSSFCell cell = row.createCell(cellIndex, CellType.STRING);
                        cell.setCellValue(annotation.cellName());
                        // 加颜色背景
                        XSSFCellStyle colorStyle = workbook.createCellStyle();
                        colorStyle.setFillForegroundColor(new XSSFColor(new Color(annotation.cellColor()[0], annotation.cellColor()[1], annotation.cellColor()[2]), new DefaultIndexedColorMap()));
                        colorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                        cell.setCellStyle(colorStyle);
                    }
                }
            }

            // 装载数据
            for (A metaData : data) {
                XSSFRow metaRow = sheet.createRow(data.indexOf(metaData) + 1);
                for (int i = 0; i < propertyDescriptors.length; i++) {
                    if (!"class".equals(propertyDescriptors[i].getName())) {
                        Field metaField = propertyDescriptors[i].getReadMethod().getDeclaringClass().getDeclaredField(propertyDescriptors[i].getName());
                        if (metaField.isAnnotationPresent(ExcelType.class)) {
                            ExcelType annotation = metaField.getAnnotation(ExcelType.class);
                            int cellIndex = annotation.cellIndex() == -1 ? i : annotation.cellIndex();
                            Method readMethod = propertyDescriptors[i].getReadMethod();
                            // 读取数据填入
                            if (readMethod.invoke(metaData) == null) {
                                continue;
                            }
                            // 时间格式
                            XSSFCell cell = metaRow.createCell(cellIndex);
                            XSSFCellStyle dateStyle = workbook.createCellStyle();
                            dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(annotation.cellDateFormat()));
                            switch (propertyDescriptors[i].getPropertyType().getName()) {
                                case "java.util.Date":
                                    SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                    cell.setCellValue(dateFormat.parse(String.valueOf(readMethod.invoke(metaData))));
                                    cell.setCellStyle(dateStyle);
                                    break;
                                case "java.time.LocalDateTime":
                                    cell.setCellValue(LocalDateTime.parse(String.valueOf(readMethod.invoke(metaData)), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSSSS")));
                                    cell.setCellStyle(dateStyle);
                                    break;
                                case "java.time.LocalDate":
                                    cell.setCellValue(LocalDate.parse(String.valueOf(readMethod.invoke(metaData)), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                    cell.setCellStyle(dateStyle);
                                    break;
                                case "java.lang.String":
                                    cell.setCellValue(String.valueOf(readMethod.invoke(metaData)));
                                    break;
                                case "java.lang.Double":
                                    cell.setCellValue(Double.parseDouble(String.valueOf(readMethod.invoke(metaData))));

                                    XSSFCellStyle formatStyle = workbook.createCellStyle();
                                    DataFormat format = workbook.createDataFormat();
                                    formatStyle.setDataFormat((format.getFormat(annotation.cellDoubleFormat())));
                                    cell.setCellStyle(formatStyle);
                                    break;
                                default:
                                    throw new CoreException("[excelUtils]: 不支持此类型转换");
                            }
                        }
                    }
                }
            }

            // 下载文件
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + java.net.URLEncoder.encode(fileName, StandardCharsets.UTF_8) + "." + XLSX.getExtension());
            response.setHeader(HttpHeaders.CONTENT_TYPE, XLSX.getContentType());
            try (OutputStream fileOut = response.getOutputStream()) {
                workbook.write(fileOut);
            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new CoreException("excel文件生成异常");
        }

    }
}
