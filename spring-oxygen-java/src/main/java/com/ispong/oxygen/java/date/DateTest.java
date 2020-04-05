package com.ispong.oxygen.java.date;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class DateTest {

    public static Date getLastSatTime() throws Exception {

        Calendar cal = Calendar.getInstance();
        cal.setTime(new SimpleDateFormat("yyyy-MM-dd").parse("2020-04-02"));
        // 获取今天是当前周的第几天
        int dayWeek = cal.get(Calendar.DAY_OF_WEEK);
        if (1 == dayWeek) {
            cal.add(Calendar.DAY_OF_MONTH, -1);
        }
        cal.setFirstDayOfWeek(Calendar.MONDAY);
        cal.add(Calendar.DATE, cal.getFirstDayOfWeek() - cal.get(Calendar.DAY_OF_WEEK) - 2);
        // 规定样式
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = dateFormat.format(cal.getTime()) + " 22:00:00";
        dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            return dateFormat.parse(dateStr);
        } catch (ParseException e) {
            throw new Exception("上周六数据初始化异常");
        }

    }

    public static void main(String[] args) throws Exception {
        
        System.out.println(String.valueOf(new Date()));
    }
}
