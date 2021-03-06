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

package com.tdmiracle.learnvoc.adapter.model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.adapter.ICallback;
import com.tdmiracle.learnvoc.adapter.NotificaitonContract;
import com.tdmiracle.learnvoc.adapter.entity.Notification;
import com.tdmiracle.learnvoc.dao.NotificationDao;
import com.tdmiracle.learnvoc.dao.daoImpl.NotificationDaoImpl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * 通知消息获取
 */
public class NotificationModel implements NotificaitonContract.Model {
    //从数据库中获取通知信息
    @Override
    public void getnotificationlist(Context context, boolean isshow,ICallback<List<Notification>> callback) {
        List<Notification> list;
        NotificationDao dao = new NotificationDaoImpl();
        list = dao.findNotificationList();
        callback.onSucceed(list);
    }




    public static class NotificationAdapter extends RecyclerView.Adapter<NotificationAdapter.Viewholder> {
        Context context;
        List<Notification> list;

        //        private static onselect select;
        public NotificationAdapter(Context context, List<Notification> list) {
            this.context = context;
            this.list = list;
        }

//        public interface onselect{
//            void onclicke(int s);
//        }
//
//        public static void setischeck(onselect myListener) {
//            select = myListener;
//        }


        @NonNull
        @Override
        public NotificationAdapter.Viewholder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            if (context == null) {
                context = parent.getContext();
            }
            View view = LayoutInflater.from(context).inflate(R.layout.activity_notification_recycleview_item, parent, false);
            final NotificationAdapter.Viewholder holder = new NotificationAdapter.Viewholder(view);
            return holder;
        }

        @Override
        public void onBindViewHolder(@NonNull final NotificationAdapter.Viewholder holder, final int position) {
            final Notification notification = list.get(position);
            holder.text_notcontent_item.setText(notification.getNot_content());
            holder.text_nottitle_item.setText(notification.getNot_title());
            holder.text_nottime_item.setText(notification.getNot_time());
            if (notification.isShow()) {
                holder.check_not_itme.setVisibility(View.VISIBLE);
            } else {
                holder.check_not_itme.setVisibility(View.GONE);
            }

            if (notification.isCheck()) {
                holder.check_not_itme.setChecked(true);
            } else {
                holder.check_not_itme.setChecked(false);
            }


            holder.check_not_itme.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (holder.check_not_itme.isChecked()) {
                        notification.setCheck(true);
                    } else {
                        notification.setCheck(false);
                    }
                }
            });


            holder.relativeLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (notification.isShow()) {
                        if (holder.check_not_itme.isChecked()) {
                            notification.setCheck(false);
                            holder.check_not_itme.setChecked(false);
                        } else {
                            notification.setCheck(true);
                            holder.check_not_itme.setChecked(true);
                        }
                    } else {

                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return list.size();
        }

        /**
         * @作者 Administrator
         * @时间 2019/4/1 0001 下午 4:00
         * 批量删除
         */
        public void deleteItem() {
            Iterator<Notification> iterator = list.iterator();
            while (iterator.hasNext()) {
                Notification notificationEntity = iterator.next();
                if (notificationEntity.isCheck()) {
                    iterator.remove();
                }
            }
            notifyDataSetChanged();
        }

        /**
         * 全选所有的通知
         */
        public void chechall(boolean t) {
            Iterator<Notification> iterator = list.iterator();
            while (iterator.hasNext()) {
                Notification notificationEntity = iterator.next();
                if (t) {
                    if (!notificationEntity.isCheck()) {
                        notificationEntity.setCheck(true);
                    }
                } else {
                    if (notificationEntity.isCheck()) {
                        notificationEntity.setCheck(false);
                    }
                }
            }
            notifyDataSetChanged();
        }

        /**
         * 是否显示单选框
         */
        public void isshowcheck(boolean t) {
            Iterator<Notification> iterator = list.iterator();
            while (iterator.hasNext()) {
                Notification notificationEntity = iterator.next();
                if (t) {
                    if (!notificationEntity.isShow()) {
                        notificationEntity.setShow(true);
                    }
                } else {
                    if (notificationEntity.isShow()) {
                        notificationEntity.setShow(false);
                    }
                }
            }
            notifyDataSetChanged();
        }


        public class Viewholder extends RecyclerView.ViewHolder {
            TextView text_nottitle_item, text_notcontent_item, text_nottime_item;
            CheckBox check_not_itme;
            RelativeLayout relativeLayout;

            public Viewholder(@NonNull View itemView) {
                super(itemView);
                relativeLayout = (RelativeLayout) itemView;
                text_nottitle_item = itemView.findViewById(R.id.text_nottitle_item);
                text_notcontent_item = itemView.findViewById(R.id.text_notcontent_item);
                text_nottime_item = itemView.findViewById(R.id.text_nottime_item);
                check_not_itme = itemView.findViewById(R.id.check_not_itme);
            }
        }
    }
}
