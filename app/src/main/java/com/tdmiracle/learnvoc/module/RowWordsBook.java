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

public class RowWordsBook extends LitePalSupport {
    int id;
    String uid;
    int wordtype;
    int wordId;
    String word;
    String yinbiao;
    String translation;

    public RowWordsBook(){
        
    }

    public RowWordsBook(int id, String uid, int wordtype, int wordId, String word, String yinbiao, String translation) {
        this.id = id;
        this.uid = uid;
        this.wordtype = wordtype;
        this.wordId = wordId;
        this.word = word;
        this.yinbiao = yinbiao;
        this.translation = translation;
    }

    @Override
    public String toString() {
        return "RowWordsBook{" +
                "id=" + id +
                ", uid='" + uid + '\'' +
                ", wordtype=" + wordtype +
                ", wordId=" + wordId +
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public int getWordtype() {
        return wordtype;
    }

    public void setWordtype(int wordtype) {
        this.wordtype = wordtype;
    }

    public int getWordId() {
        return wordId;
    }

    public void setWordId(int wordId) {
        this.wordId = wordId;
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
