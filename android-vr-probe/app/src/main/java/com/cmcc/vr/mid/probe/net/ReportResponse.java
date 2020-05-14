package com.cmcc.vr.mid.probe.net;

import java.util.LinkedHashMap;
import java.util.Map;

public class ReportResponse {
    public static final String KEY_CODE = "serverResponseCode";
    public static final String KEY_SEVER_TIME = "serverTimeStamp";
    public static final String KEY_MESSAGE = "encryptedMessage";
    public static final String KEY_AUTH_CODE = "messageAuthCode";

    private String serverResponseCode;
    private String serverTimeStamp;
    private String encryptedMessage;
    private String messageAuthCode;

    public String getServerResponseCode() {
        return serverResponseCode;
    }

    public void setServerResponseCode(String serverResponseCode) {
        this.serverResponseCode = serverResponseCode;
    }

    public String getServerTimeStamp() {
        return serverTimeStamp;
    }

    public void setServerTimeStamp(String serverTimeStamp) {
        this.serverTimeStamp = serverTimeStamp;
    }

    public String getEncryptedMessage() {
        return encryptedMessage;
    }

    public void setEncryptedMessage(String encryptedMessage) {
        this.encryptedMessage = encryptedMessage;
    }

    public String getMessageAuthCode() {
        return messageAuthCode;
    }

    public void setMessageAuthCode(String messageAuthCode) {
        this.messageAuthCode = messageAuthCode;
    }

    /**
     * to LinkedHashMap
     *
     * @return
     */
    public Map<String, String> toMap() {
        Map<String, String> map = new LinkedHashMap<>();
        map.put(KEY_CODE, serverResponseCode);
        map.put(KEY_SEVER_TIME, serverTimeStamp);
        map.put(KEY_MESSAGE, encryptedMessage);
        map.put(KEY_AUTH_CODE, messageAuthCode);
        return map;
    }
}
