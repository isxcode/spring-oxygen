package com.isxcode.oxygen.autocode;

import com.isxcode.oxygen.autocode.model.TableColumnInfo;
import org.springframework.util.ResourceUtils;

import java.io.FileNotFoundException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static java.util.regex.Pattern.compile;

/**
 * 自动生成代码工具类
 *
 * @author ispong
 * @version v0.1.0
 */
public class CodeUtils {


    /**
     * 将controller.java.ftl 转换成controller
     *
     * @param templateFileName 模板名称
     * @since 2020-01-08
     */
    public static String parseTemplateFileName(String templateFileName) {

        return templateFileName.split("\\.")[0];
    }

    /**
     * 将controller.java.ftl 转换成java
     *
     * @param templateFileName 模板名称
     * @since 2020-01-08
     */
    public static String parseTemplateFileType(String templateFileName) {

        return templateFileName.split("\\.")[1];
    }

    /**
     * 通过配置文件生成模板地址
     *
     * @since 2020-01-08
     */
    public static Path getTemplatePath(String templatePath) {

        try {
            return Paths.get(ResourceUtils.getURL(ResourceUtils.CLASSPATH_URL_PREFIX + templatePath).toString().replace(ResourceUtils.FILE_URL_PREFIX + "/", ""));
        } catch (FileNotFoundException e) {
            throw new AutocodeException("无法获取Template路径");
        }
    }

    /**
     * com.main.java 转 com/main/java
     *
     * @param pathStr com.main.java
     * @return 路径
     * @since 2020-01-09
     */
    public static String parseClassPathToPath(String pathStr) {

        return pathStr.replace(".", "/") + "/";
    }

    /**
     * 生成需要导入的jar包
     *
     * @param fieldList 数据库字段列表
     * @return 返回需要导入的包
     * @since 2020-01-09
     */
    public static List<String> generateImportPackages(List<TableColumnInfo> fieldList) {

        List<String> packages = new ArrayList<>();
        for (TableColumnInfo metaColumn : fieldList) {

            switch (metaColumn.getType()) {
                case "LocalDateTime":
                    packages.add("java.time.LocalDateTime");
                    break;
                case "LocalDate":
                    packages.add("java.time.LocalDate");
                    break;
                case "BigDecimal":
                    packages.add("java.math.BigDecimal");
                    break;
                default:
            }
        }
        return packages.stream().distinct().collect(Collectors.toList());
    }

    /**
     * 将数据库类型转换成java的类型
     *
     * @param dataType 数据库类型
     * @return java类型
     * @since 2020-01-09
     */
    public static String parseDataType(String dataType) {

        // 取消所有的括号
        Pattern pattern = compile("\\(.*?\\)");
        String dataStr = pattern.matcher(dataType).replaceAll("");

        switch (dataStr){
            case "int":
                return "Integer";
            case "datetime":
                return "LocalDateTime";
            case "date":
                return "LocalDate";
            case "double":
                return "Double";
            case "timestamp":
                return "Long";
            case "decimal":
                return "BigDecimal";
             default:
                 return "String";
        }

    }

    /**
     * 下划线转小驼峰
     *
     * @param lineStr 包含下滑线字符串
     * @return 下划线写法
     * @since 2019-12-24
     */
    public static String lineToHump(String lineStr) {

        StringBuffer humpStrBuff = new StringBuffer();
        lineStr = lineStr.toLowerCase();
        Matcher matcher = compile("_(\\w)").matcher(lineStr);
        while (matcher.find()) {
            matcher.appendReplacement(humpStrBuff, matcher.group(1).toUpperCase());
        }
        return matcher.appendTail(humpStrBuff).toString();
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @since 2020-01-13
     */
    public static String upperFirstCase(String str) {

        return str.substring(0, 1).toUpperCase() + str.substring(1);
    }

    /**
     * 首字母大写
     *
     * @param str 字符串
     * @since 2020-01-13
     */
    public static String lowerFirstCase(String str) {

        return str.substring(0, 1).toLowerCase() + str.substring(1);
    }
}
