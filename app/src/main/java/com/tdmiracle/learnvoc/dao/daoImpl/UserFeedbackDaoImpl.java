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


import com.tdmiracle.learnvoc.dao.UserFeedbackDao;
import com.tdmiracle.learnvoc.module.UserFeedback;

/**
 * 创建日期：2021/5/7 10:11
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： UserFeedbackDaoImpl.java
 * 类说明：用户意见反馈
 */
public class UserFeedbackDaoImpl implements UserFeedbackDao {
    @Override
    public Boolean IncreaseUserFeedback(UserFeedback userFeedback) {
        if(userFeedback.getContent().isEmpty()) return false;
        return userFeedback.save();
    }
}
