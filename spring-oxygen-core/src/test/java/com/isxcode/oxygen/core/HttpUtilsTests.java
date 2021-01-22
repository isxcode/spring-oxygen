package com.isxcode.oxygen.core;

import com.isxcode.oxygen.core.exception.OxygenException;
import com.isxcode.oxygen.core.http.HttpUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class HttpUtilsTests {

    @Test
    public void testPostHttp() {

        String url = "http://portal.definesys.com:30502/xdap-admin/msg/count/newMessage?timestamp=1607569924902";

        Map<String, String> headers = new HashMap<>();
        headers.put("xdaptenantid", "76714728949415937");
        headers.put("xdaptenantversion", "77404393242624000");
        headers.put("xdaptimestamp", "1607569924902");
        headers.put("xdaptoken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MDc1NzIxODUsImlhdCI6MTYwNzU2NDk4NSwieGRhcHVzZXJpZCI6IjEwMDEwNDYxNDczMjI0MDI1NzAyNCJ9.MkEVO4aM2DGmDeoZhoAm-SIcWl0zxww9FHIrl0UZJ6_816jSZQQhDfcJgU58nKwH_juj9SNSHXaih1W86WNiXg");
        headers.put("xdapversion", "1.0.3");

        try {
            System.out.println(HttpUtils.doPost(url, headers, "", String.class));
        } catch (OxygenException | IOException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void testGetHttp() {

        String url = "http://portal.definesys.com:30502/xdap-app/form/query/formConfigAndStatus?timestamp=1607570430133&formId=5fd03687543d7b3e8f107ac9&documentId=124457730072121344&type=DETAIL_FORM";

        Map<String, String> headers = new HashMap<>();
        headers.put("xdaptenantid", "76714728949415937");
        headers.put("xdaptenantversion", "77404394242624000");
        headers.put("xdaptimestamp", "1607570344954");
        headers.put("xdaptoken", "eyJ0eXAiOiJKV1QiLCJhbGciOiJIUzUxMiJ9.eyJleHAiOjE2MDc1NzIxODUsImlhdCI6MTYwNzU2NDk4NSwieGRhcHVzZXJpZCI6IjEwMDEwNDYxNDczMjI0MDI1NzAyNCJ9.MkEVO4aM2DGmDeoZhoAm-SIcWl0zxww9FHIrl0UZJ6_816jSZQQhDfcJgU58nKwH_juj9SNSHXaih1W86WNiXg");
        headers.put("xdapversion", "1.0.3");

        try {
            System.out.println(HttpUtils.doGet(url, headers, String.class));
        } catch (OxygenException e) {
            System.out.println(e.getMessage());
        }
    }
}
