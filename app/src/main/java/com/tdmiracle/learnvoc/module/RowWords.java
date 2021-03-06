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


/**
 * 创建日期：2021/5/5 14:25
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： RowWordsBook.java
 * 类说明：生词本
 */
public class RowWords extends LitePalSupport {
    int id;
    int user_id;//外键，关联User表
    int wordtype;//单词类型
    String word;
    String yinbiao;
    String translation;

    public RowWords(){
        
    }

    public RowWords(String word, int wordtype, String yinbiao, String translation) {
        this.wordtype = wordtype;
        this.word = word;
        this.yinbiao = yinbiao;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "RowWordsBook{" +
                "id=" + id +
                ", user_id'" + user_id + '\'' +
                ", wordtype=" + wordtype +
                ", word='" + word + '\'' +
                ", yinbiao='" + yinbiao + '\'' +
                ", translation='" + translation + '\'' +
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

    public int getWordtype() {
        return wordtype;
    }

    public void setWordtype(int wordtype) {
        this.wordtype = wordtype;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getYinbiao() {
        return yinbiao;
    }

    public void setYinbiao(String yinbiao) {
        this.yinbiao = yinbiao;
    }

    public String getTranslation() {
        return translation;
    }

    public void setTranslation(String translation) {
        this.translation = translation;
    }
}
