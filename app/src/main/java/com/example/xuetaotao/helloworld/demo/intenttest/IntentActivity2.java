package com.example.xuetaotao.helloworld.demo.intenttest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;

public class IntentActivity2 extends AppCompatActivity {

    private TextView tv_intent2, tv_intent3;
    private String title, content;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent2);

        tv_intent2 = (TextView) findViewById(R.id.tv_intent2);
        tv_intent3 = (TextView) findViewById(R.id.tv_intent3);

        title = getIntent().getStringExtra("title");
        content = getIntent().getStringExtra("content");

        tv_intent2.setText(title);
        tv_intent3.setText(content);
    }
}
