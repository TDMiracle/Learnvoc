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

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xui.widget.textview.supertextview.SuperTextView;

import butterknife.BindView;


/**
 * 创建日期：2021/4/9 10:28
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： MyPageFragment.java
 * 类说明：
 */
@Page(name = "我的主页")
public class MyPageFragment extends BaseFragment {

    final String TAG = "MypageFragment.class: ";
    @BindView(R.id.text3)
    TextView TextView;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_my_page;
    }

    @Override
    protected void initViews() {
        //Log.d(TAG, "initViews: " + superTextView.getId());
    }


}