package com.winfo.mypractise.baseclass;


import android.app.Application;
import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.winfo.mypractise.R;
import com.winfo.mypractise.application.MyApplication;

public abstract class BaseFragment extends Fragment implements View.OnClickListener {
    //封装Toast对象
    private static Toast toast;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(initLayout(), container, false);
        initView(view);
        initEvent();
        return view;
    }


    /**
     * 初始化布局
     */
    protected abstract int initLayout();

    protected abstract void initView(View view);

    protected abstract void initEvent();

    /**
     * 吐司
     */
    public void showToast(String msg) {

        Toast toast = Toast.makeText(MyApplication.getContext(), msg, Toast.LENGTH_SHORT);

        toast.setGravity(Gravity.CENTER, 0, 0);//改变弹出位置
        toast.show();
    }

    public void myToast(Context context,String text) {
        View view = View.inflate(context, R.layout.toast, null);//编写自己xml布局
        TextView textView = view.findViewById(R.id.toast);
        textView.setText(text);
        Toast toast = new Toast(MyApplication.getInstance().getApplicationContext());
        toast.setView(view);
        toast.setGravity(Gravity.CENTER, 0, 0);
        toast.show();
    }
}
