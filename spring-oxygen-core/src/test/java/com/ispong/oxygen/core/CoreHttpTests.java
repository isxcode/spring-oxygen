package com.ispong.oxygen.core;

import com.ispong.oxygen.core.http.HttpUtils;
import org.junit.jupiter.api.Test;

public class CoreHttpTests {

    @Test
    public void testDoGet() {

        String url = "http://pluto.definesys.com:30600/pluto/hello/test";
        String data = HttpUtils.doGet(url, String.class);
        System.out.println("data-->" + data);
    }
}
