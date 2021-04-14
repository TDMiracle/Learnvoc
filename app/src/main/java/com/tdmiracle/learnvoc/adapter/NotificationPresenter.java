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

package com.tdmiracle.learnvoc.adapter;

import android.content.Context;

import com.tdmiracle.learnvoc.adapter.entity.Notification;
import com.tdmiracle.learnvoc.adapter.model.NotificationModel;

import java.util.List;

public class NotificationPresenter implements NotificaitonContract.Presenter {

    private  NotificaitonContract.View mView;
    private NotificationModel notModel;


    public NotificationPresenter(NotificaitonContract.View mView) {
        notModel = new NotificationModel();
        this.mView = mView;
        this.mView.setPresenter(this);
    }

    @Override
    public void getnotificationlist(Context context, boolean isshow) {
        notModel.getnotificationlist(context,isshow ,new ICallback<List<Notification>>() {
            @Override
            public void onSucceed(List<Notification> data) {
                mView.shownotificationlist(data);
            }

            @Override
            public void onError(String msg) {

            }
        });
    }

    @Override
    public void start() {

    }
}
