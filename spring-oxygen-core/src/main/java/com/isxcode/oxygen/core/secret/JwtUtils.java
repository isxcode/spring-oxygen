package com.isxcode.oxygen.core.secret;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.isxcode.oxygen.core.exception.OxygenException;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.JwtParserBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.*;

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
     * @param obj     object
     * @param aesKey  aesKey
     * @param jwtKey  jwtKey
     * @param minutes minutes
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(String aesKey, Object obj, String jwtKey, Integer minutes) throws OxygenException {

        Map<String, Object> claims = new HashMap<>(1);

        try {

            String claimsStr = new ObjectMapper().writeValueAsString(obj);
            if (aesKey != null) {
                claimsStr = AesUtils.encrypt(aesKey, claimsStr);
            }
            claims.put(SecretConstants.CLAIM_KEY, claimsStr);

            JwtBuilder jwtBuilder = Jwts.builder();
            if (jwtKey == null) {
                jwtBuilder = jwtBuilder.signWith(key);
            } else {
                jwtBuilder = jwtBuilder.signWith(Keys.hmacShaKeyFor(Arrays.copyOf(jwtKey.getBytes(), 1 << 5)));
            }

             jwtBuilder
                .setClaims(claims)
                .setIssuedAt(new Date())
                .setId(String.valueOf(UUID.randomUUID()));

            if (minutes > 0) {
                Calendar calendar = Calendar.getInstance();
                calendar.setTime(new Date());
                calendar.add(Calendar.MINUTE, minutes);
                jwtBuilder = jwtBuilder.setExpiration(calendar.getTime());
            }

            return jwtBuilder.compact();

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

        return encrypt(null, obj, null, 0);
    }

    /**
     * jwt encrypt
     *
     * @param obj    object
     * @param jwtKey jwtKey 
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(Object obj, String jwtKey) throws OxygenException {

        return encrypt(null, obj, jwtKey, 0);
    }

    /**
     * jwt encrypt
     *
     * @param obj     object
     * @param jwtKey  jwtKey
     * @param minutes minutes 
     * @return jwt String
     * @since 2019-12-12
     */
    public static String encrypt(Object obj, String jwtKey,Integer minutes) throws OxygenException {

        return encrypt(null, obj, jwtKey, minutes);
    }

    /**
     * jwt decrypt
     *
     * @param jwtString   jwt
     * @param <A>         A
     * @param jwtKey      jwtKey
     * @param targetClass targetClass
     * @param aesKey      aesKey
     * @return A
     * @since 2019-12-12
     */
    public static <A> A decrypt(String aesKey, String jwtString, String jwtKey, Class<A> targetClass) {

        JwtParserBuilder jwtParserBuilder = Jwts.parserBuilder();

        if (jwtKey == null) {
            jwtParserBuilder = jwtParserBuilder.setSigningKey(key);
        }else{
            jwtParserBuilder = jwtParserBuilder.setSigningKey(Keys.hmacShaKeyFor(Arrays.copyOf(jwtKey.getBytes(), 1 << 5)));
        }

        String claimStr = jwtParserBuilder.build()
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

        return decrypt(null, jwtString, null, claimClass);
    }

    /**
     * jwt decript
     *
     * @param jwtString  jwt
     * @param <A>        A
     * @param claimClass claimClass
     * @param jwtKey     jwtKey
     * @return A
     * @since 2019-12-12
     */
    public static <A> A decrypt(String jwtString, String jwtKey, Class<A> claimClass) {

        return decrypt(null, jwtString, jwtKey, claimClass);
    }
}
