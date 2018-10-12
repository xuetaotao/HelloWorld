package com.example.xuetaotao.helloworld.utils;

import android.app.Activity;

import com.tencent.mm.opensdk.modelmsg.SendAuth;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;

public class WeiXinLoginUtils {

    private Activity activity;
    private String WEIXIN_APP_ID = "";

    public WeiXinLoginUtils(Activity activity){
        this.activity = activity;
    }

    private void weixinLogin(){

        IWXAPI mApi = WXAPIFactory.createWXAPI(activity, WEIXIN_APP_ID, true);
        mApi.registerApp(WEIXIN_APP_ID);

        if (mApi != null && mApi.isWXAppInstalled()){
            SendAuth.Req req = new SendAuth.Req();
            req.scope = "snsapi_userinfo";
            req.state = "wechat_sdk_demo_test_neng";
            mApi.sendReq(req);
        } else {
            ToastUtils.showToast(activity, "用户未安装微信");
        }
    }
}
