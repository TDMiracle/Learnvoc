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

import android.content.Intent;
import android.graphics.Color;
import android.media.Image;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import com.bigkoo.pickerview.TimePickerView;
import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;
import com.tdmiracle.learnvoc.dao.UserDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserDaoImpl;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import org.w3c.dom.Text;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import butterknife.BindView;

public class EditPersonalInfoActivity extends BaseActivity implements View.OnClickListener {

    private final String TAG = "EPersonalInfoActivity:";
    TextView userID;
    EditText nickname;
    EditText userSignature;
    EditText phone;
    EditText email;
    Spinner userSex;
    EditText district;
    EditText school;
    TextView joinTime;
    TextView punchMount;
    TextView isPunch;
    TextView birthday;
    Button imgChange;
    ImageView headImg;

    Button btn_save;//保存键
    private TimePickerView pvTime; //时间选择器对象

    //全局user
    User globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        initView();
        bindData();
    }

    //修改休息后保存
    private boolean saveInfo(){
        UserDao userDao = new UserDaoImpl();
        globalUser.setNickname(nickname.getText().toString().trim());
        globalUser.setSignature(userSignature.getText().toString().trim());
        globalUser.setPhone(phone.getText().toString().trim());
        globalUser.setEmail(email.getText().toString().trim());
        globalUser.setGender(userSex.getSelectedItem().toString());
        globalUser.setBirthday(birthday.getText().toString());
        globalUser.setDistinct(district.getText().toString().trim());
        globalUser.setSchool(school.getText().toString().trim());
        //更新用户信息
        userDao.updateUserinfo(globalUser);
        //同步全局变量
        MyApp app = (MyApp) getApplication();
        app.setUser(globalUser);
        return true;
    }

    //绑定用户信息
    private void bindData() {
        MyApp app = (MyApp) getApplication();
        //获取当前用户
        globalUser = app.getUser();
//        Log.d(TAG, "bindData: " + user.toString());
        if(globalUser != null){
            userID.setText(globalUser.getUid());
            phone.setText(globalUser.getPhone());
            joinTime.setText(FormatUtils.getDateTimeString(globalUser.getCreate_time()));
            if(globalUser.getNickname()!=null){
                nickname.setText(globalUser.getNickname());
            }
            if(globalUser.getSignature()!=null){
                userSignature.setText(globalUser.getSignature());
            }
            if(globalUser.getPhone()!=null){
                email.setText(globalUser.getEmail());
            }
            if(globalUser.getBirthday()!=null){
                birthday.setText(globalUser.getBirthday());
            }
            if(globalUser.getSchool()!=null){
                school.setText(globalUser.getSchool());
            }
            if(globalUser.getGender()!=null){
                String gender = globalUser.getGender();
                if(gender.equals("男")) {
                    userSex.setSelection(0);
                }
                else if(gender.equals("女")){
                    userSex.setSelection(1);
                }
                else {
                    userSex.setSelection(2);
                }
            }
        }
    }

    private void initView(){
        userID = (TextView)findViewById(R.id.userID);
        nickname = (EditText) findViewById(R.id.nickname);
        userSignature = (EditText) findViewById(R.id.User_signature);
        phone = (EditText)findViewById(R.id.phone);
        email = (EditText) findViewById(R.id.email);
        userSex = (Spinner) findViewById(R.id.userSex);
        district = (EditText) findViewById(R.id.district);
        school = (EditText) findViewById(R.id.school);
        joinTime = (TextView)findViewById(R.id.join_time);
        punchMount = (TextView)findViewById(R.id.punch_mount);
        isPunch = (TextView)findViewById(R.id.isPunch);
        birthday = (TextView)findViewById(R.id.birthday);
        birthday.setOnClickListener(this);
        imgChange = (Button)findViewById(R.id.button_changeimg);
        imgChange.setOnClickListener(this);
        headImg = (ImageView) findViewById(R.id.head_icon);
        headImg.setImageResource(R.drawable.mna);
        btn_save = (Button) findViewById(R.id.btn_save);
        btn_save.setOnClickListener(this);
    }

    //初始化时间选择器
    private void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);//起始时间
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 12, 31);//结束时间
        pvTime = new TimePickerView.Builder(this,
                new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        //选中事件回调
                        //mTvMyBirthday 这个组件就是个TextView用来显示日期 如2020-09-08
                        birthday.setText(getTimes(date));
                    }
                })
                //年月日时分秒 的显示与否，不设置则默认全部显示
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("年", "月", "日", "时", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }

    //格式化时间
    private String getTimes(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthday:
                initTimePicker(); //初始化时间选择器
                pvTime.show();//显示时间选择器
                break;
            case R.id.button_changeimg:
                Intent intent=new Intent(EditPersonalInfoActivity.this,SelectPhotoActivity.class);
                //把用户名传递过去
                //intent.putExtra("idUser",IDuser);
                startActivity(intent);
                break;
            case R.id.btn_save:
                if(saveInfo())
                    XToastUtils.toast("保存成功！");
                break;
        }
    }
}