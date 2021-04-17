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

package com.tdmiracle.learnvoc.utils.retrofitUtils;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * 创建日期：2021/4/16 18:33
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： EverydaySentence.java
 * 类说明： 每日一句get请求接口
 */
public interface EverydaySentence {
    //http://sentence.iciba.com/index.php?c=dailysentence&m=getdetail&title=2021-04-16
    @GET("get")
    Call<ResponseBody> get(@Query("c") String dailySentence, @Query("m") String getDetail, @Query("title") String title);
}
