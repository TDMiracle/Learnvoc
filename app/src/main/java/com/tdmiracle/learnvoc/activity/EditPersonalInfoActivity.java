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

    Button btn_save;//?????????
    private TimePickerView pvTime; //?????????????????????

    //??????user
    User globalUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_personal_info);
        initView();
        bindData();
    }

    //?????????????????????
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
        //??????????????????
        userDao.updateUserinfo(globalUser);
        //??????????????????
        MyApp app = (MyApp) getApplication();
        app.setUser(globalUser);
        return true;
    }

    //??????????????????
    private void bindData() {
        MyApp app = (MyApp) getApplication();
        //??????????????????
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
                if(gender.equals("???")) {
                    userSex.setSelection(0);
                }
                else if(gender.equals("???")){
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

    //????????????????????????
    private void initTimePicker() {
        Calendar selectedDate = Calendar.getInstance();
        Calendar startDate = Calendar.getInstance();
        startDate.set(1900, 1, 1);//????????????
        Calendar endDate = Calendar.getInstance();
        endDate.set(2099, 12, 31);//????????????
        pvTime = new TimePickerView.Builder(this,
                new TimePickerView.OnTimeSelectListener() {
                    @Override
                    public void onTimeSelect(Date date, View v) {
                        //??????????????????
                        //mTvMyBirthday ?????????????????????TextView?????????????????? ???2020-09-08
                        birthday.setText(getTimes(date));
                    }
                })
                //?????????????????? ????????????????????????????????????????????????
                .setType(new boolean[]{true, true, true, false, false, false})
                .setLabel("???", "???", "???", "???", "", "")
                .isCenterLabel(true)
                .setDividerColor(Color.DKGRAY)
                .setContentSize(21)
                .setDate(selectedDate)
                .setRangDate(startDate, endDate)
                .setDecorView(null)
                .build();
    }

    //???????????????
    private String getTimes(Date date) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        return format.format(date);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.birthday:
                initTimePicker(); //????????????????????????
                pvTime.show();//?????????????????????
                break;
            case R.id.button_changeimg:
                Intent intent=new Intent(EditPersonalInfoActivity.this,SelectPhotoActivity.class);
                //????????????????????????
                //intent.putExtra("idUser",IDuser);
                startActivity(intent);
                break;
            case R.id.btn_save:
                if(saveInfo())
                    XToastUtils.toast("???????????????");
                break;
        }
    }
}