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
 * 创建日期：2021/5/5 14:30
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordsBook.java
 * 类说明：单词书
 */
public class WordsBook extends LitePalSupport {
    int id;
    int count;//总词数
    String name;
    String description;

    public WordsBook(){

    }

    public WordsBook(int id, int count, String name, String description) {
        this.id = id;
        this.count = count;
        this.name = name;
        this.description = description;
    }

    @Override
    public String toString() {
        return "WordsBook{" +
                "id=" + id +
                ", count=" + count +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
