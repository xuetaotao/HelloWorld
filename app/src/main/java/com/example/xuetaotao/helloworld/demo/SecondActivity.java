package com.example.xuetaotao.helloworld.demo;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;

public class SecondActivity extends AppCompatActivity {

    private String data = "哈哈哈";

    public static void newsInstance(Context context){
        Intent intent = new Intent(context, SecondActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        Log.e("SecondActivity", "~~~~~~~~SecondActivity~~~~~~~~~" + "TaskId：" + getTaskId());

        initView();
    }

    public void initView(){

        TextView tvImplicitIntent = (TextView) findViewById(R.id.tv_implicit_intent);
        initData();     //此处若放在 onCreate 的 initView() 后面，则不会显示传送过来的数据

        tvImplicitIntent.setText(data);

        Button btnNext = (Button) findViewById(R.id.btn_next);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SecondActivity.this, ThirdActivity.class);
                startActivity(intent);
            }
        });
    }

    public void initData(){

        Intent intent = getIntent();
        data = intent.getStringExtra("extra_data");
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e("SecondActivity", "-----SecondActivity  onDestroy-----");
    }
}
