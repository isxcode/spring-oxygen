package com.isxcode.ispring;

import com.isxcode.ispring.utils.DateUtils;
import com.isxcode.ispring.utils.EncryptUtils;
import com.isxcode.ispring.utils.FormatUtils;
import org.junit.Test;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.FileSystem;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
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

                List<String> list = Arrays.asList("controller", "service", "entity", "dao");

                System.out.println(String.join(",", list));

//                Date date = DateUtils.parseDateTimeStrToDate("2019-12-16 00:00:02");
//                System.out.println("now" + date);
//                System.out.println(DateUtils.getLastWeekEnd(date, "23:59:59"));
        }
}
