/*
 * Copyright (C) 2017 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.cmcc.vr.mid.probe.aka.net;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

/**
 * AkaApiService
 *
 * @Type AkaApiService.java
 * @Author tuhongguo
 * @Date: 2018-10-19 下午4:46
 */
public interface AkaApiService {

    /**
     * ApiUrl
     */
    class ApiUrl {
        public final static String REQUEST_AKA = "/family/";
        public final static String REQUEST_AKA_FIRST = "/family/keyAgreementFirst";
        public final static String REQUEST_AKA_SECOND = "/family/keyAgreementSecond";
    }

    /**
     * Request certification
     *
     * @param body
     * @return
     */
    @POST(ApiUrl.REQUEST_AKA_FIRST)
    Call<CrtResponse> requestCrt(@Body HashMap<String, String> body);

    /**
     * Request to key agreement
     *
     * @param body
     * @return
     */
    @POST(ApiUrl.REQUEST_AKA_SECOND)
    Call<KeyResponse> requestKey(@Body HashMap<String, String> body);
}
