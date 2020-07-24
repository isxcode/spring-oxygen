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
package com.ispong.oxygen.core.secret;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;
import lombok.SneakyThrows;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt加密算法
 *
 * @author ispong
 * @since 0.0.1
 */
public class JwtMarker {

    /**
     * jwt默认加密工具
     *
     * @param obj 传输对象
     * @return jwt String
     * @since 2019-12-12
     */
    @SneakyThrows
    public static String jwtEncrypt(Object obj) {

        Map<String, Object> claims = new HashMap<>(1);
        claims.put("claim", aesEncrypt(new ObjectMapper().writeValueAsString(obj)));

        return Jwts.builder()
            .signWith(JWT_SECRET_KEY)
            .setId(UUID.randomUUID().toString())
            .setClaims(claims)
            .setIssuedAt(new Date())
            .compact();
    }

    /**
     * jwt加密工具
     *
     * @param key 密钥
     * @param obj 传输对象
     * @return jwt String
     * @since 2019-12-12
     */
    @SneakyThrows
    public static String jwtEncrypt(String key, Object obj) {

        Map<String, Object> claims = new HashMap<>(1);
        claims.put("claim", aesEncrypt(key, new ObjectMapper().writeValueAsString(obj)));

        return Jwts.builder()
            .signWith(JWT_SECRET_KEY)
            .setId(UUID.randomUUID().toString())
            .setClaims(claims)
            .setIssuedAt(new Date())
            .compact();
    }

    /**
     * jwt解密工具
     *
     * @param jwtString  jwt
     * @param <A>        A
     * @param claimClass claimClass
     * @return claim
     * @since 2019-12-12
     */
    @SneakyThrows
    public static <A> A jwtDecrypt(String jwtString, @NonNull Class<A> claimClass) {

        return new ObjectMapper().readValue(
            EncryptMarker.aesDecrypt(String.valueOf(
                Jwts.parser()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(jwtString)
                    .getBody()
                    .get("claim"))), claimClass);
    }

    /**
     * jwt解密工具
     *
     * @param jwtString  jwt
     * @param key        公钥
     * @param claimClass 数据对象
     * @param <A>        A
     * @return claim
     * @since 2019-12-12
     */
    public static <A> A jwtDecrypt(String key, String jwtString, @NonNull Class<A> claimClass) {

        return new ObjectMapper().readValue(
            EncryptMarker.aesDecrypt(key, String.valueOf(
                Jwts.parserBuilder()
                    .setSigningKey(JWT_SECRET_KEY)
                    .parseClaimsJws(jwtString)
                    .getBody()
                    .get("claim"))), claimClass);
    }

}
