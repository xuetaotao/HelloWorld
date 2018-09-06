package com.example.xuetaotao.helloworld.news;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.news.fragment.NewsContentFragment;

public class NewsContentActivity extends AppCompatActivity {

    public static void newInstance(Context context, String title, String content){
        Intent intent = new Intent(context, NewsContentActivity.class);
        intent.putExtra("news_title", title);
        intent.putExtra("news_content", content);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.news_content);
        String title = getIntent().getStringExtra("news_title");
        String content = getIntent().getStringExtra("news_content");
        NewsContentFragment newsContentFragment = (NewsContentFragment) getSupportFragmentManager().findFragmentById(R.id.news_content_fragment);
        newsContentFragment.refresh(title,content);
    }
}
