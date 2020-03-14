package com.ispong.oxygen;

import org.junit.Test;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;


/**
 * 前提是项目没有错,独立的测试环境
 *
 * @author ispong
 * @version v0.1.0
 */
public class DemoTest {

        @Test
        public void testOne() throws Exception {

                Map<String, Object> test = new HashMap<>();
                test.put("one", "something");
                System.out.println(1 << 30);
                SimpleDateFormat format = new SimpleDateFormat("HH:mm");

                System.out.println(format.parse("12:00"));

        }
}
