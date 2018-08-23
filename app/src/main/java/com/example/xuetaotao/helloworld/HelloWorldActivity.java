package com.example.xuetaotao.helloworld;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class HelloWorldActivity extends AppCompatActivity implements View.OnClickListener{

    private static final String TAG = "HelloWorldActivity";
    private String info = "Hello World!";
    private String info1 = "现在创建了一个子线程";
    public static final int UPDATE_TEXT = 1;
    private int test = 1;
    private TextView textView;
    private Button button1, button2, button3, button4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hello_world_layout);
        Log.d(TAG,"hahahaha");

        textView = (TextView) findViewById(R.id.tv_hello_world);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
//        textView.setText(test+"");
//        test = add(test);
//        sub(test);

    }

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what){
                case UPDATE_TEXT:
                    textView.setText(info1);
                    break;
                default:
                    break;
            }
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.button1:
                if (info == textView.getText().toString()){
                    textView.setText("Nice to Meet you!");
                }else {
                    textView.setText(info);
                }
                break;
            case R.id.button2:
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Message msg = new Message();
                        msg.what = UPDATE_TEXT;
                        handler.sendMessage(msg);
                    }
                }).start();
                break;
            case R.id.button3:
                Intent intent = new Intent(HelloWorldActivity.this,WebActivity.class);
                startActivity(intent);
                break;
            case R.id.button4:
                Intent intent1 = new Intent(HelloWorldActivity.this,GaoDeMapActivity.class);
                startActivity(intent1);
                break;
            default:
                break;
        }
    }

    //    private int  add(int i){
//
//        for(;i < 100; i++){
//
//            textView.setText(test+"");
//            Log.d(TAG, "+++ 的 i 值为：" + i);
//        }
//        return i;
//    }
//
//    private void sub(int i){
//
//        for(;i > -1; i--){
//
//            textView.setText(test+"");
//            Log.d(TAG, "--- 的 i 值为：" + i);
//        }
//    }
}