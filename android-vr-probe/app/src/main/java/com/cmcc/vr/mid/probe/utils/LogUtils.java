package com.cmcc.vr.mid.probe.utils;

import android.content.Context;
import android.util.Log;

/**
 * LogUtils
 *
 * @Type LogUtils.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午5:05
 */
public class LogUtils {
    public static boolean mIsShowToast = false;

    private LogUtils() {
    }

    /**
     * d
     * @param str
     * @param str2
     */
    public static void d(String str, String str2) {

    }

    /**
     * d
     * @param str
     */
    public static void dd(String str) {

    }

    /**
     * dd
     * @param str
     * @param str2
     */
    public static void dd(String str, String str2) {

    }

    /**
     * i
     * @param str
     * @param str2
     */
    public static void i(String str, String str2) {
    }

    /**
     * e
     * @param str
     * @param str2
     */
    public static void e(String str, String str2) {
        //Always print error log
        Log.e("2.0--- " + str, str2);
//        if (ConfigUtils.isShowLog) {
//            Log.e("2.0--- " + str, str2);
//        }
    }

    /**
     * w
     * @param str
     * @param str2
     */
    public static void w(String str, String str2) {
        //Always print warning log
        Log.w("2.0--- " + str, str2);
//        if (ConfigUtils.isShowLog) {
//            Log.w("2.0--- " + str, str2);
//        }
    }

    /**
     * v
     * @param str
     * @param str2
     */
    public static void v(String str, String str2) {

    }

    /**
     * showToastMsg
     * @param context
     * @param str
     */
    public static void showToastMsg(Context context, String str) {
    }

    /**
     * vCut
     * @param tag
     * @param msg
     */
    public static void vCut(String tag, String msg) {
        int strLength = msg.length();
        int start = 0;
        int end = 500;
        for (int i = 0; i < 100; i++) {
            //剩下的文本还是大于规定长度则继续重复截取并输出
            if (strLength > end) {
                v(tag + i, msg.substring(start, end));
                start = end;
                end = end + 500;
            } else {
                v(tag, msg.substring(start, strLength));
                break;
            }
        }
    }
}
