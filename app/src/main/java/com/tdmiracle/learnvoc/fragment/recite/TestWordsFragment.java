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

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.TestWordsActivity;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.xuexiang.xutil.app.ActivityUtils;


import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class TestWordsFragment extends BaseFragment {

    @BindView(R.id.start_test)
    Button btn_start_test;
    @BindView(R.id.word_test_back)
    Button word_test_back;
    @BindView(R.id.vocabulary)
    TextView vocabulary;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_test_words, container, false);
        ButterKnife.bind(this,rootView);
        initViews();
        return rootView;
    }

    @Override
    protected void initViews() {
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_words;
    }

    @OnClick({R.id.start_test,R.id.word_test_back})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.start_test:
                ActivityUtils.startActivity(TestWordsActivity.class);
                break;
            case R.id.word_test_back:
                break;
            default:break;
        }
    }
}