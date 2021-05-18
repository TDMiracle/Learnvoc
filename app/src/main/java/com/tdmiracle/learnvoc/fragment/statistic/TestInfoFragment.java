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

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseFragment;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;


/**
 * 创建日期：2021/5/3 20:02
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： TestInfoFragment.java
 * 类说明：
 */
public class TestInfoFragment extends BaseFragment {

    @BindView(R.id.mlineChart)
    LineChart lineChart;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_test_info, container, false);
        ButterKnife.bind(this,rootView);
        initLineChart();
        return rootView;
    }

    private void initLineChart() {
        lineChart.setViewPortOffsets(0, 0, 0, 0);
        lineChart.setBackgroundColor(Color.rgb(0,191,255));

        // no description text
        lineChart.getDescription().setEnabled(true);
        Description description = new Description();  // 这部分是深度定制描述文本，颜色，字体等
        description.setText("单词测试得分");
        description.setTextSize(12);
        description.setTextColor(R.color.white);
        lineChart.setDescription(description);
        // enable touch gestures
        lineChart.setTouchEnabled(true);

        // enable scaling and dragging
        lineChart.setDragEnabled(true);
        lineChart.setScaleEnabled(true);

        // if disabled, scaling can be done on x- and y-axis separately
        lineChart.setPinchZoom(false);

        lineChart.setDrawGridBackground(false);
        lineChart.setMaxHighlightDistance(300);

        XAxis x = lineChart.getXAxis();
        x.setEnabled(true);
        x.setEnabled(true); // 轴线是否可编辑,默认true
        x.setDrawLabels(true);  // 是否绘制标签,默认true
        x.setDrawAxisLine(true);    // 是否绘制坐标轴,默认true
        x.setDrawGridLines(false);   // 是否绘制网格线，默认true
        x.setAxisMaximum(10); // 此轴能显示的最大值；
        x.resetAxisMaximum();   // 撤销最大值；
        x.setAxisMinimum(1);    // 此轴显示的最小值；
        x.resetAxisMinimum();   // 撤销最小值；
        x.setTextSize(12);    // 标签字体大小，dp，6-24之间，默认为10dp

        YAxis y = lineChart.getAxisLeft();
//        y.setTypeface(tfLight);
        y.setLabelCount(6, false);
        y.setTextColor(Color.BLACK);
        y.setPosition(YAxis.YAxisLabelPosition.INSIDE_CHART);
        y.setDrawGridLines(false);
        y.setAxisLineColor(Color.BLACK);

        lineChart.getAxisRight().setEnabled(false);

        // add data


        lineChart.getLegend().setEnabled(false);

        lineChart.animateXY(2000, 2000);

        setLinChartData();

        // don't forget to refresh the drawing
        lineChart.invalidate();
        
    }

    private void setLinChartData() {
        ArrayList<Entry> values = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            float val = (float) (Math.random() * (30 + 1)) + 50;
            values.add(new Entry(i, val));
        }
        LineDataSet set1;
        if (lineChart.getData() != null &&
                lineChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) lineChart.getData().getDataSetByIndex(0);
            set1.setValues(values);
            lineChart.getData().notifyDataChanged();
            lineChart.notifyDataSetChanged();
        } else {
            // create a dataset and give it a type
            set1 = new LineDataSet(values, "DataSet 1");

            set1.setMode(LineDataSet.Mode.CUBIC_BEZIER);
            set1.setCubicIntensity(0.2f);
            set1.setDrawFilled(true);
            set1.setDrawCircles(false);
            set1.setLineWidth(1.8f);
            set1.setCircleRadius(4f);
            set1.setCircleColor(Color.WHITE);
            set1.setHighLightColor(Color.rgb(244, 117, 117));
            set1.setColor(Color.WHITE);
            set1.setFillColor(Color.WHITE);
            set1.setFillAlpha(100);
            set1.setDrawHorizontalHighlightIndicator(false);
            set1.setFillFormatter(new IFillFormatter() {
                @Override
                public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                    return lineChart.getAxisLeft().getAxisMinimum();
                }
            });

            // create a data object with the data sets
            LineData data = new LineData(set1);
//            data.setValueTypeface(tfLight);
            data.setValueTextSize(9f);
            data.setDrawValues(false);

            // set data
            lineChart.setData(data);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_test_info;
    }

    @Override
    protected void initViews() {

    }
}