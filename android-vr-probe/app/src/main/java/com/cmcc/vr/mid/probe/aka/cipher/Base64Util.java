package com.cmcc.vr.mid.probe.aka.cipher;

import java.io.IOException;

/**
 * Base64Util
 *
 * @Type Base64Util.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:44
 */
public class Base64Util {

    /**
     * Encode
     *
     * @param data
     * @return
     */
    public static String encode(byte[] data) {
        return Base64.encodeBase64String(data);
        //		return Base64.encodeToString(data, Base64.DEFAULT);
    }

    /**
     * Decode
     *
     * @param data
     * @return
     */
    public static byte[] decode(String data) {
        return Base64.decodeBase64(data);
        //		return Base64.decode(data, Base64.DEFAULT);
    }

    /**
     *
     * @param data
     * @return
     * @throws IOException
     */
    public static byte[] decode(byte[] data) throws IOException {
        return Base64.decodeBase64(data);
    }
}
