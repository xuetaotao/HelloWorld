package com.example.xuetaotao.helloworld.sinaapi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.sina.weibo.sdk.WbSdk;
import com.sina.weibo.sdk.auth.AccessTokenKeeper;
import com.sina.weibo.sdk.auth.AuthInfo;
import com.sina.weibo.sdk.auth.Oauth2AccessToken;
import com.sina.weibo.sdk.auth.WbAuthListener;
import com.sina.weibo.sdk.auth.WbConnectErrorMessage;
import com.sina.weibo.sdk.auth.sso.SsoHandler;
import com.sina.weibo.sdk.exception.WeiboException;
import com.sina.weibo.sdk.net.RequestListener;

import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * 微博账号登录授权流程
 * 1.   集成SDK，修改主目录和主模块下面的build.gradle文件，添加微博sdk的依赖
 * 2.   创建Constants接口，定义新浪微博授权时所需要的参数（APP_KEY，REDIRECT_URL，SCOPE权限）
 * 3.   创建微博API接口类对象 初始化WbSdk对象（在应用的Application或者调用SDK功能代码前）
 * 4.   实现WbAuthListener接口
 * 5.   调用方法，认证授权
 * 6.   添加SSOhandler的回调，需要在 {@link #onActivityResult} 中调用 {@link SsoHandler#authorizeCallBack}
 */
public class WBAuthActivity extends Activity {

    private SsoHandler ssoHandler;
    private Oauth2AccessToken mAccessToken;     // 封装了 "uid", "access_token"，"expires_in"，"refresh_token"，并提供了他们的管理功能

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        AuthInfo authInfo = new AuthInfo(this, Constants.APP_KEY, Constants.REDIRECT_URL, Constants.SCOPE);
        WbSdk.install(this, authInfo);

        ssoHandler = new SsoHandler(WBAuthActivity.this);           //创建微博实例，认证授权

        // SSO认证授权 仅客户端
//        ssoHandler.authorizeClientSso(new SelfWbAuthListener());
        // Web授权 没有客户端，网页授权
//        ssoHandler.authorizeWeb(new SelfWbAuthListener());
        // SSO 授权+Web 授权 混合授权
        ssoHandler.authorize(new SelfWbAuthListener());

        // 从 SharedPreferences 中读取上次已保存好 AccessToken 等信息，第一次启动本，AccessToken 不可用
        mAccessToken = AccessTokenKeeper.readAccessToken(WBAuthActivity.this);
        if (mAccessToken.isSessionValid()){
            updateTokenView(true);
        }
    }

     /** 微博认证授权回调类 */
    public class SelfWbAuthListener implements WbAuthListener{

        @Override
        public void onSuccess(final Oauth2AccessToken oauth2AccessToken) {

            WBAuthActivity.this.runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    mAccessToken = oauth2AccessToken;
                    if (mAccessToken.isSessionValid()){
                        //显示 mAccessToken
                        updateTokenView(false);
                        //保存 mAccessToken 到 SharedPreferences
                        AccessTokenKeeper.writeAccessToken(WBAuthActivity.this, mAccessToken);
                        Toast.makeText(WBAuthActivity.this, "授权成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        @Override
        public void cancel() {
            Toast.makeText(WBAuthActivity.this, "取消授权", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onFailure(WbConnectErrorMessage wbConnectErrorMessage) {
            Toast.makeText(WBAuthActivity.this, "授权失败：" + wbConnectErrorMessage.getErrorMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    /**
     * 显示当前 Token 信息
     * @param hasExisted 配置文件中是否存在 Token 信息并且合法
     */
    public void updateTokenView(boolean hasExisted){

        String date = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss").format(new Date(mAccessToken.getExpiresTime()));
        String format = "Token：%1$s \n有效期：%2$s \nUid：%3$s";
        String message = String.format(format, mAccessToken.getToken(), date, mAccessToken.getUid());
        if (hasExisted){
            message = "Token 仍在有效期内，无需再次登录。" + "\n" + message;
        }
        Log.e("WBAuthActivity", message);
    }

    //用户登出
    public void loginOut(){
        AccessTokenKeeper.clear(getApplicationContext());
        mAccessToken = new Oauth2AccessToken();
        updateTokenView(false);
    }

    //更新Token
    public void refreshToken(){
        if (!TextUtils.isEmpty(mAccessToken.getRefreshToken())){
            AccessTokenKeeper.refreshToken(Constants.APP_KEY, WBAuthActivity.this, new RequestListener() {
                @Override
                public void onComplete(String s) {

                }

                @Override
                public void onWeiboException(WeiboException e) {

                }
            });
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (ssoHandler != null){
            ssoHandler.authorizeCallBack(requestCode, resultCode, data);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("WBAuthActivity", "======WBAuthActivity onDestroy=====");
    }
}
