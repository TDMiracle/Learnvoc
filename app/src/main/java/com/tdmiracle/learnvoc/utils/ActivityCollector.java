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


import android.app.Activity;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2021/4/9 20:00
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ActivityCollector.java
 * 类说明：用于对当前所有活动统一管理
 */
public class ActivityCollector {
    public static List<Activity> activities = new ArrayList<>();

    public static void addActivity(Activity activity){
        activities.add(activity);
    }

    public static void removeActivity(Activity activity){
        activities.remove(activity);
    }

    //关闭所有活动
    public static void finishAll(){
        for(Activity activity : activities){
            activity.finish();;
        }
        activities.clear();
    }

}
