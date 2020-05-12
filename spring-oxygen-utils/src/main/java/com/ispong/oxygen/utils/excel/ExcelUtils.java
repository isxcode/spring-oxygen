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
package com.ispong.oxygen.utils.excel;

import com.ispong.oxygen.utils.exception.CoreException;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbookFactory;
import org.springframework.beans.BeanUtils;

import java.beans.PropertyDescriptor;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

}
