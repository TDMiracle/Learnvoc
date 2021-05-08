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

import android.app.Activity;
import android.content.Context;
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
import com.tdmiracle.learnvoc.module.WordsBook;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xutil.app.ActivityUtils;

import java.util.List;


/**
 * 创建日期：2021/5/8 10:32
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： SelectWordBookAdapter.java
 * 类说明：背诵模块【单词书选择】适配器
 */
public class SelectWordBookAdapter extends RecyclerView.Adapter<SelectWordBookAdapter.ViewHolder>{
    List<WordsBook> wordsBooks;
    Context context;

    public SelectWordBookAdapter(List<WordsBook> wordsBooks ,Context context) {
        this.wordsBooks = wordsBooks;
        this.context = context;
    }

    /*
     * 子布局创建时候对其进行布局绑定和Item的点击事件的设置
     * */
    @Override
    public SelectWordBookAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_select_book, parent, false);
        SelectWordBookAdapter.ViewHolder holder = new SelectWordBookAdapter.ViewHolder(view);
        holder.select_wordBook_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /*实现ReciteWordsFragment单词书选择回调*/
                String wordBookName = holder.select_wordBook_name.getText().toString();
                String wordBookCount = holder.select_wordBook_wordCount.getText().toString();
                XToastUtils.toast(wordBookName+wordBookCount);
                Intent intent = new Intent();
                intent.putExtra("bookName", wordBookName);
                intent.putExtra("bookWordsCount",wordBookCount);
                //从context获取主活动布局
//                View convertView = LayoutInflater.from(context).inflate(R.layout.activity_select_words_book,null);
//                ((Activity) context).startActivityForResult(intent, requestCode);
                int resultCode = 0;
                ((Activity) context).setResult(resultCode,intent);
                ((Activity) context).finish();
            }
        });
        return holder;
    }


    /*
     * 子布局控件的数据设置
     * */
    @Override
    public void onBindViewHolder(SelectWordBookAdapter.ViewHolder holder, int position) {
        //holder.image.setImageResource(R.mipmap.leak_canary_icon);
//        holder.review_type.setText(wordsBooks.get(position).getCount());
        holder.select_wordBook_name.setText(wordsBooks.get(position).getName());
        holder.select_wordBook_wordCount.setText(wordsBooks.get(position).getCount()+"");
        holder.select_wordBook_description.setText(wordsBooks.get(position).getDescription());
    }


    @Override
    public int getItemCount() {
        return wordsBooks.size();
    }

    /*
     * 子布局控件的初始化
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public CardView select_wordBook_cardView;
        public TextView select_wordBook_name;
        public TextView select_wordBook_wordCount;
        public TextView select_wordBook_description;

        public ViewHolder(View itemView) {
            super(itemView);
            select_wordBook_cardView = (CardView) itemView.findViewById(R.id.select_wordBook_cardView);
            select_wordBook_name = (TextView) itemView.findViewById(R.id.select_wordBook_name);
            select_wordBook_wordCount = (TextView) itemView.findViewById(R.id.select_wordBook_wordCount);
            select_wordBook_description = (TextView) itemView.findViewById(R.id.select_wordBook_description);
        }
    }
}
