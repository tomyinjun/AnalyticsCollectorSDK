package com.cmcc.vr.mid.probe.aka;

import android.content.Context;

import com.cmcc.vr.mid.probe.aka.cipher.Base64Util;
import com.cmcc.vr.mid.probe.aka.cipher.BcdUtil;
import com.cmcc.vr.mid.probe.aka.cipher.CipherUtil;

import org.apache.commons.codec.Charsets;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.KeyFactory;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.Security;
import java.security.Signature;
import java.security.cert.X509Certificate;
import java.security.spec.PKCS8EncodedKeySpec;

import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

/**
 * KeyAgreeHelper
 *
 * @Type KeyAgreeHelper.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:45
 */
public class KeyAgreeHelper {

    private static String clientPrivateKey = "client.key";

    private static KeyAgreeHelper cipher = new KeyAgreeHelper();

    private KeyAgreeHelper() {
    }

    /**
     * getInstance
     *
     * @return
     */
    public static KeyAgreeHelper getInstance() {
        return cipher;
    }

    /**
     * encodeBlock
     *
     * @param info
     * @param serverCertificate
     * @return
     * @throws Exception
     */
    public String encodeBlock(String info, X509Certificate serverCertificate) throws Exception {
        int offSet = 0;
        int i = 0;
        byte[] dataByte = info.getBytes(Charsets.UTF_8);
        int dataLength = dataByte.length;
        byte[] cache;
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        Security.addProvider(new BouncyCastleProvider());
        Cipher cipher = Cipher.getInstance("RSA/None/OAEPWithSHA256AndMGF1Padding");
        PublicKey publicKey = serverCertificate.getPublicKey();
        cipher.init(Cipher.ENCRYPT_MODE, publicKey);
        int blockSize = cipher.getBlockSize();
        while (dataLength - offSet > 0) {
            if (dataLength - offSet > blockSize) {
                cache = cipher.doFinal(dataByte, offSet, blockSize);
            } else {
                cache = cipher.doFinal(dataByte, offSet, dataLength - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * blockSize;
        }
        byte[] encryptedData = out.toByteArray();
        return Base64Util.encode(encryptedData);
    }

    /**
     * signKeyRequest
     *
     * @param context
     * @param data
     * @return
     * @throws Exception
     */
    public String signKeyRequest(Context context, String data) throws Exception {
        Signature signature = Signature.getInstance("SHA256WithRSA");
        PrivateKey privateKey = getPrivateKey(context);
        signature.initSign(privateKey);
        signature.update(data.getBytes(Charsets.UTF_8));
        return Base64Util.encode(signature.sign());
    }

    private static PrivateKey getPrivateKey(Context context) throws Exception {
        //byte[] keyByte = CipherUtil.readKeyFile(context.getAssets().open(clientPrivateKey));
        byte[] keyByte = CipherUtil.getPrivateKey();
        PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(keyByte);
        KeyFactory keyFactory = KeyFactory.getInstance("RSA", "BC");
        PrivateKey privateKey = keyFactory.generatePrivate(keySpec);
        return privateKey;
    }

    /**
     * generateMacAndMsgKey
     *
     * @param shareData
     * @param clientTimeStamp
     * @param serverTimeStamp
     * @return
     * @throws Exception
     */
    public byte[][] generateMacAndMsgSafeKey(String shareData, String clientTimeStamp,
                                             String serverTimeStamp)
            throws Exception {
        byte[][] result = new byte[2][];
        byte[] safeMsgKey = getMsgKey(shareData, clientTimeStamp, serverTimeStamp);
//        byte[] msgKey = generateKeyWithTimeStamp(safeMsgKey, clientTimeStamp);
        result[0] = safeMsgKey;
        byte[] safeMacKey = getMacKey(shareData, clientTimeStamp, serverTimeStamp);
//        byte[] macKey = generateKeyWithTimeStamp(safeMacKey, clientTimeStamp);
        result[1] = safeMacKey;
        return result;
    }

    private byte[] getMsgKey(String shareData, String clientTimeStamp, String serverTimeStamp)
            throws NoSuchAlgorithmException, IOException {
        return getKey(shareData, "ENC", clientTimeStamp, serverTimeStamp);
    }

    private byte[] getMacKey(String shareData, String clientTimeStamp, String serverTimeStamp)
            throws NoSuchAlgorithmException, IOException {
        return getKey(shareData, "MAC", clientTimeStamp, serverTimeStamp);
    }

    private byte[] getKey(String shareData, String keyType, String clientTimeStamp,
                          String serverTimeStamp)
            throws NoSuchAlgorithmException, IOException {

        String shaArg = keyType + shareData + clientTimeStamp + serverTimeStamp;
        MessageDigest sha1Md = MessageDigest.getInstance("SHA1");
        sha1Md.update(shaArg.getBytes(Charsets.UTF_8));

        byte[] sha1Result = sha1Md.digest();
        MessageDigest md5Md = MessageDigest.getInstance("MD5");
        String md5Arg = shareData + Base64Util.encode(sha1Result);
        md5Md.update(md5Arg.getBytes(Charsets.UTF_8));
        byte[] md5Result = md5Md.digest();
        return md5Result;
    }

    /**
     * generateKeyWithTimeStamp
     * @param key
     * @param clientTimeStamp
     * @return
     */
    public static byte[] generateKeyWithTimeStamp(byte[] key, String clientTimeStamp)
            throws Exception {
        byte[] data = BcdUtil.getDispersionParameter(clientTimeStamp);
        SecretKeySpec sKeySpec = new SecretKeySpec(key, "AES");
        Cipher cipher = Cipher.getInstance("AES/ECB/NoPadding");
        cipher.init(Cipher.ENCRYPT_MODE, sKeySpec);
        return cipher.doFinal(data);
    }
}
