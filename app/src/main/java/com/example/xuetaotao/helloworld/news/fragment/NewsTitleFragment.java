package com.example.xuetaotao.helloworld.news.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.News;
import com.example.xuetaotao.helloworld.news.NewsContentActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class NewsTitleFragment extends Fragment {

    private boolean isTwoPane = false;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.news_title_frag, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.news_title_recyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(layoutManager);
        NewsAdapter adapter = new NewsAdapter(getNews());
        recyclerView.setAdapter(adapter);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        //可以找到 ll_news_content 时，为双页模式
        if (getActivity().findViewById(R.id.news_content_layout) != null){
            isTwoPane = true;
        } else {
            isTwoPane = false;
        }
    }

    public List<News> getNews(){

        List<News> newsList2 = new ArrayList<>();
        for (int i=0; i<50; i++){
            News news = new News();
            news.setTitle("This is news title" + i);
            news.setContent(getRandomLengthContent("This is news content" + i + "."));
            newsList2.add(news);
        }
        return newsList2;
    }

    public String getRandomLengthContent(String content){

        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        int length = random.nextInt(20) + 1;
        for (int i=0; i<length; i++){
            builder.append(content);
        }

        return builder.toString();
    }

    class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ViewHolder>{

        private List<News> newsList;

        class ViewHolder extends RecyclerView.ViewHolder{

            TextView newsTitle;

            public ViewHolder(View view){
                super(view);
                newsTitle = (TextView)view.findViewById(R.id.news_title);
            }
        }

        public NewsAdapter(List<News> newsList1){

            this.newsList = newsList1;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.news_item, parent, false);
            final ViewHolder holder = new ViewHolder(view);
            view.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {

                    News news = newsList.get(holder.getAdapterPosition());

                    //双页模式
                    if (isTwoPane){
                        NewsContentFragment newsContentFragment = (NewsContentFragment) getFragmentManager().findFragmentById(R.id.news_content_fragment);
                        newsContentFragment.refresh(news.getTitle(), news.getContent());
                    } else {
                        //单页模式
                        NewsContentActivity.newInstance(getActivity(), news.getTitle(), news.getContent());
                    }
                }
            });

            return holder;
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {

            News news = newsList.get(position);
            holder.newsTitle.setText(news.getTitle());
        }

        @Override
        public int getItemCount() {
            return newsList.size();
        }
    }
}
