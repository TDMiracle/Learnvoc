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

package com.tdmiracle.learnvoc.adapter.entity;

import com.github.mikephil.charting.formatter.ValueFormatter;

public class RadarChartFormatter extends ValueFormatter {
    private final String[] mActivities = new String[]{"Abandon", "Vocabulary", "Miracle", "Implement", "Bit"};
    @Override
    public String getFormattedValue(float value) {
        return mActivities[(int) value % mActivities.length];
    }

}