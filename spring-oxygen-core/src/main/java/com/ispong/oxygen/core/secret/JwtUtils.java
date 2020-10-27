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

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ispong.oxygen.core.exception.OxygenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT Marker
 *
 * @author ispong
 * @since 0.0.1
 */
public class JwtUtils {

    private static Key key;

    public void init() {
        JwtUtils.key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    }

    /**
     * jwt默认加密工具
     *
     * @param obj 传输对象
     * @param aesKey 自定义密钥
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(Object obj, String aesKey) throws OxygenException {

        Map<String, Object> claims = new HashMap<>(1);

        try {
            String encrypt = AesMarker.encrypt(aesKey, new ObjectMapper().writeValueAsString(obj));
            claims.put(SecretConstants.CLAIM_KEY, encrypt);

            return Jwts.builder()
                .signWith(key)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setId(String.valueOf(UUID.randomUUID()))
                .compact();
        } catch (JsonProcessingException e) {
            throw new OxygenException("jwt encrypt is wrong");
        }
    }

    /**
     * jwt加密工具
     *
     * @param obj 传输对象
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(Object obj) throws OxygenException {

        return encrypt(obj, SecretConstants.AES_KEY);
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
    public static <A> A decrypt(String jwtString, Class<A> claimClass) {

        return decrypt(SecretConstants.AES_KEY, jwtString, claimClass);
    }

    /**
     * jwt解密工具
     *
     * @param jwtString jwt
     * @param <A>       A
     * @param targetClass 目标class
     * @param aesKey 自定义密钥
     * @return claim
     * @since 2019-12-12
     */
    public static <A> A decrypt(String aesKey, String jwtString, Class<A> targetClass) {

        String claimStr = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwtString)
            .getBody()
            .get(SecretConstants.CLAIM_KEY, String.class);

        String targetJsonStr = AesMarker.decrypt(aesKey, claimStr);

        try {
            return new ObjectMapper().readValue(targetJsonStr, targetClass);
        } catch (JsonProcessingException e) {
            throw new OxygenException("Jwt decrypt is wrong");
        }
    }
}
