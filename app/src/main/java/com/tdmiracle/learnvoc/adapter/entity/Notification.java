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

package com.tdmiracle.learnvoc.adapter.entity;


import org.litepal.crud.LitePalSupport;

import java.util.Date;

/**
 * 创建日期：2021/4/14 10:38
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： Notification.java
 * 类说明：系统通知实体类
 */
public class Notification extends LitePalSupport {
    int id;
    String not_title;
    String not_content;
    String not_time;//查看时间
    boolean isShow;
    boolean isCheck;
    Date create_time;//创建时间

    public Notification(){

    }

    public Notification(int id, String not_title, String not_content, String not_time, boolean isShow, boolean isCheck, Date create_time) {
        this.id = id;
        this.not_title = not_title;
        this.not_content = not_content;
        this.not_time = not_time;
        this.isShow = isShow;
        this.isCheck = isCheck;
        this.create_time = create_time;
    }


    public String getNot_title() {
        return not_title;
    }

    public void setNot_title(String not_title) {
        this.not_title = not_title;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public boolean isShow() {
        return isShow;
    }

    public void setShow(boolean show) {
        isShow = show;
    }

    public boolean isCheck() {
        return isCheck;
    }

    public void setCheck(boolean check) {
        isCheck = check;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getNot_content() {
        return not_content;
    }

    public void setNot_content(String not_content) {
        this.not_content = not_content;
    }

    public String getNot_time() {
        return not_time;
    }

    public void setNot_time(String not_time) {
        this.not_time = not_time;
    }



    @Override
    public String toString() {
        return "Notification{" +
                "id=" + id +
                ", not_titel='" + not_title      + '\'' +
                ", not_content='" + not_content + '\'' +
                ", not_time='" + not_time + '\'' +
                ", isshow=" + isShow +
                ", ischeck=" + isCheck +
                '}';
    }
}
