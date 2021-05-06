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

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xutil.app.ActivityUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReciteWordsActivity extends AppCompatActivity {

    @BindView(R.id.word_recite_fav)
    ImageView add_fav;
    @BindView(R.id.word_recite_btn_next)
    Button btn_next;
    @BindView(R.id.word_recite_btn_notice)
    Button btn_notice;
    @BindView(R.id.word_recite_remainCount)
    TextView remainCount;
    @BindView(R.id.word_recite_reviewCount)
    TextView reviewCount;
    @BindView(R.id.word_recite_word)
    TextView recite_word;
    @BindView(R.id.word_recite_yinbiao)
    TextView recite_yinbiao;
    @BindView(R.id.word_recite_translation)
    TextView recite_translation;

    boolean is_added = false;//添加生词本标记

    //全局user
    User globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite_words);
        ButterKnife.bind(this);
    }

    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.word_recite_fav,R.id.word_recite_btn_next})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.word_recite_fav:
                if(!is_added){
                    add_fav.setImageResource(R.drawable.word_add_fav_added);
                    XToastUtils.toast("成功添加到生词本");
                    is_added = !is_added;
                }
                else {
                    add_fav.setImageResource(R.drawable.word_add_fav_normal);
                    XToastUtils.toast("已从生词本移出");
                    is_added = !is_added;
                }
                break;
            case R.id.word_recite_btn_next:
                XToastUtils.toast("next");
                break;
        }
    }
}