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

public class ConstUtils {
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
