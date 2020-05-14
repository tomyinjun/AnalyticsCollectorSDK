package com.cmcc.vr.mid.probe.utils;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.aka.AkaHelper;
import com.cmcc.vr.mid.probe.constant.ConfigUtils;
import com.cmcc.vr.mid.probe.constant.StaticConfig;
import com.cmcc.vr.mid.probe.core.DetectorState;
import com.cmcc.vr.mid.probe.net.NetworkServiceFactory;
import com.cmcc.vr.mid.probe.net.NorthApiService;
import com.cmcc.vr.mid.probe.net.ReportResponse;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Response;

public class HttpUtils {
    private static final String TAG = "HttpUtils";
    private static String sDeviceId;
    private static String sDeviceMacAddress;
    private static NorthApiService sNorthApiService;
    private static HttpUtils sInstance = null;

    private Context mContext;
    private SimpleDateFormat mSimpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmss",
            Locale.getDefault());
    private DetectorState mDetectorState;
    private String mMacKey;
    private String mMsgKey;

    public HttpUtils(Context context) {
        mContext = context;
        mDetectorState = DetectorState.getInstance(mContext);
    }

    public static synchronized HttpUtils getInstance(Context context) {
        if (sInstance == null) {
            sInstance = new HttpUtils(context);

            String stbServerAddress = ConfigUtils.getInstance(context).getServerAddress();
            long connectTimeout = ConfigUtils.getInstance(context).getNetConnectTimeout();
            long readTimeout = ConfigUtils.getInstance(context).getNetReadTimeout();
            if (!TextUtils.isEmpty(stbServerAddress)) {
                sNorthApiService = NetworkServiceFactory.createApiService(stbServerAddress,
                        NorthApiService.class,
                        NetworkServiceFactory.getDiyHttpClient(connectTimeout, readTimeout));
            }
            //sDeviceId = DevInfoTools.getInstance(context).getDeviceId();
            //sDeviceMacAddress = DevInfoTools.getInstance(context).getEth0Mac();
        }
        return sInstance;
    }

    /**
     * doPost2
     *
     * @param handler
     * @param str
     * @param str2
     * @param str3
     * @return
     */
    public String doPost2(Handler handler, String str, String str2, String str3) {
        LogUtils.d(TAG, "report type:" + str + " reportTime:"
                + mSimpleDateFormat.format(new Date(StaticConfig.getDeviceRunTime())));
        LogUtils.d(TAG, str2);
        return null;
    }

    /**
     * doPost
     *
     * @param handler
     * @param type
     * @param body
     * @return
     */
    public String doPost(Handler handler, String type, String body) {
        LogUtils.d(TAG, "report type:" + type + " reportTime:"
                + mSimpleDateFormat.format(new Date(StaticConfig.getDeviceRunTime())));
        LogUtils.d(TAG, body);
        if (TextUtils.isEmpty(body)) {
            return null;
        }

        String stbServerAddress = ConfigUtils.getInstance(mContext).getServerAddress();

        return doReport(type, body, handler, stbServerAddress);
    }

    //TODO: change to private

    /**
     * doReport
     *
     * @param type
     * @param message
     * @param handler
     * @param stbServerAddress
     * @return
     */
    public synchronized String doReport(String type, String message, Handler handler,
                                        String stbServerAddress) {
        if (DetectorState.getInstance(mContext).isReportToPlatform()
                || StaticConfig.UPLOAD_NAME_BOOT.equals(type)) {
            LogUtils.d(TAG, "post message to report: " + type);

            HashMap<String, String> body = new LinkedHashMap<>();
            body.put("deviceId", sDeviceId);
            body.put("deviceMacAddress", sDeviceMacAddress);
            String clientTimeStamp = String.valueOf(
                    System.currentTimeMillis() + AkaHelper.getInstance().getTimeOffset());
            body.put("clientTimeStamp", clientTimeStamp);
            body.put("encryptedMessage", AkaHelper.getInstance().getEncryptedMessage(message, clientTimeStamp));
            body.put("messageAuthCode", AkaHelper.getInstance().getMessageAuthCode(body, clientTimeStamp));

            Call<ReportResponse> reportCall = sNorthApiService
                    .report(NorthApiService.ApiUrl.URL_BASE + type, body);
            try {
                Response<ReportResponse> response = reportCall.execute();
                if (response != null && response.isSuccessful()) {
                    ReportResponse reportResult = response.body();
                    if (reportResult != null && StaticConfig.RESPONSE_CODE_SUCCESS
                            .equals(reportResult.getServerResponseCode())) {
                        LogUtils.d(TAG, "report " + type + " succeed: " + reportResult.getEncryptedMessage());
                        return AkaHelper.getInstance().checkAndDecMsgResponse(reportResult, clientTimeStamp);
                    }
                } else {
                    if (LogUtils.mIsShowToast) {
                        sendMessage(handler, type, "report failed");
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
                if (LogUtils.mIsShowToast) {
                    sendMessage(handler, type, "report failed");
                }
            }
        }
        return null;
    }

    /**
     * sendMessage
     *
     * @param handler
     * @param url
     * @param toast
     */
    public void sendMessage(Handler handler, String url, String toast) {
        Message obtain = Message.obtain();
        obtain.what = StaticConfig.MSG_SHOW_HTTP_REPORT_RESULT;
        Bundle bundle = new Bundle();
        bundle.putString("TOAST", toast);
        bundle.putString(StaticConfig.PROGRAM_URL, url);
        obtain.setData(bundle);
        handler.sendMessage(obtain);
    }
}
