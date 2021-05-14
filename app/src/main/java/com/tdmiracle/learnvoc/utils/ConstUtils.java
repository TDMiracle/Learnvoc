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

package com.tdmiracle.learnvoc.utils;

import com.tdmiracle.learnvoc.adapter.entity.WordReviewQuestionType;

public class ConstUtils {

    //单词掌握程度
    public static class WordMaster{
        public static final int Level_1 = 1;//完全一摸黑*_*
        public static final int Level_2 = 2;//看到答案,对正确单词有印象
        public static final int Level_3 = 3;//在提示的情况下,能想起正确的单词
        public static final int Level_4 = 4;//稍微吃力的回想一下,可以正确回忆出单词
        public static final int Level_5 = 5;//回想一下,可以正确回忆出单词
        public static final int Level_6 = 6;//单词记得非常好
        public static String getTypeDesc(int level){
            String desc = "未定义熟悉度";
            switch (level){
                case Level_1:
                    desc = "完全一摸黑*_*";
                    break;
                case Level_2:
                    desc = "看到答案,对正确单词有印象";
                    break;
                case Level_3:
                    desc = "在提示的情况下,能想起正确的单词";
                    break;
                case Level_4:
                    desc = "稍微吃力的回想一下,可以正确回忆出单词";
                    break;
                case Level_5:
                    desc = "回想一下,可以正确回忆出单词";
                    break;
                case Level_6:
                    desc = "单词记得非常好";
                    break;
            }
            return desc;
        }
    }
    //单词复习提醒
    public static class WordReviewType{
        public static final int KanYingXuanZhong = 1;
        public static final int KanZhongXuanYing = 2;
        public static final int TingYinBianYi = 3;
        public static final int PinXieTianKong = 4;
        public static final int TingLiSuJi = 5;
        public static final int QuanPinLianXi = 6;
        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case KanYingXuanZhong:
                    desc = "看英选中";
                    break;
                case KanZhongXuanYing:
                    desc = "看中选英";
                    break;
                case TingYinBianYi:
                    desc = "听音辩义";
                    break;
                case PinXieTianKong:
                    desc = "拼写填空";
                    break;
                case TingLiSuJi:
                    desc = "听音速记";
                    break;
                case QuanPinLianXi:
                    desc = "全拼练习";
                    break;
            }
            return desc;
        }
    }

    //日历选择
    public static class CalenderSelect{
        public static final int CALENDER_SELECT = 1;
        public static final int CALENDER_NOT_SELECT = 0;
    }


    //通知消息
    public static class NotificationType{
        public static final int HELP = 0;
        public static final int ANNOUNCEMENT = 1;

        public static String getTypeDesc(int code){
            String desc = "未知种类";
            switch (code){
                case HELP:
                    desc = "帮助";
                    break;
                case ANNOUNCEMENT:
                    desc = "公告";
                    break;
            }
            return desc;
        }
    }

    //首页词库选择
    public static class WordsType{
        public static final int SIJI = 0;
        public static final int LIUJI = 1;
        public static final int gaozhong = 2;
        public static final int chuzhong = 3;
        public static final int yasi = 4;
        public static final int tuofu = 5;
        public static final int kaoyan = 6;
        public static final int kaobo = 7;
        public static final int Unknown = -1;

        public static int getWordsByName(String name){
            int wordsId = 0;
            if(name.equals(getWordsType(0))) {
                wordsId = 0;
            }
            else if(name.equals(getWordsType(1))){
                wordsId = 1;
            }
            else if(name.equals(getWordsType(2))){
                wordsId = 2;
            }
            else if(name.equals(getWordsType(3))){
                wordsId = 3;
            }
            else if(name.equals(getWordsType(4))){
                wordsId = 4;
            }
            else if(name.equals(getWordsType(5))){
                wordsId = 5;
            }
            else if(name.equals(getWordsType(6))){
                wordsId = 6;
            }
            return wordsId;
        }

        public static String getWordsType(int code){
            String desc = "未知种类";
            switch (code){
                case SIJI:
                    desc = "四级";
                    break;
                case LIUJI:
                    desc = "六级";
                    break;
                case gaozhong:
                    desc = "高中";
                    break;
                case chuzhong:
                    desc = "初中";
                    break;
                case yasi:
                    desc = "雅思";
                    break;
                case tuofu:
                    desc = "托福";
                    break;
                case kaoyan:
                    desc = "考研";
                    break;
                case kaobo:
                    desc = "考博";
                    break;
                case Unknown:
                    desc = "未知";
                    break;

            }
            return desc;
        }
    }

    //数据表词库选择
    public static class WordsStoreType{
        public static final int SIJI = 0;
        public static final int LIUJI = 1;
        public static final int gaozhong = 2;
        public static final int chuzhong = 3;
        public static final int yasi = 4;
        public static final int tuofu = 5;
        public static final int kaoyan = 6;
        public static final int kaobo = 7;

        public static String getWordsStoreType(int code){
            String desc = "未知种类";
            switch (code){
                case SIJI:
                    desc = "cet4";
                    break;
                case LIUJI:
                    desc = "cet6";
                    break;
                case gaozhong:
                    desc = "gaozhong";
                    break;
                case chuzhong:
                    desc = "chuzhong";
                    break;
                case yasi:
                    desc = "ielts";
                    break;
                case tuofu:
                    desc = "toefl";
                    break;
                case kaoyan:
                    desc = "kaoyan";
                    break;
                case kaobo:
                    desc = "kaobo";
                    break;

            }
            return desc;
        }
    }

}
