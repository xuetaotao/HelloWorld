package com.example.xuetaotao.helloworld.demo;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.xuetaotao.helloworld.R;

public class ThirdActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        Log.e("ThirdActivity", "~~~~~~~~ThirdActivity~~~~~~~~~" + "TaskId：" + getTaskId());

        initView();
    }

    public void initView(){
        Button btnImplicitIntent4 = (Button) findViewById(R.id.btn_implicit_intent4);
        btnImplicitIntent4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.putExtra("data_return", "Hello FirstCodeActivcity");
                setResult(RESULT_OK, intent);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent();
        intent.putExtra("data_return", "Hello FirstCodeActivcity");
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("ThirdActivity", "-----ThirdActivity  onDestroy-----");
    }
}
