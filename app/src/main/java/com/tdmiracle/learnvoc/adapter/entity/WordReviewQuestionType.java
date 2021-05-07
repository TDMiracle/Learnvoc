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

package com.tdmiracle.learnvoc.adapter.entity;


/**
 * 创建日期：2021/5/7 14:30
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordTestQuestionType.java
 * 类说明：单词测试题型
 */
public class WordReviewQuestionType {
    int id;
    String title;
    int questionCount;
    String type;

    public WordReviewQuestionType() {
    }

    public WordReviewQuestionType(int id, String title, String type, int questionCount) {
        this.id = id;
        this.title = title;
        this.questionCount = questionCount;
        this.type = type;
    }

    @Override
    public String toString() {
        return "WordTestQuestionType{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", questionCount=" + questionCount +
                ", type='" + type + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getQuestionCount() {
        return questionCount;
    }

    public void setQuestionCount(int questionCount) {
        this.questionCount = questionCount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
