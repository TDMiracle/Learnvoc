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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;

/**
 * 单词详情
 */
public class WordDetailActivity extends BaseActivity {
    TextView textView;
    ImageView imageView;
    String x;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        CollapsingToolbarLayout collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsongbar);
        imageView = (ImageView) findViewById(R.id.image_voc);
        textView = (TextView) findViewById(R.id.word_textview);
        setSupportActionBar(bar);//设置默认标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        loadData();
        collapsingToolbarLayout.setTitle("Words");//设置标题
        imageView.setImageResource(R.drawable.sky);//设置标题栏找票
        textView.setText(x);//文本信息
    }

    /*
     * 用于数据展示， 我们通过StringBuilder 重复添加500次数据保存给x
     * */
    public void loadData(){
        StringBuilder y = new StringBuilder();
        for(int i =0;i<=500;i++){
            y.append("测试");
        }
        x = y.toString();
    }
}