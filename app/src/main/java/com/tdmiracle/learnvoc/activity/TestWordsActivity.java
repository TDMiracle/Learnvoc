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

import android.os.Bundle;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.widget.TimerCircle;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/5/7 16:21
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestWordsActivity.java
 * 类说明：英汉互测
 */
public class TestWordsActivity extends AppCompatActivity {

    @BindView(R.id.word_test_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.word_test_testResult)
    TextView testResult;
    @BindView(R.id.word_test_showAnswer)
    Button showAnswer;
    @BindView(R.id.word_test_word)
    TextView test_word;
    @BindView(R.id.word_test_yinbiao)
    TextView test_yinbiao;
    @BindView(R.id.test_word_timer)
    TimerCircle timer;
    @BindView(R.id.word_test_answerA)
    Button answerA;
    @BindView(R.id.word_test_answerB)
    Button answerB;
    @BindView(R.id.word_test_answerC)
    Button answerC;
    @BindView(R.id.word_test_answerD)
    Button answerD;
    @BindView(R.id.word_test_answerNone)
    Button answerNone;
    @BindView(R.id.word_test_answerUnknown)
    Button answerUnknown;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_words);
        ButterKnife.bind(this);
        init();
    }

    private void init() {
        progressBar.setProgress(50);
        progressBar.setMax(100);
        /*设置计时器*/
        timer.setDuration(5000,600000);//ms
        //设置计时结束回调
        timer.setFinishListenter(new TimerCircle.onFinishListener() {
            @Override
            public void onFinish() {

            }
        });
    }
}