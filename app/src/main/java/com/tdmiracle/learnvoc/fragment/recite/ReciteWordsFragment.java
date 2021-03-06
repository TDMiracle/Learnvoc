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
import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.ReciteWordsActivity;
import com.tdmiracle.learnvoc.activity.SelectWordsBookActivity;
import com.tdmiracle.learnvoc.adapter.entity.EverydaySentence;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsBookDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsReciteDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.WordsBook;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.HttpUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.utils.service.JsonSerializationService;
import com.xuexiang.xutil.app.ActivityUtils;

import org.json.JSONException;
import org.json.JSONObject;
import org.litepal.LitePal;

import java.io.IOException;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * ???????????????2021/4/17 14:39
 * @author TD.Miracle
 * @version 1.0
 * ??????????????? ReciteWordsFragment.java
 * ????????????
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
    @BindView(R.id.word_recite_todayWords)
    TextView today_words;
    @BindView(R.id.word_recite_learntCount)
    TextView word_recite_learntCount;
    @BindView(R.id.word_recite_remainWords)
    TextView word_recite_remainWords;

    private Intent intent;
    private int requestCode;//????????????????????????
    String bookName = "??????";//?????????????????????
    String bookWordsCount;//????????????????????????
    int todayWordsCount;//????????????????????????????????????
    User globalUser;

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
        //??????????????????
        MyApp app = (MyApp) getActivity().getApplication();
        globalUser = app.getUser();
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
        WordsBook wordsBook = LitePal.where("name=?",ConstUtils.WordsType.getWordsType(0)).findFirst(WordsBook.class);
        bookWordsCount = wordsBook.getCount()+"";
        //???????????????????????????????????????
        updateInfo();
    }

    //?????????????????????????????????
    @Override
    public void onResume() {
        updateInfo();
        super.onResume();
    }

    //???????????????????????????????????????
    private void updateInfo() {
        WordsReciteDaoImpl dao = new WordsReciteDaoImpl();
        int todayCount = dao.getUserTodayReciteCount(globalUser.getId());
        int totalBookReciteCount = dao.getUserBookReciteCount(globalUser.getId(), ConstUtils.WordsType.getWordsByName(bookName));
        today_words.setText(todayCount+"");//????????????????????????
        word_recite_learntCount.setText("");//??????????????????
        word_recite_learntCount.setText(totalBookReciteCount+ "");//????????????????????????????????????
        int progressBarData;
        if(bookWordsCount != null){
            progressBar.setMax(Integer.parseInt(bookWordsCount));
            progressBar.setProgress(totalBookReciteCount);
            if(Integer.parseInt(bookWordsCount) != 0 && todayCount != 0)
                remain_day_count.setText(Integer.parseInt(bookWordsCount)/todayCount+"");
        }
    }

    //???????????????????????????
    private void initSpinner(){
        //??????????????????????????????????????????
        ArrayAdapter<String> starAdapter = new ArrayAdapter<String>(this.getActivity(),R.layout.item_selete,reciteCount);
        //????????????????????????????????????
        starAdapter.setDropDownViewResource(R.layout.item_dropdown);
        //??????????????????????????????sp_dialog????????????
//        Spinner sp = (Spinner) findViewById(R.id.spinner_count);
        //???????????????????????????????????????????????????????????????
        sp.setPrompt("???????????????");
        //?????????????????????????????????
        sp.setAdapter(starAdapter);
        //???????????????????????????????????????
//        sp.setSelection(0);
        //???????????????????????????????????????????????????????????????????????????????????????onItemSelected??????
        sp.setOnItemSelectedListener(new MySelectedListener());
    }

    //Spinner?????????
    class MySelectedListener implements AdapterView.OnItemSelectedListener{

        @Override
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
            todayWordsCount = Integer.parseInt(reciteCount[i]);
           //XToastUtils.toast("??????????????????"+reciteCount[i],Toast.LENGTH_SHORT);
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

    //??????????????????
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if(data.getStringExtra("bookName") != null && data.getStringExtra("bookWordsCount") != null){
            bookName = data.getStringExtra("bookName");
            bookWordsCount = data.getStringExtra("bookWordsCount");
            // ?????????????????????????????????????????????
            switch (requestCode) {
                case 0:
                    break;
                case 1:
                {
                    word_book.setText(bookName + "???");
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