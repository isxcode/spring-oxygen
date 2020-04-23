package com.ispong.oxygen.module.jump;

import org.apache.http.HttpHeaders;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

/**
 * 为跨域写的一个跳板
 *
 * @author ispong
 * @since 0.0.1
 */
@RestController
public class JumpController {

    @RequestMapping("/cors")
    public String jumpForCors(@RequestParam("url") String url) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader(HttpHeaders.USER_AGENT, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/81.0.4044.113 Safari/537.36");
        return EntityUtils.toString(httpClient.execute(httpGet).getEntity());
    }

}
