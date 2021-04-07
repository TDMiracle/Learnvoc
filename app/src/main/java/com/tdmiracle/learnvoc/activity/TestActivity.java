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

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.base.viewpager.MyFragmentPagerAdapter;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.fragment.AboutFragment;
import com.tdmiracle.learnvoc.fragment.BlankFragment;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xutil.common.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;

/**
 * 创建日期：2021/4/5 19:50
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestActivity.java
 * 类说明：测试viewpage实现活动内碎片滚动
 */
public class TestActivity extends BaseActivity implements BottomNavigationView.OnNavigationItemSelectedListener,Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager2 viewPager;
    //绑定底部导航栏
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initPage();
    }

    private void initPage() {
        mTitles = ResUtils.getStringArray(R.array.home_titles);
        toolbar.setTitle(mTitles[1]);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);


//        viewPager = findViewById(R.id.id_vp);
//        List<Fragment> fragments = new ArrayList<>();
//        fragments.add(BlankFragment.newInstance("单词测试1"));
//        fragments.add(BlankFragment.newInstance("单词测试2"));
//        fragments.add(BlankFragment.newInstance("单词测试3"));
//        fragments.add(BlankFragment.newInstance("单词测试4"));
//        fragments.add(BlankFragment.newInstance("单词测试5"));
//        MyFragmentPagerAdapter pagerAdapter = new MyFragmentPagerAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
//        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_privacy:
                XToastUtils.toast("查看隐私政策");
                break;
            case R.id.action_about:
                openNewPage(AboutFragment.class);
                break;
            default:
                break;
        }
        return false;
    }



    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);

            return true;
        }
        return false;
    }
}