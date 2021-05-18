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

import android.util.Log;

import com.tdmiracle.learnvoc.dao.UserDao;
import com.tdmiracle.learnvoc.module.LoginInfo;
import com.tdmiracle.learnvoc.module.User;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class UserDaoImpl implements UserDao {
    private final String TAG = "UserDaoImpl";

    @Override
    public Boolean IncreaseUser(User user, String password) {
        //同时更新loginInfo表
        LoginInfo login = new LoginInfo();
        login.setPassword(password);
        login.setLatest_login(new Date());
        //事物处理
        try {
            LitePal.beginTransaction();
            if (user.save() && login.save()) {
                //重新获取user设置user_id
                User savedUser = findUserByPhone(user.getPhone());
                Log.d(TAG, "IncreaseUser: " + savedUser.toString());
                login.setUser_id(savedUser.getId());
                login.save();
                LitePal.setTransactionSuccessful();
                return true;
            }
        } finally {
            LitePal.endTransaction();
        }

        return false;
    }

    @Override
    public List<User> findUserByUid(String uid) {
        List<User> user = LitePal.select()
                .where("uid = ?", uid)
                .limit(1)
                .find(User.class);
        return user;
    }

    @Override
    public List<User>   findUserById(String id) {
        List<User> user = LitePal.select()
                .where("id = ?", id)
                .limit(1)
                .find(User.class);
        return user;
    }

    @Override
    public List<User> findUserList() {
        List<User> users = LitePal.findAll(User.class);
        return users;
    }

    @Override
    public Boolean updatePassword(User user, String password) {
        List<LoginInfo> loginInfos = LitePal.select()
                .where("user_id = ?", user.getId()+"")
                .limit(1)
                .find(LoginInfo.class);
        LoginInfo login = loginInfos.get(0);
        login.setPassword(password);
        if (login.save()) {
            Log.d(TAG, "updatePassword: true");
            return true;
        }
        return false;
    }

    @Override
    public Boolean updateUserinfo(User user) {
        List<User> userInfos = LitePal.select()
                .where("user_id = ?", user.getUid())//根据用户账号更改密码
                .limit(1)
                .find(User.class);
        if (userInfos.size() == 0){
            Log.d(TAG, "updateUserInfo: user not exists");
            return  false;
        }
        user.update(user.getId());
        return true;
    }

    @Override
    public User findUserByPhone(String phone) {
        return LitePal.where("phone=?",phone).findLast(User.class);
    }
}
