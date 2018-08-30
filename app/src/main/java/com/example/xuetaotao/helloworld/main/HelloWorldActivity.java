package com.example.xuetaotao.helloworld.main;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseTitleActivity;
import com.example.xuetaotao.helloworld.dialog.DialogActivity;
import com.example.xuetaotao.helloworld.map.GaoDeMapActivity;
import com.example.xuetaotao.helloworld.toast.ToastActivity;
import com.example.xuetaotao.helloworld.webview.WebActivity;

import butterknife.BindView;


public class HelloWorldActivity extends BaseTitleActivity implements View.OnClickListener{

    private static final String TAG = "HelloWorldActivity";
    private String info = "Hello World!";
    private String info1 = "现在创建了一个子线程";
    public static final int UPDATE_TEXT = 1;
    private int test = 1;
    private TextView textView;
    private Button button1, button2, button3, button4, btnDialog, btnToast;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.hello_world_layout);
        Log.d(TAG,"hahahaha");
    }

    @Override
    public int getResourcesId() {
        return R.layout.hello_world_layout;
    }

    @Override
    public int getTitleText() {
        return R.string.main_title;
    }

    @Override
    public void initView() {

        textView = (TextView) findViewById(R.id.tv_hello_world);
        button1 = (Button) findViewById(R.id.button1);
        button1.setOnClickListener(this);
        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(this);
        button3 = (Button) findViewById(R.id.button3);
        button3.setOnClickListener(this);
        button4 = (Button) findViewById(R.id.button4);
        button4.setOnClickListener(this);
        btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(this);
        btnToast = (Button) findViewById(R.id.btn_toast);
        btnToast.setOnClickListener(this);
//        textView.setText(test+"");
//        test = add(test);
//        sub(test);
    }

    @Override
    public void initData() {

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
            case R.id.btn_dialog:
                DialogActivity.newInstance(this);
                break;
            case R.id.btn_toast:
                ToastActivity.newInstance(this);
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
