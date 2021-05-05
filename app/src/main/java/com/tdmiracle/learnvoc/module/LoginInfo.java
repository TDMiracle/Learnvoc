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

import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.FormatUtils;

import org.litepal.crud.LitePalSupport;

import java.util.Date;


/**
 * 创建日期：2021/5/5 14:30
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： LoginInfo.java
 * 类说明：用户登录信息
 */
public class LoginInfo extends LitePalSupport {
    int id;
    String password = "";
    Date latest_login;//最近登录时间

    public LoginInfo() {
    }

    public LoginInfo( String password) {
        this.password = password;
    }

    public Date getLatest_login() {
        return latest_login;
    }

    public void setLatest_login(Date latest_login) {
        this.latest_login = latest_login;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return "LoginInfo{" +
                ", password='" + password + '\'' +
                ",latest_login" + FormatUtils.getDateTimeString(latest_login) +
                '}';
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

}
