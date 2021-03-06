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
import com.tdmiracle.learnvoc.dao.RowWordsDao;
import com.tdmiracle.learnvoc.dao.WordDao;
import com.tdmiracle.learnvoc.dao.WordTestQuestionDao;
import com.tdmiracle.learnvoc.dao.WordsBookDao;
import com.tdmiracle.learnvoc.dao.daoImpl.NotificationDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.RowWordsDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.UserDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordTestQuestionDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsBookDaoImpl;
import com.tdmiracle.learnvoc.module.LoginInfo;
import com.tdmiracle.learnvoc.module.RowWords;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordTestQuestion;
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
 * ???????????????2021/4/7 16:05
 * @author TD.Miracle
 * @version 1.0
 * ??????????????? SplashActivity.java
 * ????????????
 */
public class SplashActivity extends BaseSplashActivity implements CancelAdapt {

    private final String TAG = "SplashActivity";
    public DBManager dbHelper;

    @Override
    protected long getSplashDurationMillis() {
        return 2000;
    }

    /**
     * activity?????????????????????
     */
    @Override
    protected void onCreateActivity() {
        initSplashView(R.drawable.xui_config_bg_splash);
        startSplash(false);
        //?????????????????????
        dbHelper = new DBManager(this);
        dbHelper.openDatabase();
        dbHelper.closeDatabase();
        //??????????????????
        addTestData();
    }

    //??????????????????????????????
    private void addTestData() {
        /**??????????????????*/
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
//        User user = userDao.findUserById("2").get(0);
//        user.setCreate_time(new Date());
//        user.setUpdate_time(new Date());
//        user.setGender("???");
//        user.setSchool("??????????????????");
//        userDao.updateUserinfo(user);
        /**????????????**/
//        UserDaoImpl userDao = new UserDaoImpl();
//        User newUser = userDao.findUserById("2").get(0);
//        newUser.setId(2);
//        //??????????????????
//        MyApp app = (MyApp) getApplication();
//        app.setUser(newUser);

//        User newUser = new User();
//        Log.d(TAG, "newUser: " + newUser.toString());
        /**
         * ????????????
         */
//        Notification notification1 = new Notification();
//        Notification notification2 = new Notification();
//        Notification notification3 = new Notification();
//        notification2.setCreate_time(new Date());
//        notification2.setNot_title("????????????");
//        notification2.setNot_content("???????????????~????????????????????????????????????O(???_???)O");
//        notification2.setCheck(false);
//        notification2.setShow(true);
//        notification3.setCreate_time(new Date());
//        notification3.setNot_title("????????????");
//        notification3.setNot_content("?????????????????????~????????????????????????????????????O(???_???)O");
//        notification3.setCheck(false);
//        notification3.setShow(true);
//        NotificationDao notificationDao = new NotificationDaoImpl();
//        notificationDao.IncreaseNotification(notification2);
//        notificationDao.IncreaseNotification(notification3);

        /*??????????????????*/
        //???????????????
//        WordsBook book1 = new WordsBook("????????????",3662,"????????????");
//        WordsBook book2 = new WordsBook("????????????",2087,"????????????");
//        WordsBook book3 = new WordsBook("????????????",2032,"????????????");
//        WordsBook book4 = new WordsBook("????????????",1133,"????????????");
//        WordsBook book5 = new WordsBook("????????????",1217,"????????????");
//        WordsBook book6 = new WordsBook("????????????",5396,"????????????");
//        WordsBook book7 = new WordsBook("????????????",3599,"????????????");
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

//        /*??????????????????*/
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
        /*?????????????????????*/
//        RowWordsDao rowWordsDao = new RowWordsDaoImpl();
//        RowWords rowWord = new RowWords("vocabulary",1," [v????k??bj??l??ri]","??????");
//        RowWords rowWord1 = new RowWords("vocabularies",1,"  [v??????k??bj??l??riz]","?????????");
//        RowWords rowWord2 = new RowWords(" typically",1,"  [??t??p??kli]","??????;??????;?????????;??????????????????;????????????;??????");
//        rowWordsDao.addRowWordByUser(newUser,rowWord);
//        rowWordsDao.addRowWordByUser(newUser,rowWord1);
//        rowWordsDao.addRowWordByUser(newUser,rowWord2);
//        List<RowWords> foundRowWords = rowWordsDao.findUserAllRowWords(newUser);
//        String result = "";
//        for(RowWords word : foundRowWords){
//            result += word.toString();
//        }
//        Log.d(TAG, "addTestData: " + result);

        /*????????????????????????*/
//        WordTestQuestionDao wordTestQuestionDao = new WordTestQuestionDaoImpl();
//        List<WordTestQuestion> wordTestQuestions = wordTestQuestionDao.findWordTestQuestionByType(1);
//        Log.d(TAG, "WordTestQuestion: " + wordTestQuestions.get(0).toString());
    }


    /**
     * ???????????????????????????
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

//    private void loginOrGoMainPage() {
//        if (TokenUtils.hasToken()) {
//            ActivityUtils.startActivity(MainActivity.class);
//        } else {
//            ActivityUtils.startActivity(LoginActivity.class);
//        }
//        finish();
//    }
    private void loginOrGoMainPage() {
        ActivityUtils.startActivity(LoginActivity.class);
        finish();
    }

    /**
     * ????????????????????????
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return KeyboardUtils.onDisableBackKeyDown(keyCode) && super.onKeyDown(keyCode, event);
    }
}
