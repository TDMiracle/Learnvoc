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

package com.tdmiracle.learnvoc.fragment.recite;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.ReciteWordsActivity;
import com.tdmiracle.learnvoc.activity.ReviewWordsActivity;
import com.tdmiracle.learnvoc.adapter.ReviewWordsAdapter;
import com.tdmiracle.learnvoc.adapter.WordsBookAdapter;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordsBook;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 创建日期：2021/5/6 16:21
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ReviewWordsFragment.java
 * 类说明：单词复习碎片
 */
public class ReviewWordsFragment extends BaseFragment {

    @BindView(R.id.word_review_recycleView)
    RecyclerView recyclerView;
    @BindView(R.id.start_review)
    Button start_review;

    List<WordsBook> wordsBooks = new ArrayList<>();//单词书

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_review_words, container, false);
        ButterKnife.bind(this,rootView);
        loadData();
        initViews();
        return rootView;
    }

    @Override
    protected void initViews() {
        ReviewWordsAdapter adapter = new ReviewWordsAdapter(wordsBooks);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this.getContext(),2);//GridLlayout样式
        recyclerView.setLayoutManager(gridLayoutManager);
        //添加Android自带的分割线
        recyclerView.addItemDecoration(new DividerItemDecoration(this.getContext(),DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(adapter);
    }

    //加载recyclerView数据
    private void loadData() {
        WordsBook wordBook1;
        for(int i = 0; i < 6; i++){
            wordBook1 = new WordsBook();
            wordsBooks.add(wordBook1);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_review_words;
    }

    @OnClick(R.id.start_review)
    public void onViewClicked() {
        ActivityUtils.startActivity(ReviewWordsActivity.class);
    }
}