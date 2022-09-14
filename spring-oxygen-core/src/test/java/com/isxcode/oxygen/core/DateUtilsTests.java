package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.date.DateUtils;
import org.junit.jupiter.api.Test;

public class DateUtilsTests {

    @Test
    public void testUtils() {

        System.out.println(DateUtils.parseLocalDateStr("2020-12-12"));
    }
}

