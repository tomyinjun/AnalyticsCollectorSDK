package com.cmcc.vr.mid.probe.aka.cipher;

import org.apache.commons.codec.Charsets;

import javax.crypto.Cipher;
import javax.crypto.Mac;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

/**
 * SessionCipher
 *
 * @Type SessionCipher.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:46
 */
public final class SessionCipher {
    /**
     * Sign
     *
     * @param data
     * @param key
     * @return
     * @throws Exception
     */
    public static String sign(String data, byte[] key) throws Exception {
        SecretKeySpec signingKey = new SecretKeySpec(key, "HMACSha256");
        Mac mac = Mac.getInstance("HMACSha256");
        mac.init(signingKey);
        byte[] rawMac = mac.doFinal(data.getBytes(Charsets.UTF_8));
        return Base64Util.encode(rawMac);
    }

    /**
     * Decode method
     *
     * @param key
     * @param data
     * @return
     * @throws Exception
     */
    public static String decode(byte[] key, String data) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        byte[] iv = "0000000000000000".getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.DECRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decryptedContent = cipher.doFinal(Base64Util.decode(data));
        return new String(decryptedContent, Charsets.UTF_8);
    }

    /**
     * Encode method
     *
     * @param key
     * @param secretJson
     * @return
     * @throws Exception
     */
    public static String encode(byte[] key, String secretJson) throws Exception {
        SecretKeySpec secretKey = new SecretKeySpec(key, "AES");
        byte[] iv = "0000000000000000".getBytes();
        IvParameterSpec ivParameterSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, secretKey, ivParameterSpec);
        byte[] decryptedContent = cipher.doFinal(secretJson.getBytes(Charsets.UTF_8));
        return Base64Util.encode(decryptedContent);
    }

}
