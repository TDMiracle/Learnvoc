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

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class FormatUtils {
    public static String getDateTimeString(Date date){
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        //得到字符串时间
        return formatter.format(date);
    }

    public static boolean isSameDay(Calendar cal1, Calendar cal2) {
        if(cal1 != null && cal2 != null) {
            return cal1.get(0) == cal2.get(0) && cal1.get(1) == cal2.get(1) && cal1.get(6) == cal2.get(6);
        } else {
            throw new IllegalArgumentException("The date must not be null");
        }
    }

    public static boolean isToday(Calendar calendar) {
        Calendar calendar1 = Calendar.getInstance();
        return isSameDay(calendar1,calendar);
    }

    public static boolean isSameDate(Date date1, Date date2){
        SimpleDateFormat fmt = new SimpleDateFormat("yyyyMMdd");
        return fmt.format(date1).equals(fmt.format(date2));
    }

    /**
     * 获取时间差
     */
    public static Long getSecondsNextEarlyMorning(int num) {
        Calendar cal = Calendar.getInstance();
        if (cal.get(Calendar.HOUR_OF_DAY) - num >= 0) {
            //如果当前时间大于等于8点 就计算第二天的8点的
            cal.add(Calendar.DAY_OF_YEAR, 1);
        } else {
            cal.add(Calendar.DAY_OF_YEAR, 0);
        }
        cal.set(Calendar.HOUR_OF_DAY, num);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.MILLISECOND, 0);
        Long seconds = (cal.getTimeInMillis() - System.currentTimeMillis());

        return seconds.longValue();
    }
}
