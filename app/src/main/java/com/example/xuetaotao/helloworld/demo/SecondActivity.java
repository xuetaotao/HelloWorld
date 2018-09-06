package com.example.xuetaotao.helloworld.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.xuetaotao.helloworld.R;

public class SecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e("SecondActivity", "~~~~~~~~SecondActivity~~~~~~~~~");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SecondActivity", "-----SecondActivity  onDestroy-----");
    }
}
