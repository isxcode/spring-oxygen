package com.isxcode.oxygen.core.secret;

import com.isxcode.oxygen.core.exception.OxygenException;

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
 * RSA utils
 *
 * @author ispong
 * @since 0.0.1
 */
public class RsaUtils {

    /**
     * rsa encrypt
     *
     * @param publicKey publicKey
     * @param data      data
     * @return base64 encrypt data
     * @since 0.0.1
     */
    public static String encrypt(String publicKey, String data) {

        try {
            PublicKey key = KeyFactory.getInstance(SecretConstants.RSA).generatePublic(new X509EncodedKeySpec(Base64.getDecoder().decode(publicKey)));
            Cipher cipher = Cipher.getInstance(SecretConstants.RSA);
            cipher.init(Cipher.ENCRYPT_MODE, key);
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new OxygenException(e.getMessage());
        }

    }

    /**
     * rsa decrypt
     *
     * @param privateKey privateKey
     * @param data       data
     * @return base64 encrypt data
     * @since 0.0.1
     */
    public static String decrypt(String privateKey, String data) {

        try {
            PrivateKey key = KeyFactory.getInstance(SecretConstants.RSA).generatePrivate(new PKCS8EncodedKeySpec(Base64.getDecoder().decode(privateKey)));
            Cipher cipher = Cipher.getInstance(SecretConstants.RSA);
            cipher.init(Cipher.DECRYPT_MODE, key);
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchAlgorithmException | InvalidKeySpecException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException | InvalidKeyException e) {
            throw new OxygenException(e.getMessage());
        }

    }

    /**
     * generate key pair
     *
     * @return key pair
     * @since 0.0.1
     */
    public KeyPair generateKeyPair() throws OxygenException {

        try {
            KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(SecretConstants.RSA);
            keyPairGenerator.initialize(2048);
            return keyPairGenerator.generateKeyPair();

//            System.out.println("publicKey:");
//            System.out.println(Base64.getEncoder().encodeToString(keyPair.getPublic().getEncoded()));
//
//            System.out.println("privateKey:");
//            System.out.println(Base64.getEncoder().encodeToString(keyPair.getPrivate().getEncoded()));
        } catch (NoSuchAlgorithmException e) {
            throw new OxygenException(e.getMessage());
        }
    }
}
