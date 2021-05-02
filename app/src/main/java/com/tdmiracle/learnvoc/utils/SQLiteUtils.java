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

package com.tdmiracle.learnvoc.utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.tdmiracle.learnvoc.module.Word;

import java.util.ArrayList;

/*操纵单词数据库*/
public class SQLiteUtils extends SQLiteOpenHelper {
    /**
     * 声明一个AndroidSDK自带的数据库变量db
     */
    private SQLiteDatabase db;
    public static final String DB_NAME = "voc.db";
    public static final int DB_VERSION = 2;

    private static final String TAG = "SQLiteUtils";

    /**
     * 写一个这个类的构造函数，参数为上下文context，所谓上下文就是这个类所在包的路径
     * 指明上下文，数据库名，工厂默认空值，版本号默认从1开始
     * super(context,"db_test",null,1);
     * 把数据库设置成可写入状态，除非内存已满，那时候会自动设置为只读模式
     * 不过，以现如今的内存容量，估计一辈子也见不到几次内存占满的状态
     * db = getReadableDatabase();
     */
    public SQLiteUtils(Context context){
        super(context,DB_NAME,null,DB_VERSION);
        db = getReadableDatabase();
    }

    /**
     * 重写两个必须要重写的方法，因为class DBOpenHelper extends SQLiteOpenHelper
     * 而这两个方法是 abstract 类 SQLiteOpenHelper 中声明的 abstract 方法
     * 所以必须在子类 DBOpenHelper 中重写 abstract 方法
     * 想想也是，为啥规定这么死必须重写？
     * 因为，一个数据库表，首先是要被创建的，然后免不了是要进行增删改操作的
     * 所以就有onCreate()、onUpgrade()两个方法
     *
     */
    @Override
    public void onCreate(SQLiteDatabase db){

    }
    //版本适应
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){

    }

    public ArrayList<Word> getData(String tableName ,int count){
        ArrayList<Word> list = new ArrayList<Word>();
        Cursor cursor = db.query(tableName,null,null,null,null,null,"id DESC");
        //未定义加载个数则全部加载
        if(count == 0){
            while(cursor.moveToNext()){
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String word = cursor.getString(cursor.getColumnIndex("word"));
                String yinbiao = cursor.getString(cursor.getColumnIndex("yinbiao"));
                String translation = cursor.getString(cursor.getColumnIndex("translation"));
                list.add(new Word(id,word,yinbiao,translation,0));
            }
        }
        else {
            int i = 0;
            while (cursor.moveToNext()) {
                i ++;
                if(i > count) {
                    break;
                }
                int id = cursor.getInt(cursor.getColumnIndex("id"));
                String word = cursor.getString(cursor.getColumnIndex("word"));
                String yinbiao = cursor.getString(cursor.getColumnIndex("yinbiao"));
                String translation = cursor.getString(cursor.getColumnIndex("translation"));
                list.add(new Word(id, word, yinbiao, translation, 0));
            }
        }
        Log.d(TAG, "getAllData: " + list.get(0).toString());
        cursor.close();
        return list;
    }


}
