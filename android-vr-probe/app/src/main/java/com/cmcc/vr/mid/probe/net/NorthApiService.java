package com.cmcc.vr.mid.probe.net;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface NorthApiService {

    /**
     * ApiUrl
     */
    class ApiUrl {
        public final static String URL_BASE = "/family/";
        public final static String REQUEST_AKA = "/family/";
        public final static String REPORT_BOOT = "/family/boot";
        public final static String REPORT_PERIODIC = "/family/periodic";
        public final static String REPORT_PROGRAM_INFO = "/family/programInfo"; //POST
        public final static String REPORT_ALARM = "/family/alarm";
    }

    //    @POST(ApiUrl.REPORT_BOOT)
    //    @FormUrlEncoded
    //    Call<BootReportResultNode> bootReport(@Field("deviceId") String deviceId,
    //                                          @Field("deviceMACAddress") String deviceMacAddress,
    //                                          @Field("encryptedMessage") String encryptedMessage,
    //                                          @Field("messageAuthCode") String messageAuthCode);
    //
    //    @POST(ApiUrl.REPORT_PERIODIC)
    //    @FormUrlEncoded
    //    Call<BootReportResultNode> periodicReport(@Field("deviceId") String deviceId,
    //                                              @Field("deviceMACAddress") String deviceMacAddress,
    //                                              @Field("encryptedMessage") String encryptedMessage,
    //                                              @Field("messageAuthCode") String messageAuthCode);
    //
    //    @POST(ApiUrl.REPORT_BOOT)
    //    @FormUrlEncoded
    //    Call<ProgramInfoReportResultNode> programInfoReport(@Field("deviceId") String deviceId,
    //                                                        @Field("deviceMACAddress") String deviceMacAddress,
    //                                                        @Field("encryptedMessage") String encryptedMessage,
    //                                                        @Field("messageAuthCode") String messageAuthCode);
    //
    //    @POST(ApiUrl.REPORT_BOOT)
    //    @FormUrlEncoded
    //    Call<AlarmReportResultNode> alarmReport(@Field("deviceId") String deviceId,
    //                                            @Field("deviceMACAddress") String deviceMacAddress,
    //                                            @Field("encryptedMessage") String encryptedMessage,
    //                                            @Field("messageAuthCode") String messageAuthCode);

    /**
     * Report
     * @param url
     * @param body
     * @return
     */
    @POST()
    Call<ReportResponse> report(@Url String url, @Body HashMap<String, String> body);

    //    @POST()
    //    @FormUrlEncoded
    //    Call<ReportResponse> report(@Url String url,
    //                                @Field("deviceId") String deviceId,
    //                                @Field("deviceMACAddress") String deviceMacAddress,
    //                                @Field("encryptedMessage") String encryptedMessage,
    //                                @Field("messageAuthCode") String messageAuthCode);

    //    @POST(ApiUrl.REQUEST_AKA)
    //    @FormUrlEncoded
    //    Call<CrtResponse> requestCrt(@Field("deviceId") String deviceId,
    //                                    @Field("deviceMACAddress") String deviceMacAddress);
    //
    //    @POST(ApiUrl.REQUEST_AKA)
    //    @FormUrlEncoded
    //    Call<KeyResponse> requestKey(@Field("deviceId") String deviceId,
    //                                    @Field("deviceMACAddress") String deviceMacAddress,
    //                                    @Field("softprobeVersion") String softprobeVersion,
    //                                    @Field("clientTimeStamp") String clientTimeStamp,
    //                                    @Field("serverTimeStamp") String serverTimeStamp,
    //                                    @Field("encryptedMessage") String encryptedMessage,
    //                                    @Field("messageSignature") String messageSignature);
}
