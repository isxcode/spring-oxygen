package com.isxcode.oxygen.core.secret;

import com.isxcode.oxygen.core.exception.OxygenException;
import lombok.extern.slf4j.Slf4j;

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
 * AES utils
 *
 * @author ispong
 * @since 0.0.1
 */
@Slf4j
public class AesUtils {

    /**
     * AES encrypt
     *
     * @param key  key
     * @param data data
     * @return base64 encrypt data
     * @since 0.0.1
     */
    public static String encrypt(String key, String data) throws OxygenException {

        try {
            Cipher cipher = Cipher.getInstance(SecretConstants.AES);
            cipher.init(Cipher.ENCRYPT_MODE, new SecretKeySpec(Arrays.copyOf(key.getBytes(), 1 << 5), SecretConstants.AES));
            return Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes(StandardCharsets.UTF_8)));
        } catch (NoSuchAlgorithmException | InvalidKeyException | NoSuchPaddingException | BadPaddingException | IllegalBlockSizeException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * AES encrypt
     *
     * @param data data
     * @return base64 encrypt data
     * @since 0.0.1
     */
    public static String encrypt(String data) throws OxygenException {

        return encrypt(SecretConstants.AES_KEY, data);
    }

    /**
     * AES decrypt
     *
     * @param key  key
     * @param data data
     * @return base64 encrypt data
     * @since 0.0.1
     */
    public static String decrypt(String key, String data) {

        try {
            Cipher cipher = Cipher.getInstance(SecretConstants.AES);
            cipher.init(Cipher.DECRYPT_MODE, new SecretKeySpec(Arrays.copyOf(key.getBytes(), 1 << 5), SecretConstants.AES));
            return new String(cipher.doFinal(Base64.getDecoder().decode(data)), StandardCharsets.UTF_8);
        } catch (NoSuchPaddingException | NoSuchAlgorithmException | InvalidKeyException | BadPaddingException | IllegalBlockSizeException e) {
            throw new OxygenException(e.getMessage());
        }
    }

    /**
     * AES decrypt
     *
     * @param data data
     * @return base64 encrypt data
     * @since 0.0.1
     */
    public static String decrypt(String data) {

        return decrypt(SecretConstants.AES_KEY, data);
    }
}
