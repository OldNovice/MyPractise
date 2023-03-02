package com.winfo.mypractise.ui;

import android.view.View;

import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity3;
import com.winfo.mypractise.diyview.AttendanceProgressBar;

public class ChartActivity extends BaseActivity3 {

    AttendanceProgressBar progressBar;
    @Override
    protected int intiLayout() {
        return R.layout.activity_chart;
    }

    @Override
    protected void initView() {
        progressBar = findViewById(R.id.apb);
        //进度条
        progressBar.setProgress(10 ,18);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClicks(View v) {

    }


}