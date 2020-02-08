package com.isxcode.oxygen;

import org.junit.Test;

import java.text.SimpleDateFormat;


/**
 * 前提是项目没有错,独立的测试环境
 *
 * @author ispong
 * @version v0.1.0
 */
public class DemoTest {

        @Test
        public void testOne() throws Exception {

                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                System.out.println(format.parse("12:00"));

        }
}
