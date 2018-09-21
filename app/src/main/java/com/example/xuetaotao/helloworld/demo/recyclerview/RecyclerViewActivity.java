package com.example.xuetaotao.helloworld.demo.recyclerview;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.Fruit;
import com.example.xuetaotao.helloworld.demo.recyclerview.adapter.FruitAdapter;
import com.example.xuetaotao.helloworld.listener.OnRcyItemClickListener;
import com.example.xuetaotao.helloworld.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerViewActivity extends AppCompatActivity {

    private List<Fruit> fruits = new ArrayList<>();

    public static void newInstance(Context context){
        Intent intent = new Intent(context, RecyclerViewActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recyclerview_layout);

        initData(1);
        initView(1);
    }

    //水平或纵向布局
    public void initView(){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);   //水平布局
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruits);

        //点击事件二 TODO 有点问题
        adapter.setOnRcyItemClickListener(new OnRcyItemClickListener() {
            @Override
            public void onClick(int position) {
                Fruit fruit = fruits.get(position);
                Log.e("RecyclerViewActivity", "点击事件二 Activity");
                ToastUtils.showToast(RecyclerViewActivity.this, "Image");
            }
        });

        recyclerView.setAdapter(adapter);
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

    //瀑布流布局
    public void initView(int view){

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        StaggeredGridLayoutManager layoutManager = new StaggeredGridLayoutManager(5, StaggeredGridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
        FruitAdapter adapter = new FruitAdapter(fruits);
        recyclerView.setAdapter(adapter);
    }

    public void initData(int data){

        for (int i=0; i<30; i++){
            Fruit apple = new Fruit(getRandomLengthName("apple"), R.mipmap.ic_launcher);
            fruits.add(apple);
            Fruit org = new Fruit(getRandomLengthName("org"), R.mipmap.jyfr_icon_mpossh2x);
            fruits.add(org);
            Fruit pear = new Fruit(getRandomLengthName("pear"), R.mipmap.syzhxq_icon_balance);
            fruits.add(pear);
            Fruit grape = new Fruit(getRandomLengthName("grape"), R.mipmap.yemx_icon_blue);
            fruits.add(grape);
            Fruit linger = new Fruit(getRandomLengthName("linger"), R.mipmap.yqhy_icon_moment2x);
            fruits.add(linger);
        }
    }

    public String getRandomLengthName(String name){

        Random random = new Random();
        int length = random.nextInt(20) + 1;
        StringBuilder builder = new StringBuilder();
        for (int i=0; i<length; i++){
            builder.append(name);
        }
        return builder.toString();
    }

}
