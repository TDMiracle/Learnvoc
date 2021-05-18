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

package com.tdmiracle.learnvoc.fragment;

import android.content.DialogInterface;
import android.graphics.Color;
import android.util.Log;
import android.view.View;

import androidx.appcompat.app.AlertDialog;

import com.tdmiracle.learnvoc.MyApp;
import com.tdmiracle.learnvoc.R;
import com.tdmiracle.learnvoc.activity.MainActivity;
import com.tdmiracle.learnvoc.activity.ReciteWordsActivity;
import com.tdmiracle.learnvoc.activity.WordsBookActivity;
import com.tdmiracle.learnvoc.core.BaseFragment;
import com.tdmiracle.learnvoc.dao.UserDao;
import com.tdmiracle.learnvoc.dao.daoImpl.UserDaoImpl;
import com.tdmiracle.learnvoc.module.LoginInfo;
import com.tdmiracle.learnvoc.module.User;
import com.tdmiracle.learnvoc.utils.MD5Utils;
import com.tdmiracle.learnvoc.utils.RandomUtils;
import com.tdmiracle.learnvoc.utils.SettingUtils;
import com.tdmiracle.learnvoc.utils.TokenUtils;
import com.tdmiracle.learnvoc.utils.Utils;
import com.tdmiracle.learnvoc.utils.XToastUtils;
import com.xuexiang.xaop.annotation.SingleClick;
import com.xuexiang.xpage.annotation.Page;
import com.xuexiang.xpage.enums.CoreAnim;
import com.xuexiang.xui.utils.CountDownButtonHelper;
import com.xuexiang.xui.utils.ResUtils;
import com.xuexiang.xui.utils.ThemeUtils;
import com.xuexiang.xui.widget.actionbar.TitleBar;
import com.xuexiang.xui.widget.button.roundbutton.RoundButton;
import com.xuexiang.xui.widget.edittext.materialedittext.MaterialEditText;
import com.xuexiang.xutil.app.ActivityUtils;

import butterknife.BindView;
import butterknife.OnClick;


/**
 * 登录页面
 *
 * @author TD.Miracle
 * @since 2021-3-17 22:15
 */
@Page(anim = CoreAnim.none)
public class LoginFragment extends BaseFragment {

    private final String TAG = "LoginFragment";

    @BindView(R.id.et_phone_number)
    MaterialEditText etPhoneNumber;
    @BindView(R.id.et_password)
    MaterialEditText etPassword;
    @BindView(R.id.btn_get_verify_code)
    RoundButton btnGetVerifyCode;

    private CountDownButtonHelper mCountDownHelper;
    private AlertDialog.Builder builder;//对话框建造者

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected TitleBar initTitle() {
        TitleBar titleBar = super.initTitle()
                .setImmersive(true);
        titleBar.setBackgroundColor(Color.TRANSPARENT);
        titleBar.setTitle("");
        titleBar.setLeftImageDrawable(ResUtils.getVectorDrawable(getContext(), R.drawable.ic_login_close));
        titleBar.setActionTextColor(ThemeUtils.resolveColor(getContext(), R.attr.colorAccent));
        titleBar.addAction(new TitleBar.TextAction(R.string.title_jump_login) {
            @Override
            public void performAction(View view) {
                /**Debug模式运行**/
                onLoginForTest();
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {
        mCountDownHelper = new CountDownButtonHelper(btnGetVerifyCode, 60);

        //隐私政策弹窗
        if (!SettingUtils.isAgreePrivacy()) {
            Utils.showPrivacyDialog(getContext(), (dialog, which) -> {
                dialog.dismiss();
                SettingUtils.setIsAgreePrivacy(true);
            });
        }
    }

    @SingleClick
    @OnClick({R.id.btn_get_verify_code, R.id.btn_login, R.id.tv_other_login, R.id.tv_forget_password, R.id.tv_user_protocol, R.id.tv_privacy_protocol})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_get_verify_code:
                if (etPhoneNumber.validate()) {
                    getVerifyCode(etPhoneNumber.getEditValue());
                }
                break;
            case R.id.btn_login:
                if (etPhoneNumber.validate()) {
                    if (etPassword.validate()) {
                        loginOrRegister(etPhoneNumber.getEditValue(), etPassword.getEditValue());
                    }
                }
                break;
            case R.id.tv_other_login:
                XToastUtils.info("其他登录方式");
                break;
            case R.id.tv_forget_password:
                XToastUtils.info("忘记密码");
                break;
            case R.id.tv_user_protocol:
                XToastUtils.info("用户协议");
                break;
            case R.id.tv_privacy_protocol:
                XToastUtils.info("隐私政策");
                break;
            default:
                break;
        }
    }

    /**
     * 获取验证码
     */
    private void getVerifyCode(String phoneNumber) {
        // TODO: 2020/8/29 这里只是界面演示而已
        XToastUtils.warning("只是演示，验证码请随便输");
        mCountDownHelper.start();
    }

    /**
     * 根据验证码登录
     *
     * @param phoneNumber 手机号
     * @param verifyCode  验证码
     */
    private void loginByVerifyCode(String phoneNumber, String verifyCode) {
        // TODO: 2020/8/29 这里只是界面演示而已
        onLoginSuccess();
    }

    /**
     * 根据手机好密码注册
     * @param phoneNumber
     * @param password
     */
    private void loginOrRegister(String phoneNumber, String password){
        UserDao userDao = new UserDaoImpl();
        User oldUser = userDao.findUserByPhone(phoneNumber);
        if(oldUser != null){//查询到对应手机的用户，则登录
            if(oldUser.getLoginInfo().getPassword().equals(MD5Utils.md5(password))){//密码正确登录成功
                Log.d(TAG, "loginOrRegister: 登录成功" + oldUser.toString());
                //存入全局对象
                MyApp app = (MyApp) getActivity().getApplication();
                app.setUser(oldUser);
                onLoginSuccess();//跳转主页面
            }
            else {//密码错误，登录失败
                XToastUtils.toast("密码错误，请检查密码",2000);
                etPassword.setText("");//情况密码框
            }
        }else {//没有查询到对应手机的用户，则注册新账号
            User newUser = new User();
            newUser.setPhone(phoneNumber);
            newUser.setUid(RandomUtils.getRandomNumbersAndLetters(10));//分配10位新账号
            Log.d(TAG, "loginOrRegister: 注册新账号");
            if(userDao.IncreaseUser(newUser,MD5Utils.md5(password))){//注册新用户
                //注册成功
                //存入全局对象
                MyApp app = (MyApp) getActivity().getApplication();
                app.setUser(newUser);
                initAlertDialog();
                builder.show();
            }else {
                XToastUtils.toast("注册失败",2000);
                //注册失败
                Log.d(TAG, "loginOrRegister: 注册失败");
            }
        }
    }

    private void initAlertDialog() {
        //新建对话框
        builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("提示");
        builder.setMessage("检测到当前手机号为首次登录，已为您注册成为易拾单词新用户");
        builder.setPositiveButton("知道啦", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onLoginSuccess();
            }
        });
    }

    /**
     * 登录成功的处理
     */
    private void onLoginSuccess() {
        String token = RandomUtils.getRandomNumbersAndLetters(16);
        if (TokenUtils.handleLoginSuccess(token)) {
            popToBack();
            ActivityUtils.startActivity(MainActivity.class);
        }
    }

    /**
     * 以debug模式调试系统，仅供模拟器调试使用
     */
    private void onLoginForTest(){
        UserDaoImpl userDao = new UserDaoImpl();
        User newUser = userDao.findUserById("2").get(0);
        if(newUser!=null){
            //存入全局对象
            MyApp app = (MyApp) this.getActivity().getApplication();
            app.setUser(newUser);
            Log.d(TAG, "onLoginForTest: " + newUser.toString());
            //新建对话框
            builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle("Debug");
            builder.setMessage("即将以Debug模式运行");
            builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onLoginSuccess();
                }
            });
        }
        else{
            //新建对话框
            builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle("Error");
            builder.setMessage("当前模式不可调试");
            builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                }
            });
        }
            builder.show();
    }

    @Override
    public void onDestroyView() {
        if (mCountDownHelper != null) {
            mCountDownHelper.recycle();
        }
        super.onDestroyView();
    }
}

