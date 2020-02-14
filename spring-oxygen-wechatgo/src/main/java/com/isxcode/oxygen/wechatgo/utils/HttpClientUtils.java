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
package com.isxcode.oxygen.wechatgo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isxcode.oxygen.wechatgo.WechatgoException;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.Map;

/**
 * httpClient utils
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class HttpClientUtils {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * do get http request
     *
     * @param url           url
     * @param requestParams requestParams
     * @param responseClass responseClass
     * @param <A>           responseClass
     * @return responseObject
     * @since 2019-11-04
     */
    public static <A> A doGet(String url, Map<String, String> requestParams, Class<A> responseClass) {

        StringBuilder requestBody = new StringBuilder("?");
        requestParams.forEach((k, v) -> requestBody.append(k).append("=").append(v).append("&"));
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url + requestBody.toString()))
                .build();
        try {
            String responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            log.debug("get http request success" + responseBody);
            return new ObjectMapper().readValue(responseBody, responseClass);
        } catch (IOException | InterruptedException e) {
            throw new WechatgoException("get http request fail");
        }
    }

    /**
     * do post http request
     *
     * @param url           url
     * @param requestParams requestParams
     * @since 2019-11-04
     */
    public static void doPost(String url, String requestParams) {

        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .POST(HttpRequest.BodyPublishers.ofString(requestParams))
                .build();

        try {
            String responseBody = client.send(request, HttpResponse.BodyHandlers.ofString()).body();
            log.debug("post http request success:" + responseBody);
        } catch (IOException | InterruptedException e) {
            throw new WechatgoException("post http request fail");
        }
    }
}

