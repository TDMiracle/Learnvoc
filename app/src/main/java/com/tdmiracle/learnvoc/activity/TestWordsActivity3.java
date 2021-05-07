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
 * 创建日期：2021/5/7 16:50
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestWordsActivity3.java
 * 类说明：单词拼写
 */
public class TestWordsActivity3 extends AppCompatActivity {

    @BindView(R.id.word_test3_1)
    Button answer1;
    @BindView(R.id.word_test3_2)
    Button answer2;
    @BindView(R.id.word_test3_3)
    Button answer3;
    @BindView(R.id.word_test3_4)
    Button answer4;
    @BindView(R.id.word_test3_5)
    Button answer5;
    @BindView(R.id.word_test3_6)
    Button answer6;
    @BindView(R.id.word_test3_7)
    Button answer7;
    @BindView(R.id.word_test3_8)
    Button answer8;
    @BindView(R.id.word_test3_9)
    Button answer9;
    @BindView(R.id.test3_word_timer)
    TimerCircle timer;
    @BindView(R.id.word_test3_word)
    TextView word;
    @BindView(R.id.word_test3_translation)
    TextView translation;
    @BindView(R.id.word_test3_progressBar)
    ProgressBar progressBar;
    @BindView(R.id.word_test3_remainCount)
    TextView remainCount;
    @BindView(R.id.word_test3_reviewCount)
    TextView reviewCount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test_words3);
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