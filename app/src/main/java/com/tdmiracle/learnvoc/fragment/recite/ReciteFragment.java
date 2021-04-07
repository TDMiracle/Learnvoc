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

import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.R;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.widget.actionbar.TitleBar;


/**
 * 创建日期：2021/4/7 19:31
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ReciteFragment.java
 * 类说明：
 */
@Page(anim = CoreAnim.none)
public class ReciteFragment extends BaseFragment {

    /**
     * @return 返回为 null意为不需要导航栏
     */
    @Override
    protected TitleBar initTitle() {
        return null;
    }

    /**
     * 布局的资源id
     *
     * @return
     */
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_trending;
    }

    /**
     * 初始化控件
     */
    @Override
    protected void initViews() {

    }
}
