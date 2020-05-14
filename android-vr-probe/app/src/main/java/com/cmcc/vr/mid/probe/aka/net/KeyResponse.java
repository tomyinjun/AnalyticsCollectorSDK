package com.cmcc.vr.mid.probe.aka.net;

/**
 * KeyResponse
 *
 * @Type KeyResponse.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:45
 */
public class KeyResponse {
    private String serverResponseCode;
    private String keyVerification;

    public String getServerResponseCode() {
        return serverResponseCode;
    }

    public void setServerResponseCode(String serverResponseCode) {
        this.serverResponseCode = serverResponseCode;
    }

    public String getKeyVerification() {
        return keyVerification;
    }

    public void setKeyVerfication(String keyVerfication) {
        this.keyVerification = keyVerfication;
    }
}
