package com.isxcode.ispring;

import com.isxcode.ispring.utils.DateUtils;
import com.isxcode.ispring.utils.EncryptUtils;
import com.isxcode.ispring.utils.FormatUtils;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


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

                Date date = DateUtils.parseDateTimeStrToDate("2019-12-16 00:00:02");
                System.out.println("now" + date);
                System.out.println(DateUtils.getLastWeekEnd(date, "23:59:59"));
        }
}
