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

}
