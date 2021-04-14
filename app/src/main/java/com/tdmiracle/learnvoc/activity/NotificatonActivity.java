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

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.material.bottomsheet.BottomSheetBehavior;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.NotificaitonContract;
import com.tdmiracle.learnvoc.adapter.NotificationAdapter;
import com.tdmiracle.learnvoc.adapter.NotificationPresenter;
import com.tdmiracle.learnvoc.adapter.entity.Notification;
import com.tdmiracle.learnvoc.core.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificatonActivity extends BaseActivity implements NotificaitonContract.View,View.OnClickListener {

    NotificaitonContract.Presenter mpresenter;
    RecyclerView recyNotList;
    TextView textNotCompile;
    CheckBox checkboxNotCheckall;
    RelativeLayout RelNotbottom;
    LinearLayout lineNotCompile;
    TextView textNotDelete;
    SwipeRefreshLayout swfNotGetdata;
    private NotificationAdapter adapter;
    private List<Notification> datalist;
    boolean isshow = false;
    BottomSheetBehavior bottomSheetBehavior;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notificaiton);
        //ButterKnife.bind(this);
        textNotCompile = (TextView) findViewById(R.id.text_not_compile);
        recyNotList = (RecyclerView) findViewById(R.id.recy_not_list);
        checkboxNotCheckall = (CheckBox) findViewById(R.id.checkbox_not_checkall);
        RelNotbottom = (RelativeLayout)findViewById(R.id.Rel_notbottom);
        lineNotCompile = (LinearLayout) findViewById(R.id.line_not_compile);
        textNotDelete = (TextView) findViewById(R.id.text_not_delete);
        swfNotGetdata = (SwipeRefreshLayout)findViewById(R.id.swf_not_getdata);
        lineNotCompile.setOnClickListener(this);
        textNotDelete.setOnClickListener(this);

        new NotificationPresenter(this);
        inittooler();
        initdata();
    }

    private void initdata() {
        datalist = new ArrayList<>();
        GridLayoutManager layoutManager = new GridLayoutManager(this, 1);
//        recyNotList.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        recyNotList.setLayoutManager(layoutManager);
        adapter = new NotificationAdapter(this, datalist);
        recyNotList.setAdapter(adapter);
        mpresenter.getnotificationlist(this, isshow);
        checkboxNotCheckall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    adapter.chechall(true);
                } else {
                    adapter.chechall(false);
                }
            }
        });
        swfNotGetdata.setColorSchemeResources(R.color.xui_config_color_blue);
        swfNotGetdata.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mpresenter.getnotificationlist(NotificatonActivity.this, isshow);
                swfNotGetdata.setRefreshing(false);
            }
        });
    }

    private void inittooler() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        toolbar.setTitle("系统通知");
        setSupportActionBar(toolbar);

        ActionBar ab = getSupportActionBar();

        assert ab != null;

        ab.setDisplayHomeAsUpEnabled(false);

        bottomSheetBehavior = BottomSheetBehavior.from(findViewById(R.id.Rel_notbottom));
        bottomSheetBehavior.setSkipCollapsed(true);
        bottomSheetBehavior.setHideable(true);
        bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
    }

    @Override
    public void setPresenter(NotificaitonContract.Presenter presenter) {
        mpresenter = presenter;
    }

    @Override
    public void shownotificationlist(List<Notification> list) {
        datalist.clear();
        datalist.addAll(list);
        adapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.line_not_compile:
                if (isshow) {
                    isshow = false;
                    adapter.isshowcheck(false);
                    textNotCompile.setText("编辑");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_HIDDEN);
                } else {
                    isshow = true;
                    adapter.isshowcheck(true);
                    textNotCompile.setText("取消");
                    bottomSheetBehavior.setState(BottomSheetBehavior.STATE_COLLAPSED);
                }
                break;
            case R.id.text_not_delete:
                AlertDialog.Builder builder = new AlertDialog.Builder(NotificatonActivity.this);
                builder.setTitle("提示");
                builder.setMessage("是否清除已选中的消息通知？");
                AlertDialog dialog = builder.create();
                builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        adapter.deleteItem();
                        checkboxNotCheckall.setChecked(false);
                    }
                });
                builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
                Button positiveButton = dialog.getButton(DialogInterface.BUTTON_POSITIVE);
                if(positiveButton != null){
                    positiveButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                Button negitiveButton = dialog.getButton(DialogInterface.BUTTON_NEGATIVE);
                if(negitiveButton != null){
                    negitiveButton.setBackgroundColor(getResources().getColor(R.color.colorAccent));
                }
                builder.show();
                break;
        }
    }
}