package com.example.xuetaotao.helloworld.qqapi;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;

import android.widget.Button;
import android.widget.EditText;


import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.utils.ToastUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONObject;

public class QQLoginActivity extends AppCompatActivity{

    private static final String TAG = QQLoginActivity.class.getName();
    public static String mAppid = "222222";
    private Button mNewLoginButton;
    private UserInfo mInfo;
    private EditText mEtAppid = null;
    public static Tencent mTencent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_qqlogin);
        Log.e(TAG, "-->onCreate");
        mNewLoginButton = (Button) findViewById(R.id.btn_qq);
        mNewLoginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickLogin();
            }
        });
        mTencent = Tencent.createInstance(mAppid, QQLoginActivity.this);
    }

    private void onClickLogin() {
//        if (!mTencent.isSessionValid()) {
            //最后一项boolean参数没有的话默认是false
            mTencent.login(this, "all", loginListener);
//        } else {
//            mTencent.logout(this);
//        }
    }


    IUiListener loginListener = new BaseUiListener() {
        @Override
        protected void doComplete(JSONObject values) {
//            initOpenidAndToken(values);
        }
    };

    private class BaseUiListener implements IUiListener {

        protected void doComplete(JSONObject values) { }

        @Override
        public void onComplete(Object response) {
            if (null == response) {
                ToastUtils.showToast(QQLoginActivity.this, "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0) {
                ToastUtils.showToast(QQLoginActivity.this, "登录失败");
                return;
            }
            /** response中即包含相关用户信息 */
            ToastUtils.showToast(QQLoginActivity.this, "登录成功");
            Log.e(TAG, "=====登录成功=====" + response.toString());
            doComplete((JSONObject)response);
        }

        @Override
        public void onError(UiError e) {
            ToastUtils.showToast(QQLoginActivity.this, "onError: " + e.errorDetail);
        }

        @Override
        public void onCancel() {
            ToastUtils.showToast(QQLoginActivity.this, "onCancel:");
        }
    }


    public static void initOpenidAndToken(JSONObject jsonObject) {
        try {
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            if (!TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)
                    && !TextUtils.isEmpty(openId)) {
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "-->onActivityResult " + requestCode  + " resultCode=" + resultCode);
        if (requestCode == Constants.REQUEST_LOGIN ||
                requestCode == Constants.REQUEST_APPBAR) {
            Tencent.onActivityResultData(requestCode,resultCode,data,loginListener);
        }

        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onStart() {
        Log.d(TAG, "-->onStart");
        super.onStart();
    }

    @Override
    protected void onResume() {
        Log.d(TAG, "-->onResume");
        super.onResume();
    }

    @Override
    protected void onPause() {
        Log.d(TAG, "-->onPause");
        super.onPause();
    }

    @Override
    protected void onStop() {
        Log.d(TAG, "-->onStop");
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        Log.d(TAG, "-->onDestroy");
        super.onDestroy();

    }

}
