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

package com.tdmiracle.learnvoc.adapter;

import android.content.Intent;
import android.graphics.Color;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.WordDetailActivity;
import com.tdmiracle.learnvoc.module.UserWordTest;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordTestQuestion;

import java.util.List;

public class GradeActivityAdapter extends RecyclerView.Adapter<GradeActivityAdapter.ViewHolder>{

    private final String TAG = "GradeActivityAdapter";
    List<UserWordTest> userWordTests;

    public GradeActivityAdapter(List<UserWordTest> userWordTests) {
        this.userWordTests = userWordTests;
    }
    /*
     * 子布局创建时候对其进行布局绑定和Item的点击事件的设置
     * */
    @Override
    public GradeActivityAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_activity_score, parent, false);
        GradeActivityAdapter.ViewHolder holder = new GradeActivityAdapter.ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(parent.getContext(), WordDetailActivity.class);
//                intent.putExtra("word",holder.tvTitle.getText());
//                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    /*
     * 子布局控件的数据设置
     * */
    @Override
    public void onBindViewHolder(GradeActivityAdapter.ViewHolder holder, int position) {
        WordTestQuestion wordTestQuestion = userWordTests.get(position).getWordTestQuestion();
        Log.d(TAG, "onBindViewHolder: "+ wordTestQuestion.toString());
        holder.tvTitle.setText(wordTestQuestion.getWord());
        holder.tvOptionA.setText(wordTestQuestion.getChoice_A());
        holder.tvOptionB.setText(wordTestQuestion.getChoice_B());
        holder.tvOptionC.setText(wordTestQuestion.getChoice_C());
        holder.tvOptionD.setText(wordTestQuestion.getChoice_D());
        holder.tvRightAnswer.setText(wordTestQuestion.getRightChoice() + "");
        holder.tvWrongAnswer.setText(userWordTests.get(position).getUserChoice()+"");
        if(!userWordTests.get(position).isIs_right()){
            holder.tvTitle.setTextColor(Color.RED);
        }
    }


    @Override
    public int getItemCount() {
        return userWordTests.size();
    }

    /*
     * 子布局控件的初始化
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvTitle;
        public TextView tvOptionA;
        public TextView tvOptionB;
        public TextView tvOptionC;
        public TextView tvOptionD;
        public TextView tvRightAnswer;
        public TextView tvWrongAnswer;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            cardView = (CardView) itemView.findViewById(R.id.grade_card_view);
            tvTitle = (TextView) itemView.findViewById(R.id.tv_item_activty_score_title);
            tvOptionA = (TextView) itemView.findViewById(R.id.tv_item_activty_score_optionA);
            tvOptionB = (TextView) itemView.findViewById(R.id.tv_item_activty_score_optionB);
            tvOptionC = (TextView) itemView.findViewById(R.id.tv_item_activty_score_optionC);
            tvOptionD = (TextView) itemView.findViewById(R.id.tv_item_activty_score_optionD);
            tvRightAnswer = (TextView) itemView.findViewById(R.id.tv_item_activty_score_right);
            tvWrongAnswer = (TextView) itemView.findViewById(R.id.tv_item_activty_score_wrong);
        }
    }
}
