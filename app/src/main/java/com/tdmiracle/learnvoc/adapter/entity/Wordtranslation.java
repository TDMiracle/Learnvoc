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
 * 创建日期：2021/5/2 20:22
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： Wordtranslation.java
 * 类说明：单词书词汇翻译实体
 */
public class Wordtranslation {
    String word;
    String content;

    public Wordtranslation(){

    }
    public Wordtranslation(String word, String content) {
        this.word = word;
        this.content = content;
    }

    @Override
    public String toString() {
        return "Wordtranslation{" +
                "word='" + word + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
