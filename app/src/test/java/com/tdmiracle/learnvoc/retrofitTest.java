/*
 * Copyright (C) 2021 TD.Miracle(584290367@qq.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package com.tdmiracle.learnvoc;

import com.tdmiracle.learnvoc.utils.retrofitUtils.HttpbinService;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;

public class retrofitTest {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpbinService httpbinService;

    public void init(){
        okHttpClient =  new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpbinService = retrofit.create(HttpbinService.class);
    }

    public void postAsync(){
        retrofit2.Call<ResponseBody> call = httpbinService.post("lance","123");
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                System.out.println(response.body().toString());
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                System.out.println("fail");

            }
        });
    }

}