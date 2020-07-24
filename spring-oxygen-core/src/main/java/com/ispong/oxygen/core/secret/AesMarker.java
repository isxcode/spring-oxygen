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

import com.ispong.oxygen.core.exception.OxygenException;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.Base64;

/**
 * aes加密Marker
 *
 * @author ispong
 * @since 0.0.1
 */
public class AesMarker {

    /**
     * AES 加密工具
     *
     * @param key  密钥
     * @param data 机密数据
     * @return 加密后的数据
     * @since 0.0.1
     */
    public static String aesEncrypt(String key, String data) throws OxygenException{

        try {
            Cipher cipher = Cipher.getInstance(SecretConstants.AES);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Arrays.copyOf(key.getBytes(), 32), SecretConstants.AES));
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            throw new OxygenException("AES Encrypt is wrong");
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
            Cipher cipher = Cipher.getInstance(SecretConstants.AES);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Arrays.copyOf(key.getBytes(), 32), SecretConstants.AES));
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new OxygenException("AES Decrypt is wrong");
        }
    }
}
