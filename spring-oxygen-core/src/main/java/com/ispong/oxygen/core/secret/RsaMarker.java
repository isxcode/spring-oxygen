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
import java.nio.charset.StandardCharsets;
import java.security.*;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;

/**
 * RSA Marker
 *
 * @author ispong
 * @since 0.0.1
 */
public class RsaMarker {

    /**
     * 加密
     *
     * @param publicKey 公钥
     * @param data      加密数据
     * @return 加密后的数据
     * @since 0.0.1
     */
    public static String encrypt(String publicKey, String data) {

        try {
            PublicKey key = KeyFactory.getInstance(SecretConstants.RSA).generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            Cipher cipher = Cipher.getInstance(SecretConstants.RSA);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new OxygenException("RAS encrypt is wrong");
        }
    }

    /**
     * 解密
     *
     * @param privateKey 私钥
     * @param data       解密数据
     * @return 解密后的数据
     * @since 0.0.1
     */
    public static String decrypt(String privateKey, String data) {

        try {
            PrivateKey key = KeyFactory.getInstance(SecretConstants.RSA).generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            Cipher cipher = Cipher.getInstance(SecretConstants.RSA);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new OxygenException("RAS decrypt is wrong");
        }
    }

    /**
     * 钥匙对生成方法
     */
    public static void generateKeyPair() throws OxygenException {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(SecretConstants.RSA);
            keyPairGenerator.initialize(2048);
            KeyPair keyPair = keyPairGenerator.generateKeyPair();
            System.out.println("publicKey:");
            System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
            System.out.println("privateKey:");
            System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new OxygenException("RAS generate key pair is wrong");
        }
    }
}
