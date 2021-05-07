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
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.widget.TimerCircle;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/5/7 16:20
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestWordsActivity2.java
 * 类说明：听音辩义
 */
public class TestWordsActivity2 extends AppCompatActivity {

    @BindView(R.id.word_test2_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.test2_word_timer)
    TimerCircle timer;
    @BindView(R.id.word_test2_knowA)
    Button answerA;
    @BindView(R.id.word_test2_knowB)
    Button answerB;
    @BindView(R.id.word_test2_knowC)
    Button answerC;
    @BindView(R.id.word_test2_knowD)
    Button answerD;
    @BindView(R.id.word_test2_answerNone)
    Button answerNone;
    @BindView(R.id.word_test2_Unknown)
    Button answerUnknown;
    @BindView(R.id.word_test2_voice)
    ImageButton Img_voice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_words2);
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