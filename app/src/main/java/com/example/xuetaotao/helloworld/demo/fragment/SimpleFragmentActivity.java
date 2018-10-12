package com.example.xuetaotao.helloworld.demo.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xuetaotao.helloworld.R;

public class SimpleFragmentActivity extends AppCompatActivity {

    public static void newInstance(Context context){
        Intent intent = new Intent(context, SimpleFragmentActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment);
    }
}
