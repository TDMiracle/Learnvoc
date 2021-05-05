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

import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;


/**
 * 创建日期：2021/5/5 16:10
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ApplicationNotificationActivity.java
 * 类说明：通知消息发送
 */
public class ApplicationNotificationActivity extends BaseActivity implements View.OnClickListener {

    Button button1;
    Button button2;

    private NotificationManager manager;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_application_notification);
        init();
    }

    private void init(){
        button1 = (Button)findViewById(R.id.send_notification1);
        button2 = (Button)findViewById(R.id.send_notification2);
        button1.setOnClickListener(this);
        button2.setOnClickListener(this);
        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "chat";
            String channelName = "聊天消息";
            int importance = NotificationManager.IMPORTANCE_HIGH;
            createNotificationChannel(channelId, channelName, importance);
            channelId = "subscribe";
            channelName = "订阅消息";
            importance = NotificationManager.IMPORTANCE_DEFAULT;
            createNotificationChannel(channelId, channelName, importance);

        }
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createNotificationChannel(String channelId, String channelName, int importance) {
        NotificationChannel channel = new NotificationChannel(channelId, channelName, importance);
        NotificationManager notificationManager = (NotificationManager) getSystemService(
                NOTIFICATION_SERVICE);
        notificationManager.createNotificationChannel(channel);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.send_notification1:
                Intent intent = new Intent(this, LoginActivity.class);
                //点击后跳转
                PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
                Notification notification = new NotificationCompat.Builder(this, "chat")
                        .setAutoCancel(true)
                        .setContentTitle("今天你背单词了吗？")
                        .setContentText("快来单词打卡吧~")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.logo_name_round)
                        //设置红色
                        .setColor(Color.parseColor("#F00606"))
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_name85))
                        .setContentIntent(pendingIntent)
                        .build();
                manager.notify(1, notification);
                break;
            case R.id.send_notification2:
                Notification notificationGet = new NotificationCompat.Builder(this, "subscribe")
                        .setAutoCancel(true)
                        .setContentTitle("单词复习通知")
                        .setContentText("快来完成今日单词复习任务吧~")
                        .setWhen(System.currentTimeMillis())
                        .setSmallIcon(R.mipmap.logo_name_round)
                        .setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.logo_name85))
                        .build();
                manager.notify(2, notificationGet);
                break;
            default:
                break;
        }
    }
}