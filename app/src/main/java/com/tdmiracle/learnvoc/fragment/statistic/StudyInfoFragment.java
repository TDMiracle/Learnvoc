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

package com.tdmiracle.learnvoc.fragment.statistic;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.RadarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.RadarData;
import com.github.mikephil.charting.data.RadarDataSet;
import com.github.mikephil.charting.data.RadarEntry;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.interfaces.datasets.IDataSet;
import com.github.mikephil.charting.interfaces.datasets.IRadarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.entity.RadarChartFormatter;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.widget.RadarMarkerView;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/5/3 20:02
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： StudyInfoFragment.java
 * 类说明：
 */
public class StudyInfoFragment extends BaseFragment {

    @BindView(R.id.barChart)
    BarChart yearBarChart;
    @BindView(R.id.radarChart)
    RadarChart radarChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView =  inflater.inflate(R.layout.fragment_study_info, container, false);
        ButterKnife.bind(this,rootView);
        initBarChart();
        initRadarChart();
        return rootView;
    }

    private void initBarChart() {
        //柱状图
        yearBarChart.getDescription().setEnabled(false);
        yearBarChart.setBackgroundColor(Color.rgb(0,191,255));
        //设置最大值条目，超出之后不会有值
        yearBarChart.setMaxVisibleValueCount(60);
        //分别在x轴和y轴上进行缩放
        yearBarChart.setPinchZoom(true);
        //设置剩余统计图的阴影
        yearBarChart.setDrawBarShadow(false);
        //设置网格布局
        yearBarChart.setDrawGridBackground(true);
        //通过自定义一个x轴标签来实现2,015 有分割符符bug
//        ValueFormatter custom = new MyValueFormatter("");
        //获取x轴线
        XAxis xAxis = yearBarChart.getXAxis();

        //设置x轴的显示位置
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置网格布局
        xAxis.setDrawGridLines(true);
        //图表将避免第一个和最后一个标签条目被减掉在图表或屏幕的边缘
        xAxis.setAvoidFirstLastClipping(false);
        //绘制标签  指x轴上的对应数值 默认true
        xAxis.setDrawLabels(true);
        //设置X轴上文本大小
        xAxis.setTextSize(12);

//        xAxis.setValueFormatter(custom);
        //缩放后x 轴数据重叠问题
        xAxis.setGranularityEnabled(true);
        //获取右边y标签
        YAxis axisRight = yearBarChart.getAxisRight();

        axisRight.setAxisMinimum(0f);


        //获取左边y轴的标签
        YAxis axisLeft = yearBarChart.getAxisLeft();
        //设置Y轴数值 从零开始
        axisLeft.setAxisMinimum(0f);
        yearBarChart.getAxisLeft().setDrawGridLines(false);
        //设置动画时间（X轴和Y轴都设置）
//        mBarChart.animateXY(5000, 5000);
        //只设置X轴动画时间
        yearBarChart.animateY(5000);//动画时长

        yearBarChart.getLegend().setEnabled(true);

        getYearBarData();
        //设置柱形统计  柱上的文本大小
        yearBarChart.getData().setValueTextSize(10);

        for (IDataSet set : yearBarChart.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
    }

    //获取柱状图数据
    private void getYearBarData() {
        ArrayList<BarEntry> values = new ArrayList<>();
        BarEntry barEntry = new BarEntry(Float.valueOf("1"), Float.valueOf("290"));
        BarEntry barEntry1 = new BarEntry(Float.valueOf("2"), Float.valueOf("210"));
        BarEntry barEntry2 = new BarEntry(Float.valueOf("3"), Float.valueOf("400"));
        BarEntry barEntry3 = new BarEntry(Float.valueOf("4"), Float.valueOf("450"));
        BarEntry barEntry4 = new BarEntry(Float.valueOf("5"), Float.valueOf("500"));
        BarEntry barEntry5 = new BarEntry(Float.valueOf("6"), Float.valueOf("650"));
//        BarEntry barEntry6 = new BarEntry(Float.valueOf(""), Float.valueOf("740"));
//        BarEntry barEntry7 = new BarEntry(Float.valueOf("2023"), Float.valueOf("240"));

        values.add(barEntry);
        values.add(barEntry1);
        values.add(barEntry2);
        values.add(barEntry3);
        values.add(barEntry4);
        values.add(barEntry5);
//        values.add(barEntry6);
//        values.add(barEntry7);

        BarDataSet set1;

        if (yearBarChart.getData() != null &&
                yearBarChart.getData().getDataSetCount() > 0) {
            set1 = (BarDataSet) yearBarChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            yearBarChart.getData().notifyDataChanged();
            yearBarChart.notifyDataSetChanged();
        } else {
            set1 = new BarDataSet(values, "单词量");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            yearBarChart.setData(data);
            yearBarChart.setFitBars(true);
        }
        //绘制图表
        yearBarChart.invalidate();
    }

    private void initRadarChart() {
        radarChart.setBackgroundColor(Color.rgb(123,104,238));
        radarChart.getDescription().setEnabled(false);

        radarChart.setWebLineWidth(1f);
        radarChart.setWebColor(Color.LTGRAY);
        radarChart.setWebLineWidthInner(1f);
        radarChart.setWebColorInner(Color.LTGRAY);
        radarChart.setWebAlpha(100);

        // create a custom MarkerView (extend MarkerView) and specify the layout
        // to use for it
        MarkerView mv = new RadarMarkerView(this.getContext(), R.layout.radar_markerview);
        mv.setChartView(radarChart); // For bounds control
        radarChart.setMarker(mv); // Set the marker to the chart

        setRadarChartData();

        radarChart.animateXY(1400, 1400, Easing.EaseInOutQuad);

        XAxis xAxis = radarChart.getXAxis();
//        xAxis.setTypeface(tfLight);
        xAxis.setTextSize(12f);
        xAxis.setYOffset(0f);
        xAxis.setXOffset(0f);
        xAxis.setValueFormatter(new RadarChartFormatter());

        xAxis.setTextColor(Color.WHITE);

        YAxis yAxis = radarChart.getYAxis();
//        yAxis.setTypeface(tfLight);
        yAxis.setLabelCount(5, false);
        yAxis.setTextSize(9f);
        yAxis.setAxisMinimum(0f);
        yAxis.setAxisMaximum(80f);
        yAxis.setDrawLabels(false);

        Legend l = radarChart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.CENTER);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(false);
//        l.setTypeface(tfLight);
        l.setXEntrySpace(7f);
        l.setYEntrySpace(5f);
        l.setTextColor(Color.WHITE);

    }

    private void setRadarChartData() {
        float mul = 80;
        float min = 20;
        int cnt = 5;

        ArrayList<RadarEntry> entries1 = new ArrayList<>();
        ArrayList<RadarEntry> entries2 = new ArrayList<>();

        // NOTE: The order of the entries when being added to the entries array determines their position around the center of
        // the chart.
        for (int i = 0; i < cnt; i++) {
            float val1 = (float) (Math.random() * mul) + min;
            entries1.add(new RadarEntry(val1));
            float val2 = (float) (Math.random() * mul) + min;
            entries2.add(new RadarEntry(val2));
        }
        RadarDataSet set1 = new RadarDataSet(entries1, "上周");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);
        RadarDataSet set2 = new RadarDataSet(entries2, "本周");
        set2.setColor(Color.rgb(121, 162, 175));
        set2.setFillColor(Color.rgb(121, 162, 175));
        set2.setDrawFilled(true);
        set2.setFillAlpha(180);
        set2.setLineWidth(2f);
        set2.setDrawHighlightCircleEnabled(true);
        set2.setDrawHighlightIndicators(false);
        ArrayList<IRadarDataSet> sets = new ArrayList<>();
        sets.add(set1);
        sets.add(set2);

        RadarData data = new RadarData(sets);
//        data.setValueTypeface(tfLight);
        data.setValueTextSize(10f);
        data.setDrawValues(false);
        data.setValueTextColor(Color.WHITE);

        radarChart.setData(data);
        radarChart.invalidate();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_study_info;
    }

    @Override
    protected void initViews() {

    }
}