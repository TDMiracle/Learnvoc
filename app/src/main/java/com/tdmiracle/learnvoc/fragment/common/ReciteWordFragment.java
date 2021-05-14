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

package com.tdmiracle.learnvoc.fragment.common;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.module.RowWords;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.UserWordTest;
import com.tdmiracle.learnvoc.module.Word;
import com.tdmiracle.learnvoc.module.WordTestQuestion;
import com.tdmiracle.learnvoc.module.WordsRecite;
import com.tdmiracle.learnvoc.utils.XToastUtils;

import org.litepal.LitePal;

import java.util.Date;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * 创建日期：2021/5/14 15:03
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： ReciteWordFragment.java
 * 类说明：单词背诵数据承载碎片
 */
public class ReciteWordFragment extends BaseFragment {

    @BindView(R.id.word_recite_frag_fav)
    ImageView add_fav;
    @BindView(R.id.word_recite_frag_btn_know)
    Button btn_next;
    @BindView(R.id.word_recite_frag_btn_notice)
    Button btn_notice;
    @BindView(R.id.word_recite_frag_btn_master)
    Button btn_master;
    @BindView(R.id.word_recite_frag_word)
    TextView recite_word;
    @BindView(R.id.word_recite_frag_yinbiao)
    TextView recite_yinbiao;
    @BindView(R.id.word_recite_frag_translation)
    TextView recite_translation;
    @BindView(R.id.word_recite_frag_tip)
    ImageView tip;

    WordsRecite wordsRecite;
    User globalUser;
    boolean is_added = false;//添加生词本标记

    public ReciteWordFragment(WordsRecite wordsRecite) {
        this.wordsRecite = wordsRecite;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView =  inflater.inflate(R.layout.fragment_recite_words, container, false);
        ButterKnife.bind(this,rootView);
        initViews();
        recordWordInfo();
        //获取全局变量
        MyApp app = (MyApp) getActivity().getApplication();
        globalUser = app.getUser();
        return rootView;
    }

    //记录单词背诵信息
    private void recordWordInfo() {
        wordsRecite.setFirst_time(new Date());
        wordsRecite.setTimes(1);
    }

    @Override
    protected void initViews() {
        recite_yinbiao.setText(wordsRecite.getWord().getYinbiao());
        recite_translation.setText(wordsRecite.getWord().getTranslation());
        recite_word.setText(wordsRecite.getWord().getWord());
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_recite_words;
    }


    @SuppressLint("NonConstantResourceId")
    @OnClick({R.id.word_recite_frag_fav,R.id.word_recite_frag_btn_know,R.id.word_recite_frag_btn_notice,
            R.id.word_recite_frag_btn_master,R.id.word_recite_frag_tip})
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.word_recite_frag_tip:
                //翻译、音标可见
                recite_translation.setVisibility(View.VISIBLE);
                recite_yinbiao.setVisibility(View.VISIBLE);
                break;
            case R.id.word_recite_frag_fav:
                if(!is_added){
                    addToWordsBook(wordsRecite.getWord());//添加到生词本
                }
                else {
                    deleteFromWordsBook(wordsRecite.getWord());//从生词本移除
                }
                break;
            case R.id.word_recite_frag_btn_know://认识3
                wordsRecite.setFamiliarity(3);
                break;
            case R.id.word_recite_frag_btn_notice://完全一抹黑1
                wordsRecite.setFamiliarity(1);
                break;
            case R.id.word_recite_frag_btn_master://记得非常好6
                wordsRecite.setFamiliarity(6);
                break;
        }
    }

    @Override
    public void onDestroy() {
        saveReciteWordInfo();
        super.onDestroy();
    }

    //碎片结束时保存背诵信息
    private void saveReciteWordInfo() {
        if(wordsRecite.getFamiliarity()==6){
            wordsRecite.setIs_grasp(true);
        }
        wordsRecite.setLatest_time(new Date());
        wordsRecite.save();
    }

    //添加生词本
    private void addToWordsBook(Word addWord){
        RowWords oldWord = LitePal.where("word = ?",addWord.getWord()).findLast(RowWords.class);
        if(oldWord == null){
            //添加到生词本
            oldWord = new RowWords();
            oldWord.setUser_id(globalUser.getId());
            oldWord.setWord(addWord.getWord());
            oldWord.setWordtype(addWord.getWordType());
            oldWord.setYinbiao(addWord.getYinbiao());
            oldWord.setTranslation(addWord.getTranslation());
            oldWord.save();
            XToastUtils.toast("成功添加到生词本");
        }else {
            XToastUtils.toast("已在单词本中");
        }
        add_fav.setImageResource(R.drawable.word_add_fav_added);
        is_added = true;
    }

    //从生词本移除
    private void deleteFromWordsBook(Word deleteWord){
        RowWords oldWord = LitePal.where("word = ?",deleteWord.getWord()).findLast(RowWords.class);
        if(oldWord == null){
            XToastUtils.toast("未添加到单词本，不可删除");
        }else{
            oldWord.delete();
            XToastUtils.toast("已从生词本中删除");
        }
        add_fav.setImageResource(R.drawable.word_add_fav_normal);
        is_added = false;
    }
}
