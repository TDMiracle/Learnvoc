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
 * 创建日期：2021/4/16 20:22
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： EverydaySentence.java
 * 类说明：每日一句实体
 */
public class EverydaySentence {
    //日期 ”2021-4-16“
    private String title;
    //英文短语
    private String content;
    //短语翻译
    private String note;
    //来源：词霸每日一句
    private String caption;
    //中方图
    private String picture;
    //大图
    private String picture2;
    //小正方图
    private String picture3;
    //英文朗读
    private String tts;
    //前一天
    private String last_title;
    //后一天
    private String next_title;
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public String getPicture2() {
        return picture2;
    }

    public void setPicture2(String picture2) {
        this.picture2 = picture2;
    }

    public String getPicture3() {
        return picture3;
    }

    public void setPicture3(String picture3) {
        this.picture3 = picture3;
    }

    public String getTts() {
        return tts;
    }

    public void setTts(String tts) {
        this.tts = tts;
    }

    public String getLast_title() {
        return last_title;
    }

    public void setLast_title(String last_title) {
        this.last_title = last_title;
    }

    public String getNext_title() {
        return next_title;
    }

    public void setNext_title(String next_title) {
        this.next_title = next_title;
    }


    @Override
    public String toString() {
        return "EverydaySentence{" +
                "title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", note='" + note + '\'' +
                ", caption='" + caption + '\'' +
                ", picture='" + picture + '\'' +
                ", picture2='" + picture2 + '\'' +
                ", picture3='" + picture3 + '\'' +
                ", tts='" + tts + '\'' +
                ", last_title='" + last_title + '\'' +
                ", next_title='" + next_title + '\'' +
                '}';
    }
}
