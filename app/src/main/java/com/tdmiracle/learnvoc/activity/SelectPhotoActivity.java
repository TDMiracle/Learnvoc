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

package com.tdmiracle.learnvoc.activity;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.StrictMode;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.core.BaseActivity;

import java.io.File;
import java.io.IOException;


/**
 * 创建日期：2021/4/11 19:55
 * @author TD.Miracle
 * @version 1.0
 * 文件名称： SelectPhotoActivity.java
 * 类说明：个人信息——修改头像
 */
public class SelectPhotoActivity extends BaseActivity implements View.OnClickListener {
    private TextView text_take_photo;
    private TextView text_pick_photo;
    private TextView text_cancle;
    private ImageView head_icon;
    private File mFile;
    private Bitmap mBitmap;
    String path = "";
    public static final int TAKE_PHOTO = 1;
    public static final int CHOOSE_PHOTO = 2;
    public static final int CUT_PHOTO = 3;
    private Uri imageUri;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_photo);
        initview();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void initview() {

        text_cancle=(TextView)findViewById(R.id.text_cancle);
        text_take_photo=(TextView)findViewById(R.id.text_take_photo);
        text_pick_photo=(TextView)findViewById(R.id.text_pick_photo);

        head_icon=findViewById(R.id.head_icon);

        text_cancle.setOnClickListener(this);
        text_take_photo.setOnClickListener(this);
        text_pick_photo.setOnClickListener(this);
        Request();
        //取消严格模式  FileProvider
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            StrictMode.VmPolicy.Builder builder = new StrictMode.VmPolicy.Builder();
            StrictMode.setVmPolicy( builder.build() );
        }

    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    void Request() {
        //获取相机拍摄读写权限
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            //版本判断
            if (checkSelfPermission(Manifest.permission.READ_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CAMERA}, 1);
            }
        }
    }


    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.text_cancle:
                //取消
                finish();
                break;
            case R.id.text_take_photo:
                pickImageFromCamera();

                break;
            case R.id.text_pick_photo:
                pickImageFromAlbum();
                break;
        }
    }
    //从相册选择
    private void pickImageFromAlbum() {
        Intent intent = new Intent(Intent.ACTION_PICK, null);
        intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent, CHOOSE_PHOTO);
    }

    //拍照
    private void pickImageFromCamera() {
        File outputImage=new File(getFilesDir(),
                "output_image.jpg");//创建File对象，用于存储拍照后的图片，获取sd卡根目录
        try{
            if(outputImage.exists()){
                outputImage.delete();
            }
            outputImage.createNewFile();
        }catch (IOException e){
            e.printStackTrace();
        }
        imageUri=Uri.fromFile(outputImage);//File对象转化为Uri对象
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT,imageUri);
        //startActivityForResult(intent,TAKE_PHOTO); //启动相机程序
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivityForResult(intent, TAKE_PHOTO);
        }

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PHOTO:
                    cropPhoto(imageUri);
                    break;
                case CHOOSE_PHOTO:
                    if (data == null || data.getData() == null) {
                        return;
                    }
                    try {
                        Uri uri = data.getData();
                        cropPhoto(uri);
                    }catch (Exception e){
                        e.printStackTrace();
                    }

                    break;
                case CUT_PHOTO:
                    Bundle bundle = data.getExtras();
                    if (bundle != null) {

                        //获取传递过来的用户名
                        Intent intent=getIntent();
                        String IdUser=intent.getStringExtra("idUser");

//                        //这里是绑定用户！！！
//                        UserServiceImpl userService=new UserServiceImpl();
//                        UserInfo userInfo= userService.findUserByID(IdUser);
//
//                        //在这里获得了剪裁后的Bitmap对象，可以用于上传
//                        Bitmap image = bundle.getParcelable("data");
//                        Log.e("我是裁剪后",""+image);
//                        //保存在数据库中
//                        userInfo.setHeadshot(Utils.imageToByte(image));
//                        userInfo.save();
//                        //显示在ImageView中
//                        head_icon.setImageBitmap(BitmapFactory.decodeByteArray(userInfo.getHeadshot(),0,userInfo.getHeadshot().length));


                        //更换成功后回到个人信息资料页
                        Intent intent1=new Intent(SelectPhotoActivity.this,EditPersonalInfoActivity.class);
                        startActivity(intent1);
                    }
                    break;
            }
        }
        super.onActivityResult(requestCode,resultCode,data);

    }

    /**
     * 裁剪图片
     */
    private void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.addFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
        intent.addFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        intent.putExtra("outputX", 300);
        intent.putExtra("outputY", 300);
        intent.putExtra("return-data", true);

        startActivityForResult(intent, CUT_PHOTO);
    }
}