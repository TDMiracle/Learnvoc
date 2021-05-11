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

import com.tdmiracle.learnvoc.dao.UserWordTestDao;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserWordTest;

import org.litepal.LitePal;

import java.util.List;

public class UserWordTestDaoImpl implements UserWordTestDao {
    private final String TAG = "UserWordTestDaoImpl";

    @Override
    public List<UserWordTest> getUserWordTestByCount(User user, int count) {
        int recordCount = LitePal.count(UserWordTest.class);//获取记录条数，用于设置查询偏移量
        List<UserWordTest> userWordTestList = LitePal.where("user_id=?"+ user.getId()).
                where("is_show=0").find(UserWordTest.class);
        Log.d(TAG, "getUserWordTestByCount: "+ userWordTestList.size());
        return userWordTestList;
    }

    @Override
    public UserWordTest getUserWordTest(User user) {
        return LitePal.where("user_id = "+ user.getId()).findLast(UserWordTest.class);
    }

    @Override
    public boolean addUserWordTest(UserWordTest userWordTest) {
        return userWordTest.save();
    }
}
