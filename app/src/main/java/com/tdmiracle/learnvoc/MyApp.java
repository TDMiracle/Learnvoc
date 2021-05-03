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

package com.tdmiracle.learnvoc;

import android.app.Application;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.ColorSpace;

import androidx.multidex.MultiDex;

import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.utils.sdkinit.ANRWatchDogInit;
import com.tdmiracle.learnvoc.utils.sdkinit.UMengInit;
import com.tdmiracle.learnvoc.utils.sdkinit.XBasicLibInit;
import com.tdmiracle.learnvoc.utils.sdkinit.XUpdateInit;

import org.litepal.LitePal;
import org.litepal.tablemanager.Connector;


/**
 * 创建日期：2021/4/7 14:53
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： MyApp.java
 * 类说明：全局类
 */
public class MyApp extends Application {
    //内存中缓存user对象
    User user;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        //解决4.x运行崩溃的问题
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        initLibs();
    }

    /**
     * 初始化基础库
     */
    private void initLibs() {
        XBasicLibInit.init(this);

        XUpdateInit.init(this);

        //运营统计数据运行时不初始化
        if (!MyApp.isDebug()) {
            UMengInit.init(this);
        }

        //ANR监控
        ANRWatchDogInit.init();
        //litepal初始化
        LitePal.initialize(this);
        LitePal.getDatabase();
        //获取sqlite数据库实例
        SQLiteDatabase db = Connector.getDatabase();
    }


    /**
     * @return 当前app是否是调试开发模式
     */
    public static boolean isDebug() {
        return BuildConfig.DEBUG;
    }

    /**
     *  获取和设置全局对象
     *  这些共享数据只在应用程序的生命周期内有效，当该应用程序被杀死，这些数据也会被清空，所以只能存储一些具备 临时性的共享数据
     * */
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
