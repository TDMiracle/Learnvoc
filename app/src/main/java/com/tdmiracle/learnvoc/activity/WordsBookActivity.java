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
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.WordListAdapter;
import com.tdmiracle.learnvoc.adapter.WordsBookAdapter;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/5/6 12:24
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordsBookActivity.java
 * 类说明：我的单词本
 */
public class WordsBookActivity extends AppCompatActivity {

    @BindView(R.id.word_book_toolbar)
    Toolbar toolbar;
    @BindView(R.id.word_book_tab)
    TabLayout tabLayout;
    @BindView(R.id.word_book_recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.word_book_wordsCount)
    TextView wordsBookCount;

    private String[] titles = new String[]{"已学单词","未学单词","生词本"};
    List<Word> words = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_book);
        ButterKnife.bind(this);
        initTab();
        loadData();
        WordsBookAdapter adapter = new WordsBookAdapter(words);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);//GridLlayout样式
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    //加载recyclerView数据
    private void loadData() {
        Word word;
        for(int i = 0; i < 20 ; i++){
            word = new Word("abandoned","英 [əˈbændənd]","adj.\n" +
                    "被离弃的;被遗弃的;被抛弃的;(人、行为)放纵的，不羁的",0);
            words.add(word);
        }
    }

    private void initTab() {
        //初始化tab
        for(int i=0;i<titles.length;i++){
            tabLayout.addTab(tabLayout.newTab());
        }
        for(int i=0;i<titles.length;i++){
            tabLayout.getTabAt(i).setText(titles[i]);
        }
        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                XToastUtils.toast("选中的"+tab.getText());
            }
            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
//                Toast.makeText(mContext, "未选中的"+tab.getText(), Toast.LENGTH_SHORT).show();
            }
            @Override
            public void onTabReselected(TabLayout.Tab tab) {
//                Toast.makeText(mContext, "复选的"+tab.getText(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}