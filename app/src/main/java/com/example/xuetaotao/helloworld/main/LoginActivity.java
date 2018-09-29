package com.example.xuetaotao.helloworld.main;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.ali.ALiLoginActivity;
import com.example.xuetaotao.helloworld.ali.ALiLoginUtils;
import com.example.xuetaotao.helloworld.base.BaseMvpActivity;
import com.example.xuetaotao.helloworld.qqapi.QQLoginUtils;
import com.example.xuetaotao.helloworld.sinaapi.WBAuthActivity;
import com.example.xuetaotao.helloworld.sinaapi.WeiBoLoginUtils;
import com.example.xuetaotao.helloworld.utils.LogUtils;
import com.example.xuetaotao.helloworld.utils.ToastUtils;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.Tencent;

public class LoginActivity extends BaseMvpActivity implements View.OnClickListener{

    private String ACCOUNT = "15773282585";
    private String PASSWORD = "123456a";
    private String account, password;

    private SsoHandler ssoHandler;
    private EditText etAccount, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("LoginActivity", "=====LoginActivity=====");
    }

    @Override
    public int getResourcesId() {
        return R.layout.activity_login;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {

        etAccount = (EditText) findViewById(R.id.et_account);
        etPassword = (EditText) findViewById(R.id.et_password);
        btnLogin = (Button) findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        TextView tvApplyRegister = (TextView) findViewById(R.id.tv_apply_register);
        TextView tvForgetPassword = (TextView) findViewById(R.id.tv_forget_password);
        ImageView ivAlipay = (ImageView) findViewById(R.id.iv_alipay);
        ivAlipay.setOnClickListener(this);
        ImageView ivSina = (ImageView) findViewById(R.id.iv_sina);
        ivSina.setOnClickListener(this);
        ImageView ivQq = (ImageView) findViewById(R.id.iv_qq);
        ivQq.setOnClickListener(this);
        ImageView ivWeiXin = (ImageView) findViewById(R.id.iv_weixin);
        ivWeiXin.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.e("LoginActivity","=====onActivityResult=====" + requestCode);
        switch (requestCode){
            case 32973:
                if (ssoHandler != null){
                    ssoHandler.authorizeCallBack(requestCode, resultCode, data);
                }
                break;
            case Constants.REQUEST_APPBAR:
            case Constants.REQUEST_LOGIN:
                Tencent.onActivityResultData(requestCode, resultCode, data, QQLoginUtils.baseUiListener);
                break;
        }

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_login:
//                account = etAccount.getText().toString();
//                password = etPassword.getText().toString();
                account = ACCOUNT;
                password = PASSWORD;
                LogUtils.e("LoginActivity", "account：" + account + "\n" + "password：" + password);
                if (account.equals(ACCOUNT) && password.equals(PASSWORD)){
                    HelloWorldActivity.newInstance(LoginActivity.this);
                    etPassword.getText().clear();
                    finish();
                } else {
                    ToastUtils.showToast(LoginActivity.this, "密码输入错误");
                }
                break;

            /** 腾讯qq授权登录 */
            case R.id.iv_qq:
                QQLoginUtils qqLogin = new QQLoginUtils(this);
                qqLogin.qqLogin();
                break;

            /** 微信授权登录 */
            case R.id.iv_weixin:
                break;

            /** 支付宝授权登录 */
            case R.id.iv_alipay:
//                Intent intent = new Intent(LoginActivity.this, ALiLoginActivity.class);
//                startActivity(intent);
                ALiLoginUtils aLiLogin = new ALiLoginUtils(LoginActivity.this);
                aLiLogin.authV2();
                break;

            /** 新浪微博授权登录 */
            case R.id.iv_sina:
//                Intent intent = new Intent(this, WBAuthActivity.class);
//                startActivity(intent);
                WeiBoLoginUtils weiBoLogin = new WeiBoLoginUtils(this);
                ssoHandler = weiBoLogin.weiBologin();
                break;
            default:
                break;
        }
    }
}
