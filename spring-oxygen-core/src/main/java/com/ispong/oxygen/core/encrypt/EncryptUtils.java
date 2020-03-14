///*
// * Copyright [2020] [ispong]
// *
// * Licensed under the Apache License, Version 2.0 (the "License");
// * you may not use this file except in compliance with the License.
// * You may obtain a copy of the License at
// *
// *     http://www.apache.org/licenses/LICENSE-2.0
// *
// * Unless required by applicable law or agreed to in writing, software
// * distributed under the License is distributed on an "AS IS" BASIS,
// * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// * See the License for the specific language governing permissions and
// * limitations under the License.
// */
package com.ispong.oxygen.core.encrypt;

import com.ispong.oxygen.core.exception.CoreException;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.*;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Base64;

/**
 * 加密工具
 *
 * @author ispong
 * @version v0.1.0
 */
@Slf4j
public class EncryptUtils {

    public static Integer KEY_SIZE = 128;

    /**
     * 初始化key处理异常
     *
     * @param algorithmType 加密算法类型
     * @return KeyGenerator
     * @since 0.0.1
     */
    public static KeyGenerator initKeyGenerator(AlgorithmType algorithmType) {

        try {
            return KeyGenerator.getInstance(algorithmType.name());
        } catch (NoSuchAlgorithmException e) {
            throw new CoreException("不可能的异常");
        }
    }

    /**
     * 加密工具 String->String
     *
     * @param algorithmType 加密算法
     * @param keySize       key大小
     * @param data          待加密字符串
     * @return 加密后字符串
     * @since 2019-12-12
     */
    public static String encrypt(AlgorithmType algorithmType, Integer keySize, String slat, String data) {

        KeyGenerator keyGenerator = initKeyGenerator(algorithmType);
        keyGenerator.init(keySize, new SecureRandom(slat.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();

        try {
            Cipher cipher = Cipher.getInstance(algorithmType.name());
            cipher.init(Cipher.ENCRYPT_MODE, secretKey);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("加密失败:" + e.getMessage());
        }
    }

    /**
     * 加密工具 String->String
     *
     * @param algorithmType 加密算法
     * @param data          待加密字符串
     * @return 加密后字符串
     * @since 2019-12-12
     */
    public static String encrypt(AlgorithmType algorithmType, String slat, String data) {

        return encrypt(algorithmType, KEY_SIZE, slat, data);
    }

    /**
     * 加密工具 String->String
     *
     * @param algorithmType 加密算法
     * @param data          待加密字符串
     * @return 加密后字符串
     * @since 2019-12-12
     */
    public static String encrypt(AlgorithmType algorithmType, String data) {

        return encrypt(algorithmType, KEY_SIZE, "", data);
    }


    /**
     * 解密工具 String->String
     *
     * @param algorithmType
     * @param data decrypt data
     * @return strData
     * @since 2019-12-12
     */
    public static String decrypt(AlgorithmType algorithmType, Integer keySize, String slat, String data) {

        KeyGenerator keyGenerator = initKeyGenerator(algorithmType);
        keyGenerator.init(keySize, new SecureRandom(slat.getBytes()));
        SecretKey secretKey = keyGenerator.generateKey();

        try {
            Cipher cipher = Cipher.getInstance(algorithmType.name());
            cipher.init(Cipher.DECRYPT_MODE, secretKey);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new CoreException("解密失败:" + e.getMessage());
        }

    }

    /**
     * 加密工具 String->String
     *
     * @param algorithmType 加密算法
     * @param data          待加密字符串
     * @return 加密后字符串
     * @since 2019-12-12
     */
    public static String decrypt(AlgorithmType algorithmType, String slat, String data) {

        return decrypt(algorithmType, KEY_SIZE, slat, data);
    }

    /**
     * 加密工具 String->String
     *
     * @param algorithmType 加密算法
     * @param data          待加密字符串
     * @return 加密后字符串
     * @since 2019-12-12
     */
    public static String decrypt(AlgorithmType algorithmType, String data) {

        return decrypt(algorithmType, KEY_SIZE, "", data);
    }


    public static void main(String[] args) {
        // 我爱你中国
        // l love you
        String data = "我爱你中国";
        String encrypt = encrypt(AlgorithmType.AES, "123", data);
        System.out.println("encrypt--" + encrypt);
        String decrypt = decrypt(AlgorithmType.AES, "123", encrypt);
        System.out.println("decrypt--" + decrypt);

    }
}

