package com.example.xuetaotao.helloworld.demo.listview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.Fruit;
import com.example.xuetaotao.helloworld.demo.listview.adapter.FruitAdapter;
import com.example.xuetaotao.helloworld.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class ListViewActivity extends AppCompatActivity {

    private List<Fruit> fruits = new ArrayList<>();

    public static void newsInstance(Context context){
        Intent intent = new Intent(context, ListViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.simple_listview);

        initData();
        initView();
    }

    public void initView(){
        ListView listView = (ListView) findViewById(R.id.listview);
        FruitAdapter adapter = new FruitAdapter(this, R.layout.fruit_item, fruits);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Fruit fruit = fruits.get(position);
                ToastUtils.showToast(ListViewActivity.this, fruit.getName());
            }
        });

    }

    public void initData(){

        for (int i=0; i<5; i++){
            Fruit apple = new Fruit("apple", R.mipmap.ic_launcher);
            fruits.add(apple);
            Fruit org = new Fruit("org", R.mipmap.jyfr_icon_mpossh2x);
            fruits.add(org);
            Fruit pear = new Fruit("pear", R.mipmap.syzhxq_icon_balance);
            fruits.add(pear);
            Fruit grape = new Fruit("grape", R.mipmap.yemx_icon_blue);
            fruits.add(grape);
            Fruit linger = new Fruit("linger", R.mipmap.yqhy_icon_moment2x);
            fruits.add(linger);
        }
    }
}
