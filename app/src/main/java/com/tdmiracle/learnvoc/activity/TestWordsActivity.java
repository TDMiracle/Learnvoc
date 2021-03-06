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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.dao.UserWordTestDao;
import com.tdmiracle.learnvoc.dao.WordTestQuestionDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserWordTestDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordTestQuestionDaoImpl;
import com.tdmiracle.learnvoc.fragment.common.AnswerFragment;
import com.tdmiracle.learnvoc.fragment.recite.ReciteFragment;
import com.tdmiracle.learnvoc.module.WordTestQuestion;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.widget.TimerCircle;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * ???????????????2021/5/7 16:21
 * @author TD.Miracle
 * @version 1.0
 * ??????????????? TestWordsActivity.java
 * ??????????????????????????????????????????+??????
 */
public class TestWordsActivity extends FragmentActivity implements Chronometer.OnChronometerTickListener {

    @BindView(R.id.word_test_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.word_test_chronometer)
    Chronometer chronometer;
    @BindView(R.id.word_test_showAnswer)
    Button showAnswer;
    @BindView(R.id.word_test_fragVp)
    ViewPager2 answerFragVp;
    @BindView(R.id.word_test_btn_previous)
    Button btn_previous;
    @BindView(R.id.word_test_btn_submit)
    Button btn_submit;
    @BindView(R.id.word_test_btn_next)
    Button btn_next;

    private AlertDialog.Builder builder;//??????????????????
    private List<Fragment> fragmentList;//?????????
    private List<WordTestQuestion> wordTestQuestionList;//?????????
    String type;//???????????????????????????
    private int nowPager = 0;//??????????????????
    private int minute = 0;
    private int second = 0;
    private int progressBarCount = 10;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_words);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        //?????????????????????
//        type = getIntent().getExtras().get("type").toString().trim();
        progressBar.setProgress(progressBarCount);
        progressBar.setMax(100);
        setChronometer();
        initNet(type);
    }

    private void initNet(String type) {
        fragmentList = new ArrayList<>();
        //??????????????????
        final ProgressDialog progressDialog = new ProgressDialog(TestWordsActivity.this, R.layout.customdialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("???????????????...");
        progressDialog.show();
        WordTestQuestionDao wordTestQuestionDao = new WordTestQuestionDaoImpl();
        wordTestQuestionList = wordTestQuestionDao.findWordTestQuestionByType(1);
        for(int i = 0; i < wordTestQuestionList.size(); i++){
            fragmentList.add(new AnswerFragment(wordTestQuestionList.get(i)));
        }
        //???????????????
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        answerFragVp.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        answerFragVp.setAdapter(adapter);
        answerFragVp.setOffscreenPageLimit(10);
        answerFragVp.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
        progressDialog.dismiss();
    }

    //???????????????
    private void setChronometer() {
        chronometer.setText(nowtime());
        chronometer.start();
        chronometer.setOnChronometerTickListener(this);
    }

    //????????????
    private String nowtime() {
        if (second < 10) {
            return (minute + ":0" + second);
        } else {
            return (minute + ":" + second);
        }
    }

    @OnClick({R.id.word_test_btn_submit,R.id.word_test_btn_next,R.id.word_test_btn_previous,
            R.id.word_test_showAnswer,R.id.word_test_chronometer})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.word_test_btn_submit:
                //???????????????????????????????????????
                initAlertDialog();
                builder.show();
                break;
            case R.id.word_test_btn_next:
                //??????????????????????????????????????????????????????????????????
                if (nowPager == fragmentList.size() - 1) {
                    Toast.makeText(TestWordsActivity.this, "????????????????????????!", Toast.LENGTH_SHORT).show();
                } else {
                    answerFragVp.setCurrentItem( ++nowPager);
                    if(progressBarCount <= 90)
                        progressBarCount += 10;
                    progressBar.setProgress(progressBarCount);
                }
                break;
            case R.id.word_test_btn_previous:
                //???????????????????????????????????????????????????????????????
                if (nowPager == 0) {
                    Toast.makeText(TestWordsActivity.this, "???????????????!", Toast.LENGTH_SHORT).show();
                } else {
                    answerFragVp.setCurrentItem(--nowPager);
                    if(progressBarCount > 10)
                        progressBarCount -= 10;
                    progressBar.setProgress(progressBarCount);
                }
                break;
            case R.id.word_test_showAnswer:
                break;
            case R.id.word_test_chronometer:
            default:break;
        }
    }

    private void initAlertDialog() {
        //???????????????
        builder = new AlertDialog.Builder(TestWordsActivity.this);
        builder.setTitle("??????");
        builder.setMessage("???????????????????");
        builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setNegativeButton("??????", null);

    }


    @Override
    public void onChronometerTick(Chronometer chronometer) {
        second++;
        if (second == 59) {
            minute++;
            second = 00;
        }
    }

    //?????????????????????????????????????????????fragment?????????fragment???????????????xml??????
    public class MyAdapter extends FragmentStateAdapter {

        private List<Fragment> fragmentList;
        public MyAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle, List<Fragment> fragmentList) {
            super(fm, lifecycle);
            this.fragmentList = fragmentList;
        }

        @NonNull
        @Override
        public Fragment createFragment(int position) {
            return fragmentList.get(position);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}
