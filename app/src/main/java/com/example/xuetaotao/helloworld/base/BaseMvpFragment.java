package com.example.xuetaotao.helloworld.base;

import android.os.Bundle;
import android.support.annotation.Nullable;

public abstract class BaseMvpFragment extends BaseFragment{

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}

