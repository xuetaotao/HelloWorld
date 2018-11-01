package com.example.xuetaotao.helloworld.demo.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.utils.ToastUtils;

public class LeftFragment extends Fragment {

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.left_fragment, container, false);
//        Button btn1 = (Button) view.findViewById(R.id.btn1);
//        btn1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                ToastUtils.showToast(getActivity(), "宝儿早安");
//            }
//        });

        return view;
    }

    public void CommuWithActivity(){

        ToastUtils.showToast(getActivity(), "这是LeftFragment中的方法");
    }

}
