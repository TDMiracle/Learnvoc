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

package com.tdmiracle.learnvoc.dao;

import com.tdmiracle.learnvoc.module.RowWords;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.module.WordsBook;

import java.util.List;

public interface RowWordsDao {
    /**
     * 查找用户生词列表
     * @param user
     * @return
     */
    public List<RowWords> findUserAllRowWords(User user);

    /**
     * 用户添加生词
     * @param user
     * @param rowWord
     * @return
     */
    public boolean addRowWordByUser(User user,RowWords rowWord);

    /**
     * 用户添加生词
     * @param user
     * @param rowWords
     * @return
     */
    public boolean addRowWordsByUser(User user,List<RowWords> rowWords);

    /**
     * 用户删除生词
     * @param user
     * @param word
     * @return 返回删除条目数
     */
    public int deleteRowWordsByUser(User user,RowWords word);
}
