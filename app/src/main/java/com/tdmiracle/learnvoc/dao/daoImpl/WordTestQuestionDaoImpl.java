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

package com.tdmiracle.learnvoc.dao.daoImpl;

import com.tdmiracle.learnvoc.dao.WordTestQuestionDao;
import com.tdmiracle.learnvoc.module.WordTestQuestion;

import org.litepal.LitePal;

import java.util.List;

public class WordTestQuestionDaoImpl implements WordTestQuestionDao {
    @Override
    public List<WordTestQuestion> findWordTestQuestionByType(int typeNum) {
        return LitePal.where("type = "+typeNum).find(WordTestQuestion.class);
    }
}
