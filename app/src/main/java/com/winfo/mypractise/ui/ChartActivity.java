package com.winfo.mypractise.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

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
        findViewById(R.id.title_menu).setVisibility(View.GONE);
        TextView textView=findViewById(R.id.title_text);
        textView.setText("图表");
        ImageView back=findViewById(R.id.title_drawer);
        back.setImageResource(R.mipmap.back_day);
        back.setOnClickListener(v-> finish());

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