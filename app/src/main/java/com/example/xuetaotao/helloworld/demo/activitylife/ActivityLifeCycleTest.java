package com.example.xuetaotao.helloworld.demo.activitylife;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;

public class ActivityLifeCycleTest extends AppCompatActivity {

    private static final String TAG = "ActivityLifeCycleTest";

    public static void newsInstance(Context context){
        Intent intent = new Intent(context, ActivityLifeCycleTest.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_life_cycle_test);
        Log.e(TAG, "=====onCreate=====");

        if (savedInstanceState != null){
            String saveState = savedInstanceState.getString("bundle_save");
            Log.e(TAG, "#####" + saveState + "#####");
        }

        Button btnNormal = (Button) findViewById(R.id.btn_normal);
        btnNormal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLifeCycleTest.this, NormalActivity.class);
                startActivity(intent);
            }
        });

        Button btnDialog = (Button) findViewById(R.id.btn_dialog);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ActivityLifeCycleTest.this, DialogActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString("bundle_save", "I have stored some money for you");
        Log.e(TAG, "onSaveInstanceState：" + outState.getString("bundle_save"));
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e(TAG, "=====onStart=====");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.e(TAG, "=====onResume=====");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e(TAG, "=====onPause=====");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e(TAG, "=====onStop=====");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.e(TAG, "=====onDestroy=====");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e(TAG, "=====onRestart=====");
    }
}
