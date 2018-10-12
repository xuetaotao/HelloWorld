package com.example.xuetaotao.helloworld.qqapi;

import android.app.Activity;
import android.text.TextUtils;
import android.util.Log;

import com.example.xuetaotao.helloworld.main.HelloWorldActivity;
import com.example.xuetaotao.helloworld.utils.ToastUtils;
import com.tencent.connect.UserInfo;
import com.tencent.connect.auth.QQToken;
import com.tencent.connect.common.Constants;
import com.tencent.tauth.IUiListener;
import com.tencent.tauth.Tencent;
import com.tencent.tauth.UiError;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * QQ登录授权
 */
public class QQLoginUtils {

    private String AppID = "101509822";
    private Activity activity;
    private Tencent mTencent;
    private UserInfo userInfo;
    public static BaseUiListener baseUiListener;

    public QQLoginUtils(Activity activity){
        this.activity = activity;
    }

    public void qqLogin(){

        baseUiListener = new BaseUiListener();
        mTencent = Tencent.createInstance(AppID, activity);
        mTencent.login(activity, "all", baseUiListener);
    }

    public class BaseUiListener implements IUiListener{

        @Override
        public void onComplete(Object response) {
            if (null == response){
                ToastUtils.showToast(activity, "登录失败");
                return;
            }
            JSONObject jsonResponse = (JSONObject) response;
            if (null != jsonResponse && jsonResponse.length() == 0){
                ToastUtils.showToast(activity, "登录失败");
                return;
            }
            ToastUtils.showToast(activity, "登录成功");
            Log.e("QQLoginUtils", response.toString());
//            String accessToken = response.toString();
//            try {
//                JSONObject json = new JSONObject(accessToken);
//                String token = json.getString("access_token");
//                Log.e("QQLoginUtils", token);
//            } catch (JSONException e){
//                e.printStackTrace();
//            }
            initOpenidAndToken(jsonResponse);
            getAccountInfo();
//            HelloWorldActivity.newInstance(activity);
//            activity.finish();
        }

        @Override
        public void onCancel() {
            ToastUtils.showToast(activity, "取消登录");
        }

        @Override
        public void onError(UiError uiError) {
            ToastUtils.showToast(activity, "登录失败" + uiError.errorDetail);
        }
    }

    /**
     *
     * @param jsonObject 登录成功后获得的数据
     * "ret":0,
     * "pay_token":"xxxxxxxxxxxxxxxx",
     * "pf":"openmobile_android",
     * "query_authority_cost":347,
     * "authority_cost":0,
     * "expires_time":1545962865104,
     * "openid":"xxxxxxxxxxxxxxxxxxx",
     * "expires_in":"7776000",
     * "pfkey":"xxxxxxxxxxxxxxxxxxx",
     * "msg":"sucess",
     * "access_token":"xxxxxxxxxxxxxxxxxxxxx"
     * "login_cost":838
     */
    private void initOpenidAndToken(JSONObject jsonObject){

        try {
            String openId = jsonObject.getString(Constants.PARAM_OPEN_ID);
            String token = jsonObject.getString(Constants.PARAM_ACCESS_TOKEN);
            String expires = jsonObject.getString(Constants.PARAM_EXPIRES_IN);
            if (!TextUtils.isEmpty(openId) && !TextUtils.isEmpty(token) && !TextUtils.isEmpty(expires)){
                mTencent.setAccessToken(token, expires);
                mTencent.setOpenId(openId);
            }
        } catch (JSONException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取用户信息
     */
    private void getAccountInfo(){

        QQToken qqToken = mTencent.getQQToken();
        userInfo = new UserInfo(activity, qqToken);
        userInfo.getUserInfo(new IUiListener() {
            @Override
            public void onComplete(Object o) {

                Log.e("UserInfo",  "===UserInfo===" + o.toString());
                HelloWorldActivity.newInstance(activity);
                activity.finish();
            }

            @Override
            public void onError(UiError uiError) {

            }

            @Override
            public void onCancel() {

            }
        });
    }
}
