package com.ispong.oxygen.java.secret;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

public class Base64Test {

    public static void main(String[] args) {
        System.out.println(new String(Base64.getDecoder().decode("SGVsbG8sIG9zY2hpbmHvvIHlj4jmmK/kuIDlubTmmKXmnaXliLB+"), StandardCharsets.UTF_8));
    }
}
