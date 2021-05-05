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
 * 创建日期：2021/5/5 14:52
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordsRecite.java
 * 类说明：单词背诵表。表示用户与单词多对多关系
 */
public class WordsRecite extends LitePalSupport {
    int id;
    int user_id;//外键，User
    int word_id;//外键，Word
    int familiarity;//单词熟悉度，越大表示越属，最大为10
    int times;//背诵遍数
    Date first_time;//首次背诵时间
    Date latest_time;//最新背诵时间

    public WordsRecite() {
    }

    public WordsRecite(int id, int user_id, int word_id, int familiarity, int times, Date first_time, Date latest_time) {
        this.id = id;
        this.user_id = user_id;
        this.word_id = word_id;
        this.familiarity = familiarity;
        this.times = times;
        this.first_time = first_time;
        this.latest_time = latest_time;
    }

    @Override
    public String toString() {
        return "WordsRecite{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", word_id=" + word_id +
                ", familiarity=" + familiarity +
                ", times=" + times +
                ", first_time=" + first_time +
                ", latest_time=" + latest_time +
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

    public int getWord_id() {
        return word_id;
    }

    public void setWord_id(int word_id) {
        this.word_id = word_id;
    }

    public int getFamiliarity() {
        return familiarity;
    }

    public void setFamiliarity(int familiarity) {
        this.familiarity = familiarity;
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Date getFirst_time() {
        return first_time;
    }

    public void setFirst_time(Date first_time) {
        this.first_time = first_time;
    }

    public Date getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(Date latest_time) {
        this.latest_time = latest_time;
    }
}
