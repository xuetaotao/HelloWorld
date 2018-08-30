package com.example.xuetaotao.helloworld.toast;


import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseTitleActivity;
import com.example.xuetaotao.helloworld.utils.ToastUtils;
import com.example.xuetaotao.helloworld.widget.CustomToast;

import butterknife.BindView;
import butterknife.OnClick;


public class ToastActivity extends BaseTitleActivity {

    @BindView(R.id.btn_basic_toast)
    Button basicToast;
    @BindView(R.id.btn_basic_toast2)
    Button basicToast2;
    @BindView(R.id.btn_custom_location)
    Button customLocation;
    @BindView(R.id.btn_custom_picture)
    Button customPicture;
    @BindView(R.id.btn_custom_smile)
    Button customSmile;
    @BindView(R.id.btn_custom_smile2)
    Button customSmile2;

//    private static CustomToast customToastView;


    public static void newInstance(Context context){

        Intent intent = new Intent(context, ToastActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public int getResourcesId() {
        return R.layout.activity_toast;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }

    @Override
    public int getTitleText() {
        return R.string.play_toast;
    }

    @OnClick( {R.id.btn_basic_toast, R.id.btn_custom_location, R.id.btn_custom_picture, R.id.btn_custom_smile, R.id.btn_custom_smile2,
                R.id.btn_basic_toast2} )
    public void onViewClick(View v){

        switch (v.getId()){

            /*  最基本的Toast，解决了原生Toast不能快速更新的问题 */
            case R.id.btn_basic_toast:
                ToastUtils.showToast(this, "这是最基本的Toast");
                break;
            case R.id.btn_basic_toast2:
                ToastUtils.showToast(this, "===已更新===");
                break;

            /* 自定义位置的Toast
             * 相对于Gravity.LEFT位置， x方向上的偏移量, y方向上的偏移量 */
            case R.id.btn_custom_location:
                Toast toast = Toast.makeText(ToastActivity.this, "自定义位置的Toast", Toast.LENGTH_SHORT);
                toast.setGravity(Gravity.LEFT,0, 0);
                toast.show();
                break;

            /* 带图片的Toast，自定义布局
             * 参考 Toast.makeText() 方法 */
            case R.id.btn_custom_picture:
                Toast result = new Toast(this);
                View toastView = LayoutInflater.from(this).inflate(R.layout.toast_custom, null);
                ImageView img = (ImageView) toastView.findViewById(R.id.iv_img);
                TextView msg = (TextView) toastView.findViewById(R.id.tv_msg);
                img.setImageResource(R.mipmap.jyfr_icon_mpossh3x);
                msg.setText(R.string.picture_toast);

                result.setView(toastView);
                result.setGravity(Gravity.BOTTOM, 0 , 0);
                result.setDuration(Toast.LENGTH_SHORT);
                result.show();
                break;

            /* 自定义Toast控件，带个动画效果
             * 但是并没有摆脱原生Toast显示方法的调用 */
            case R.id.btn_custom_smile:
                ToastUtils.showToast(this, "在看我", true);
                break;
            case R.id.btn_custom_smile2:
                ToastUtils.showToast(this, "==还在看我==", true);
                break;

            default:
                break;
        }
    }
}
