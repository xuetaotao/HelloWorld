package com.example.xuetaotao.helloworld.webview;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.example.xuetaotao.helloworld.R;
import com.example.xuetaotao.helloworld.base.BaseTitleActivity;

public class WebActivity extends BaseTitleActivity {

    private String url = "http://www.baidu.com";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_web);
        WebView webView = (WebView) findViewById(R.id.web_view);
        //设置浏览器属性
        webView.getSettings().setJavaScriptEnabled(true);
        //创建WebViewClient实例
        webView.setWebViewClient(new WebViewClient());
        webView.loadUrl(url);
    }

    @Override
    public int getResourcesId() {
        return R.layout.activity_web;
    }

    @Override
    public int getTitleText() {
        return R.string.explore;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

    }
}
