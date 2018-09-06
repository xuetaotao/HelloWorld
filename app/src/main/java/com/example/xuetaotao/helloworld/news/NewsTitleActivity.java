package com.example.xuetaotao.helloworld.news;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.news.fragment.NewsTitleFragment;

public class NewsTitleActivity extends AppCompatActivity {

    public static void newInstance(Context context){

        Intent intent = new Intent(context, NewsTitleActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_title);
        //已经静态加载了，可以不要
//        NewsTitleFragment newsTitleFragment = (NewsTitleFragment) getSupportFragmentManager().findFragmentById(R.id.news_title_fragment);
    }
}
