package com.isxcode.ispring;

import org.junit.Test;
import freemarker.template.*;

import java.util.*;
import java.io.*;


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


                for (int i = 0; i < 10; i++) {
                        if (i == 5) {
                                continue;
                        }
                        System.out.println(i);
                }
        }
}
