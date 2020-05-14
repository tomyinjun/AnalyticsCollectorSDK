package com.cmcc.vr.mid.probe.aka.cipher;

import java.text.SimpleDateFormat;
import java.util.Arrays;

/**
 * BcdUtil
 *
 * @Type BcdUtil.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:45
 */
public class BcdUtil {

    private BcdUtil() {
    }

    /**
     * 获取时间戳分散参数
     * 
     * @param timeStamp 时间戳
     * @return byte[]
     */
    public static byte[] getDispersionParameter(String timeStamp) {
        byte b1 = (byte) 0x80;
        byte b2 = (byte) 0x00;
        return append(string2Bcd(timeStamp), b1, b2);
    }

    /**
     * 拼接字节数组至16字节
     * 
     * @param byteArray 原始字节数字
     * @param b1        需要拼接的字节
     * @param b2        需要拼接的字节
     * @return 16个字节的字节数组
     */
    private static byte[] append(byte[] byteArray, byte b1, byte b2) {
        if (isEmpty(byteArray)) {
            System.out.println("append byte array failed! byte array is empty");
            return null;
        }
        byte[] array = null;
        if (byteArray.length < 16) {
            array = Arrays.copyOf(byteArray, 16);
            array[byteArray.length] = b1;
            for (int i = byteArray.length + 1; i < 16; i++) {
                array[i] = b2;
            }

        }
        return array;
    }

    /**
     * 将十进制字符串进行BCD编码
     * 
     * @param asc 原始字符串
     * @return 编码后的字节数组
     */
    private static byte[] string2Bcd(String asc) {
        if (isEmpty(asc)) {
            System.out.println("decimalism str BCD encode failed！ str is empty");
            return null;
        }
        SimpleDateFormat sf = new SimpleDateFormat("yyyyMMddHHmmss");
        asc = sf.format(Long.parseLong(asc));
        int len = asc.length();
        int mod = len % 2;

        if (mod != 0) {
            asc = "0" + asc;
            len = asc.length();
        }

        if (len >= 2) {
            len = len / 2;
        }

        byte[] bbt = new byte[len];
        byte[] abt = asc.getBytes();
        int j;
        int k;

        for (int p = 0; p < asc.length() / 2; p++) {
            if ((abt[2 * p] >= '0') && (abt[2 * p] <= '9')) {
                j = abt[2 * p] - '0';
            } else if ((abt[2 * p] >= 'a') && (abt[2 * p] <= 'z')) {
                j = abt[2 * p] - 'a' + 0x0a;
            } else {
                j = abt[2 * p] - 'A' + 0x0a;
            }

            if ((abt[2 * p + 1] >= '0') && (abt[2 * p + 1] <= '9')) {
                k = abt[2 * p + 1] - '0';
            } else if ((abt[2 * p + 1] >= 'a') && (abt[2 * p + 1] <= 'z')) {
                k = abt[2 * p + 1] - 'a' + 0x0a;
            } else {
                k = abt[2 * p + 1] - 'A' + 0x0a;
            }
            int a = (j << 4) + k;
            byte b = (byte) a;
            bbt[p] = b;
        }
        return bbt;
    }

    /**
     * BCD字节数组转字符串
     * 
     * @param bytes 原字节数组
     * @return 字符串
     */
    private static String bcd2String(byte[] bytes) {
        if (isEmpty(bytes)) {
            System.out.println("BCD byte array to str failed! byte array is empty");
            return null;
        }
        StringBuilder temp = new StringBuilder(bytes.length * 2);
        for (byte aByte : bytes) {
            temp.append((byte) ((aByte & 0xf0) >>> 4));
            temp.append((byte) (aByte & 0x0f));
        }
        return "0".equalsIgnoreCase(temp.toString().substring(0, 1)) ? temp.toString().substring(1)
                : temp.toString();
    }

    private static boolean isEmpty(byte[] bytes) {
        return bytes == null || bytes.length == 0;
    }

    private static boolean isEmpty(String str) {
        return str == null || str.length() == 0;
    }

}
