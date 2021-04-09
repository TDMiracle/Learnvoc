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

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.xuexiang.xui.widget.layout.XUIButton;

/**
 * 创建日期：2021/4/9 20:24
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ForceOfflineActivity.java
 * 类说明：用于测试实现强制下线功能
 */
public class ForceOfflineActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_force_offline);
        Button forceOffline = (Button)findViewById(R.id.force_offline);
        forceOffline.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.tdmiracle.learnvoc.FORCE_OFFLINE");
                sendBroadcast(intent);
            }
        });
    }
}