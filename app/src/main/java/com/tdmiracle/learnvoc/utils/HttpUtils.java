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

package com.tdmiracle.learnvoc.utils;

import android.content.Context;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;

import java.io.IOException;

public class HttpUtils {
    public static final String TAG = "HttpUtils";
    private static String APIKEY = "45189353602dd9275ec23628b606d272";

    public static void getEnglishSentence(Context context, String date, StringCallback callback) throws IOException {
        String url = "http://sentence.iciba.com/index.php";
        OkGo.<String>get(url)
                .tag(context)
                .params("c", "dailysentence")
                .params("m", "getdetail")
                .params("title", date)
                .execute(callback);
    }

    public static void getEnglishSentence2(Context context, String date, StringCallback callback) throws IOException {
        String url = "http://api.tianapi.com/txapi/everyday/index";
        OkGo.<String>get(url)
                .tag(context)
                .params("key", APIKEY)
                .params("date", date)
                .execute(callback);

    }
}
