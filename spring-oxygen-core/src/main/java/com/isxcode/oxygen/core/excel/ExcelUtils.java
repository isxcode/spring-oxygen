package com.isxcode.oxygen.core.excel;

import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.reflect.FieldBody;
import com.isxcode.oxygen.core.reflect.ReflectConstants;
import com.isxcode.oxygen.core.reflect.ReflectUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletResponse;
import java.awt.Color;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
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
import java.util.stream.Collectors;

import static org.apache.poi.xssf.usermodel.XSSFWorkbookType.XLSX;

/**
 * excel utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class ExcelUtils {

    /**
     * parse excel file to data list
     *
     * @param inputStream inputStream
     * @param targetClass targetClass
     * @param <A>         A
     * @return List[A]
     * @since 0.0.1
     */
    public static <A> List<A> parseFile(InputStream inputStream, Class<A> targetClass) throws OxygenException {

        List<A> result = new ArrayList<>();

        try {
            OPCPackage pkg = OPCPackage.open(inputStream);
            Workbook workbook = XSSFWorkbookFactory.createWorkbook(pkg);

            Sheet sheet = workbook.getSheetAt(0);
            int firstRowNum = sheet.getFirstRowNum();
            Row firstRow = sheet.getRow(firstRowNum);
            int firstColumnNum = firstRow.getFirstCellNum();
            int lastColumnNum = firstRow.getLastCellNum();
            List<String> rowColumnList = new ArrayList<>();
            for (int i = firstColumnNum; i < lastColumnNum; i++) {
                rowColumnList.add(firstRow.getCell(i).getStringCellValue());
            }

            List<FieldBody> fieldList = ReflectUtils.queryFields(targetClass);

            fieldList = fieldList.stream().filter(e -> e.getField().isAnnotationPresent(ExcelType.class)).collect(Collectors.toList());

            Map<Integer, FieldBody> columnMap = new HashMap<>(fieldList.size());
            for (String metaRowName : rowColumnList) {
                for (FieldBody metaFieldBody : fieldList) {
                    Field metaField = metaFieldBody.getField();
                    if (metaRowName.equals(metaField.getAnnotation(ExcelType.class).cellName())) {
                        columnMap.put(rowColumnList.indexOf(metaRowName) + firstColumnNum, metaFieldBody);
                        break;
                    }
                }
            }

            firstRowNum++;
            while (sheet.getRow(firstRowNum) != null) {

                A target = ReflectUtils.newInstance(targetClass);

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

                    Method writeMethod = fieldBody.getWriteMethod();
                    switch (fieldBody.getClassName()) {
                        case ReflectConstants.STRING:
                            writeMethod.invoke(target, metaCell.getStringCellValue());
                            break;
                        case ReflectConstants.LOCAL_DATE_TIME:
                            writeMethod.invoke(target, metaCell.getLocalDateTimeCellValue());
                            break;
                        case ReflectConstants.DOUBLE:
                            writeMethod.invoke(target, metaCell.getNumericCellValue());
                            break;
                        case ReflectConstants.INTEGER:
                            String cellValue = String.valueOf(metaCell.getNumericCellValue());
                            cellValue = cellValue.substring(0, cellValue.indexOf("."));
                            writeMethod.invoke(target, Integer.parseInt(cellValue));
                            break;
                        case ReflectConstants.DATE:
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
        } catch (IOException | IllegalAccessException | InvocationTargetException | InvalidFormatException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * write in web servlet response
     *
     * @param data     data
     * @param fileName fileName
     * @param response http response
     * @since 0.0.1
     */
    public static void generateToResponse(List<?> data, String fileName, HttpServletResponse response) {

        try {
            response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + URLEncoder.encode(fileName, String.valueOf(StandardCharsets.UTF_8)) + "." + XLSX.getExtension());
            response.setHeader(HttpHeaders.CONTENT_TYPE, XLSX.getContentType());
            try (OutputStream fileOut = response.getOutputStream()) {
                generateFile(data, fileOut);
            } catch (IOException e) {
                throw new OxygenException(e.getMessage());
            }
        } catch (UnsupportedEncodingException e) {
            throw new OxygenException(e.getMessage());
        }

    }

    /**
     * write in output stream
     *
     * @param data         data
     * @param outputStream outputStream
     * @since 0.0.1
     */
    public static void generateFile(List<?> data, OutputStream outputStream) {

        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();

        try {

            if (data == null || data.isEmpty()) {
                workbook.write(outputStream);
                return;
            }

            List<FieldBody> fieldBodies = ReflectUtils.queryFields(data.get(0).getClass());

            // filter ExcelType.class
            fieldBodies = fieldBodies.stream().filter(e -> e.getField().isAnnotationPresent(ExcelType.class)).collect(Collectors.toList());

            // create table first row
            XSSFRow row = sheet.createRow(0);
            for (FieldBody metaFieldBody : fieldBodies) {

                Field metaField = metaFieldBody.getField();

                ExcelType annotation = metaField.getAnnotation(ExcelType.class);
                int cellIndex = annotation.cellIndex() == -1 ? fieldBodies.indexOf(metaFieldBody) : annotation.cellIndex();
                sheet.setColumnWidth(cellIndex, annotation.cellWidth());
                XSSFCell cell = row.createCell(cellIndex, CellType.STRING);

                // set cell name
                cell.setCellValue(annotation.cellName());

                // set cell color
                XSSFCellStyle colorStyle = workbook.createCellStyle();
                colorStyle.setFillForegroundColor(new XSSFColor(new Color(annotation.cellColor()[0], annotation.cellColor()[1], annotation.cellColor()[2]), new DefaultIndexedColorMap()));
                colorStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
                cell.setCellStyle(colorStyle);
            }

            // add data
            for (Object metaData : data) {
                XSSFRow metaRow = sheet.createRow(data.indexOf(metaData) + 1);
                for (FieldBody metaFieldBody : fieldBodies) {
                    Field metaField = metaFieldBody.getField();
                    if (metaField.isAnnotationPresent(ExcelType.class)) {
                        ExcelType annotation = metaField.getAnnotation(ExcelType.class);
                        int cellIndex = annotation.cellIndex() == -1 ? fieldBodies.indexOf(metaFieldBody) : annotation.cellIndex();

                        if (metaFieldBody.getReadMethod().invoke(metaData) == null) {
                            continue;
                        }

                        XSSFCell cell = metaRow.createCell(cellIndex);

                        // time style
                        XSSFCellStyle dateStyle = workbook.createCellStyle();
                        dateStyle.setDataFormat(workbook.getCreationHelper().createDataFormat().getFormat(annotation.cellDateFormat()));

                        // number style
                        XSSFCellStyle numberStyle = workbook.createCellStyle();
                        numberStyle.setDataFormat((workbook.createDataFormat().getFormat(annotation.cellDoubleFormat())));

                        Method readMethod = metaFieldBody.getReadMethod();
                        String dataStr = String.valueOf(readMethod.invoke(metaData));
                        switch (metaFieldBody.getClassName()) {
                            case ReflectConstants.STRING:
                                cell.setCellValue(dataStr);
                                break;
                            case ReflectConstants.DATE:
                                SimpleDateFormat dateFormat = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy");
                                cell.setCellValue(dateFormat.parse(dataStr));
                                cell.setCellStyle(dateStyle);
                                break;
                            case ReflectConstants.LOCAL_DATE_TIME:
                                cell.setCellValue(LocalDateTime.parse(dataStr.substring(0, dataStr.indexOf('.')), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss")));
                                cell.setCellStyle(dateStyle);
                                break;
                            case ReflectConstants.LOCAL_DATE:
                                cell.setCellValue(LocalDate.parse(dataStr.substring(0, dataStr.indexOf('.')), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
                                cell.setCellStyle(dateStyle);
                                break;
                            case ReflectConstants.DOUBLE:
                            case ReflectConstants.INTEGER:
                                cell.setCellValue(Double.parseDouble(dataStr));
                                cell.setCellStyle(numberStyle);
                                break;
                            default:
                                throw new OxygenException("not support class properties");
                        }
                    }
                }
            }

            workbook.write(outputStream);

        } catch (Exception e) {
            throw new OxygenException(e.getMessage());
        }
    }
}
