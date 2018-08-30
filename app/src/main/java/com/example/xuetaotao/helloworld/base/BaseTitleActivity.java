package com.example.xuetaotao.helloworld.base;


import android.os.Bundle;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;

import butterknife.BindView;
import butterknife.OnClick;

public abstract class BaseTitleActivity extends BaseMvpActivity{

    @BindView(R.id.iv_back)
    ImageView ivBack;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    @BindView(R.id.tv_right)
    TextView tvRight;
    @BindView(R.id.iv_home)
    ImageView ivHome;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (tvTitle != null && getTitleText() != 0){
            tvTitle.setText(getTitleText());
        }

        if (ivBack != null){
            ivBack.setVisibility( showBackImg()? View.VISIBLE : View.GONE);
        }
    }

    //TODO 该方法有待完善，控制左侧返回键是否显示
    public boolean showBackImg(){

        return true;
    }

    public abstract @StringRes int getTitleText();

    public void setTitleText(String title){

        tvTitle.setText(title);
    }

    public void setTitleText(@StringRes int titleId){

        tvTitle.setText(titleId);
    }

    public void setRightImg(@DrawableRes int imgId){

        ivHome.setImageResource(imgId);
        ivHome.setVisibility(View.VISIBLE);
    }

    public void setRightText(String right){

        tvRight.setText(right);
        tvRight.setVisibility(View.VISIBLE);
    }

    public void setRightText(@StringRes int rightId){

        tvRight.setText(rightId);
        tvRight.setVisibility(View.VISIBLE);
    }

    @OnClick(R.id.iv_back)
    public void back(){
        onBackPressed();
//        finish();
    }

    @OnClick(R.id.iv_home)
    public void home(){

    }

    @OnClick(R.id.tv_right)
    public void right(){

    }
}
