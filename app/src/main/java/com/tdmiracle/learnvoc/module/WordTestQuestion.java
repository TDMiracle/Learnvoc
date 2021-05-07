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
 * 创建日期：2021/5/5 14:40
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordTestQuestion.java
 * 类说明：单词测试试题
 */
public class WordTestQuestion extends LitePalSupport {
    int id;
    String word;
    String yinbiao;
    int type;//ConstUntil.WordReviewType
    String choice_A;
    String choice_B;
    String choice_C;
    String choice_D;
    int rightChoice;//ABCD：1234

    public WordTestQuestion() {
    }

    public WordTestQuestion(int id, String word, String yinbiao, int type, String choice_A, String choice_B, String choice_C, String choice_D, int rightChoice) {
        this.id = id;
        this.word = word;
        this.yinbiao = yinbiao;
        this.type = type;
        this.choice_A = choice_A;
        this.choice_B = choice_B;
        this.choice_C = choice_C;
        this.choice_D = choice_D;
        this.rightChoice = rightChoice;
    }

    @Override
    public String toString() {
        return "WordTestQuestion{" +
                "id=" + id +
                ", word='" + word + '\'' +
                ", yinbiao='" + yinbiao + '\'' +
                ", type=" + type +
                ", choice_A='" + choice_A + '\'' +
                ", choice_B='" + choice_B + '\'' +
                ", choice_C='" + choice_C + '\'' +
                ", choice_D='" + choice_D + '\'' +
                ", rightChoice=" + rightChoice +
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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getChoice_A() {
        return choice_A;
    }

    public void setChoice_A(String choice_A) {
        this.choice_A = choice_A;
    }

    public String getChoice_B() {
        return choice_B;
    }

    public void setChoice_B(String choice_B) {
        this.choice_B = choice_B;
    }

    public String getChoice_C() {
        return choice_C;
    }

    public void setChoice_C(String choice_C) {
        this.choice_C = choice_C;
    }

    public String getChoice_D() {
        return choice_D;
    }

    public void setChoice_D(String choice_D) {
        this.choice_D = choice_D;
    }

    public int getRightChoice() {
        return rightChoice;
    }

    public void setRightChoice(int rightChoice) {
        this.rightChoice = rightChoice;
    }
}
