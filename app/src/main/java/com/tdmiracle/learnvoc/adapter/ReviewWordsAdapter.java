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


import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordsBook;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import java.util.List;

/**
 * 创建日期：2021/5/6 16:39
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ReviewWordsAdapter.java
 * 类说明：单词复习：单词书选择适配器
 */
public class ReviewWordsAdapter extends RecyclerView.Adapter<ReviewWordsAdapter.ViewHolder> {
    List<WordsBook> wordsBooks;

    public ReviewWordsAdapter(List<WordsBook> wordsBooks) {
        this.wordsBooks = wordsBooks;
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
                XToastUtils.toast(holder.review_type.getText());
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
        holder.review_type.setText("看英选中");
    }


    @Override
    public int getItemCount() {
        return wordsBooks.size();
    }

    /*
     * 子布局控件的初始化
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView review_type;
        public CardView review_cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            review_type = (TextView) itemView.findViewById(R.id.review_type);
            review_cardView = (CardView) itemView.findViewById(R.id.review_cardView);
        }
    }
}
