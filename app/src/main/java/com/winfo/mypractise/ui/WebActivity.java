package com.winfo.mypractise.ui;


import android.annotation.SuppressLint;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;

public class WebActivity extends BaseActivity {

    final String uri = "file:///android_asset/index.html";

    @Override
    protected int initLayout() {
        return (R.layout.activity_web);
    }

    @SuppressLint("SetJavaScriptEnabled")
    @Override
    protected void initView() {
        WebView webView = findViewById(R.id.web_view);

        webView.loadUrl(uri);

        //如果网页是http请求头，Android会自动打开手机默认浏览器。
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        WebSettings webSettings = webView.getSettings();
//如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

//设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小
//自适应屏幕
        webView.getSettings().setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

//缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

//其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

//优先使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_CACHE_ELSE_NETWORK);
//缓存模式如下：
//LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
//LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
//LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
//LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据
//不使用缓存
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);

    }

    @Override
    public void onClick(View v) {

    }
}