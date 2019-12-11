package com.isxcode.ispring;

import com.isxcode.ispring.utils.DateUtils;
import com.isxcode.ispring.utils.FormatUtils;
import org.junit.Test;

import java.util.Date;


/**
 * 前提是项目没有错,独立的测试环境
 *
 * @author ispong
 * @date 2019-11-15
 * @version v0.1.0
 */
public class DemoTest {

        @Test
        public void testOne() throws Exception {

                String dateStr = "2019-12-01 12:00:00";
                System.out.println(DateUtils.addCustomDayNum(DateUtils.parseDateTimeStrToDate(dateStr), 31));
        }
}
