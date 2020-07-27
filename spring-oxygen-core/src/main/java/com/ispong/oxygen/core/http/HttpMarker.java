/*
 * Copyright [2020] [ispong]
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ispong.oxygen.core.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * http Marker
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class HttpMarker {

    /**
     * get请求
     *
     * @param url           get请求地址
     * @param requestParams 请求参数
     * @param targetClass   目标Class
     * @param <A>           A
     * @return target
     * @throws IOException 访问失败
     * @since 0.0.1
     */
    public static <A> A doGet(String url, Map<String, String> requestParams, Class<A> targetClass) throws IOException {

        // 拼接get接口请求后缀
        StringBuilder requestBody = new StringBuilder("?");
        requestParams.forEach((k, v) -> requestBody.append(k).append("=").append(v).append("&"));

        // 发起请求
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpGet httpGet = new HttpGet(url + requestBody.toString());
        HttpEntity responseBody = httpClient.execute(httpGet).getEntity();
        return new ObjectMapper().readValue(EntityUtils.toString(responseBody), targetClass);
    }

    /**
     * post请求
     *
     * @param url           url
     * @param requestParams requestParams
     * @return post body str
     * @throws IOException 访问失败
     * @since 0.0.1
     */
    public static String doPost(String url, String requestParams) throws IOException {

        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost httpPost = new HttpPost(url);
        httpPost.setEntity(new StringEntity(requestParams, StandardCharsets.UTF_8));
        return EntityUtils.toString(httpClient.execute(httpPost).getEntity());
    }

}

