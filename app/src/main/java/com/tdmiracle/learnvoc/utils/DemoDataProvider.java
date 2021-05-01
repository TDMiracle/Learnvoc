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

package com.tdmiracle.learnvoc.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.net.Uri;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.entity.NewInfo;
import com.xuexiang.xaop.annotation.MemoryCache;
import com.xuexiang.xui.adapter.simple.AdapterItem;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.widget.banner.widget.banner.BannerItem;

import java.sql.SQLOutput;
import java.util.ArrayList;
import java.util.List;

import static com.xuexiang.xutil.app.AppUtils.getPackageName;


/**
 * 创建日期：2021/4/5 20:14
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： DemoDataProvider.java
 * 类说明：演示数据
 */
public class DemoDataProvider {

    public static String[] titles = new String[]{
            "1",
            "2",
            "3",
            "4",
            "5",
    };

    public static String[] urls = new String[]{//640*360 360/640=0.5625
            Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.banner_1).toString(),
            Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.banner_2).toString(),
            Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.banner_3).toString(),
            Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.banner_6).toString(),
            Uri.parse("android.resource://" + getPackageName() + "/" + R.drawable.banner_7).toString(),
    };

    @MemoryCache
    public static List<BannerItem> getBannerList() {
        List<BannerItem> list = new ArrayList<>();
        for (int i = 0; i < urls.length; i++) {
            BannerItem item = new BannerItem();
            item.imgUrl = urls[i];
            item.title = titles[i];
            list.add(item);
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getDemoNewInfos() {
        List<NewInfo> list = new ArrayList<>();
        list.add(new NewInfo("四级", "大学英语四级单词攻略")
                .setSummary("三个字，稳，准，狠！！！有人说一个月？要我说，三天就够了！！")
                .setDetailUrl("https://www.zhihu.com/search?q=%E5%9B%9B%E7%BA%A7%E6%80%8E%E4%B9%88%E5%A4%87%E8%80%83&utm_content=search_suggestion&type=content")
                .setImageUrl("https://pic4.zhimg.com/80/v2-b85ace44396edc6bbd6f42a6d683a543_720w.jpg"));

        list.add(new NewInfo("六级", "怎么备考六级考到 600+？")
                .setSummary("全文将近5000字，花了老学姐3天时间整理，包含多种实用的解题技巧和策略，建议先码住收藏，顺便给老学姐的呕心沥血点个小小的赞吧\n")
                .setDetailUrl("https://www.zhihu.com/search?type=content&q=%E5%85%AD%E7%BA%A7")
                .setImageUrl("https://pic1.zhimg.com/80/v2-f7e51ab6f5b24a5cfe6f869b9109dd6c_720w.jpg"));

        list.add(new NewInfo("高中", "高考英语如何提到140分？")
                .setSummary("这篇文章将完全重塑你对英语学习的认知！ 不管你现在英语什么水平，题目里140分的目标都太低了，我觉得高中英语的目标至少应该是145分。这么说是因为如果你把英语试卷全部翻译成中文再看会发现就难度而言除了作文之外真的很难再找到其他值得扣分的地方了。")
                .setDetailUrl("https://www.zhihu.com/question/357234340/answer/1529132253")
                .setImageUrl("https://pic4.zhimg.com/80/v2-00f9db99be0599113c4c93c7700e81ff_720w.jpg?source=1940ef5c"));

        list.add(new NewInfo("雅思", "雅思考试报名流程")
                .setSummary("2021年1-6月雅思纸笔考试、1-3月用于英国签证及移民的雅思纸笔考试即日起接受报名，2021年1-3月雅思机考和用于英国签证及移民的雅思机考将于2020年10月12日开放报名 请大家登录中国教育部考试")
                .setDetailUrl("https://zhuanlan.zhihu.com/p/66357729")
                .setImageUrl("https://pic2.zhimg.com/v2-e6252b252d07be6029e254d417126ff2_1440w.jpg?source=172ae18b"));

        list.add(new NewInfo("托福", "首考托福110上岸，托福考试备考全攻略分享，如何在最短时间内拿到高分。")
                .setSummary("托福是由美国教育测验服务社（ETS）举办的英语能力考试，现在咱们考的主要为ibt—internet based test 网考，满分是120分")
                .setDetailUrl("https://zhuanlan.zhihu.com/p/242712346")
                .setImageUrl("https://pic2.zhimg.com/v2-8369cdbb3a776f1fdf61c2ab53eba581_b.jpg"));
        return list;
    }

    public static List<AdapterItem> getGridItems(Context context) {
        return getGridItems(context, R.array.grid_titles_entry, R.array.grid_icons_entry);
    }


    private static List<AdapterItem> getGridItems(Context context, int titleArrayId, int iconArrayId) {
        List<AdapterItem> list = new ArrayList<>();
        String[] titles = ResUtils.getStringArray(titleArrayId);
        Drawable[] icons = ResUtils.getDrawableArray(context, iconArrayId);
        for (int i = 0; i < titles.length; i++) {
            list.add(new AdapterItem(titles[i], icons[i]));
        }
        return list;
    }

    /**
     * 用于占位的空信息
     *
     * @return
     */
    @MemoryCache
    public static List<NewInfo> getEmptyNewInfo() {
        List<NewInfo> list = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            list.add(new NewInfo());
        }
        return list;
    }

}
