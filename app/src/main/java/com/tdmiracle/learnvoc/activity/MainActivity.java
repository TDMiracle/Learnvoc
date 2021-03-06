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

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;
import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.dao.UserDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserDaoImpl;
import com.tdmiracle.learnvoc.fragment.AboutFragment;
import com.tdmiracle.learnvoc.fragment.SettingsFragment;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.fragment.news.NewsFragment;
import com.tdmiracle.learnvoc.fragment.profile.ProfileFragment;
import com.tdmiracle.learnvoc.fragment.recite.ReciteFragment;
import com.tdmiracle.learnvoc.fragment.statistic.StatisticFragment;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.WordDetail;
import com.tdmiracle.learnvoc.utils.Utils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xui.adapter.FragmentAdapter;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xutil.XUtil;
import com.xuexiang.xutil.app.ActivityUtils;
import com.xuexiang.xutil.common.ClickUtils;
import com.xuexiang.xutil.common.CollectionUtils;
import com.xuexiang.xutil.common.StringUtils;
import com.xuexiang.xutil.display.Colors;

import org.jetbrains.annotations.NotNull;

import butterknife.BindView;
import butterknife.OnClick;


public class MainActivity extends BaseActivity implements View.OnClickListener, BottomNavigationView.OnNavigationItemSelectedListener, ClickUtils.OnClick2ExitListener, Toolbar.OnMenuItemClickListener {

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.view_pager)
    ViewPager viewPager;
    /**
     * ???????????????
     */
    @BindView(R.id.bottom_navigation)
    BottomNavigationView bottomNavigation;
    /**
     * ?????????
     */
    @BindView(R.id.nav_view)
    NavigationView navView;
    @BindView(R.id.drawer_layout)
    DrawerLayout drawerLayout;
    /**
     * ???????????????
     */
    @BindView(R.id.main_search_fab)
    FloatingActionButton search_fab;

    private String[] mTitles;
    private AlertDialog.Builder builder;//??????????????????

    // ??????????????????
    User user;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        initViews();

        initListeners();

        //??????????????????
        MyApp app = (MyApp) getApplication();
        user = app.getUser();
    }

    @Override
    protected boolean isSupportSlideBack() {
        return false;
    }

    private void initViews() {
        mTitles = ResUtils.getStringArray(R.array.home_titles);
        toolbar.setTitle(mTitles[0]);
        toolbar.inflateMenu(R.menu.menu_main);
        toolbar.setOnMenuItemClickListener(this);

        initHeader();

        //??????????????????
        BaseFragment[] fragments = new BaseFragment[]{
                new NewsFragment(),//??????
                new ReciteFragment(),//??????
                new StatisticFragment(),//??????
                new ProfileFragment()//??????
        };
        FragmentAdapter<BaseFragment> adapter = new FragmentAdapter<>(getSupportFragmentManager(), fragments);
        viewPager.setOffscreenPageLimit(mTitles.length - 1);
        viewPager.setAdapter(adapter);
        search_fab.setOnClickListener(this);
    }

    @NotNull
    private void initHeader() {
        navView.setItemIconTintList(null);
        View headerView = navView.getHeaderView(0);
        LinearLayout navHeader = headerView.findViewById(R.id.nav_header);
        RadiusImageView ivAvatar = headerView.findViewById(R.id.iv_avatar);
        TextView tvAvatar = headerView.findViewById(R.id.tv_avatar);
        TextView tvSign = headerView.findViewById(R.id.tv_sign);

        if (Utils.isColorDark(ThemeUtils.resolveColor(this, R.attr.colorAccent))) {
            tvAvatar.setTextColor(Colors.WHITE);
            tvSign.setTextColor(Colors.WHITE);
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivAvatar.setImageTintList(ResUtils.getColors(R.color.xui_config_color_white));
            }
        } else {
            tvAvatar.setTextColor(ThemeUtils.resolveColor(this, R.attr.xui_config_color_title_text));
            tvSign.setTextColor(ThemeUtils.resolveColor(this, R.attr.xui_config_color_explain_text));
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                ivAvatar.setImageTintList(ResUtils.getColors(R.color.xui_config_color_gray_3));
            }
        }
        //???????????????????????????
        ivAvatar.setImageResource(R.drawable.ic_logo);
        tvAvatar.setText("?????????");
        tvSign.setText("????????????,????????????");
//        if(StringUtils.isEmpty(user.getNickname())){
//            tvAvatar.setText(user.getNickname());
//            tvSign.setText(user.getSignature());
//        }
        navHeader.setOnClickListener(this);
    }

    protected void initListeners() {
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //?????????????????????
        navView.setNavigationItemSelectedListener(menuItem -> {
            if (menuItem.isCheckable()) {
                drawerLayout.closeDrawers();
                return handleNavigationItemSelected(menuItem);
            } else {
                switch (menuItem.getItemId()) {
                    case R.id.nav_settings:
                        openNewPage(SettingsFragment.class);
                        break;
                    case R.id.nav_about:
                        openNewPage(AboutFragment.class);
                        break;
                    case R.id.nav_search:
                        break;
                    default:
                        XToastUtils.toast("?????????:" + menuItem.getTitle());
                        break;
                }
            }
            return true;
        });
        //??????????????????
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                MenuItem item = bottomNavigation.getMenu().getItem(position);
                toolbar.setTitle(item.getTitle());
                item.setChecked(true);
                updateSideNavStatus(item);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
        bottomNavigation.setOnNavigationItemSelectedListener(this);
    }

    /**
     * ???????????????????????????
     *
     * @param menuItem
     * @return
     */
    private boolean handleNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);
            return true;
        }
        return false;
    }

    @Override
    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_notification:
                ActivityUtils.startActivity(NotificatonActivity.class);
                break;
            case R.id.action_search:
                ActivityUtils.startActivity(SearchVocActivity.class);
                break;
        }
        return false;
    }

    @SingleClick
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.nav_header:
                XToastUtils.toast("???????????????");
                break;
            case R.id.main_search_fab://????????????
                View view = getLayoutInflater().inflate(R.layout.dialog_search,null);
                SearchView searchView = (SearchView)findViewById(R.id.searchView);
                //???????????????
                builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle("????????????");
                builder.setView(view);
                builder.setPositiveButton("??????", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //????????????????????????
//                        Intent intent = new Intent(MainActivity.this, WordDetailActivity.class);
//                        intent.putExtra("word","miracle");
                        XToastUtils.toast(searchView.getQuery().toString().trim());
//                        startActivity(intent);
//                        intent.putExtra("word",searchView.getQuery().toString().trim());
                    }

                });
                builder.setNegativeButton("??????",null);
                builder.show();
            default:
                break;
        }
    }

    //================Navigation================//

    /**
     * ???????????????????????????
     *
     * @param menuItem
     * @return
     */
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        int index = CollectionUtils.arrayIndexOf(mTitles, menuItem.getTitle());
        if (index != -1) {
            toolbar.setTitle(menuItem.getTitle());
            viewPager.setCurrentItem(index, false);

            updateSideNavStatus(menuItem);
            return true;
        }
        if(index == 0){
            search_fab.bringToFront();
        }
        else {
            search_fab.setVisibility(View.INVISIBLE);
        }
        return false;
    }

    /**
     * ?????????????????????????????????
     *
     * @param menuItem
     */
    private void updateSideNavStatus(MenuItem menuItem) {
        MenuItem side = navView.getMenu().findItem(menuItem.getItemId());
        if (side != null) {
            side.setChecked(true);
        }
    }

    /**
     * ????????????????????????
     */
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            ClickUtils.exitBy2Click(2000, this);
        }
        return true;
    }

    @Override
    public void onRetry() {
        XToastUtils.toast("????????????????????????");
    }

    @Override
    public void onExit() {
        XUtil.exitApp();
    }


}
