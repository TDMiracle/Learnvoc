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

import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserWordTest;
import com.tdmiracle.learnvoc.module.UserWordsBook;
import com.tdmiracle.learnvoc.module.WordsBook;
import com.tdmiracle.learnvoc.module.WordsRecite;

import org.litepal.LitePal;

import java.util.Date;
import java.util.List;

public class UserWordBookDaoImpl {
    private final String TAG = "UserWordBookDaoImpl";

    /**
     * 获取用户单词书学习信息
     * @param user
     * @return
     */
    public List<UserWordsBook> getAllUserWordsBook(User user){
        List<UserWordsBook> userWordsBooks = LitePal.where("user_id=?",user.getId()+"").order("add_time desc").find(UserWordsBook.class);
        return userWordsBooks;
    }

    /**
     * 更新用户单词书信息
     * @param user
     */
    public boolean updateUserWordsBook(User user){
        List<WordsRecite> wordsRecites = LitePal.where("user_id=? and is_grasp = 1",user.getId()+"").find(WordsRecite.class);
        List<UserWordsBook> userWordsBooks = LitePal.where("user_id=?",user.getId()+"").find(UserWordsBook.class);
        if(userWordsBooks.size() != 0 && wordsRecites.size() != 0){// 用户已添加单词书并已背诵
            for(UserWordsBook userWordsBook : userWordsBooks){// 遍历每个词汇书
                userWordsBook.setStudiedCount(0);// 清零已背单词
                int count = 0;
                for(WordsRecite wordsRecite : wordsRecites){// 统计每个单词
                    if(wordsRecite.getWord().getWordType() == (userWordsBook.getWordsBook_id() - 1)){
                        count ++;
                    }
                }
                userWordsBook.setStudiedCount(count);
                userWordsBook.setFinish_time(new Date());
            }
        }
        for(UserWordsBook userWordsBook : userWordsBooks){
            Log.d(TAG, "updateUserWordsBook: "+ userWordsBook.getWordsBook().getName() + userWordsBook.getStudiedCount());
        }
        return LitePal.saveAll(userWordsBooks);
    }

    /**
     * 添加用户单词书
     * @param user
     * @param wordsBook_id
     * @return
     */
    public Boolean addUserWordsBook(User user, int wordsBook_id){
        Log.d(TAG, "addUserWordsBook: wordsBook_id="+wordsBook_id);
        //1. 先检查用户是否已经添加该单词书
        List<UserWordsBook> oldUserBooks = LitePal.where("user_id=? and wordsbook_id=?",user.getId()+"",wordsBook_id+"").limit(1).find(UserWordsBook.class);
        if(oldUserBooks.size() == 0){//2. 未添加，则添加
            UserWordsBook userWordsBook = new UserWordsBook();
            userWordsBook.setUser_id(user.getId());
            userWordsBook.setWordsBook_id(wordsBook_id);
            userWordsBook.setAdd_time(new Date());
            Log.d(TAG, "addUserWordsBook: " + userWordsBook.toString());
            return userWordsBook.save();
        }
        return false;
    }
}
