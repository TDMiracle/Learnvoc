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
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.WordDetailActivity;
import com.tdmiracle.learnvoc.module.Word;

import java.util.List;

/**
 * WordListActivity recycleView适配器
 */
public class WordListAdapter extends RecyclerView.Adapter<WordListAdapter.ViewHolder> {

    List<Word> words;

    public WordListAdapter(List<Word> words) {
        this.words = words;
    }
    /*
     * 子布局创建时候对其进行布局绑定和Item的点击事件的设置
     * */
    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item, parent, false);
        ViewHolder holder = new ViewHolder(view);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(parent.getContext(), WordDetailActivity.class);
                intent.putExtra("word",holder.word.getText());
                parent.getContext().startActivity(intent);
            }
        });
        return holder;
    }

    /*
     * 子布局控件的数据设置
     * */
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        //holder.image.setImageResource(R.mipmap.leak_canary_icon);
        holder.word.setText(words.get(position).getWord());
        holder.yinbiao.setText(words.get(position).getYinbiao());
        holder.translation.setText(words.get(position).getTranslation());
    }


    @Override
    public int getItemCount() {
        return words.size();
    }

    /*
     * 子布局控件的初始化
     * */
    static class ViewHolder extends RecyclerView.ViewHolder {
        public ImageView image;
        public TextView word;
        public TextView yinbiao;
        public TextView translation;
        public CardView cardView;

        public ViewHolder(View itemView) {
            super(itemView);
            //image = (ImageView) itemView.findViewById(R.id.voc_image);
            word = (TextView) itemView.findViewById(R.id.voc_word);
            yinbiao = (TextView) itemView.findViewById(R.id.voc_yinbiao);
            translation = (TextView) itemView.findViewById(R.id.voc_translation);
            cardView = (CardView) itemView.findViewById(R.id.card_view);
        }
    }

}
