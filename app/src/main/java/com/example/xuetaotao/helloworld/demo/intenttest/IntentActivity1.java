package com.example.xuetaotao.helloworld.demo.intenttest;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;

public class IntentActivity1 extends AppCompatActivity {

    private TextView tv_intent1;
    private Button btn_intent1;

    public static void newInstance(Context context){
        Intent intent = new Intent(context, IntentActivity1.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intent1);

        tv_intent1 = (TextView) findViewById(R.id.tv_intent1);
        btn_intent1 = (Button) findViewById(R.id.btn_intent1);
        btn_intent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(IntentActivity1.this, IntentActivity2.class);
                intent.putExtra("title", "信用卡申请");
//                intent.putExtra("content", "额度高，审批快");
                intent.putExtra("subtitle", "招行信用卡");
                startActivity(intent);
            }
        });

    }
}
