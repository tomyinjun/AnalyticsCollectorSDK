package com.cmcc.vr.mid.probe.aka;

import android.content.Context;
import android.text.TextUtils;

import com.cmcc.vr.mid.probe.aka.cipher.Base64Util;
import com.cmcc.vr.mid.probe.aka.cipher.CipherUtil;
import com.cmcc.vr.mid.probe.aka.cipher.SessionCipher;
import com.cmcc.vr.mid.probe.aka.net.CrtResponse;
import com.cmcc.vr.mid.probe.aka.net.KeyResponse;
import com.cmcc.vr.mid.probe.constant.StaticConfig;
import com.cmcc.vr.mid.probe.net.ReportResponse;
import com.cmcc.vr.mid.probe.tools.SystemInfoTools;
import com.cmcc.vr.mid.probe.utils.LogUtils;
import com.google.gson.Gson;

import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

/**
 * AkaHelper
 *
 * @Type AkaHelper.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:44
 */
public final class AkaHelper {
    private static final String TAG = "AkaHelper";
    private static AkaHelper sInstance = new AkaHelper();

    private static String sDeviceId;
    private static String sImsi;
    private static String sCpu;
    private static String sVersion;
    private static String sRelease;
    private static String sModel;
    private static String sManufacturer;
    private boolean sIsDevInfoInited;

    private String mServerTimeStamp = null;
    private String mClientTimeStamp = null;

    private byte[] mMacSafeKey = null;
    private byte[] mMsgSafeKey = null;

    private X509Certificate mServerCrt = null;
    private String mSecureMessageKey = null;

    private long mTimeOffset = 0;

    private AkaHelper() {
    }

    /**
     * Get Instance
     *
     * @return
     */
    public static AkaHelper getInstance() {
        return sInstance;
    }

    /**
     * Check aka succeed or not
     *
     * @return
     */
    public boolean checkAkaStatus() {
        return (mMacSafeKey != null && mMsgSafeKey != null);
    }

    /**
     * Time offset to server
     *
     * @return
     */
    public long getTimeOffset() {
        return mTimeOffset;
    }

    /**
     * To encrypt message base on the spec.
     *
     * @param msgJson
     * @param clientTimeStamp
     * @return
     */
    public String getEncryptedMessage(String msgJson, String clientTimeStamp) {
        if (checkAkaStatus()) {
            try {
                LogUtils.vCut(TAG, "encrypting message: " + msgJson);
                return SessionCipher.encode(KeyAgreeHelper
                        .generateKeyWithTimeStamp(mMsgSafeKey, clientTimeStamp), msgJson);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Get auth code for target request or response body
     *
     * @param body
     * @return
     */
    public String getMessageAuthCode(Map<String, String> body, String serverTimeStamp) {
        if (checkAkaStatus()) {
            Map<String, String> localMap = new LinkedHashMap<>();
            localMap.putAll(body);
            localMap.remove("messageAuthCode");

            String data = CipherUtil.getToSignStr(localMap);
            try {
                return SessionCipher.sign(data, KeyAgreeHelper
                        .generateKeyWithTimeStamp(mMacSafeKey, serverTimeStamp));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    /**
     * Verify report response and decode the message
     *
     * @param reportResponse
     * @return
     */
    public String checkAndDecMsgResponse(ReportResponse reportResponse, String clientTimeStamp) {
        try {
            if (TextUtils.equals(reportResponse.getMessageAuthCode(),
                    getMessageAuthCode(reportResponse.toMap(), clientTimeStamp))) {
                String data = SessionCipher.decode(KeyAgreeHelper
                                .generateKeyWithTimeStamp(mMsgSafeKey, clientTimeStamp),
                        reportResponse.getEncryptedMessage());
                LogUtils.vCut(TAG, "decrypted message: " + data);
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Verify message auth code
     *
     * @param msgMap
     * @return
     */
    public boolean verifyMessageAuthCode(Map<String, String> msgMap, String serverTimeStamp) {
        if (msgMap != null && checkAkaStatus()) {
            String messageAuthCode = msgMap.get("messageAuthCode");
            if (messageAuthCode.equals(getMessageAuthCode(msgMap, serverTimeStamp))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Verify report response and decode the message
     *
     * @param msgMap
     * @param serverTimeStamp
     * @return
     */
    public String checkAndDecMsgMap(Map<String, String> msgMap, String serverTimeStamp) {
        try {
            if (verifyMessageAuthCode(msgMap, serverTimeStamp)) {
                String data = SessionCipher.decode(
                        KeyAgreeHelper.generateKeyWithTimeStamp(mMsgSafeKey, serverTimeStamp),
                        msgMap.get("encryptedMessage"));
                LogUtils.vCut(TAG, "decrypted message: " + data);
                return data;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    void clean() {
        mServerTimeStamp = null;
        mClientTimeStamp = null;

        mMacSafeKey = null;
        mMsgSafeKey = null;

        mServerCrt = null;
        mSecureMessageKey = null;

        mTimeOffset = 0;
    }

    String getServerTimeStamp() {
        return mServerTimeStamp;
    }

    String getClientTimeStamp() {
        return mClientTimeStamp;
    }

    String getKeyRequestEncryptedMsg(Context context) throws Exception {
        String clearText = getKeyRequestMessageJson(context);
        String encryptedMsg = KeyAgreeHelper.getInstance().encodeBlock(clearText, mServerCrt);
        return encryptedMsg;
    }

    String getKeyRequestMsgSignature(Context context, Map<String, String> body) throws Exception {
        String data = CipherUtil.getToSignStr(body);
        String sign = KeyAgreeHelper.getInstance().signKeyRequest(context, data);
        return sign;
    }

    boolean saveAndCheckCrt(Context context, CrtResponse crtResponse) throws Exception {
        if (StaticConfig.RESPONSE_CODE_SUCCESS.equals(crtResponse.getServerResponseCode())) {
            mServerTimeStamp = crtResponse.getServerTimeStamp();
            // note: save offset for other agreement request sync
            long currentTime = System.currentTimeMillis();
            mTimeOffset = Long.parseLong(mServerTimeStamp) - currentTime;
            mClientTimeStamp = String.valueOf(currentTime);
            String certificateStr = crtResponse.getServerCertificate();
            mServerCrt = CrtVerifyHelper.getInstance().getServerCertificate(certificateStr);
            if (mServerCrt == null) {
                return false;
            }
            //return CrtVerifyHelper.getInstance().checkServerCertificateValid(context, mServerCrt);
            //TODO: no valid root certificate, return true directly.
            return true;
        } else {
            LogUtils.v(TAG,
                    "[saveAndCheckCrt]serverResponseCode=" + crtResponse.getServerResponseCode());
            return false;
        }
    }

    boolean generateAndCheckKey(KeyResponse keyResponse) throws Exception {
        String serverResponseCode = keyResponse.getServerResponseCode();
        if (StaticConfig.RESPONSE_CODE_SUCCESS.equals(serverResponseCode)) {

            byte[][] keys = KeyAgreeHelper.getInstance().generateMacAndMsgSafeKey(mSecureMessageKey,
                    mClientTimeStamp, mServerTimeStamp);
            mMsgSafeKey = keys[0];
            mMacSafeKey = keys[1];
            String serverKeyVerification = keyResponse.getKeyVerification();
            String selfKeyVerification = getSelfVerification(KeyAgreeHelper
                    .generateKeyWithTimeStamp(mMacSafeKey, mClientTimeStamp), serverResponseCode);

            if (TextUtils.equals(selfKeyVerification, serverKeyVerification)) {
                return true;
            } else {
                LogUtils.v(TAG, "[generateAndCheckKey] verify key failed!");
                mMsgSafeKey = null;
                mMacSafeKey = null;
            }
        } else {
            LogUtils.v(TAG, "[generateAndCheckKey] serverResponseCode=" + serverResponseCode);
        }
        return false;
    }

    private String getKeyRequestMessageJson(Context context) throws Exception {
        if (!sIsDevInfoInited) {
            initDevInfo(context);
        }

        Map deviceInfoMap = new HashMap<String, String>();
        deviceInfoMap.put("deviceId", sDeviceId);
        deviceInfoMap.put("imsi", sImsi);
        deviceInfoMap.put("cpu", sCpu);
        deviceInfoMap.put("version", sVersion);
        deviceInfoMap.put("release", sRelease);
        deviceInfoMap.put("model", sModel);
        deviceInfoMap.put("manufacturer", sManufacturer);

        byte[] shareDataByte = new byte[48];
        new Random().nextBytes(shareDataByte);
        mSecureMessageKey = Base64Util.encode(shareDataByte);
        Map keyInfoMap = new HashMap<String, Object>();
        keyInfoMap.put("secureMessageKey", mSecureMessageKey);
        keyInfoMap.put("serverTimeStamp", mServerTimeStamp);

        Map clearMessageMap = new HashMap<String, Object>();
        clearMessageMap.put("deviceInfo", deviceInfoMap);
        clearMessageMap.put("keyInfo", keyInfoMap);

        Gson gson = new Gson();
        String secretInfo = gson.toJson(clearMessageMap);
        return secretInfo;
    }

    private String getSelfVerification(byte[] macKey, String serverResponseCode) throws Exception {
        StringBuilder sb = new StringBuilder();
        sb.append(serverResponseCode).append(mClientTimeStamp).append(mServerTimeStamp);
        sb.append(sDeviceId).append(sImsi).append(sCpu).append(sVersion).append(sRelease);
        sb.append(sModel).append(sManufacturer);
        return SessionCipher.sign(sb.toString(), macKey);
    }

    private void initDevInfo(Context context) {
        SystemInfoTools systemInfoTools = SystemInfoTools.getInstance(context);
//        sDeviceId = devInfoTools.getDeviceId();
//        sImsi = devInfoTools.getImsiModel();
//        sCpu = devInfoTools.getCpuModel();
//        sVersion = systemInfoTools.getSystemVersion();
//        sRelease = devInfoTools.getSTBFirmwareVersion();
//        sModel = devInfoTools.getSTBModel();
//        sManufacturer = devInfoTools.getManuFacturer();

        sIsDevInfoInited = true;
    }
}
