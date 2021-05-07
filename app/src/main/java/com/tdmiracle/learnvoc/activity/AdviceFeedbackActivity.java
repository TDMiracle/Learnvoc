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

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.dao.UserFeedbackDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserFeedbackDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserFeedback;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xui.widget.dialog.materialdialog.MaterialDialog;

import java.util.Date;

/**
 * 创建日期：2021/4/11 10:05
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： AdviceFeedbackActivity.java
 * 类说明：我的-关于-意见反馈
 */
public class AdviceFeedbackActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "AdviceFeedbackActivity";

    private static final int MAX_COUNT = 200;
    private EditText mEtContent = null;
    private TextView mTextCount = null;
    private Button btn_submit;
    private Button btn_connectKeFu;
    User globalUser;
    UserFeedbackDao dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_advice_feedback);
        initView();
        //获取全局变量
        MyApp app = (MyApp) getApplication();
        globalUser = app.getUser();
    }

    private void initView(){
        mEtContent = (EditText) findViewById(R.id.et_content);
        mTextCount = (TextView) findViewById(R.id.text_count);
        btn_submit = (Button) findViewById(R.id.btn_submit);
        btn_submit.setOnClickListener(this);
        btn_connectKeFu = (Button) findViewById(R.id.btn_connectKeFu);
        btn_connectKeFu.setOnClickListener(this);
        mEtContent.addTextChangedListener(new TextWatcher() { //对EditText进行监听
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                mTextCount.setText("剩余字数：" + (MAX_COUNT - editable.length()));
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_submit: {
                if(sendFeedback()){
                    mEtContent.setText("");
                    XToastUtils.toast("反馈成功！");
                }
                else {
                    XToastUtils.toast("请编辑反馈内容再次发送。");
                }
                break;
            }
            case R.id.btn_connectKeFu:
                new MaterialDialog.Builder(this)
                        .iconRes(R.drawable.ic_menu_notifications)
                        .title(R.string.tip_title)
                        .content(R.string.connect_keFu)
                        .positiveText(R.string.lab_ok)
                        .show();
                break;
        }
    }

    //发送反馈消息
    private boolean sendFeedback(){
        String feedback = mEtContent.getText().toString().trim();
        dao = new UserFeedbackDaoImpl();
        UserFeedback userFeedback = new UserFeedback();
        userFeedback.setContent(feedback);
        userFeedback.setUser_id(globalUser.getId());
        userFeedback.setSend_time(new Date());
        return dao.IncreaseUserFeedback(userFeedback);
    }
}