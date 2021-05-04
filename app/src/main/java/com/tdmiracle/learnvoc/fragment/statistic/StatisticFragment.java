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

package com.tdmiracle.learnvoc.fragment.statistic;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.fragment.recite.EverydayFragment;
import com.tdmiracle.learnvoc.fragment.recite.ReciteFragment;
import com.tdmiracle.learnvoc.fragment.recite.ReciteWordsFragment;
import com.tdmiracle.learnvoc.fragment.recite.ReviewWordsFragment;
import com.tdmiracle.learnvoc.fragment.recite.TestWordsFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/4/7 19:34
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： StatisticFragment.java
 * 类说明：
 */
public class StatisticFragment extends BaseFragment {

    private final String TAG = "StatisticFragment";

    private String[] strings = new String[]{"遗忘曲线","学习情况","复习情况","测试情况"};
    private List<Fragment> fragmentList = new ArrayList<Fragment>();

    @BindView(R.id.fragment_statistic_tablayout)
    public TabLayout tabLayout;
    @BindView(R.id.fragment_statistic_vp)
    public ViewPager2 viewPager;

    private MyAdapter adapter;

    @Override
    protected int getLayoutId() {
        return 0;
    }



    @Override
    protected void initViews() {
        adapter = new StatisticFragment.MyAdapter(getFragmentManager(),getLifecycle(),fragmentList, strings);
        viewPager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        viewPager.setAdapter(adapter);
        viewPager.setOffscreenPageLimit(4);
        TabLayoutMediator mediator = new TabLayoutMediator(tabLayout, viewPager, (tab, position) -> {
            tab.setText(strings[position]);
        });
        mediator.attach();
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_statistic, container, false);
        ButterKnife.bind(this,rootView);
        initFragment();
        initViews();
        return rootView;
    }


    private void initFragment() {
            fragmentList.add(new ForgettingCurveFragment());
            fragmentList.add(new StudyInfoFragment());
            fragmentList.add(new ReviewInfoFragment());
            fragmentList.add(new TestInfoFragment());
        }

    //这是适配器，让每个标题对应一个fragment，每个fragment中加载一个xml文件
    public class MyAdapter extends FragmentStateAdapter {

        private String[] mList;
        private List<Fragment> fragmentList;
        public MyAdapter(@NonNull FragmentManager fm, @NonNull Lifecycle lifecycle, List<Fragment> fragmentList, String[] mList ) {
            super(fm, lifecycle);
            this.fragmentList = fragmentList;
            this.mList = mList;
        }


        @NonNull
        @Override
        public Fragment createFragment(int position) {
            int index = position % mList.length;
            return fragmentList.get(index);
        }

        @Override
        public int getItemCount() {
            return fragmentList.size();
        }
    }
}