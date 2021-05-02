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

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.entity.EverydaySentenceEntity;
import com.tdmiracle.learnvoc.adapter.entity.Wordtranslation;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.HttpUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.utils.service.JsonSerializationService;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Calendar;

/**
 * 单词详情
 */
public class WordDetailActivity extends BaseActivity {
    private static final String TAG = "WordDetailActivity";
    TextView textView;
    ImageView imageView;
    String tittleWord;
    String detailInfo;

    CollapsingToolbarLayout collapsingToolbarLayout;
    //单词联网词典查询结果
    Wordtranslation wordtranslation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_word_detail);
        Toolbar bar = (Toolbar) findViewById(R.id.toolbar);
        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsongbar);
        imageView = (ImageView) findViewById(R.id.image_voc);
        textView = (TextView) findViewById(R.id.word_textview);
        setSupportActionBar(bar);//设置默认标题栏
        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null){
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        /*绑定数据*/
        loadData();
        collapsingToolbarLayout.setTitle(tittleWord);//设置标题
        imageView.setImageResource(R.drawable.sky);//设置标题栏背景
    }


    public void loadData(){
        //获取传入词汇
        Intent intent = getIntent();
        tittleWord = intent.getStringExtra("word");
        //联网查词
        getHttpData(tittleWord);
    }

    //绑定词意
    private void setWordContent(Wordtranslation wordtranslation){
        if (wordtranslation.getContent() != null){
            detailInfo = "网络词意：\n"+wordtranslation.getContent();
            textView.setText(detailInfo);//文本信息
        }

    }

    /**
     * 调用天行词典接口获取单词对象
     * @param word
     */
    private void getHttpData(String word) {
        try {
            StringCallback callback = new StringCallback() {
                @Override
                public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                    try {
                        String json = response.body().toString();
                        //Log.d(TAG,json);
                        JSONObject jsonObject = new JSONObject(json);
                        int code = jsonObject.getInt("code");
                        //请求接口,获取词典实体
                        if (code == 200) {
                            JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                            JSONObject jsonNewsList = jsonArray.getJSONObject(0);
                            //Log.d(TAG, "newslist: " + jsonNewsList.toString());
                            JsonSerializationService jsonSerializationService = new JsonSerializationService();
                            wordtranslation = jsonSerializationService.parseObject(jsonNewsList.toString(), Wordtranslation.class);
                            Log.d(TAG, "onSuccess: "+ wordtranslation.toString());
                            setWordContent(wordtranslation);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                }
            };
            HttpUtils.getEnglishWord(this, word, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            finish();
        }
        return true;
    }
}