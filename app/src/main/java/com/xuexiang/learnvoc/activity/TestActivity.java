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

package com.xuexiang.learnvoc.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;

import com.xuexiang.learnvoc.R;
import com.xuexiang.learnvoc.adapter.base.viewpager.MyFragmentPagerAdapter;
import com.xuexiang.learnvoc.fragment.BlankFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 创建日期：2021/4/5 19:50
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestActivity.java
 * 类说明：测试viewpage实现活动内碎片滚动
 */
public class TestActivity extends AppCompatActivity {

    ViewPager2 viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        initPage();
    }

    private void initPage() {
        viewPager = findViewById(R.id.id_vp);
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(BlankFragment.newInstance("单词测试1"));
        fragments.add(BlankFragment.newInstance("单词测试2"));
        fragments.add(BlankFragment.newInstance("单词测试3"));
        fragments.add(BlankFragment.newInstance("单词测试4"));
        fragments.add(BlankFragment.newInstance("单词测试5"));
        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewPager.setAdapter(pagerAdapter);
    }
}