package com.example.xuetaotao.helloworld.base;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * fragment基类
 */
public abstract class BaseFragment extends Fragment{

    Unbinder unbinder;

    public View mview;

    protected abstract void initView();

    protected abstract void initData();

    protected abstract int getResourceId();


    /**
     * 为碎片创建视图，加载布局时调用
     * @param inflater
     * @param container
     * @param savedInstanceState
     * @return
     */
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        mview = inflater.inflate(getResourceId(), null);
        unbinder = ButterKnife.bind(this, mview);
        initView();
        return mview;
    }

    /**
     * 确保已经与碎片相关联的活动一定已经创建完毕的时候调用
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    /**
     * 当与碎片关联的视图被移除的时候调用
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (unbinder != null){
            unbinder.unbind();
        }
    }
}


