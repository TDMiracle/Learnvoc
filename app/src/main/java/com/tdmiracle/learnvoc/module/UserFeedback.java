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

package com.tdmiracle.learnvoc.module;

import org.litepal.crud.LitePalSupport;

import java.util.Date;


/**
 * 创建日期：2021/5/7 10:08
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： UserFeedback.java
 * 类说明：用户反馈
 */
public class UserFeedback extends LitePalSupport {
    int id;
    String content;
    Date send_time;
    int user_id;//外键，User

    public UserFeedback() {
    }

    public UserFeedback(int id, String content, Date send_time, int user_id) {
        this.id = id;
        this.content = content;
        this.send_time = send_time;
        this.user_id = user_id;
    }

    @Override
    public String toString() {
        return "UserFeedback{" +
                "id=" + id +
                ", content='" + content + '\'' +
                ", send_time=" + send_time +
                ", user_id=" + user_id +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getSend_time() {
        return send_time;
    }

    public void setSend_time(Date send_time) {
        this.send_time = send_time;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }
}
