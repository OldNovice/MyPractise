package com.winfo.mypractise.application;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.PopupWindow;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;
import com.winfo.mypractise.recycler.RecyclerActivity;
import com.winfo.mypractise.ui.ChartActivity;

public class MainActivity extends BaseActivity {

    private Button recycleLayout, openDrawer, chartLayout, popLayout;
    private FloatingActionButton flyBnt;
    private DrawerLayout drawerLayout;
    private PopupWindow popupWindow;

    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        recycleLayout = findViewById(R.id.recycle_layout);
        flyBnt = findViewById(R.id.fly_bnt);
        openDrawer = findViewById(R.id.drawer_layout);
        Toolbar toolbar = findViewById(R.id.main_toolbar);
        setSupportActionBar(toolbar);
        drawerLayout = findViewById(R.id.main_drawer);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {//显示标题栏back键
            actionBar.setDisplayHomeAsUpEnabled(true);
            //更换图标
            actionBar.setHomeAsUpIndicator(R.drawable.ic_drawer_24);
        }
        chartLayout = findView(R.id.chart_layout);
        popLayout = findView(R.id.pop_layout);
    }

    /**
     * 初始化PopupWindow
     */
    @SuppressLint("InflateParams")
    private void initPopWindow() {
        // 获取自定义布局文件pop.xml的视图
        View customView = this.getLayoutInflater().inflate(R.layout.layout_popwindow, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupWindow = new PopupWindow(customView, 300, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        customView.findViewById(R.id.lin_scan_qr_code).setOnClickListener(v -> {
            showToast("扫一扫");
            popupWindow.dismiss();
        });
        customView.findViewById(R.id.qr_code).setOnClickListener(v -> {
            showToast("二维码");
            popupWindow.dismiss();
        });
    }
    @Override
    protected void initData() {

    }

    @Override
    protected void Business() {
        recycleLayout.setOnClickListener(this);
        openDrawer.setOnClickListener(this);
        chartLayout.setOnClickListener(this);
        flyBnt.setOnClickListener(this);
        popLayout.setOnClickListener(this);
    }

    @SuppressLint({"NonConstantResourceId", "ShowToast"})
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recycle_layout://列表布局
                startActivity(new Intent(this, RecyclerActivity.class));
                break;
            case R.id.fly_bnt://底部出来的
                Snackbar.make(v, "", Snackbar.LENGTH_SHORT)
                        .setAction("Undo", view -> showToast("来了老弟"))
                        .show();
                break;
            case R.id.drawer_layout://抽屉
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.chart_layout://图表
                startActivity(new Intent(this, ChartActivity.class));
                break;
            case R.id.pop_layout://pop
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    initPopWindow();
                    popupWindow.showAsDropDown(v);
                }
                break;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.toolbar_menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.backup:
                showToast("backup");
                break;
            case R.id.delete:
                showToast("delete");
                break;
            case R.id.settings:
                showToast("settings");
                break;
            case R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                break;
        }
        return true;
    }
}