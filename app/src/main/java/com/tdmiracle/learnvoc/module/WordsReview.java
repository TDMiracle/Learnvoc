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
 * 创建日期：2021/5/5 14:58
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordsReview.java
 * 类说明：单词复习表。表示用户与单词多对多关系
 */
public class WordsReview  extends LitePalSupport {
    int id;
    int user_id;//外键，User
    int word_id;//外键，Word
    int times;//复习遍数
    boolean is_grasp;//复习是否掌握
    int familiarity;//复习熟悉度
    Date latest_time;//最新背诵时间
    Date next_reviewTime;//下次背诵时间，由算法算出

    public WordsReview() {
    }

    public WordsReview(int id, int user_id, int word_id, int times, Date next_reviewTime, Date latest_time) {
        this.id = id;
        this.user_id = user_id;
        this.word_id = word_id;
        this.times = times;
        this.next_reviewTime = next_reviewTime;
        this.latest_time = latest_time;
    }

    public WordsReview(int id, int user_id, int word_id, int times, boolean is_grasp, int familiarity, Date latest_time, Date next_reviewTime) {
        this.id = id;
        this.user_id = user_id;
        this.word_id = word_id;
        this.times = times;
        this.is_grasp = is_grasp;
        this.familiarity = familiarity;
        this.latest_time = latest_time;
        this.next_reviewTime = next_reviewTime;
    }

    @Override
    public String toString() {
        return "WordsReview{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", word_id=" + word_id +
                ", times=" + times +
                ", is_grasp=" + is_grasp +
                ", familiarity=" + familiarity +
                ", latest_time=" + latest_time +
                ", next_reviewTime=" + next_reviewTime +
                '}';
    }

    public boolean isIs_grasp() {
        return is_grasp;
    }

    public void setIs_grasp(boolean is_grasp) {
        this.is_grasp = is_grasp;
    }

    public int getFamiliarity() {
        return familiarity;
    }

    public void setFamiliarity(int familiarity) {
        this.familiarity = familiarity;
    }

    public Date getNext_reviewTime() {
        return next_reviewTime;
    }

    public void setNext_reviewTime(Date next_reviewTime) {
        this.next_reviewTime = next_reviewTime;
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

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public Date getLatest_time() {
        return latest_time;
    }

    public void setLatest_time(Date latest_time) {
        this.latest_time = latest_time;
    }
}
