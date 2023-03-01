package com.winfo.mypractise.application;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;

public class MyApplication extends Application {

    @SuppressLint("StaticFieldLeak")
    private static MyApplication myApplication;
    @SuppressLint("StaticFieldLeak")
    private static Context context;

    public MyApplication() {
        super();
        myApplication = this;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();
    }

    public static MyApplication getInstance() {
        return myApplication;
    }

    public static Context getContext() {
        return context;
    }
}
