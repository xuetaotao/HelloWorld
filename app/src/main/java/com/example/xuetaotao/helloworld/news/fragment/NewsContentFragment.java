package com.example.xuetaotao.helloworld.news.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;

public class NewsContentFragment extends Fragment {

    private View view;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.news_content_frag, container, false);
        return view;
    }

    public void refresh(String title, String content){

        LinearLayout llNews = (LinearLayout) view.findViewById(R.id.ll_news_content_frag);
        llNews.setVisibility(View.VISIBLE);
        TextView tvNewsTitle = (TextView) view.findViewById(R.id.tv_news_title);
        tvNewsTitle.setText(title);
        TextView tvNewsContent = (TextView) view.findViewById(R.id.tv_news_content);
        tvNewsContent.setText(content);
    }
}
