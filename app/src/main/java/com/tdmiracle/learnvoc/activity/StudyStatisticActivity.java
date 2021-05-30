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

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.SelectWordBookAdapter;
import com.tdmiracle.learnvoc.adapter.StudyStatisticAdapter;
import com.tdmiracle.learnvoc.dao.WordsBookDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserWordBookDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsBookDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserWordsBook;
import com.tdmiracle.learnvoc.module.WordsBook;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/5/30 15:02
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： StudyStatisticActivity.java
 * 类说明：我的——学习数据
 */
public class StudyStatisticActivity extends AppCompatActivity {

    private final String TAG = "StudyStatisticActivity";

    @BindView(R.id.study_statistic_toolbar)
    Toolbar toolbar;
    @BindView(R.id.study_statistic_recycleView)
    RecyclerView recyclerView;
    List<UserWordsBook> wordsBooks;
    User globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_study_statistic);
        ButterKnife.bind(this);
        loadDatas();
        initViews();
    }
    private void loadDatas() {
        //获取全局变量
        MyApp app = (MyApp) getApplication();
        globalUser = app.getUser();
        UserWordBookDaoImpl dao = new UserWordBookDaoImpl();
        dao.updateUserWordsBook(globalUser);
        wordsBooks = dao.getAllUserWordsBook(globalUser);
    }

    private void initViews() {
        StudyStatisticAdapter adapter = new StudyStatisticAdapter(wordsBooks,this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);//GridLlayout样式
        linearLayoutManager.setOrientation(RecyclerView.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
}