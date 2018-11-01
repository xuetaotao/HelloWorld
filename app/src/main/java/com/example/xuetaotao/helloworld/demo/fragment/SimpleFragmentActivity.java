package com.example.xuetaotao.helloworld.demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.utils.ToastUtils;

public class SimpleFragmentActivity extends AppCompatActivity implements View.OnClickListener{

    public static void newInstance(Context context){
        Intent intent = new Intent(context, SimpleFragmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);

//        LeftFragment leftFragment = (LeftFragment) getFragmentManager().findFragmentById(R.id.left_fragment); //暂时不清楚问题在哪里？
        replaceFragment(new RightFragment());
        Button btn1 = (Button) findViewById(R.id.btn1);
        btn1.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){

            case R.id.btn1:
                replaceFragment(new AnotherRightFragment());
                break;
        }
    }

    public void replaceFragment(Fragment fragment){

        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fl_another_fragment, fragment);
        transaction.addToBackStack(null);   //模仿类似于返回栈的效果，但是如果多次点击按钮，就会创建出多个 Fragment，需要多次点击才能退出
        transaction.commit();
    }

}
