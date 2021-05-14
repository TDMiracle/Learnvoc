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

package com.tdmiracle.learnvoc.fragment.recite;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.ReciteWordsActivity;
import com.tdmiracle.learnvoc.activity.SelectWordsBookActivity;
import com.tdmiracle.learnvoc.adapter.entity.EverydaySentence;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.HttpUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.utils.service.JsonSerializationService;
import com.xuexiang.xutil.app.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 创建日期：2021/4/17 14:39
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ReciteWordsFragment.java
 * 类说明：
 */
public class ReciteWordsFragment extends BaseFragment {

    private String[] reciteCount = {"10","20","30","40","50","60","70","80","90","100"};

    @BindView(R.id.spinner_count)
    Spinner sp;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.start_recite)
    Button btn_start;
    @BindView(R.id.word_recite_book)
    TextView word_book;
    @BindView(R.id.word_recite_remainDay)
    TextView remain_day_count;
    @BindView(R.id.word_recite_todayRemainWords)
    TextView remain_today_words;
    @BindView(R.id.word_recite_learntCount)
    TextView word_recite_learntCount;
    @BindView(R.id.word_recite_remainWords)
    TextView word_recite_remainWords;

    private Intent intent;
    private int requestCode;//单词书选择请求码
    String bookName = "四级";//用户选择单词书
    String bookWordsCount;//用户单词书数总数
    int todayWordsCount;//用户选择的每日背诵单词数

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_recit_words, container, false);
        ButterKnife.bind(this,rootView);
        initSpinner();
        initViews();
        return rootView;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recit_words;
    }

    @Override
    protected void initViews() {
        progressBar.setProgress(0);
        progressBar.setMax(100);
    }

    //初始化单词数下拉框
    private void initSpinner(){
        //声明一个下拉列表的数组适配器
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.item_selete,reciteCount);
        //设置数组适配器的布局样式
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //从布局文件中获取名叫sp_dialog的下拉框
//        Spinner sp = (Spinner) findViewById(R.id.spinner_count);
        //设置下拉框的标题，不设置就没有难看的标题了
        sp.setPrompt("每日学习量");
        //设置下拉框的数组适配器
        sp.setAdapter(starAdapter);
        //设置下拉框默认的显示第一项
//        sp.setSelection(0);
        //给下拉框设置选择监听器，一旦用户选中某一项，就触发监听器的onItemSelected方法
        sp.setOnItemSelectedListener(new MySelectedListener());
    }

    //Spinner监听器
    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            todayWordsCount = Integer.parseInt(reciteCount[i]);
           //XToastUtils.toast("您选择的是："+reciteCount[i],Toast.LENGTH_SHORT);
        }

        @Override
        public void onNothingSelected(AdapterView<?> adapterView) {

        }
    }

    @OnClick({R.id.start_recite,R.id.word_recite_book})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_recite:
                Intent intent1 = new Intent(getActivity(),ReciteWordsActivity.class);
                intent1.putExtra("bookName",bookName);
                intent1.putExtra("todayWordsCount",todayWordsCount);
                startActivity(intent1);
//                ActivityUtils.startActivity(ReciteWordsActivity.class);
                break;
            case R.id.word_recite_book:
                intent = new Intent(this.getContext(),SelectWordsBookActivity.class);
                requestCode = 1;
                startActivityForResult(intent,requestCode);
                break;
        }
    }

    //活动回调方法
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data.getStringExtra("bookName") != null && data.getStringExtra("bookWordsCount") != null){
            bookName = data.getStringExtra("bookName");
            bookWordsCount = data.getStringExtra("bookWordsCount");
            // 根据上面发送过去的请求吗来区别
            switch (requestCode) {
                case 0:
                    break;
                case 1:
                {
                    word_book.setText(bookName + "书");
                    word_recite_learntCount.setText(0+ "");
                    word_recite_remainWords.setText(bookWordsCount);
                    break;
                }
                default:
                    break;
            }
        }
    }
}