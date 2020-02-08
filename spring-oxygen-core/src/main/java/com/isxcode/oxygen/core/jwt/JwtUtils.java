package com.isxcode.oxygen.core.jwt;

import com.alibaba.fastjson.JSON;
import com.isxcode.oxygen.core.encrypt.EncryptUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt生成 工具类
 *
 * @author ispong
 * @version v0.1.0
 */
@Component
public class JwtUtils {

    /**
     * 默认加密
     */
    private static Key secretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    /**
     * jwt 加密
     *
     * @param obj 传输对象
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encryptJwt(Object obj) {

        Map<String, Object> claims = new HashMap<>(1);
        claims.put("claim", EncryptUtils.encryptAes(JSON.toJSONString(obj)));

        return Jwts.builder()
                .signWith(secretKey)
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .compact();
    }

    /**
     * jwt 解密
     *
     * @param jwtString jwt
     * @return claim
     * @since 2019-12-12
     */
    public static <A> A decryptJwt(String jwtString, @NonNull Class<A> claimClass) {

        return JSON.parseObject(
                EncryptUtils.decryptAes(
                        String.valueOf(Jwts.parser()
                                .setSigningKey(secretKey)
                                .parseClaimsJws(jwtString)
                                .getBody()
                                .get("claim"))), claimClass);
    }
}
