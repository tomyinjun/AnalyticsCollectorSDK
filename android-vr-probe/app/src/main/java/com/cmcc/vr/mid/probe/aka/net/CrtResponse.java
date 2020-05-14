package com.cmcc.vr.mid.probe.aka.net;

/**
 * CrtResponse
 *
 * @Type CrtResponse.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:46
 */
public class CrtResponse {
    private String serverResponseCode;
    private String serverTimeStamp;
    private String serverCertificate;

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

    public String getServerCertificate() {
        return serverCertificate;
    }

    public void setServerCertificate(String serverCertificate) {
        this.serverCertificate = serverCertificate;
    }
}
