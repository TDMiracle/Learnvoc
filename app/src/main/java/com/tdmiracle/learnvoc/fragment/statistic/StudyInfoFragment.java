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
 * ???????????????2021/5/3 20:02
 * @author TD.Miracle
 * @version 1.0
 * ??????????????? StudyInfoFragment.java
 * ????????????
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
        //?????????
        yearBarChart.getDescription().setEnabled(false);
        yearBarChart.setBackgroundColor(Color.rgb(0,191,255));
        //????????????????????????????????????????????????
        yearBarChart.setMaxVisibleValueCount(60);
        //?????????x??????y??????????????????
        yearBarChart.setPinchZoom(true);
        //??????????????????????????????
        yearBarChart.setDrawBarShadow(false);
        //??????????????????
        yearBarChart.setDrawGridBackground(true);
        //?????????????????????x??????????????????2,015 ???????????????bug
//        ValueFormatter custom = new MyValueFormatter("");
        //??????x??????
        XAxis xAxis = yearBarChart.getXAxis();

        //??????x??????????????????
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //??????????????????
        xAxis.setDrawGridLines(true);
        //???????????????????????????????????????????????????????????????????????????????????????
        xAxis.setAvoidFirstLastClipping(false);
        //????????????  ???x????????????????????? ??????true
        xAxis.setDrawLabels(true);
        //??????X??????????????????
        xAxis.setTextSize(12);

//        xAxis.setValueFormatter(custom);
        //?????????x ?????????????????????
        xAxis.setGranularityEnabled(true);
        //????????????y??????
        YAxis axisRight = yearBarChart.getAxisRight();

        axisRight.setAxisMinimum(0f);


        //????????????y????????????
        YAxis axisLeft = yearBarChart.getAxisLeft();
        //??????Y????????? ????????????
        axisLeft.setAxisMinimum(0f);
        yearBarChart.getAxisLeft().setDrawGridLines(false);
        //?????????????????????X??????Y???????????????
//        mBarChart.animateXY(5000, 5000);
        //?????????X???????????????
        yearBarChart.animateY(5000);//????????????

        yearBarChart.getLegend().setEnabled(true);

        getYearBarData();
        //??????????????????  ?????????????????????
        yearBarChart.getData().setValueTextSize(10);

        for (IDataSet set : yearBarChart.getData().getDataSets()) {
            set.setDrawValues(!set.isDrawValuesEnabled());
        }
    }

    //?????????????????????
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
            set1 = new BarDataSet(values, "?????????");
            set1.setColors(ColorTemplate.VORDIPLOM_COLORS);
            set1.setDrawValues(false);
            ArrayList<IBarDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            BarData data = new BarData(dataSets);
            yearBarChart.setData(data);
            yearBarChart.setFitBars(true);
        }
        //????????????
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
        RadarDataSet set1 = new RadarDataSet(entries1, "??????");
        set1.setColor(Color.rgb(103, 110, 129));
        set1.setFillColor(Color.rgb(103, 110, 129));
        set1.setDrawFilled(true);
        set1.setFillAlpha(180);
        set1.setLineWidth(2f);
        set1.setDrawHighlightCircleEnabled(true);
        set1.setDrawHighlightIndicators(false);
        RadarDataSet set2 = new RadarDataSet(entries2, "??????");
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