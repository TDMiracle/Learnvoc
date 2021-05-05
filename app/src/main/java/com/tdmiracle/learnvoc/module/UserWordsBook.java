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
 * 创建日期：2021/5/5 14:32
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： UserWordsBook.java
 * 类说明：用户单词书表。表示用户与单词书多对多关系
 */
public class UserWordsBook extends LitePalSupport {
    int id;
    int user_id;//User表外键
    int wordsBook_id;//WordsBook表外键
    Date add_time;//单词书添加时间
    Date finish_time;//单词书完成时间
    int studiedCount;//已学习数量

    public UserWordsBook() {
    }

    public UserWordsBook(int id, int user_id, int wordsBook_id, Date add_time, Date finish_time, int studiedCount) {
        this.id = id;
        this.user_id = user_id;
        this.wordsBook_id = wordsBook_id;
        this.add_time = add_time;
        this.finish_time = finish_time;
        this.studiedCount = studiedCount;
    }

    @Override
    public String toString() {
        return "UserWordsBook{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", wordsBook_id=" + wordsBook_id +
                ", add_time=" + add_time +
                ", finish_time=" + finish_time +
                ", studiedCount=" + studiedCount +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public int getWordsBook_id() {
        return wordsBook_id;
    }

    public void setWordsBook_id(int wordsBook_id) {
        this.wordsBook_id = wordsBook_id;
    }

    public Date getAdd_time() {
        return add_time;
    }

    public void setAdd_time(Date add_time) {
        this.add_time = add_time;
    }

    public Date getFinish_time() {
        return finish_time;
    }

    public void setFinish_time(Date finish_time) {
        this.finish_time = finish_time;
    }

    public int getStudiedCount() {
        return studiedCount;
    }

    public void setStudiedCount(int studiedCount) {
        this.studiedCount = studiedCount;
    }
}
