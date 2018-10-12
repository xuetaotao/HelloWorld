package com.example.xuetaotao.helloworld.demo.wechat.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.Msg;

import java.util.List;

import static com.example.xuetaotao.helloworld.bean.Msg.TYPE_RECEIVED;

public class MsgAdapter extends RecyclerView.Adapter<MsgAdapter.ViewHolder>{


    private List<Msg> msgList;

    public MsgAdapter(List<Msg> msgList1){
        msgList = msgList1;
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout llLeft, llRight;
        TextView tvLeft, tvRight;

        public ViewHolder(View view){
            super(view);

            llLeft = (LinearLayout) view.findViewById(R.id.ll_left);
            llRight = (LinearLayout) view.findViewById(R.id.ll_right);
            tvLeft = (TextView) view.findViewById(R.id.tv_left);
            tvRight = (TextView) view.findViewById(R.id.tv_right);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.msg_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Msg msg = msgList.get(position);
        if (msg.getType() == TYPE_RECEIVED){
            holder.llLeft.setVisibility(View.GONE);
            holder.llRight.setVisibility(View.VISIBLE);
            holder.tvRight.setText(msg.getContent());
        } else{
            holder.llRight.setVisibility(View.GONE);
            holder.llLeft.setVisibility(View.VISIBLE);
            holder.tvLeft.setText(msg.getContent());
        }
    }

    @Override
    public int getItemCount() {
        return msgList.size();
    }
}
