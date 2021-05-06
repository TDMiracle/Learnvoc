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


import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.textview.MaterialTextView;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.WordListAdapter;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.dao.UserDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.SQLiteUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import org.litepal.tablemanager.Connector;

import java.util.ArrayList;
import java.util.List;


/**
 * 创建日期：2021/5/6 12:24
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordsListActivity.java
 * 类说明：首页单词书
 */
public class WordsListActivity extends BaseActivity {

    private static final String TAG = "word";
    RecyclerView rc;
    Toolbar toolbar;
    MaterialTextView tv_wordCount;
    //List<Word> words = new ArrayList<Word>();
    List<Word> words;
    //单词书类型
    int wordBookType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_list);
        toolbar = (Toolbar) findViewById(R.id.tool_bar);
        toolbar.setTitle("");
        setSupportActionBar(toolbar);//系统标题栏设置
        tv_wordCount = (MaterialTextView) findViewById(R.id.tv_wordCount);
        rc = (RecyclerView) findViewById(R.id.recycler_view);
        init();
        loadData();
        WordListAdapter adapter = new WordListAdapter(words);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);//GridLlayout样式
        rc.setLayoutManager(gridLayoutManager);
        rc.setAdapter(adapter);
    }

    @SuppressLint("SetTextI18n")
    private void init(){
        //获取主页传递单词书数据
        Intent intent = getIntent();
        wordBookType = intent.getIntExtra("type",ConstUtils.WordsType.SIJI);
        //添加单词书标题
        TextView titleTv = new TextView(this);
        titleTv.setTextColor(ContextCompat.getColor(this, R.color.white));
        titleTv.setText(ConstUtils.WordsType.getWordsType(wordBookType)+"词汇书");
        titleTv.setGravity(Gravity.CENTER);
        titleTv.setTextSize(18);
        Toolbar.LayoutParams layoutParams = new Toolbar.LayoutParams(Toolbar.LayoutParams.WRAP_CONTENT,
                Toolbar.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.CENTER;
        titleTv.setLayoutParams(layoutParams);
        toolbar.addView(titleTv);
    }

    //单词数据填充
    private void loadData() {
        SQLiteUtils vocDb = new SQLiteUtils(this.getBaseContext());
        words = vocDb.getData(ConstUtils.WordsStoreType.getWordsStoreType(wordBookType),0);
        tv_wordCount.setText("共收录" + words.size()+"词");
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}