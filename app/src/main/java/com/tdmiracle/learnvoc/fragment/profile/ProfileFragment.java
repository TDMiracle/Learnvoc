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

package com.tdmiracle.learnvoc.fragment.profile;

import android.media.Image;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.tdmiracle.learnvoc.activity.ApplicationNotificationActivity;
import com.tdmiracle.learnvoc.activity.EditPersonalInfoActivity;
import com.tdmiracle.learnvoc.activity.MainActivity;
import com.tdmiracle.learnvoc.activity.NotificatonActivity;
import com.tdmiracle.learnvoc.activity.SplashActivity;
import com.tdmiracle.learnvoc.activity.StudyStatisticActivity;
import com.tdmiracle.learnvoc.activity.WordsBookActivity;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.fragment.AboutFragment;
import com.tdmiracle.learnvoc.fragment.SettingsFragment;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;
import com.xuexiang.xutil.app.ActivityUtils;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * @author xuexiang
 * @since 2019-10-30 00:18
 */
@Page(anim = CoreAnim.none)
public class ProfileFragment extends BaseFragment implements SuperTextView.OnSuperTextViewClickListener {
    @BindView(R.id.profile_myPage_head_pic)
    RadiusImageView rivHeadPic;
    @BindView(R.id.menu_settings)
    SuperTextView menuSettings;
    @BindView(R.id.menu_about)
    SuperTextView menuAbout;
    @BindView(R.id.tv_profile_copyright)
    TextView textView;
    @BindView(R.id.tv_profile_product_name)
    TextView textView2;
    @BindView(R.id.profile_danCiBen)
    SuperTextView danCiBen;
    @BindView(R.id.profile_xueXiSheZhi)
    SuperTextView xueXiSheZhi;
    @BindView(R.id.profile_xueXiShuJu)
    SuperTextView XueXiShuJu;

    /**
     * @return ????????? null????????????????????????
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * ???????????????id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_profile;
    }

    /**
     * ???????????????
     */
    @Override
    protected void initViews() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy", Locale.CHINA);
        String currentYear = dateFormat.format(new Date());
        textView.setText(String.format(getResources().getString(R.string.about_copyright), currentYear));
        textView2.setText(R.string.app_name);
    }

    @Override
    protected void initListeners() {
        menuSettings.setOnSuperTextViewClickListener(this);
        menuAbout.setOnSuperTextViewClickListener(this);
        SuperTextView stv_myPage = (SuperTextView)findViewById(R.id.profile_myPage);
        stv_myPage.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(EditPersonalInfoActivity.class);
            }
        });
        SuperTextView stv_infoCenter = (SuperTextView)findViewById(R.id.profile_infoCenter);
        stv_infoCenter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(NotificatonActivity.class);
            }
        });
        xueXiSheZhi = (SuperTextView)findViewById(R.id.profile_xueXiSheZhi);
        xueXiSheZhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ActivityUtils.startActivity(ApplicationNotificationActivity.class);
            }
        });
    }

    @SingleClick
    @Override
    public void onClick(SuperTextView view) {
        switch(view.getId()) {
            case R.id.menu_settings:
                openNewPage(SettingsFragment.class);
                break;
            case R.id.menu_about:
                openNewPage(AboutFragment.class);
                break;
            default:
                break;
        }
    }

    @OnClick({R.id.profile_danCiBen,R.id.profile_xueXiShuJu})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.profile_danCiBen:
                ActivityUtils.startActivity(WordsBookActivity.class);
                break;
            case R.id.profile_xueXiShuJu:
                ActivityUtils.startActivity(StudyStatisticActivity.class);
//                XToastUtils.toast("????????????");
                break;
        }
    }



}
