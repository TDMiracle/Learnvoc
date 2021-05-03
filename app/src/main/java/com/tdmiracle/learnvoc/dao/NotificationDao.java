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

package com.tdmiracle.learnvoc.dao;

import com.tdmiracle.learnvoc.adapter.entity.Notification;
import com.tdmiracle.learnvoc.module.User;

import java.util.List;

public interface NotificationDao {
    /**
     * 查询通知列表
     * @return
     */
    public List<Notification> findNotificationList();

    /**
     * 添加通知
     * @param notification
     * @return
     */
    public Boolean IncreaseNotification(Notification notification);
}
