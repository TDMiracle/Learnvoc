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

package com.tdmiracle.learnvoc.dao.daoImpl;

import com.tdmiracle.learnvoc.adapter.entity.Notification;
import com.tdmiracle.learnvoc.dao.NotificationDao;
import com.tdmiracle.learnvoc.module.User;

import org.litepal.LitePal;

import java.util.List;

/**
 * 通知消息操纵类
 */
public class NotificationDaoImpl implements NotificationDao {
    @Override
    public List<Notification> findNotificationList() {
        List<Notification> notifications = LitePal.order("create_time desc").find(Notification.class);
        return notifications;
    }

    @Override
    public Boolean IncreaseNotification(Notification notification) {
        return notification.save();
    }
}
