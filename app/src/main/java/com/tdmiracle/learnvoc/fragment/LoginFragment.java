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
 * ????????????
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
    private AlertDialog.Builder builder;//??????????????????

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
                /**Debug????????????**/
                onLoginForTest();
            }
        });
        return titleBar;
    }

    @Override
    protected void initViews() {
        mCountDownHelper = new CountDownButtonHelper(btnGetVerifyCode, 60);

        //??????????????????
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
                XToastUtils.info("??????????????????");
                break;
            case R.id.tv_forget_password:
                XToastUtils.info("????????????");
                break;
            case R.id.tv_user_protocol:
                XToastUtils.info("????????????");
                break;
            case R.id.tv_privacy_protocol:
                XToastUtils.info("????????????");
                break;
            default:
                break;
        }
    }

    /**
     * ???????????????
     */
    private void getVerifyCode(String phoneNumber) {
        // TODO: 2020/8/29 ??????????????????????????????
        XToastUtils.warning("????????????????????????????????????");
        mCountDownHelper.start();
    }

    /**
     * ?????????????????????
     *
     * @param phoneNumber ?????????
     * @param verifyCode  ?????????
     */
    private void loginByVerifyCode(String phoneNumber, String verifyCode) {
        // TODO: 2020/8/29 ??????????????????????????????
        onLoginSuccess();
    }

    /**
     * ???????????????????????????
     * @param phoneNumber
     * @param password
     */
    private void loginOrRegister(String phoneNumber, String password){
        UserDao userDao = new UserDaoImpl();
        User oldUser = userDao.findUserByPhone(phoneNumber);
        if(oldUser != null){//??????????????????????????????????????????
            if(oldUser.getLoginInfo().getPassword().equals(MD5Utils.md5(password))){//????????????????????????
                Log.d(TAG, "loginOrRegister: ????????????" + oldUser.toString());
                //??????????????????
                MyApp app = (MyApp) getActivity().getApplication();
                app.setUser(oldUser);
                onLoginSuccess();//???????????????
            }
            else {//???????????????????????????
                XToastUtils.toast("??????????????????????????????",2000);
                etPassword.setText("");//???????????????
            }
        }else {//?????????????????????????????????????????????????????????
            User newUser = new User();
            newUser.setPhone(phoneNumber);
            newUser.setUid(RandomUtils.getRandomNumbersAndLetters(10));//??????10????????????
            Log.d(TAG, "loginOrRegister: ???????????????");
            if(userDao.IncreaseUser(newUser,MD5Utils.md5(password))){//???????????????
                //????????????
                //??????????????????
                MyApp app = (MyApp) getActivity().getApplication();
                app.setUser(newUser);
                initAlertDialog();
                builder.show();
            }else {
                XToastUtils.toast("????????????",2000);
                //????????????
                Log.d(TAG, "loginOrRegister: ????????????");
            }
        }
    }

    private void initAlertDialog() {
        //???????????????
        builder = new AlertDialog.Builder(this.getActivity());
        builder.setTitle("??????");
        builder.setMessage("????????????????????????????????????????????????????????????????????????????????????");
        builder.setPositiveButton("?????????", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                onLoginSuccess();
            }
        });
    }

    /**
     * ?????????????????????
     */
    private void onLoginSuccess() {
        String token = RandomUtils.getRandomNumbersAndLetters(16);
        if (TokenUtils.handleLoginSuccess(token)) {
            popToBack();
            ActivityUtils.startActivity(MainActivity.class);
        }
    }

    /**
     * ???debug????????????????????????????????????????????????
     */
    private void onLoginForTest(){
        UserDaoImpl userDao = new UserDaoImpl();
        User newUser = userDao.findUserById("2").get(0);
        if(newUser!=null){
            //??????????????????
            MyApp app = (MyApp) this.getActivity().getApplication();
            app.setUser(newUser);
            Log.d(TAG, "onLoginForTest: " + newUser.toString());
            //???????????????
            builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle("Debug");
            builder.setMessage("?????????Debug????????????");
            builder.setPositiveButton("Go", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onLoginSuccess();
                }
            });
        }
        else{
            //???????????????
            builder = new AlertDialog.Builder(this.getActivity());
            builder.setTitle("Error");
            builder.setMessage("????????????????????????");
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

