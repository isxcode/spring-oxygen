package com.isxcode.oxygen.core.freemarker;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.stream.Collectors;

/**
 * 转换类型工具类
 *
 * @author ispong
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

    /**
     * 自动生成随机数
     *
     * @param bit 需要生成几位随机数(最大16位)
     * @since 2019/10/10
     */
    public static String generateNumber(int bit){

        assert bit < 17 : "随机数生成不能超过16位";
        SecureRandom ng = new SecureRandom();
        byte[] randomBytes = new byte[8];
        ng.nextBytes(randomBytes);
        long msb = 0;
        for (byte randomByte : randomBytes) {
            msb = (msb << randomBytes.length) | (randomByte & 0xff);
        }
        return String.valueOf(msb).substring(1, 1 + bit);
    }

    public static String inputStreamToString(InputStream inputStream) {

        return new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))
                .lines().parallel().collect(Collectors.joining(System.lineSeparator()));
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
