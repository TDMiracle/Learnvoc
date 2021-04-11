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

package com.tdmiracle.learnvoc.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.utils.retrofitUtils.HttpbinService;

import java.io.IOException;

import butterknife.BindView;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;


/**
 * 创建日期：2021/4/10 21:18
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： testRetrofitActivity.java
 * 类说明：使用retrofit请求url
 */
public class testRetrofitActivity extends AppCompatActivity {

    private OkHttpClient okHttpClient;
    private Retrofit retrofit;
    private HttpbinService httpbinService;

    private final String TAG = "testRetrofit:";

    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_retrofit);
        init();
    }

    public void init(){
        okHttpClient =  new OkHttpClient();
        retrofit = new Retrofit.Builder().baseUrl("https://www.httpbin.org/").build();
        httpbinService = retrofit.create(HttpbinService.class);
        Button button = findViewById(R.id.test_asyn);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postAsync();
            }
        });
    }

    //发送异步方法
    public void postAsync(){
        retrofit2.Call<ResponseBody> call = httpbinService.post("lance","123");
        //发送同步方法
        //call.execute();
        call.enqueue(new retrofit2.Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.d(TAG, response.body().string());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.d(TAG, "onFailure: ");

            }
        });
    }
}