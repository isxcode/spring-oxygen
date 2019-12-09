package com.isxcode.ispring.utils;


/**
 * 转换类型工具类
 *
 * @author ispong
 * @date 2019/10/17
 * @version v0.1.0
 */
public class FormatUtils {

    /**
     * 将String类型的数字去除小数点
     *
     * @param doubleStr 有小数点的string
     * @return 去除小数点的string
     * @since 2019-12-09
     */
    public static String parseDoubleStr(String doubleStr) {

        return doubleStr.replaceAll("\\..*", "");
    }
}
