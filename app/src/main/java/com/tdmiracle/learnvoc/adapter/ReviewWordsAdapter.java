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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.ReviewWordsActivity;
import com.tdmiracle.learnvoc.activity.TestWordsActivity2;
import com.tdmiracle.learnvoc.activity.TestWordsActivity3;
import com.tdmiracle.learnvoc.adapter.entity.WordReviewQuestionType;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.List;

/**
 * 创建日期：2021/5/6 16:39
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ReviewWordsAdapter.java
 * 类说明：单词复习：单词书选择适配器
 */
public class ReviewWordsAdapter extends RecyclerView.Adapter<ReviewWordsAdapter.ViewHolder> {
    List<WordReviewQuestionType> questionTypes;

    public ReviewWordsAdapter(List<WordReviewQuestionType> questionTypes) {
        this.questionTypes = questionTypes;
    }
    /*
     * 子布局创建时候对其进行布局绑定和Item的点击事件的设置
     * */
    @Override
    public ReviewWordsAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_review, parent, false);
        ReviewWordsAdapter.ViewHolder holder = new ReviewWordsAdapter.ViewHolder(view);
        holder.review_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(parent.getContext(), WordDetailActivity.class);
//                intent.putExtra("word",holder.word.getText());
//                parent.getContext().startActivity(intent);
                Intent intent;
                int typeNum = holder.questionNum;
                switch (typeNum){
                    case ConstUtils.WordReviewType.KanYingXuanZhong:
                    case ConstUtils.WordReviewType.KanZhongXuanYing:
                        intent = new Intent(parent.getContext(),ReviewWordsActivity.class);
                        intent.putExtra("type",typeNum);//传递题型编号
                        parent.getContext().startActivity(intent);
                        break;
                    case ConstUtils.WordReviewType.TingYinBianYi:
                        ActivityUtils.startActivity(TestWordsActivity2.class);
                        break;
                    case ConstUtils.WordReviewType.PinXieTianKong:
                        ActivityUtils.startActivity(TestWordsActivity3.class);
                        break;
                    default:
                        XToastUtils.toast("敬请期待...");
                }
            }
        });
        return holder;
    }


    /*
     * 子布局控件的数据设置
     * */
    @Override
    public void onBindViewHolder(ReviewWordsAdapter.ViewHolder holder, int position) {
        //holder.image.setImageResource(R.mipmap.leak_canary_icon);
//        holder.review_type.setText(wordsBooks.get(position).getCount());
        holder.word_review_questionTitle.setText(questionTypes.get(position).getTitle());
        holder.word_review_questionType.setText(questionTypes.get(position).getType());
        holder.word_review_questionCount.setText(questionTypes.get(position).getQuestionCount() + "+");
        holder.questionNum = questionTypes.get(position).getId();
    }


    @Override
    public int getItemCount() {
        return questionTypes.size();
    }

    /*
     * 子布局控件的初始化
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView word_review_questionCount;
        public TextView word_review_questionTitle;
        public TextView word_review_questionType;
        public CardView review_cardView;
        public int questionNum;

        public ViewHolder(View itemView) {
            super(itemView);
            review_cardView = (CardView) itemView.findViewById(R.id.review_cardView);
            word_review_questionCount = (TextView) itemView.findViewById(R.id.word_review_questionCount);
            word_review_questionTitle = (TextView) itemView.findViewById(R.id.word_review_questionTitle);
            word_review_questionType = (TextView) itemView.findViewById(R.id.word_review_questionType);
        }
    }
}
