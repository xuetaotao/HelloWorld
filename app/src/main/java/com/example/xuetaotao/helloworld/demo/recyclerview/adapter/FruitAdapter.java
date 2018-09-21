package com.example.xuetaotao.helloworld.demo.recyclerview.adapter;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.Fruit;
import com.example.xuetaotao.helloworld.listener.OnRcyItemClickListener;
import com.example.xuetaotao.helloworld.utils.ToastUtils;

import java.util.List;


public class FruitAdapter extends RecyclerView.Adapter<FruitAdapter.ViewHolder> {

    private List<Fruit> fruitList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView fruitName;
        ImageView  fruitImg;

        public ViewHolder(View view){

            super(view);
            fruitName = (TextView) view.findViewById(R.id.tv_fruit_name);
            fruitImg = (ImageView) view.findViewById(R.id.iv_fruit_img);
        }
    }

    public FruitAdapter(List<Fruit> fruitList1){

        this.fruitList = fruitList1;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

//        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item, parent, false);
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fruit_item2, parent, false);

        //点击事件一
        final ViewHolder holder = new ViewHolder(view);
        holder.fruitName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                Fruit fruit = fruitList.get(position);
                ToastUtils.showToast(v.getContext(), fruit.getName());
            }
        });

        //点击事件二 TODO 有点问题
        holder.fruitImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
//                ToastUtils.showToast(v.getContext(), "Image");
                if (listener != null){
                    listener.onClick(position);
                }else {
                    Log.e("RecyclerViewActivity", "点击事件二 Adapter");
                }
            }
        });

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Fruit fruit = fruitList.get(position);
        holder.fruitName.setText(fruit.getName());
        holder.fruitImg.setImageResource(fruit.getImageId());
    }

    @Override
    public int getItemCount() {
        return fruitList.size();
    }


    /**
     * 点击事件二
     */
    OnRcyItemClickListener listener;

    public void setOnRcyItemClickListener(OnRcyItemClickListener listener1){
        this.listener = listener1;
    }

}
