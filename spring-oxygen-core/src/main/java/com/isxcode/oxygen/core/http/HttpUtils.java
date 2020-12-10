package com.isxcode.oxygen.core.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.isxcode.oxygen.core.exception.OxygenException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.Map;

/**
 * http utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class HttpUtils {

    /**
     * http get
     *
     * @param url           url
     * @param headerParams  header
     * @param requestParams params
     * @param targetClass   targetClass
     * @param <A>           A
     * @return A
     * @since 0.0.1
     */
    public static <A> A doGet(String url, Map<String, String> requestParams, Map<String, String> headerParams, Class<A> targetClass) {

        StringBuilder requestBody = new StringBuilder("?");

        // add params
        if (requestParams != null) {
            requestParams.forEach((k, v) -> requestBody.append(k).append("=").append(v).append("&"));
        }

        // add headers
        HttpHeaders headers = new HttpHeaders();
        if (headerParams != null) {
            headerParams.forEach(headers::add);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>("", headers);
        try {
            return new RestTemplate().exchange(url + requestBody.toString(), HttpMethod.GET, requestEntity, targetClass).getBody();
        } catch (Exception e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * simple get
     *
     * @param <A>         A
     * @param targetClass targetClass
     * @return A
     * @since 0.0.1
     */
    public static <A> A doGet(String url, Class<A> targetClass) {

        return doGet(url, null, null, targetClass);
    }

    /**
     * simple get
     *
     * @param <A>          A
     * @param targetClass  targetClass
     * @param headerParams headerParams
     * @return A
     * @since 0.0.1
     */
    public static <A> A doGet(String url, Map<String, String> headerParams, Class<A> targetClass) {

        return doGet(url, null, headerParams, targetClass);
    }

    /**
     * post http
     *
     * @param <T>           T
     * @param url           url
     * @param requestParams requestParams
     * @param headerParams  headerParams
     * @param targetCls     targetCls
     * @return T
     * @throws IOException JACKSON EXCEPTION
     * @since 0.0.1
     */
    public static <T> T doPost(String url, Map<String, String> headerParams, Object requestParams, Class<T> targetCls) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        if (headerParams != null) {
            headerParams.forEach(headers::add);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(new ObjectMapper().writeValueAsString(requestParams), headers);
        try {
            return new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, targetCls).getBody();
        } catch (Exception e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * post http
     *
     * @param <T>           T
     * @param url           url
     * @param requestParams requestParams
     * @param targetCls     targetCls
     * @return T
     * @throws IOException JACKSON EXCEPTION
     * @since 0.0.1
     */
    public static <T> T doPost(String url, Object requestParams, Class<T> targetCls) throws IOException {

        return doPost(url, null, requestParams, targetCls);
    }

    /**
     * 执行post请求
     *
     * @param url           url
     * @param requestParams requestParams
     * @param headerParams  headerParams
     * @return data string
     * @throws IOException JACKSON EXCEPTION
     * @since 0.0.1
     */
    public static String doPost(String url, Map<String, String> headerParams, Object requestParams) throws IOException {

        HttpHeaders headers = new HttpHeaders();
        if (headerParams != null) {
            headerParams.forEach(headers::add);
        }

        HttpEntity<String> requestEntity = new HttpEntity<>(new ObjectMapper().writeValueAsString(requestParams), headers);
        return new RestTemplate().exchange(url, HttpMethod.POST, requestEntity, String.class).getBody();
    }

}

