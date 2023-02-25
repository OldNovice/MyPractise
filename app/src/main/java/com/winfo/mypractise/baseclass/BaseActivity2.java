package com.winfo.mypractise.baseclass;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.winfo.mypractise.util.ClickUtil;


public abstract class BaseActivity2 extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(intiLayout());

        //默认黑色字体
        statusBarBlack();

        //初始化控件
        initView();

        //设置数据
        initData();

    }

    protected abstract int intiLayout();

    protected abstract void initView();

    protected abstract void initData();

    public void statusBarBlack() {
//        WindowUtils.setStatusBarColor(this, R.color.transparent);
//        WindowUtils.setLightStatusBar(this, true, true);
//    }
//
//    public void statusBarWhite() {
//        WindowUtils.setStatusBarColor(this, R.color.transparent);
//        WindowUtils.setLightStatusBar(this, false, true);
    }

    /**
     * 跳转页面动画
     */
    public void pushActivity() {
       // overridePendingTransition(R.anim.push_right_in, R.anim.push_left_out);
    }

    /**
     * 结束统一动画
     */
    @Override
    public void finish() {
      super.finish();
//        overridePendingTransition(R.anim.push_left_in, R.anim.push_right_out);
    }

    /**
     * 获取点击事件
     */
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View view = getCurrentFocus();
            if (isHideInput(view, ev)) {
                HideSoftInput(view.getWindowToken());
                view.clearFocus();
            }
        }
        return super.dispatchTouchEvent(ev);
    }

    /**
     * 判定是否需要隐藏
     */
    private boolean isHideInput(View v, MotionEvent ev) {
        if ((v instanceof EditText)) {
            int[] l = {0, 0};
            v.getLocationInWindow(l);
            int left = l[0], top = l[1], bottom = top + v.getHeight(), right = left + v.getWidth();
            return !(ev.getX() > left) || !(ev.getX() < right) || !(ev.getY() > top) || !(ev.getY() < bottom);
        }
        return false;
    }

    /**
     * 隐藏软键盘
     */
    private void HideSoftInput(IBinder token) {
        if (token != null) {
            InputMethodManager manager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            manager.hideSoftInputFromWindow(token, InputMethodManager.HIDE_NOT_ALWAYS);
        }
    }

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

    /**
     * 页面跳转
     */
    public void startActivity(Class<?> clz) {
        startActivity(clz, null);
    }

    /**
     * 携带数据的页面跳转
     */
    public void startActivity(Class<?> clz, Bundle bundle) {
        Intent intent = new Intent();
        intent.setClass(this, clz);
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        startActivity(intent);
    }

    /**
     * 网络判断
     */
    public boolean checkNetwork(Context context) {

        ConnectivityManager conn = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        @SuppressLint("MissingPermission") NetworkInfo net = conn.getActiveNetworkInfo();
        return net != null && net.isConnected();
    }

}