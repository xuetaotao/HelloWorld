package com.example.xuetaotao.helloworld.demo.activitylife;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xuetaotao.helloworld.R;

public class NormalActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.normal_layout);
    }
}
