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

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.tabs.TabLayout;
import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.WordListAdapter;
import com.tdmiracle.learnvoc.adapter.WordsBookAdapter;
import com.tdmiracle.learnvoc.dao.RowWordsDao;
import com.tdmiracle.learnvoc.dao.daoImpl.RowWordsDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsReciteDaoImpl;
import com.tdmiracle.learnvoc.module.RowWords;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordsRecite;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


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
    @BindView(R.id.word_book_show)
    Button btn_show_mean;
    @BindView(R.id.word_book_plus)
    ImageView word_plus;
    @BindView(R.id.word_book_minus)
    ImageView word_minus;

    boolean is_show = true;
    private String[] titles = new String[]{"生词本","已学单词"};
    List<Word> words = new ArrayList<>();
    WordsBookAdapter adapter;
    RowWordsDao rowWordsDao = new RowWordsDaoImpl();

    User user;//全局用户

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_words_book);
        ButterKnife.bind(this);

        //获取全局变量
        MyApp app = (MyApp) getApplication();
        user = app.getUser();

        initTab();
        loadData();
        adapter = new WordsBookAdapter(words);
        //GridLayoutManager gridLayoutManager = new GridLayoutManager(this,4);//GridLlayout样式
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL,false));
//        recyclerView.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this,DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }


    //加载recyclerView数据
    private void loadData() {
        /*初始加载生词本数据*/
        List<RowWords> rowWords = rowWordsDao.findUserAllRowWords(user);
        Word word;
        for(int i = 0; i < rowWords.size() ; i++){
            word = new Word(rowWords.get(i).getWord(),rowWords.get(i).getYinbiao(),rowWords.get(i).getTranslation(),rowWords.get(i).getWordtype());
            words.add(word);
        }
        wordsBookCount.setText(words.size()+" ");
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
                if(tab.getText().equals(titles[0])){
                    /*初始加载生词本数据*/
                    List<RowWords> rowWords = rowWordsDao.findUserAllRowWords(user);
                    Word word;
                    //清空原始数据
                    words.clear();
                    words = new ArrayList<>();
                    for(int i = 0; i < rowWords.size() ; i++){
                        word = new Word(rowWords.get(i).getWord(),rowWords.get(i).getYinbiao(),rowWords.get(i).getTranslation(),rowWords.get(i).getWordtype());
                        words.add(word);
                    }
                    adapter.notifyAdapter(words,true);
                    wordsBookCount.setText(words.size()+" ");
                }
                else {
                    /****************已学单词数据获取************************/
                    List<WordsRecite> reciteList = new WordsReciteDaoImpl().getWordsReciteList(user.getId());
                    adapter.notifyAdapter2(reciteList,1);
                    wordsBookCount.setText(reciteList.size()+" ");
                }
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

    @OnClick({R.id.word_book_show,R.id.word_book_plus,R.id.word_book_minus})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.word_book_show:{
                if(is_show){
                    btn_show_mean.setBackgroundColor(getResources().getColor(R.color.UI_recite_heavy));
                    is_show = false;
                }
                else {
                    btn_show_mean.setBackgroundColor(getResources().getColor(R.color.UI_recite));
                    is_show = true;
                }
                break;
            }
            case R.id.word_book_plus:{
                showAddDialog();
                break;
            }
            case R.id.word_book_minus:{
                words.remove(0);
                adapter.notifyAdapter(words,true);
                break;
            }
            default:break;
        }
    }

    //添加生词
    private void showAddDialog(){
        LayoutInflater factory = LayoutInflater.from(this);
        final View textEntryView = factory.inflate(R.layout.dialog_insert_rowword, null);
        final EditText editWord = (EditText) textEntryView.findViewById(R.id.insert_dialog_word);
        final EditText editYinBiao = (EditText)textEntryView.findViewById(R.id.insert_dialog_yinbiao);
        final EditText editTranslation = (EditText)textEntryView.findViewById(R.id.insert_dialog_translation);
        AlertDialog.Builder ad1 = new AlertDialog.Builder(this);
        ad1.setTitle("增加新单词:");
        ad1.setIcon(android.R.drawable.ic_input_add);
        ad1.setView(textEntryView);
        ad1.setPositiveButton("是", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {
                String word = editWord.getText().toString().trim();
                String yinbiao = editYinBiao.getText().toString().trim();
                String translation = editTranslation.getText().toString();
                int wordType = ConstUtils.WordsType.Unknown;
                RowWords rowWord = new RowWords(word, wordType,yinbiao,translation);
                boolean resultCode = rowWordsDao.addRowWordByUser(user,rowWord);
                Word addedWord = new Word(word,yinbiao,translation,wordType);
                words.add(0,addedWord);
                adapter.addData(0,addedWord);
                if(resultCode){
                    XToastUtils.toast("单词添加成功！");
                }
                else {
                    XToastUtils.toast("单词添加失败！");

                }
            }
        });
        ad1.setNegativeButton("否", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int i) {

            }
        });
        ad1.show();// 显示对话框
    }
}