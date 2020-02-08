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
package com.isxcode.oxygen.core.encrypt;

import com.isxcode.oxygen.core.exception.UtilsException;
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
 * encrypt utils
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
@Component
public class EncryptUtils {

    private static Key keyAES;

    @Autowired
    public void setKeyAes(EncryptProperties encryptProperties) throws NoSuchAlgorithmException {

        KeyGenerator keyGenerator = KeyGenerator.getInstance("AES");
        keyGenerator.init(128, new SecureRandom(encryptProperties.getAesSecurityKey().getBytes()));
        keyAES = new SecretKeySpec(keyGenerator.generateKey().getEncoded(), "AES");
    }

    /**
     * AES encrypt
     *
     * @param data encrypt data
     * @return strData
     * @since 2019-12-12
     */
    public static String encryptAes(String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.ENCRYPT_MODE, keyAES);
            return Base64.getEncoder().encodeToString(Base64.getEncoder().encode(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8))));
        } catch (Exception e) {
            throw new UtilsException("AES encrypt fail");
        }

    }

    /**
     * AES decrypt
     *
     * @param data decrypt data
     * @return strData
     * @since 2019-12-12
     */
    public static String decryptAes(String data) {

        try {
            Cipher cipher = Cipher.getInstance("AES");
            cipher.init(Cipher.DECRYPT_MODE, keyAES);
            return new String(cipher.doFinal(Base64.getDecoder().decode(Base64.getDecoder().decode(data))), StandardCharsets.UTF_8);
        } catch (Exception e) {
            throw new UtilsException("AES decrypt fail");
        }

    }

}

