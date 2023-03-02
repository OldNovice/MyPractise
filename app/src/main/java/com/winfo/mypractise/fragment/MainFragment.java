package com.winfo.mypractise.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.snackbar.Snackbar;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseFragment;
import com.winfo.mypractise.recycler.RecyclerActivity;
import com.winfo.mypractise.ui.ChartActivity;
import com.winfo.mypractise.ui.DataStorageActivity;
import com.winfo.mypractise.ui.WebActivity;

public class MainFragment extends BaseFragment {

    private DrawerLayout drawerLayout;
    private PopupWindow popupWindow;
    private ImageView menuDrawer,popLayout;
    private Button recycleLayout, snackBar, chartLayout,webLayout,spLayout;
    @Override
    protected int initLayout() {
        return R.layout.fragment_main;
    }

    @Override
    protected void initView(View view) {
        recycleLayout = view.findViewById(R.id.recycle_layout);
        snackBar = view.findViewById(R.id.snack_bar);
        popLayout = view.findViewById(R.id.title_menu);
        drawerLayout = view.findViewById(R.id.main_drawer);
        chartLayout = view.findViewById(R.id.chart_layout);
        webLayout= view.findViewById(R.id.web_layout);
        spLayout=view.findViewById(R.id.sp_layout);

        menuDrawer=view.findViewById(R.id.title_drawer);
        TextView textView=view.findViewById(R.id.title_text);
        textView.setText("主页");
    }

    @Override
    protected void initEvent() {
        recycleLayout.setOnClickListener(this);
        snackBar.setOnClickListener(this);
        chartLayout.setOnClickListener(this);
        popLayout.setOnClickListener(this);
        menuDrawer.setOnClickListener(this);
        webLayout.setOnClickListener(this);
        spLayout.setOnClickListener(this);
    }

    /**
     * 初始化PopupWindow
     */
    @SuppressLint("InflateParams")
    private void initPopWindow() {
        // 获取自定义布局文件pop.xml的视图
        View popView = this.getLayoutInflater().inflate(R.layout.layout_popwindow, null, false);
        // 创建PopupWindow实例,200,150分别是宽度和高度
        popupWindow = new PopupWindow(popView, 300, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        // 设置动画效果 [R.style.AnimationFade 是自己事先定义好的]
        popupWindow.setAnimationStyle(R.style.Animation_AppCompat_DropDownUp);
        popupWindow.setTouchable(true);
        popupWindow.setOutsideTouchable(true);
        popupWindow.setBackgroundDrawable(new BitmapDrawable());
        popView.findViewById(R.id.lin_scan_qr_code).setOnClickListener(v -> {
            Toast.makeText(requireContext(), "扫一扫", Toast.LENGTH_SHORT).show();
            popupWindow.dismiss();
        });
        popView.findViewById(R.id.qr_code).setOnClickListener(v -> {
            View view = View.inflate(requireContext(), R.layout.toast, null);//编写自己xml布局
            TextView text = view.findViewById(R.id.toast);
            Toast toast=new Toast(requireContext());
            text.setText("表哥来了喔");
            toast.setView(view);
            toast.setGravity(Gravity.CENTER, 0, 0);
            toast.show();
            popupWindow.dismiss();
        });
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.recycle_layout://列表布局
                startActivity(new Intent(requireContext(), RecyclerActivity.class));
                break;
            case R.id.snack_bar://底部提示
                Snackbar.make(v, "我出来了喔", Snackbar.LENGTH_SHORT)
                        .setAction("好的", view -> Toast.makeText(requireContext(), "嗯嗯", Toast.LENGTH_SHORT).show())
                        .show();
                break;
            case R.id.chart_layout://图表
                startActivity(new Intent(requireActivity(), ChartActivity.class));
                break;
            case R.id.title_menu://pop
                if (popupWindow != null && popupWindow.isShowing()) {
                    popupWindow.dismiss();
                } else {
                    initPopWindow();
                    popupWindow.showAsDropDown(v);
                }
                break;
            case R.id.title_drawer://抽屉
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            case R.id.web_layout://网页布局
                startActivity(new Intent(requireContext(), WebActivity.class));
                break;
            case R.id.sp_layout://数据存储-sp
                startActivity(new Intent(requireContext(), DataStorageActivity.class));
                break;
        }
    }
}