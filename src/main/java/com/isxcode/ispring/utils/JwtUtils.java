package com.isxcode.ispring.utils;

import com.isxcode.ispring.config.PropertiesConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * jwt生成工具
 *
 * @author ispong
 * @date 2019-11-17
 * @version v0.1.0
 */
@Component
public class JwtUtils {

    private static Key secretKey;

    @Autowired
    public JwtUtils(PropertiesConfig propertiesConfig) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(256, new SecureRandom(propertiesConfig.getJwtSecret().getBytes()));
        secretKey = Keys.hmacShaKeyFor(keyGenerator.generateKey().getEncoded());
    }

    /**
     * Id (JWT ID)：编号
     * Issuer (issuer)：签发人
     * IssuedAt (Issued At)：签发时间
     * NotBefore (Not Before)：生效时间
     * Expiration (expiration time)：过期时间
     * sub (subject)：主题
     * aud (audience)：受众
     *
     * @since 2019-11-17
     */
    public static String encodeJwt() {

        // headers
        Map<String, Object> headers = new HashMap<>(2);
        headers.put("header1", "aaa");
        headers.put("header2", "bbb");

        // claim
        Map<String, Object> claims = new HashMap<>();
        claims.put("c1", "111");
        claims.put("c2", "222");

        // date
        return Jwts.builder().signWith(secretKey, SignatureAlgorithm.HS256)
                .setHeader(headers)
                .setClaims(claims)
                .setId(UUID.randomUUID().toString())
                .setIssuer("isxcode")
                .setIssuedAt(new Date())
                .setSubject("subject")
                .setAudience("you")
//                .setNotBefore()
//                .setExpiration()
                .compact();
    }


    public static String decodeJwt(String jwt) {
        Claims body = Jwts.parser()
//                    .deserializeJsonWith(new JacksonDeserializer(User))
                .setSigningKey(secretKey)
//                    .setAllowedClockSkewSeconds(seconds)
                .parseClaimsJws(jwt)
                .getBody();

        return null;
    }
}
