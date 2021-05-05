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
 * 创建日期：2021/5/5 14:45
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： UserWordTest.java
 * 类说明：用户单词测试表。表示用户与单词测试试题多对多关系
 */
public class UserWordTest extends LitePalSupport {
    int id;
    int user_id;//外键，User
    int wordTestQuestion_id;//外键，WordTestQuestion
    int userChoice;//ABCD:1234//用户选项
    boolean is_right;//是否正确
    Date test_time;//用户测试时间

    public UserWordTest() {
    }

    public UserWordTest(int id, int user_id, int wordTestQuestion_id, int userChoice, boolean is_right, Date test_time) {
        this.id = id;
        this.user_id = user_id;
        this.wordTestQuestion_id = wordTestQuestion_id;
        this.userChoice = userChoice;
        this.is_right = is_right;
        this.test_time = test_time;
    }

    @Override
    public String toString() {
        return "UserWordTest{" +
                "id=" + id +
                ", user_id=" + user_id +
                ", wordTestQuestion_id=" + wordTestQuestion_id +
                ", userChoice=" + userChoice +
                ", is_right=" + is_right +
                ", test_time=" + test_time +
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

    public int getWordTestQuestion_id() {
        return wordTestQuestion_id;
    }

    public void setWordTestQuestion_id(int wordTestQuestion_id) {
        this.wordTestQuestion_id = wordTestQuestion_id;
    }

    public int getUserChoice() {
        return userChoice;
    }

    public void setUserChoice(int userChoice) {
        this.userChoice = userChoice;
    }

    public boolean isIs_right() {
        return is_right;
    }

    public void setIs_right(boolean is_right) {
        this.is_right = is_right;
    }

    public Date getTest_time() {
        return test_time;
    }

    public void setTest_time(Date test_time) {
        this.test_time = test_time;
    }
}