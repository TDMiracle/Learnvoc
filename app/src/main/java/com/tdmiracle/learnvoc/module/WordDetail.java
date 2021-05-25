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


/**
 * 创建日期：2021/5/24 21:26
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordDetail.java
 * 类说明：voc.db, vocabulary模型
 */
public class WordDetail {
    String word;
    String hans;
    String tongyis;
    String means;
    String englishMeans;
    String lijus;

    public WordDetail() {
    }

    @Override
    public String toString() {
        return "WordDetail{" +
                "word='" + word + '\'' +
                ", hans='" + hans + '\'' +
                ", tongyis='" + tongyis + '\'' +
                ", means='" + means + '\'' +
                ", englishMeans='" + englishMeans + '\'' +
                ", lijus='" + lijus + '\'' +
                '}';
    }

    public WordDetail(String word, String hans, String tongyis, String means, String englishMeans, String lijus) {
        this.word = word;
        this.hans = hans;
        this.tongyis = tongyis;
        this.means = means;
        this.englishMeans = englishMeans;
        this.lijus = lijus;
    }

    public String getLijus() {
        return lijus;
    }

    public void setLijus(String lijus) {
        this.lijus = lijus;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getHans() {
        return hans;
    }

    public void setHans(String hans) {
        this.hans = hans;
    }

    public String getTongyis() {
        return tongyis;
    }

    public void setTongyis(String tongyis) {
        this.tongyis = tongyis;
    }

    public String getMeans() {
        return means;
    }

    public void setMeans(String means) {
        this.means = means;
    }

    public String getEnglishMeans() {
        return englishMeans;
    }

    public void setEnglishMeans(String englishMeans) {
        this.englishMeans = englishMeans;
    }
}
