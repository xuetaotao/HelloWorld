package com.example.xuetaotao.helloworld.demo.listview.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.Fruit;

import java.util.List;

public class FruitAdapter extends ArrayAdapter<Fruit> {

    private int resourcedId;

    public FruitAdapter(Context context, int textViewResourceId, List<Fruit> objects){
        super(context, textViewResourceId, objects);
        resourcedId = textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Fruit fruit = getItem(position);
        View view;
        ViewHolder holder;
        if (convertView == null){
            view = LayoutInflater.from(getContext()).inflate(resourcedId, parent, false);
            holder = new ViewHolder();
            holder.fruitName = (TextView) view.findViewById(R.id.tv_fruit_name);
            holder.fruitImg = (ImageView) view.findViewById(R.id.iv_fruit_img);
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }

        holder.fruitName.setText(fruit.getName());
        holder.fruitImg.setImageResource(fruit.getImageId());
        return view;
    }

    class ViewHolder{
        TextView fruitName;
        ImageView fruitImg;
    }
}
