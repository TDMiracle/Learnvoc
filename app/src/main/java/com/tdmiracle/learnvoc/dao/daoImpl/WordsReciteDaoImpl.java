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

import android.database.Cursor;
import android.util.Log;

import com.tdmiracle.learnvoc.module.WordsRecite;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.FormatUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class WordsReciteDaoImpl {
    private final String TAG = "WordsReciteDaoImpl";

    /**
     * 获取今日背诵单词
     * @param userId
     * @param wordType
     * @param count
     * @return
     */
    public ArrayList<WordsRecite> getNewRecite(int userId, int wordType, int count){
        ArrayList<WordsRecite> list = new ArrayList<>();
        Cursor cursor = LitePal.findBySQL("select * from word where wordtype=? and id not in" +
                "(select word_id from wordsrecite where user_id=?) order by RANDOM() asc limit 0,?",wordType+"",userId+"",count+"");
        while(cursor.moveToNext()){
            int word_id = cursor.getInt(cursor.getColumnIndex("id"));
            WordsRecite wordsRecite = new WordsRecite();
            wordsRecite.setWord_id(word_id);
            wordsRecite.setUser_id(userId);
            list.add(wordsRecite);
        }
        cursor.close();
        return list;
    }

    /**
     * 获取用户全部单词列表
     * @param userId
     * @return
     */
    public List<WordsRecite> getWordsReciteList(int userId){
        return LitePal.where("user_id=?",userId+"").order("latest_time desc").find(WordsRecite.class);
    }

    /**
     * 获取用户全部背诵词数
     * @param userId
     * @return
     */
    public int getUserTotalReciteCount(int userId){
        return LitePal.where("user_id = ?",userId+"").count(WordsRecite.class);
    }

    /**
     * 获取用户今日背诵词数
     * @param userId
     * @return
     */
    public int getUserTodayReciteCount(int userId) {
        int count = 0;
        List<WordsRecite> list = LitePal.where("user_id = ?",userId+"").find(WordsRecite.class);
        for(WordsRecite wordsRecite : list){
            if(FormatUtils.isSameDate(new Date(),wordsRecite.getLatest_time())){
                count ++;
            }
        }
        return count;
    }

    public int getUserBookReciteCount(int userId, int bookId){
        int count = 0;
        List<WordsRecite> list = LitePal.where("user_id = ?",userId+"").find(WordsRecite.class);
        for(WordsRecite wordsRecite : list){
            if(wordsRecite.getWord().getWordType() == bookId){
                count++;
            }
        }
        return count;
    }
}
