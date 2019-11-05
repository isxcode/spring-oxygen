package com.isxcode.isxcodespring;

import com.isxcode.isxcodespring.utils.DateUtils;
import org.junit.Test;

import java.time.LocalDateTime;

public class DemoTest {

    @Test
    public void testOne(){

        LocalDateTime now = LocalDateTime.now();
        System.out.println(DateUtils.getWeekDate(now, 1, 7));
    }
}
