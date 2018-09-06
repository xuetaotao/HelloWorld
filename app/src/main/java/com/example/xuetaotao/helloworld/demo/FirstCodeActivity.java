package com.example.xuetaotao.helloworld.demo;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseTitleActivity;

/**
 * 该部分，主要用于练习《第一行代码》中的例子
 */
public class FirstCodeActivity extends BaseTitleActivity implements View.OnClickListener{

    public static void newInstance(Context context){
        Intent intent = new Intent(context, FirstCodeActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.e("FirstCodeActivity", "~~~~~~~~FirstCodeActivity~~~~~~~~~");
    }

    @Override
    public int getTitleText() {
        return R.string.first_code;
    }

    @Override
    public int getResourcesId() {
        return R.layout.activity_first_code;
    }

    @Override
    public void initView() {

        /**
         * 2.2.4  在活动中使用Toast
         */
        Button btnToast = (Button) findViewById(R.id.btn_toast);
        btnToast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FirstCodeActivity.this, "You Click Button", Toast.LENGTH_SHORT).show();
            }
        });

        /**
         * 2.2.6  销毁一个活动
         */
        Button btnFinish = (Button) findViewById(R.id.btn_finish);
        btnFinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Log.e("FirstCodeActivity", "-----FirstCodeActivity  finished-----");
            }
        });

        /**
         *2.3.2  使用隐式Intent启动一个Activity
         */
        Button btnImplicitIntent = (Button) findViewById(R.id.btn_implicit_intent);
        btnImplicitIntent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent("com.example.intent.ACTIONSTART");
                intent.addCategory("com.example.intent.CUSTOM_CATEGORY");
                startActivity(intent);
            }
        });

        /**
         *2.3.3  隐式Intent启动其他程序
         */
        Button btnImplicitIntent1 = (Button) findViewById(R.id.btn_implicit_intent1);
        btnImplicitIntent1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_VIEW);     //这里不需要在AndroidManifest中添加声明
                intent.setData(Uri.parse("http://www.taobao.com"));
                startActivity(intent);
            }
        });
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){

            default:
                break;
        }
    }


    /**
     * 2.2.5  在活动中使用Menu(未实现)
     * 需要基于ActionBar来实现
     */
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//
//        switch (item.getItemId()){
//            case R.id.add_item:
//                Toast.makeText(FirstCodeActivity.this, "Add", Toast.LENGTH_SHORT).show();
//                break;
//            case R.id.remove_item:
//                Toast.makeText(FirstCodeActivity.this, "Remove", Toast.LENGTH_SHORT).show();
//                break;
//            default:
//                break;
//        }
//        return true;
//    }
}
