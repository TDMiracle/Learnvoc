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

package com.tdmiracle.learnvoc.activity;

import android.util.Log;
import android.view.KeyEvent;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.entity.Notification;
import com.tdmiracle.learnvoc.dao.NotificationDao;
import com.tdmiracle.learnvoc.dao.WordDao;
import com.tdmiracle.learnvoc.dao.WordsBookDao;
import com.tdmiracle.learnvoc.dao.daoImpl.NotificationDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.UserDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsBookDaoImpl;
import com.tdmiracle.learnvoc.module.LoginInfo;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordsBook;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.DBManager;
import com.tdmiracle.learnvoc.utils.MD5Utils;
import com.tdmiracle.learnvoc.utils.SQLiteUtils;
import com.tdmiracle.learnvoc.utils.SettingUtils;
import com.tdmiracle.learnvoc.utils.TokenUtils;
import com.tdmiracle.learnvoc.utils.Utils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xui.utils.KeyboardUtils;
import com.xuexiang.xui.widget.activity.BaseSplashActivity;
import com.xuexiang.xupdate.utils.Md5Utils;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.jessyan.autosize.internal.CancelAdapt;


/**
 * 创建日期：2021/4/7 16:05
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： SplashActivity.java
 * 类说明：
 */
public class SplashActivity extends BaseSplashActivity implements CancelAdapt {

    private final String TAG = "SplashActivity";
    public DBManager dbHelper;

    @Override
    protected long getSplashDurationMillis() {
        return 2000;
    }

    /**
     * activity启动后的初始化
     */
    @Override
    protected void onCreateActivity() {
        initSplashView(R.drawable.xui_config_bg_splash);
        startSplash(false);
        //加载单词数据库
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
        //添加测试数据
        addTestData();
    }

    //数据库中插入测试数据
    private void addTestData() {
        /**用户信息测试*/
//        User user = new User();
//        user.setUid("20210503");
//        user.setPhone("18888888888");
//        user.setBirthday(new Date());
//        user.setNickname("Miracle");
//        user.setSignature("Just do it right now!");
//        user.setEmail("hebuter@edu.com");
//        LoginInfo loginInfo = new LoginInfo();
//        String password = "123456";
//        loginInfo.setPassword(MD5Utils.md5(password));
//        user.setLoginInfo(loginInfo);
//        loginInfo.save();
//        user.save();
//        User user;
//        UserDaoImpl dao = new UserDaoImpl();
//        user = dao.findUserById("2").get(0);
//        user.setUid("hebuter_001");
//        dao.updateUserinfo(user);
//        Log.d(TAG, "addTestData: " + user.toString());
        UserDaoImpl userDao = new UserDaoImpl();
//        User user = userDao.findUserById("2").get(0);
//        user.setCreate_time(new Date());
//        user.setUpdate_time(new Date());
//        user.setGender("男");
//        user.setSchool("河北工业大学");
//        userDao.updateUserinfo(user);
        User newUser = userDao.findUserById("2").get(0);
//        Log.d(TAG, "newUser: " + newUser.toString());
        //存入全局对象
        MyApp app = (MyApp) getApplication();
        app.setUser(newUser);
        /**
         * 通知测试
         */
//        Notification notification1 = new Notification();
//        Notification notification2 = new Notification();
//        Notification notification3 = new Notification();
//        notification2.setCreate_time(new Date());
//        notification2.setNot_title("学习周报");
//        notification2.setNot_content("每周很重要~快来查收你上周得学习周报O(∩_∩)O");
//        notification2.setCheck(false);
//        notification2.setShow(true);
//        notification3.setCreate_time(new Date());
//        notification3.setNot_title("学习周报");
//        notification3.setNot_content("五一假期很重要~快来查收你上周得学习周报O(∩_∩)O");
//        notification3.setCheck(false);
//        notification3.setShow(true);
//        NotificationDao notificationDao = new NotificationDaoImpl();
//        notificationDao.IncreaseNotification(notification2);
//        notificationDao.IncreaseNotification(notification3);

        /*单词背诵注释*/
        //添加单词书
//        WordsBook book1 = new WordsBook("四级词汇",3662,"易拾单词");
//        WordsBook book2 = new WordsBook("六级词汇",2087,"易拾单词");
//        WordsBook book3 = new WordsBook("高中词汇",2032,"易拾单词");
//        WordsBook book4 = new WordsBook("初中词汇",1133,"易拾单词");
//        WordsBook book5 = new WordsBook("雅思词汇",1217,"易拾单词");
//        WordsBook book6 = new WordsBook("托福词汇",5396,"易拾单词");
//        WordsBook book7 = new WordsBook("考研词汇",3599,"易拾单词");
//        WordsBookDao dao = new WordsBookDaoImpl();
//        List<WordsBook> books = new ArrayList<>();
//        books.add(book1);
//        books.add(book2);
//        books.add(book3);
//        books.add(book4);
//        books.add(book5);
//        books.add(book6);
//        books.add(book1);
//        dao.IncreaseWordsBook(book1);

//        /*单词数据导入*/
//        WordDao wordDao = new WordDaoImpl();
//        SQLiteUtils vocDb = new SQLiteUtils(this);
//        List<Word> words;
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(0),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(ConstUtils.WordsType.SIJI);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(1),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(ConstUtils.WordsType.LIUJI);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(2),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(2);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(3),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(3);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(4),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(4);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(5),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(5);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//        /*******************************************************/
//        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(6),0);
//        for(Word word:words){
//            word.setId(0);
//            word.setWordType(6);
//        }
//        //Log.d(TAG, "addTestData: " + words.get(0).toString());
//        wordDao.IncreaseWords(words);
//    }
    }


    /**
     * 启动页结束后的动作
     */
    @Override
    protected void onSplashFinished() {
        if (SettingUtils.isAgreePrivacy()) {
            loginOrGoMainPage();
        } else {
            Utils.showPrivacyDialog(this, (dialog, which) -> {
                dialog.dismiss();
                SettingUtils.setIsAgreePrivacy(true);
                loginOrGoMainPage();
            });
        }
    }

    private void loginOrGoMainPage() {
        if (TokenUtils.hasToken()) {
            ActivityUtils.startActivity(MainActivity.class);
        } else {
            ActivityUtils.startActivity(LoginActivity.class);
        }
        finish();
    }

    /**
     * 菜单、返回键响应
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }
}
