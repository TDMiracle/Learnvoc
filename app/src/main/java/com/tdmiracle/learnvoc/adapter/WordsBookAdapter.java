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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.WordDetailActivity;
import com.tdmiracle.learnvoc.module.RowWords;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordsRecite;
import com.tdmiracle.learnvoc.utils.ConstUtils;
import com.tdmiracle.learnvoc.utils.FormatUtils;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import java.util.List;

/**
 * 创建日期：2021/5/6 13:59
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： WordsBookAdapter.java
 * 类说明：单词本适配器
 */
public class WordsBookAdapter extends RecyclerView.Adapter<WordsBookAdapter.ViewHolder>{
    List<Word> words;//生词本
    List<WordsRecite> wordsReciteList;//背诵数据
    int type;//1:生词本；2：单词本

    public WordsBookAdapter(List<Word> words) {
        this.words = words;
    }

    /*
     * 子布局创建时候对其进行布局绑定和Item的点击事件的设置
     * */
    @Override
    public WordsBookAdapter.ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item2, parent, false);
        WordsBookAdapter.ViewHolder holder = new WordsBookAdapter.ViewHolder(view);
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Intent intent = new Intent(parent.getContext(), WordDetailActivity.class);
//                intent.putExtra("word",holder.word.getText());
//                parent.getContext().startActivity(intent);
//                XToastUtils.toast(holder.word.getText());
            }
        });
        return holder;
    }

    public void addData(int position, Word newWord) {
        words.add(newWord);
        notifyItemChanged(position);
//        notifyDataSetChanged();
    }

    public void removeData(int position) {
        words.remove(position);
        notifyItemRemoved(position);
    }

    public void notifyAdapter(List<Word> wordList,boolean isAdd){
        if (isAdd){
            words = wordList;
            wordsReciteList = null;
            type=0;
            notifyDataSetChanged();
        }
    }

    public void notifyAdapter2(List<WordsRecite> wordsReciteList,int type){
            this.wordsReciteList = wordsReciteList;
            words = null;
            this.type = type;
            notifyDataSetChanged();
    }

    /*
     * 子布局控件的数据设置
     * */
    @Override
    public void onBindViewHolder(WordsBookAdapter.ViewHolder holder, int position) {
        //holder.image.setImageResource(R.mipmap.leak_canary_icon);
        if(type!=0){//绑定单词本
            holder.word_reciteInfo.setVisibility(View.VISIBLE);//背诵详情可见
            holder.word.setText(wordsReciteList.get(position).getWord().getWord());
            holder.yinbiao.setText(wordsReciteList.get(position).getWord().getYinbiao());
            holder.translation.setText(wordsReciteList.get(position).getWord().getTranslation());
            if(!wordsReciteList.get(position).isIs_grasp()){
                holder.word_grasp.setText("未掌握");
                holder.word_grasp.setTextColor(Color.rgb(255,153,0));
            }
            holder.word_latestTime.setText(FormatUtils.getDateTimeString(wordsReciteList.get(position).getLatest_time()));
            holder.word_book_fromBook.setText(ConstUtils.WordsType.
                    getWordsType(wordsReciteList.get(position).getWord().getWordType()));
        }else {//绑定生词本
            holder.word_reciteInfo.setVisibility(View.GONE);//背诵详情不可见
            holder.word.setText(words.get(position).getWord());
            holder.yinbiao.setText(words.get(position).getYinbiao());
            holder.translation.setText(words.get(position).getTranslation());
        }
    }


    @Override
    public int getItemCount() {
        if(type!=0){
            return wordsReciteList.size();
        }else {
            return words.size();
        }
    }

    /*
     * 子布局控件的初始化
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView word;
        public TextView yinbiao;
        public TextView translation;
        public LinearLayout layout;
        public LinearLayout word_reciteInfo;
        public TextView word_grasp;
        public TextView word_latestTime;
        public TextView word_book_fromBook;

        public ViewHolder(View itemView) {
            super(itemView);
            layout = (LinearLayout) itemView.findViewById(R.id.word_book_linearLayout);
            word = (TextView) itemView.findViewById(R.id.word_book_word);
            yinbiao = (TextView) itemView.findViewById(R.id.word_book_yinbiao);
            translation = (TextView) itemView.findViewById(R.id.word_book_translation);
            word_reciteInfo = (LinearLayout) itemView.findViewById(R.id.word_book_reciteInfo);
            word_grasp = (TextView)itemView.findViewById(R.id.word_book_grasp);
            word_latestTime = (TextView) itemView.findViewById(R.id.word_book_latestTime);
            word_book_fromBook = (TextView) itemView.findViewById(R.id.word_book_fromBook);
        }
    }
}
