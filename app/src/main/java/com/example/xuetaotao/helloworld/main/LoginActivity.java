package com.example.xuetaotao.helloworld.main;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseMvpActivity;
import com.example.xuetaotao.helloworld.utils.LogUtils;
import com.example.xuetaotao.helloworld.utils.ToastUtils;

public class LoginActivity extends BaseMvpActivity implements View.OnClickListener{

    private String ACCOUNT = "15773282585";
    private String PASSWORD = "123456a";
    private String account, password;

    private EditText etAccount, etPassword;
    private Button btnLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
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
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.btn_login:
                account = etAccount.getText().toString();
                password = etPassword.getText().toString();
                LogUtils.e("LoginActivity", "account：" + account + "\n" + "password：" + password);
                if (account.equals(ACCOUNT) && password.equals(PASSWORD)){
                    HelloWorldActivity.newInstance(LoginActivity.this);
                    etPassword.getText().clear();
                    finish();
                } else {
                    ToastUtils.showToast(LoginActivity.this, "密码输入错误");
                }
                break;
            case R.id.iv_alipay:
                break;
            default:
                break;
        }
    }
}
