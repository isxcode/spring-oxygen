package com.isxcode.oxygen.core.date;

import java.time.LocalDate;

public class DateUtils {

    public static LocalDate parseLocalDateStr(String dateStr) {

        return LocalDate.parse(dateStr);
    }
}
