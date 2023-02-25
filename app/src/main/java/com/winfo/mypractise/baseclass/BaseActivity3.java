package com.winfo.mypractise.baseclass;

import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

import com.winfo.mypractise.application.ActivityCollector;
import com.winfo.mypractise.util.ClickUtil;


public abstract class BaseActivity3 extends AppCompatActivity implements View.OnClickListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityCollector.addActivity(this);
        setContentView(intiLayout());
        initView();
        initData();
        initClickEvent();
    }
    /**初始化布局*/
    protected abstract int intiLayout();
    /**初始化控件*/
    protected abstract void initView();
    /**初始化数据*/
    protected abstract void initData();
    /**处理点击setXXX*/
    protected abstract void  initClickEvent();

    /**
     * 防止多次点击
     */
    public abstract void onClicks(View v);

    @Override
    public void onClick(View v) {
        //防止快速点击
        if (!ClickUtil.isFastClick()) {
            return;
        }
        //下面写点击事件逻辑
        onClicks(v);
    }
}