package com.isxcode.oxygen.core.secret;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isxcode.oxygen.core.exception.OxygenException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * JWT utils
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
     * jwt encrypt
     *
     * @param obj    object
     * @param aesKey aesKey
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(String aesKey, Object obj) throws OxygenException {

        Map<String, Object> claims = new HashMap<>(1);

        try {

            String claimsStr = new ObjectMapper().writeValueAsString(obj);
            if (aesKey != null) {
                claimsStr = AesUtils.encrypt(aesKey, claimsStr);
            }
            claims.put(SecretConstants.CLAIM_KEY, claimsStr);

            return Jwts.builder()
                .signWith(key)
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setId(String.valueOf(UUID.randomUUID()))
                .compact();

        } catch (JsonProcessingException e) {

            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * jwt encrypt
     *
     * @param obj object
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(Object obj) throws OxygenException {

        return encrypt(null, obj);
    }

    /**
     * jwt decrypt
     *
     * @param jwtString   jwt
     * @param <A>         A
     * @param targetClass targetClass
     * @param aesKey      aesKey
     * @return A
     * @since 2019-12-12
     */
    public static <A> A decrypt(String aesKey, String jwtString, Class<A> targetClass) {

        String claimStr = Jwts.parserBuilder()
            .setSigningKey(key)
            .build()
            .parseClaimsJws(jwtString)
            .getBody()
            .get(SecretConstants.CLAIM_KEY, String.class);

        String targetJsonStr = claimStr;
        if (aesKey != null) {
            targetJsonStr = AesUtils.decrypt(aesKey, claimStr);
        }

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(targetJsonStr, targetClass);
        } catch (JsonProcessingException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * jwt decript
     *
     * @param jwtString  jwt
     * @param <A>        A
     * @param claimClass claimClass
     * @return A
     * @since 2019-12-12
     */
    public static <A> A decrypt(String jwtString, Class<A> claimClass) {

        return decrypt(null, jwtString, claimClass);
    }
}
