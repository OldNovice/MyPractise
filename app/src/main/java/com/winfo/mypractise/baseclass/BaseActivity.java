package com.winfo.mypractise.baseclass;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.winfo.mypractise.R;
import com.winfo.mypractise.application.ActivityCollector;


public abstract class BaseActivity extends AppCompatActivity implements View.OnClickListener {

    //获取TAG的activity名称
    protected final String TAG = this.getClass().getSimpleName();
    //是否允许旋转屏幕
    private boolean isAllowScreenRotation = true;
    //封装Toast对象
    private static Toast toast;
    public Context context;
    //private ImmersionBar mImmersionBar;
    //static 代码段可以防止内存泄露
    static {
        //设置全局的Header构建器
        SmartRefreshLayout.setDefaultRefreshHeaderCreator((context, layout) -> {
            layout.setPrimaryColorsId(R.color.colorPrimary, android.R.color.white);//全局设置主题颜色
            return new ClassicsHeader(context);//.setTimeFormat(new DynamicTimeFormat("更新于 %s"));//指定为经典Header，默认是 贝塞尔雷达Header
        });
        //设置全局的Footer构建器
        SmartRefreshLayout.setDefaultRefreshFooterCreator((context, layout) -> {
            //指定为经典Footer，默认是 BallPulseFooter
            return new ClassicsFooter(context).setDrawableSize(20);
        });
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //activity管理
        ActivityCollector.addActivity(this);
        setContentView(initLayout());
        //设置屏幕是否可旋转
        if (!isAllowScreenRotation) { setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE); }
        else { setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT); }
        //初始化控件
        initView();
        //设置数据
        initData();
        Business();
//        mImmersionBar = ImmersionBar.with(this);
//        mImmersionBar.init();   //所有子类都将继承这些相同的属性
    }

    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置数据
     */
    protected abstract void initData();

    /**
     * 业务逻辑
     */
    protected abstract void Business();

    /**
     * 省去转换
     *
     * @param id
     * @param <T>
     * @return
     */

    protected <T extends View> T findView(int id) {
        return (T) findViewById(id);
    }


    /**
     * 是否允许屏幕旋转
     *
     * @param allowScreenRotation true or false
     */
    public void setAllowScreenRotation(boolean allowScreenRotation) {
        isAllowScreenRotation = allowScreenRotation;
    }

    /**
     * 保证同一按钮在1秒内只会响应一次点击事件
     */
    public abstract static class OnSingleClickListener implements View.OnClickListener {
        //两次点击按钮之间的间隔，目前为1000ms
        private static final int MIN_CLICK_DELAY_TIME = 1000;
        private long lastClickTime;

        public abstract void onSingleClick(View view);

        @Override
        public void onClick(View view) {
            long curClickTime = System.currentTimeMillis();
            if ((curClickTime - lastClickTime) >= MIN_CLICK_DELAY_TIME) {
                lastClickTime = curClickTime;
                onSingleClick(view);
            }
        }
    }


    /**
     * 同一按钮在短时间内可重复响应点击事件
     */
    public abstract static class OnMultiClickListener implements View.OnClickListener {
        public abstract void onMultiClick(View view);

        @Override
        public void onClick(View v) {
            onMultiClick(v);
        }
    }

    /**
     * 显示提示  toast
     *
     * @param msg 提示信息
     */
    @SuppressLint("ShowToast")
    public void showToast(String msg) {
        try {
            if (null == toast) {
                toast = Toast.makeText(context, msg, Toast.LENGTH_SHORT);
            } else {
                toast.setText(msg);
            }
            runOnUiThread(() -> toast.show());
        } catch (Exception e) {
            e.printStackTrace();
            //解决在子线程中调用Toast的异常情况处理
            Looper.prepare();
            Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
            Looper.loop();
        }
    }


    /**
     * 隐藏软键盘
     */
    public void hideSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && null != imm) {
            imm.hideSoftInputFromWindow(getCurrentFocus().getWindowToken(), 0);
        }
    }

    /**
     * 显示软键盘
     */
    public void showSoftInput() {
        InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
        if (getCurrentFocus() != null && null != imm) {
            imm.showSoftInputFromInputMethod(getCurrentFocus().getWindowToken(), 0);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //activity管理
        ActivityCollector.removeActivity(this);
        //不调用该方法，如果界面bar发生改变，在不关闭app的情况下，退出此界面再进入将记忆最后一次bar改变的状态
        //mImmersionBar.destroy(this,new Dialog(this),true);
    }
}
