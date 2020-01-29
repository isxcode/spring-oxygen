package com.isxcode.oxygen.core.utils;

import com.isxcode.oxygen.exception.IsxcodeException;
import com.isxcode.oxygen.model.properties.SecurityProperties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加解密 工具类
 *
 * @author ispong
 * @version v0.1.0
 * @date 2019-11-28
 */
@Slf4j
@Component
public class EncryptUtils {

    private static Key keyAES;

    @Autowired
    public void setKeyAes(SecurityProperties securityProperties) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(securityProperties.getJwtSecurityKey().getBytes()));
        keyAES = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
    }

    /**
     * AES 加密
     *
     * @param data 被加密数据
     * @return 加密后数据
     * @since 2019-12-12
     */
    static String encryptAes(String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyAES);
            return Base64.getEncoder().encodeToString(Base64.getEncoder().encode(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new IsxcodeException("AES 加密失败");
        }

    }

    /**
     * AES 解密
     *
     * @param data 被解密数据
     * @return 解密后数据
     * @since 2019-12-12
     */
    static String decryptAes(String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keyAES);
            return new String(cipher.doFinal(Base64.getDecoder().decode(Base64.getDecoder().decode(data))), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new IsxcodeException("AES 解密失败");
        }

    }

}
