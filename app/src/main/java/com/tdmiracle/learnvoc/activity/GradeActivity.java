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

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.ContentValues;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.GradeActivityAdapter;
import com.tdmiracle.learnvoc.adapter.WordListAdapter;
import com.tdmiracle.learnvoc.dao.UserWordTestDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserWordTestDaoImpl;
import com.tdmiracle.learnvoc.dao.daoImpl.WordTestQuestionDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserWordTest;
import com.tdmiracle.learnvoc.module.WordTestQuestion;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 创建日期：2021/5/10 20:23
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： GradeActivity.java
 * 类说明：单词测试后跳转评分
 */
public class GradeActivity extends AppCompatActivity {

    private final String TAG = "GradeActivity";

    @BindView(R.id.tv_grade_score)
    TextView tvGradeScore;
    @BindView(R.id.lv_grade_score_detail)
    RecyclerView lvGradeScoreDetail;

    private ArrayList<CharSequence> titleName;
    private int grade = 0;//测试成绩
    private int testCount;//测试题数目

    User globalUser;//当前用户
    List<UserWordTest> userWordTests;//用户答题信息

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grade);
        ButterKnife.bind(this);
        //获取全局变量
        MyApp app = (MyApp) getApplication();
        globalUser = app.getUser();
        loadData();
        init();
    }

    //加载数据
    private void loadData() {
        UserWordTestDao dao = new UserWordTestDaoImpl();
        userWordTests = dao.getUserWordTestByCount(globalUser,0);

        //更新展示状态
        for(UserWordTest userWordTest : userWordTests){
            userWordTest.setIs_show(true);
            userWordTest.save();
        }
//        userWordTests.add(new UserWordTest(1,1,false,new Date()));
    }

    //初始化
    private void init() {
        testCount = getIntent().getIntExtra("testCount",10);
        for(UserWordTest userWordTest : userWordTests){
            if(userWordTest.isIs_right()){
                grade += 10;
            }
        }
//        Log.d(TAG, "init: " + userWordTests.get(0).toString());
        //设置显示成绩分数
        tvGradeScore.setText("您的成绩是： " + grade);
        GradeActivityAdapter adapter = new GradeActivityAdapter(userWordTests);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this,2);//GridLlayout样式
        lvGradeScoreDetail.setLayoutManager(gridLayoutManager);
        lvGradeScoreDetail.setAdapter(adapter);

    }
}