package com.cmcc.vr.mid.probe.aka.net;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * NetworkServiceFactory
 *
 * @Type NetworkServiceFactory.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:52
 */
public class NetServiceFactory {
    /**
     * createApiService
     *
     * @param baseUrl
     * @param clazz
     * @param <T>
     * @return
     */
    public static <T> T createApiService(String baseUrl, Class<T> clazz) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(getBaseHttpClient())
                .build();
        return retrofit.create(clazz);
    }

    /**
     * createApiService
     *
     * @param baseUrl
     * @param clazz
     * @param client
     * @param <T>
     * @return
     */
    public static <T> T createApiService(String baseUrl, Class<T> clazz, OkHttpClient client) {
        Retrofit retrofit = new Retrofit.Builder().baseUrl(baseUrl)
                .addConverterFactory(GsonConverterFactory.create()).client(client).build();
        return retrofit.create(clazz);
    }

    private static OkHttpClient getBaseHttpClient() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        httpClientBuilder.addInterceptor((chain) -> {
            Request original = chain.request();
            Request request = original.newBuilder().header("Accept", "application/json")
                    .header("Content-Type", "application/json")
                    .method(original.method(), original.body()).build();

            return chain.proceed(request);
        });
        return httpClientBuilder.build();
    }

    public static OkHttpClient getDiyHttpClient(long connectTimeout, long readTimeout) {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS).addInterceptor((chain) -> {
                    Request original = chain.request();
                    Request request = original.newBuilder().header("Accept", "application/json")
                            .header("Content-Type", "application/json")
                            .header("user-agent",
                                    "Mozilla/5.0 (Windows NT 6.1; rv:8.0.1) Gecko/20100101 Firefox/8.0.1")
                            .method(original.method(), original.body()).build();

                    return chain.proceed(request);
                });
        return httpClientBuilder.build();
    }
}
