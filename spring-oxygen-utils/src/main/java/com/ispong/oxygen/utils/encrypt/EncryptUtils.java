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
package com.ispong.oxygen.utils.encrypt;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ispong.oxygen.utils.exception.CoreException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import lombok.NonNull;
import lombok.SneakyThrows;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

/**
 * 加密工具类
 *
 * @author ispong
 * @since 0.0.1
 */
public class EncryptUtils {

    private static SecretKey aesSecretKey;

    private static Key jwtSecretKey = Keys.secretKeyFor(SignatureAlgorithm.HS256);

    static {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
            keyGenerator.init(256, new SecureRandom());
            aesSecretKey = keyGenerator.generateKey();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }

    /**
     * AES 加密工具
     *
     * @param key  密钥
     * @param data 机密数据
     * @return 加密后的数据
     * @since 0.0.1
     */
    public static String aesEncrypt(String key, String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Arrays.copyOf(key.getBytes(), 32), "AES"));
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("加密失败");
        }
    }

    /**
     * AES解密工具
     *
     * @param key  密钥
     * @param data 解密数据
     * @return 解密后的数据
     * @since 0.0.1
     */
    public static String aesDecrypt(String key, String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Arrays.copyOf(key.getBytes(), 32), "AES"));
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("解密失败");
        }
    }

    /**
     * 默认AES加密
     *
     * @param data 加密数据
     * @return 加密后的数据
     * @since 0.0.1
     */
    public static String aesEncrypt(String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, aesSecretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("加密失败");
        }

    }

    /**
     * 默认AES解密
     *
     * @param data 解密数据
     * @return 解密后的数据
     * @since 0.0.1
     */
    public static String aesDecrypt(String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, aesSecretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("解密失败");
        }

    }

    /**
     * RSA加密
     *
     * @param publicKey 公钥
     * @param data      加密数据
     * @return 加密后的数据
     * @since 0.0.1
     */
    public static String rsaEncrypt(String publicKey, String data) {

        try {
            PublicKey key = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("加密失败");
        }
    }

    /**
     * RSA解密工具
     *
     * @param privateKey 私钥
     * @param data       解密数据
     * @return 解密后的数据
     * @since 0.0.1
     */
    public static String rsaDecrypt(String privateKey, String data) {

        try {
            PrivateKey key = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            Cipher cipher = Cipher.getInstance("RSA");
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new CoreException("解密失败");
        }
    }

    /**
     * RSA公钥密钥生成工具
     *
     * @author ispong
     * @since 0.0.1
     */
    public static void generateRsaPairKey() {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA");
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            System.out.println("publicKey:" + Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
            System.out.println("privateKey:" + Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

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
                .signWith(jwtSecretKey)
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
                .signWith(jwtSecretKey)
                .setId(UUID.randomUUID().toString())
                .setClaims(claims)
                .setIssuedAt(new Date())
                .compact();
    }

    /**
     * jwt解密工具
     *
     * @param jwtString jwt
     * @param <A>       A
     * @param claimClass claimClass
     * @return claim
     * @since 2019-12-12
     */
    @SneakyThrows
    public static <A> A jwtDecrypt(String jwtString, @NonNull Class<A> claimClass) {

        return new ObjectMapper().readValue(
            EncryptUtils.aesDecrypt(String.valueOf(
                Jwts.parser()
                    .setSigningKey(jwtSecretKey)
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
    @SneakyThrows
    public static <A> A jwtDecrypt(String key, String jwtString, @NonNull Class<A> claimClass) {

        return new ObjectMapper().readValue(
            EncryptUtils.aesDecrypt(key, String.valueOf(
                Jwts.parser()
                    .setSigningKey(jwtSecretKey)
                    .parseClaimsJws(jwtString)
                    .getBody()
                    .get("claim"))), claimClass);
    }

}
