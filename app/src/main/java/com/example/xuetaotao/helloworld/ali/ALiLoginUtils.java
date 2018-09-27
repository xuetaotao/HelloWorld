package com.example.xuetaotao.helloworld.ali;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.alipay.sdk.app.AuthTask;
import com.example.xuetaotao.helloworld.main.HelloWorldActivity;
import com.example.xuetaotao.helloworld.main.LoginActivity;

import java.util.Map;

/**
 *  APP支付宝登录
 *
 *  这里只是为了方便,直接向加签过程直接放在客户端完成；
 *  真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
 *  防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
 */
public class ALiLoginUtils {

    public Activity activity;

    /** 支付宝支付业务：入参app_id */
    public static final String APPID = "2018092661505525";
    /** 支付宝账户登录授权业务：入参pid值 */
    public static final String PID = "2088922399231239";
    /** 支付宝账户登录授权业务：入参target_id值 可自定义，保证唯一性即可*/
    public static final String TARGET_ID = "2088102175966980";

    /** 商户私钥，pkcs8格式 */
    /** 如下私钥，RSA2_PRIVATE 或者 RSA_PRIVATE 只需要填入一个 */
    /** 如果商户两个都设置了，优先使用 RSA2_PRIVATE */
    /** RSA2_PRIVATE 可以保证商户交易在更加安全的环境下进行，建议使用 RSA2_PRIVATE */
    /** 获取 RSA2_PRIVATE，建议使用支付宝提供的公私钥生成工具生成， */
    /** 工具地址：https://doc.open.alipay.com/docs/doc.htm?treeId=291&articleId=106097&docType=1 */
    public static final String RSA2_PRIVATE = "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCJNJ8kZWFZt3o7CvMZRhjA743WYRh4mkHM0MwLo+HwIsnkMQyw1YE0NrJ2g7j51zqEcIBbuUDoSIfdqdzHC60elMhqdy1TwA2AkUI+wjc64QiAYZ4grkaT8k4KWR4DMKrzQGl1Siec3jW7R7qsEyhj6TbxaCfoxNtgVEO2bDRDqWlJ0cHHWdpO83SL8xL2dxxhtQMTO2oyB6T/R1hXQ/6Hqowse0czREj72ELMBS03EfzIqF9l1q+mTEDZY/poCqbY49iO5bhagOYGKUu43prnqHLOL9aZbjktrzKnxv5MX31yLudRu9AXHCd5xSO+mg+ef2CDMXkJ+CaUrMkXQd1HAgMBAAECggEAT5yb9tdofFFM+DNrSS/2LwURDOJ7+4auRXSl4N8vu1HEScb4MSdBeBWAyem2jCmJh81rfU1pzx4+z6y/MTOiOQtlmvkZ8O2QKBihEp9Iwr5OW4fI4/ebdj7zb2e0VW+I0ep/BpKHl6sMDGYbd7sKBacNvVeNSK/pBuT+ATyZuuQZWsHiCD5esGMjwp0V90xbUjZZHbZEhAL9YB2LWXTM/3o90jmoCzNb5ZbBJrml0IHMv1c7Gqk+UD9KKipCWxWoYSTS1840ygYHdUqlFkpRc9pJV1sWMHaGOpk//vrOa92ja3ABhNRlB52l5uzSXEiIpUWWiG7EJVlePcV1Lne22QKBgQDBnCRv5DMXX4bT4Rb2bRNLhD6zWzb57PBMyvDTr1ys6F3tzYp6PIOHbB1BtjxN4ROa3Z1Gl3MbSuYyPbhEzbZV6nhO7W4fWwII1Sg+HbGUBfHp875DBE14uVJCzttT8KI6R9mHvA1zKDvwlUthKVZ1+ORCIdtmgH11+58TDtHTdQKBgQC1a2X328+OCU9sRgIECvkfPhwc8fpP35M381BEiHNNXaBew2LvJLyn1q6Xkf/NaEtEgvWJsKojbh+JNZOCx7ysYz8xkjf+8mBBwLS49uQGsuL3AXJCbjtmOwy/UjToVf1ercRwbe3LHdxtcfqHa8gl7kwQv2a5P7uaZQRLEVoCSwKBgG5oWYsJSm1wx1V6jfq3VwKAhrUHNkGt8ZcJP+6nmSTtWesrhGWSrrsCV2jKwHb0/JYa/P5tjXGp874A3l02Jl22WSdBVMRCxNU+oTVS/5pXDY6Jth1HjdpjbFq21fJ3TjwIDy/NeKoL7qqSqKrexPR/m8NuxUwdx+4JMLzQfa2lAoGBAInb7+N2VHVhm8nyNQvvbrmQD/rq4kSZ55xHt8IsUjoDIJ+CKAcDypxb5dZl6pWjYDbSsVUNO2yq/Snd3Zcuwq/YxyebLhP9CKpOjsZTTpnMi+4ZWkMi0t5vltM3296FoYC12SV6U43axisgxrjrbW9tcr3zRFMXHC9J/gYxTE2bAoGAK5/7TZ/rwYm3Yfc0vNPXN8OsBr6HwAJiqd152UKR/ppej9PkfRDxH3wN+KH110BRORro2v3FkIETtkFRaDcHO5GZTmC3rWvJGhORQCC6YlYY6uPuRx24KyCU7RzdH+Hn2PXZMYIXqGdh+vypyVBJDz1LygoPjqBhzDle/mFgal0=";

    public static final String RSA_PRIVATE = "";

    private static final int SDK_PAY_FLAG = 1;
    private static final int SDK_AUTH_FLAG = 2;

    public ALiLoginUtils(Activity activity){
        this.activity = activity;
    }

    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @SuppressWarnings("unused")
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_AUTH_FLAG: {
                    @SuppressWarnings("unchecked")
                    AuthResult authResult = new AuthResult((Map<String, String>) msg.obj, true);
                    String resultStatus = authResult.getResultStatus();
                    String resultCode = authResult.getResultCode();
                    Log.e("ALiLoginActivity", "=====resultStatus=====" + resultStatus);
                    Log.e("ALiLoginActivity", "=====resultCode=====" + resultCode);

                    // 判断resultStatus 为“9000”且result_code
                    // 为“200”则代表授权成功，具体状态码代表含义可参考授权接口文档
                    if (TextUtils.equals(resultStatus, "9000") && TextUtils.equals(authResult.getResultCode(), "200")) {
                        // 获取alipay_open_id，调支付时作为参数extern_token 的value
                        // 传入，则支付账户为该授权账户
                        HelloWorldActivity.newInstance(activity);
                        activity.finish();
                        Log.e("ALiLoginActivity", "授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()));
//                        Toast.makeText(activity,"授权成功\n" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                    } else {
                        // 其他状态值则为授权失败
                        Log.e("ALiLoginActivity", "授权失败\n" + String.format("authCode:%s", authResult.getAuthCode()));
//                        Toast.makeText(activity,"授权失败" + String.format("authCode:%s", authResult.getAuthCode()), Toast.LENGTH_SHORT).show();
                    }
                    break;
                }
                default:
                    break;
            }
        };
    };


    /**
     * 支付宝账户授权业务
     */
    public void authV2() {
        if (TextUtils.isEmpty(PID) || TextUtils.isEmpty(APPID)
                || (TextUtils.isEmpty(RSA2_PRIVATE) && TextUtils.isEmpty(RSA_PRIVATE))
                || TextUtils.isEmpty(TARGET_ID)) {
            new AlertDialog.Builder(activity).setTitle("警告").setMessage("需要配置PARTNER |APP_ID| RSA_PRIVATE| TARGET_ID")
                    .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialoginterface, int i) {
                        }
                    }).show();
            return;
        }

        /**
         * 这里只是为了方便直接向商户展示支付宝的整个支付流程；所以Demo中加签过程直接放在客户端完成；
         * 真实App里，privateKey等数据严禁放在客户端，加签过程务必要放在服务端完成；
         * 防止商户私密数据泄露，造成不必要的资金损失，及面临各种安全风险；
         *
         * authInfo的获取必须来自服务端；
         */
        boolean rsa2 = (RSA2_PRIVATE.length() > 0);
        Map<String, String> authInfoMap = OrderInfoUtil2_0.buildAuthInfoMap(PID, APPID, TARGET_ID, rsa2);
        String info = OrderInfoUtil2_0.buildOrderParam(authInfoMap);

        String privateKey = rsa2 ? RSA2_PRIVATE : RSA_PRIVATE;
        String sign = OrderInfoUtil2_0.getSign(authInfoMap, privateKey, rsa2);
        final String authInfo = info + "&" + sign;
        Log.e("ALiLoginActivity", "=====authInfo=====" + authInfo);
        Runnable authRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造AuthTask 对象
                AuthTask authTask = new AuthTask(activity);
                // 调用授权接口，获取授权结果
                Map<String, String> result = authTask.authV2(authInfo, true);

                Message msg = new Message();
                msg.what = SDK_AUTH_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread authThread = new Thread(authRunnable);
        authThread.start();
    }

}
