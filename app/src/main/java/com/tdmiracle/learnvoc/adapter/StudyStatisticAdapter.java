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
import com.tdmiracle.learnvoc.module.UserWordsBook;
import com.tdmiracle.learnvoc.module.WordsBook;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.xuexiang.xui.widget.imageview.RadiusImageView;
import com.xuexiang.xupdate.widget.NumberProgressBar;

import org.w3c.dom.Text;

import java.util.List;

public class StudyStatisticAdapter extends RecyclerView.Adapter<StudyStatisticAdapter.ViewHolder>{
    List<UserWordsBook> wordsBooks;
    Context context;

    public StudyStatisticAdapter(List<UserWordsBook> wordsBooks ,Context context) {
        this.wordsBooks = wordsBooks;
        this.context = context;
    }

    /*
     * 子布局创建时候对其进行布局绑定和Item的点击事件的设置
     * */
    @Override
    public StudyStatisticAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item_study_stat,parent, false);
        StudyStatisticAdapter.ViewHolder holder = new StudyStatisticAdapter.ViewHolder(view);
        holder.select_wordBook_cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                /*实现ReciteWordsFragment单词书选择回调*/
//                String wordBookName = holder.select_wordBook_name.getText().toString();
//                String wordBookCount = holder.select_wordBook_wordCount.getText().toString();
//                Intent intent = new Intent();
//                intent.putExtra("bookName", wordBookName);
//                intent.putExtra("bookWordsCount",wordBookCount);
//                //从context获取主活动布局
////                View convertView = LayoutInflater.from(context).inflate(R.layout.activity_select_words_book,null);
////                ((Activity) context).startActivityForResult(intent, requestCode);
//                int resultCode = 0;
//                ((Activity) context).setResult(resultCode,intent);
//                ((Activity) context).finish();
            }
        });
        return holder;
    }


    /*
     * 子布局控件的数据设置
     * */
    @Override
    public void onBindViewHolder(StudyStatisticAdapter.ViewHolder holder, int position) {
        holder.imageView.setCornerRadius(2);
        switch (wordsBooks.get(position).getId() - 1){
            case 0:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.siji));
                break;
            case 1:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.liuji));
                break;
            case 2:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.gaozhong));
                break;
            case 3:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.chuzhong));
                break;
            case 4:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.yasi));
                break;
            case 5:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.tuofu));
                break;
            case 6:
                holder.imageView.setImageDrawable(context.getResources().getDrawable(R.drawable.kaoyan));
                break;
            default:
                holder.imageView.setVisibility(View.GONE);
                break;
        }
        holder.select_wordBook_name.setText(wordsBooks.get(position).getWordsBook().getName()+"词汇书");
        holder.add_time.setText(FormatUtils.getDateTimeString(wordsBooks.get(position).getAdd_time()));
        holder.update_time.setText(FormatUtils.getDateTimeString(wordsBooks.get(position).getFinish_time()));
        holder.numberProgressBar.setMax(wordsBooks.get(position).getWordsBook().getCount());
        holder.numberProgressBar.setProgress(wordsBooks.get(position).getStudiedCount());
        holder.numberProgressBar.setSuffix("%");
        holder.finishWord.setText(wordsBooks.get(position).getStudiedCount()+"");
        holder.totalWord.setText(wordsBooks.get(position).getWordsBook().getCount()+"");
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
        public NumberProgressBar numberProgressBar;
        public RadiusImageView imageView;
        public TextView add_time;
        public TextView update_time;
        public TextView totalWord;
        public TextView finishWord;

        public ViewHolder(View itemView) {
            super(itemView);
            select_wordBook_cardView = (CardView) itemView.findViewById(R.id.static_wordBook_cardView);
            select_wordBook_name = (TextView) itemView.findViewById(R.id.static_wordBook_name);
            numberProgressBar = (NumberProgressBar)itemView.findViewById(R.id.static_wordBook_bar);
            imageView = (RadiusImageView) itemView.findViewById(R.id.static_wordBook_bookImg);
            add_time = (TextView) itemView.findViewById(R.id.static_wordBook_addTime);
            update_time = (TextView) itemView.findViewById(R.id.static_wordBook_updateTime);
            totalWord = (TextView) itemView.findViewById(R.id.static_wordBook_totalWord);
            finishWord = (TextView) itemView.findViewById(R.id.static_wordBook_finishCount);
        }
    }
}
