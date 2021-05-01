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

import com.tdmiracle.learnvoc.module.User;

import java.util.List;

public interface UserDao {
    /**
     * 添加用户
     * @param user
     * @param password
     * @return
     */
    public Boolean IncreaseUser(User user, String password);


    /**
     * 根据用户ID获取用户信息
     * @param uid
     * @return
     */
    public List<User> findUserByID(String uid);


    /**
     * 查询用户列表
     * @return
     */
    public List<User> findUserList();

    /**
     * 更新密码
     * @param uid
     * @param password
     * @return
     */
    public Boolean updatePassword(String uid, String password);

    /**
     * 更新用户信息
     * @param user
     * @return
     */
    public Boolean updateUserinfo(User user);

}
