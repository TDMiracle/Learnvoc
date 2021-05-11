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
 * 创建日期：2021/5/7 16:21
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestWordsActivity.java
 * 类说明：单词测试主界面，做题+统计
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

    private AlertDialog.Builder builder;//对话框建造者
    private List<Fragment> fragmentList;//答题页
    private List<WordTestQuestion> wordTestQuestionList;//题目集
    String type;//上层传递的题目类型
    private int nowPager = 0;//当前题目页数
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
        //获取测试题类型
//        type = getIntent().getExtras().get("type").toString().trim();
        progressBar.setProgress(progressBarCount);
        progressBar.setMax(100);
        setChronometer();
        initNet(type);
    }

    private void initNet(String type) {
        fragmentList = new ArrayList<>();
        //进度条对话框
        final ProgressDialog progressDialog = new ProgressDialog(TestWordsActivity.this, R.layout.customdialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("获取题目中...");
        progressDialog.show();
        WordTestQuestionDao wordTestQuestionDao = new WordTestQuestionDaoImpl();
        wordTestQuestionList = wordTestQuestionDao.findWordTestQuestionByType(1);
        for(int i = 0; i < wordTestQuestionList.size(); i++){
            fragmentList.add(new AnswerFragment(wordTestQuestionList.get(i)));
        }
        //设置适配器
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

    //设置计时器
    private void setChronometer() {
        chronometer.setText(nowtime());
        chronometer.start();
        chronometer.setOnChronometerTickListener(this);
    }

    //现在时间
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
                //否则初始化并展示提交对话框
                initAlertDialog();
                builder.show();
                break;
            case R.id.word_test_btn_next:
                //如果是最后一题，则谈吐司提醒，否则下移一道题
                if (nowPager == fragmentList.size() - 1) {
                    Toast.makeText(TestWordsActivity.this, "已经是最后一题了!", Toast.LENGTH_SHORT).show();
                } else {
                    answerFragVp.setCurrentItem( ++nowPager);
                    if(progressBarCount <= 90)
                        progressBarCount += 10;
                    progressBar.setProgress(progressBarCount);
                }
                break;
            case R.id.word_test_btn_previous:
                //如果是第一题，则谈吐司提醒，否则上移一道题
                if (nowPager == 0) {
                    Toast.makeText(TestWordsActivity.this, "已经到头啦!", Toast.LENGTH_SHORT).show();
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
        //新建对话框
        builder = new AlertDialog.Builder(TestWordsActivity.this);
        builder.setTitle("提示");
        builder.setMessage("是否确定提交?");
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
            }

        });
        builder.setNegativeButton("取消", null);

    }


    @Override
    public void onChronometerTick(Chronometer chronometer) {
        second++;
        if (second == 59) {
            minute++;
            second = 00;
        }
    }

    //这是适配器，让每个标题对应一个fragment，每个fragment中加载一个xml文件
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
