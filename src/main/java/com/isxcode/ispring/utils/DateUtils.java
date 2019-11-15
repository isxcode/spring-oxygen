package com.isxcode.ispring.utils;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;

/**
 * 时间处理工具
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-05
 */
public class DateUtils {

    private static LocalDateTime now = LocalDateTime.now();

    private static DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /**
     * 判断时间是否为当前天
     *
     * @param date 当前时间
     * @return 返回true为是  返回false为不是
     * @since 2019-11-05
     */
    public static Boolean isToday(LocalDateTime date) {

        return dateFormatter.format(date).equals(dateFormatter.format(now));
    }


    /**
     * 获取某月某周某一天
     *
     * @param date       输入的当前时间
     * @param monthIndex 第几月
     * @param weekIndex  第几周
     * @param dayIndex   第几天 （周日为0 周一为1 周二为2 依次叠加）
     * @return 具体时间
     * @since 2019-11-05
     */
    public static LocalDateTime getWeekDate(LocalDateTime date, int monthIndex, int weekIndex, int dayIndex) {

        return date.plusMonths(monthIndex)
                .plusWeeks(weekIndex)
                .with(TemporalAdjusters.previous(DayOfWeek.SUNDAY))
                .plusDays(dayIndex);
    }

}
