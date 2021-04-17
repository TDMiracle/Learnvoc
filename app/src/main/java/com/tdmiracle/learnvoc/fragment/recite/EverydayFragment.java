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

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.text.format.DateUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.entity.EverydaySentence;
import com.tdmiracle.learnvoc.adapter.entity.EverydaySentenceEntity;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.utils.CalenderEvent;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.HttpUtils;
import com.tdmiracle.learnvoc.utils.service.JsonSerializationService;
import com.tdmiracle.learnvoc.widget.MyImageView;
import com.umeng.commonsdk.framework.UMLogDataProtocol;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.Calendar;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 创建日期：2021/4/17 14:55
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： EverydayFragment.java
 * 类说明：每日一句碎片
 */
public class EverydayFragment extends BaseFragment {

    private final String TAG = "EverydayFragment";

    @BindView(R.id.tv_year)
    TextView tv_year;
    @BindView(R.id.tv_month)
    TextView tv_month;
    @BindView(R.id.tv_day)
    TextView tv_day;
    @BindView(R.id.calendar_left)
    ImageView calendar_left;
    @BindView(R.id.calendar_right)
    ImageView calendar_right;
    @BindView(R.id.img_english)
    MyImageView imgEnglish;

    //每日一句entity
    EverydaySentenceEntity everydaySentence;
    private Calendar calendar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    //注意碎片的生命周期
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_everyday,container,false);
        ButterKnife.bind(this,rootView);
        initViews();
        getEverydaySentenceEntity();
        return rootView;
    }


    private void setTextDate(Calendar calendar) {
        tv_year.setText(calendar.get(Calendar.YEAR) + "/");
        tv_month.setText(calendar.get(Calendar.MONTH) + 1 + "/");
        tv_day.setText(calendar.get(Calendar.DAY_OF_MONTH) + "");
        changeArrowImage(calendar);
    }

    public void changeArrowImage(Calendar calendar){
        if (FormatUtils.isSameDay(calendar,Calendar.getInstance())){
            calendar_left.setImageResource(R.drawable.left_arrow);
            calendar_right.setImageResource(R.drawable.icon_arrow_right_grey);
            calendar_right.setClickable(false);
        } else {
            calendar_left.setImageResource(R.drawable.left_arrow);
            calendar_right.setImageResource(R.drawable.right_arrow);
        }
    }

    private void setTextImg(EverydaySentenceEntity newslistBean){
        if (newslistBean.getImgurl() != null){
            //Log.d(TAG, "setTextImg: " + newslistBean.getImgurl());
            imgEnglish.setImageURL(newslistBean.getImgurl());
        }

    }

    @OnClick({R.id.english_mediaPlayer,R.id.calendar_left, R.id.calendar_right})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.english_mediaPlayer:
                if (everydaySentence == null){
                    return;
                }
                onPlayer(everydaySentence);
                break;
            case R.id.calendar_left:
                calendar.add(Calendar.DATE, -1);
                setTextDate(calendar);
                getHttpData(calendar);
                break;
            case R.id.calendar_right:
                if (FormatUtils.isToday(calendar)){
                    return;
                }
                calendar.add(Calendar.DATE, 1);
                setTextDate(calendar);
                getHttpData(calendar);
                break;
            default:
        }
    }

    //播放每日一句
    private void onPlayer(EverydaySentenceEntity newslistBean) {
        if (newslistBean.getTts() != null) {
            try {
                MediaPlayer mediaPlayer = new MediaPlayer();
                mediaPlayer.setDataSource(newslistBean.getTts());
                mediaPlayer.prepare();
                mediaPlayer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        OkGo.getInstance().cancelAll();
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_everyday;
    }

    @Override
    protected void initViews() {

    }


    public void getEverydaySentenceEntity(){
        calendar = Calendar.getInstance();
        getHttpData(calendar);
    }

    /**
     * 掉用金山词霸每日一句接口获取每日一句对象
     * @param calendar
     */
    private void getHttpData(Calendar calendar) {
        String date = FormatUtils.getDateTimeString(calendar.getTime());
        try {
            StringCallback callback = new StringCallback() {
                @Override
                public void onSuccess(com.lzy.okgo.model.Response<String> response) {
                    try {
                        String json = response.body().toString();
                        //Log.d(TAG,json);
                        JSONObject jsonObject = new JSONObject(json);
                        int code = jsonObject.getInt("code");
                        //请求接口,获取每日一句实体
                        if (code == 200) {
                            JSONArray jsonArray = jsonObject.getJSONArray("newslist");
                            JSONObject jsonNewsList = jsonArray.getJSONObject(0);
                            //Log.d(TAG, "newslist: " + jsonNewsList.toString());
                            JsonSerializationService jsonSerializationService = new JsonSerializationService();
                            everydaySentence = jsonSerializationService.parseObject(jsonNewsList.toString(), EverydaySentenceEntity.class);
                            //Log.d(TAG, "onSuccess: "+ everydaySentence.toString());
                            setTextImg(everydaySentence);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onError(Response<String> response) {
                    super.onError(response);
                }
            };
            HttpUtils.getEnglishSentence2(getContext(), date, callback);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}