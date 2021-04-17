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

import com.tdmiracle.learnvoc.activity.CalenderSelectActivity;

import java.util.Calendar;


/**
 * 创建日期：2021/4/17 15:53
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： CalenderEvent.java
 * 类说明：每日一句模块，日历选择事件类
 */
public class CalenderEvent {
    Calendar calendar;
    int isSelect;
    String message;

    public CalenderEvent(int isSelect, Calendar calendar) {
        this.calendar = calendar;
        this.isSelect = isSelect;
    }

    public int getIsSelect() {
        return isSelect;
    }

    public static CalenderEvent getInstance(int isSelect, Calendar calendar){
        return new CalenderEvent(isSelect,calendar);
    }
}
