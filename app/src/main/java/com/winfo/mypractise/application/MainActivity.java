package com.winfo.mypractise.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.View;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;
import com.winfo.mypractise.recycler.RecyclerActivity;

public class MainActivity extends BaseActivity {

    private FloatingActionButton fab;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        fab = findView(R.id.fab);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void Business() {
        fab.setOnClickListener(this);
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fab:
                startActivity(new Intent(MainActivity.this, RecyclerActivity.class));
                break;
        }
    }
}