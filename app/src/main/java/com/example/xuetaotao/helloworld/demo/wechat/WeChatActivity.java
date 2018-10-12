package com.example.xuetaotao.helloworld.demo.wechat;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.bean.Msg;
import com.example.xuetaotao.helloworld.demo.wechat.adapter.MsgAdapter;

import java.util.ArrayList;
import java.util.List;

public class WeChatActivity extends AppCompatActivity {

    private List<Msg> msgList = new ArrayList<>();

    private EditText etContent;
    private RecyclerView recyclerView;
    private MsgAdapter adapter;

    public static void newInstance(Context context){
        Intent intent = new Intent(context, WeChatActivity.class);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        recyclerView = (RecyclerView) findViewById(R.id.mRecyclerView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new MsgAdapter(msgList);
        recyclerView.setAdapter(adapter);

        etContent = (EditText) findViewById(R.id.et_content);
        Button btnSend = (Button) findViewById(R.id.btn_send);
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = etContent.getText().toString();
                if (!TextUtils.isEmpty(content)){

                    Msg msg = new Msg(content, Msg.TYPE_SEND);
                    msgList.add(msg);
//                    adapter.notifyDataSetChanged();   //换成下面一条语句也可以
                    adapter.notifyItemInserted(msgList.size() - 1);     //当有新消息时，刷新界面的显示
                    recyclerView.scrollToPosition(msgList.size() - 1);  //将RecycleView定位到最后一行，保证输入法界面不会将聊天信息遮挡
                    etContent.setText("");
                }
            }
        });

        initData();
    }

    public void initData(){
        Msg msg1 = new Msg("宝儿早安", Msg.TYPE_SEND);
        msgList.add(msg1);
        Msg msg2 = new Msg("宝宝早安", Msg.TYPE_RECEIVED);
        msgList.add(msg2);
        Msg msg3 = new Msg("弄啥嘞", Msg.TYPE_SEND);
        msgList.add(msg3);
    }
}
