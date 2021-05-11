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

package com.tdmiracle.learnvoc.fragment.common;

import android.os.Bundle;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.dao.UserWordTestDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserWordTestDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserWordTest;
import com.tdmiracle.learnvoc.module.WordTestQuestion;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.tdmiracle.learnvoc.widget.FlowRadioGroup;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 创建日期：2021/5/10 10:01
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： AnswerFragment.java
 * 类说明：答题页
 */
public class AnswerFragment extends BaseFragment {

    private final String TAG = "AnswerFragment";
    @BindView(R.id.word_test_word)
    TextView test_word;
    @BindView(R.id.word_test_yinbiao)
    TextView test_yinbiao;
    @BindView(R.id.word_test_answer_A)
    RadioButton answerA;
    @BindView(R.id.word_test_answer_B)
    RadioButton answerB;
    @BindView(R.id.word_test_answer_C)
    RadioButton answerC;
    @BindView(R.id.word_test_answer_D)
    RadioButton answerD;
    @BindView(R.id.word_test_answer_None)
    RadioButton answerNone;
    @BindView(R.id.word_test_answer_Unknown)
    RadioButton answerUnknown;
    @BindView(R.id.word_test_answer)
    RadioGroup radioGroup;

    //当前用户
    User globalUser;
    //题目数据
    WordTestQuestion wordTestQuestion;
    //用户答题数据
    UserWordTest userWordTest;

    int choice = 0;//选择结果,默认为0（未作）
    int count = 1;//计数器，防止多次提交

    public AnswerFragment(WordTestQuestion wordTestQuestion) {
        this.wordTestQuestion = wordTestQuestion;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_answer, container, false);
        ButterKnife.bind(this,rootView);
        initViews();
        userWordTest = new UserWordTest();
        return rootView;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected void initViews() {
        //获取全局变量
        MyApp app = (MyApp) this.getActivity().getApplication();
        globalUser = app.getUser();
        Log.d(TAG, "initViews: " + globalUser.toString());

        if(wordTestQuestion != null){
            test_word.setText(wordTestQuestion.getWord());
            test_yinbiao.setText(wordTestQuestion.getYinbiao());
            answerA.setText(wordTestQuestion.getChoice_A());
            answerB.setText(wordTestQuestion.getChoice_B());
            answerC.setText(wordTestQuestion.getChoice_C());
            answerD.setText(wordTestQuestion.getChoice_D());
        }
        else {
            Log.d(TAG, "initViews: " + wordTestQuestion.toString());
        }
        //设置监听
        radioGroup.setOnCheckedChangeListener(onCheckedChangeListener);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_answer;
    }

//    @OnClick({R.id.word_test_answer_A,R.id.word_test_answer_B,R.id.word_test_answer_C,
//            R.id.word_test_answer_D,R.id.word_test_answer_Unknown,R.id.word_test_answer_None})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.word_test_answer_Unknown:
//        }
//    }

    private RadioGroup.OnCheckedChangeListener onCheckedChangeListener = new RadioGroup.OnCheckedChangeListener() {

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId) {
                case R.id.word_test_answer_A:
                    choice = 1;
                    break;
                case R.id.word_test_answer_B:
                    choice = 2;
                    break;
                case R.id.word_test_answer_C:
                    choice = 3;
                    break;
                case R.id.word_test_answer_D:
                    choice = 4;
                    break;
                case R.id.word_test_answer_None:
                    choice = 5;
                    break;
                case R.id.word_test_answer_Unknown:
                    choice = 6;
                    break;
            }
//            saveUserWordTest(choice);
        }
    };

    //保存答题数据
    private void saveUserWordTest(int choice) {
        if(count != 0){
            userWordTest.setUser_id(globalUser.getId());
            userWordTest.setTest_time(new Date());
            userWordTest.setWordTestQuestion_id(wordTestQuestion.getId());
            userWordTest.setUserChoice(choice);
            userWordTest.setIs_show(false);
            if(choice == wordTestQuestion.getRightChoice()){
                userWordTest.setIs_right(true);
            }else {
                userWordTest.setIs_right(false);
            }
            if(new UserWordTestDaoImpl().addUserWordTest(userWordTest)){
//                XToastUtils.toast("添加成功");
            }
            count --;//计数，防止多次提交
        }else {
//            XToastUtils.toast("请勿重新提交");
        }

    }

    @Override
    public void onDestroy() {
        saveUserWordTest(choice);//销毁活动时保存数据
        super.onDestroy();
    }
}