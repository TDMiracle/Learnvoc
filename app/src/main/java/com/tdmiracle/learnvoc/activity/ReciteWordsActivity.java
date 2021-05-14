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
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.MyPageViewAdapter;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.dao.daoImpl.WordsReciteDaoImpl;
import com.tdmiracle.learnvoc.fragment.common.AnswerFragment;
import com.tdmiracle.learnvoc.fragment.common.ReciteWordFragment;
import com.tdmiracle.learnvoc.module.RowWords;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordsRecite;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xui.widget.dialog.DialogLoader;
import com.xuexiang.xui.widget.dialog.strategy.impl.MaterialDialogStrategy;
import com.xuexiang.xutil.app.ActivityUtils;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ReciteWordsActivity extends AppCompatActivity {

    private final String TAG = "ReciteWordsActivity";

    @BindView(R.id.word_recite_remainCount)
    TextView remainCount;
    @BindView(R.id.word_recite_fragVp)
    ViewPager2 viewPager;
    @BindView(R.id.word_recite_btn_previous)
    Button btn_previous;
    @BindView(R.id.word_recite_btn_next)
    Button btn_next;
    @BindView(R.id.word_recite_bookName)
    TextView tv_bookName;

    private List<Fragment> fragmentList;//背诵页
    List<WordsRecite> wordsRecites;//今日背诵单词记录

    private int nowPager = 0;//当前题目页数
    //全局user
    User globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recite_words);
        ButterKnife.bind(this);
        //获取全局变量
        MyApp app = (MyApp) getApplication();
        globalUser = app.getUser();
        loadData();
        init();
    }

    private void init() {
        fragmentList = new ArrayList<>();
        for(int i = 0; i < wordsRecites.size(); i++){
            fragmentList.add(new ReciteWordFragment(wordsRecites.get(i)));
        }
        //设置适配器
        MyPageViewAdapter adapter = new MyPageViewAdapter(getSupportFragmentManager(),getLifecycle(),fragmentList);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(10);
        viewPager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
            }
        });
    }

    private void loadData() {
        //获取传入单词数据
        String bookName = getIntent().getStringExtra("bookName");
        int todayWordsCount = getIntent().getIntExtra("todayWordsCount",10);
        int wordBookId = ConstUtils.WordsType.getWordsByName(bookName);
        //设置头部书名
        tv_bookName.setText(ConstUtils.WordsType.getWordsType(wordBookId));
        //获取单词数据
        wordsRecites = new WordsReciteDaoImpl().getNewRecite(globalUser.getId(),wordBookId,todayWordsCount);
        //设置剩余单词数目
        remainCount.setText(wordsRecites.size()+"");
    }

    @OnClick({R.id.word_recite_btn_previous,R.id.word_recite_btn_next})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.word_recite_btn_previous:
            {
                //如果是第一题，则谈吐司提醒，否则上移一道题
                if (nowPager == 0) {
                    XToastUtils.toast("已经到头啦");
                } else {
                    viewPager.setCurrentItem(--nowPager);
                }
                //设置剩余单词数目
                remainCount.setText((wordsRecites.size()-nowPager)+"");
                break;
            }
            case R.id.word_recite_btn_next:{
                Log.d(TAG, "onClick: " + viewPager.getCurrentItem());
                //如果是最后一题，则谈吐司提醒，否则下移一道题
                if (nowPager == fragmentList.size() - 1) {
                    XToastUtils.toast("单词已背完");
                    //跳转到背诵详情页
                } else {
                    viewPager.setCurrentItem( ++nowPager);
                }
                //设置剩余单词数目
                remainCount.setText((wordsRecites.size()-nowPager+1)+"");
                break;
            }
        }
    }

}