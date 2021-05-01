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

public class Word extends LitePalSupport {
    int id;
    String word;
    String yinbiao;
    String translation;
    //ConstUtils wordsType
    int wordType;

    public Word(){

    }

    public Word(String word, String yinbiao, String translation, int wordType) {
        this.word = word;
        this.yinbiao = yinbiao;
        this.translation = translation;
        this.wordType = wordType;
    }

    public Word(int id, String word, String yinbiao, String translation, int wordType) {
        this.id = id;
        this.word = word;
        this.yinbiao = yinbiao;
        this.translation = translation;
        this.wordType = wordType;
    }

    @Override
    public String toString() {
        return "Word{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", yinbiao='" + yinbiao + '\'' +
                ", translation='" + translation + '\'' +
                ", wordType=" + wordType +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getWordType() {
        return wordType;
    }

    public void setWordType(int wordType) {
        this.wordType = wordType;
    }
}

