package com.example.xuetaotao.helloworld.demo.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.xuetaotao.helloworld.R;

public class SimpleListViewActivity extends AppCompatActivity {

    private String[] data = {"Apple", "Orange", "Banner", "Pear", "Grape", "PineApple", "Linger", "Xiaoyao", "Anu", "Yueru"
    , "Xiaohu", "Yiru", "Sumei", "Qiqi", "Jingtian", "Feipeng", "Chonglou", "Zixuan", "Changqing"};

    public static void newsInstance(Context context){
        Intent intent = new Intent(context, SimpleListViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_listview);

        initView();
    }

    public void initView(){
        ListView listView = (ListView) findViewById(R.id.listview);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, data);
        listView.setAdapter(adapter);
    }
}
