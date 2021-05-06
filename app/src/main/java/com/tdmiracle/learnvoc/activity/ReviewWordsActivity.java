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
import android.widget.TextView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xutil.app.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReviewWordsActivity extends AppCompatActivity {

    @BindView(R.id.word_review_remainCount)
    TextView remainCount;
    @BindView(R.id.word_review_reviewCount)
    TextView reviewCount;
    @BindView(R.id.word_review_word)
    TextView word_word;
    @BindView(R.id.word_review_yinbiao)
    TextView word_yinbiao;
    @BindView(R.id.word_review_tran_A)
    TextView tran_A;
    @BindView(R.id.word_review_tran_B)
    TextView tran_B;
    @BindView(R.id.word_review_tran_C)
    TextView tran_C;
    @BindView(R.id.word_review_tran_D)
    TextView tran_D;
    @BindView(R.id.word_review_btn_notice)
    Button btn_notice;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review_words);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.word_review_btn_notice)
    public void onViewClicked() {
        XToastUtils.toast("提示信息");
    }
}