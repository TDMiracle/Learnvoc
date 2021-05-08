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
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.ReviewWordsAdapter;
import com.tdmiracle.learnvoc.adapter.SelectWordBookAdapter;
import com.tdmiracle.learnvoc.dao.WordsBookDao;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsBookDaoImpl;
import com.tdmiracle.learnvoc.module.WordsBook;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SelectWordsBookActivity extends AppCompatActivity {

    @BindView(R.id.select_wordBook_toolbar)
    Toolbar toolbar;
    @BindView(R.id.select_wordBook_recycleView)
    RecyclerView recyclerView;
    List<WordsBook> wordsBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_words_book);
        ButterKnife.bind(this);
        loadDatas();
        initViews();
    }

    private void loadDatas() {
        WordsBookDao dao = new WordsBookDaoImpl();
        wordsBooks = dao.findWordsBookList();
    }

    private void initViews() {
        SelectWordBookAdapter adapter = new SelectWordBookAdapter(wordsBooks,this);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);//GridLlayout样式
        recyclerView.setLayoutManager(gridLayoutManager);
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