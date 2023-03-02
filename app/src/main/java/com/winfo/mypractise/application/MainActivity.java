package com.winfo.mypractise.application;


import android.view.View;
import android.widget.FrameLayout;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;
import com.winfo.mypractise.fragment.FilmFragment;
import com.winfo.mypractise.fragment.MainFragment;
import com.winfo.mypractise.fragment.UserFragment;
import com.winfo.mypractise.fragment.VideoFragment;

public class MainActivity extends BaseActivity {

    private Fragment[] fragments;
    @Override
    protected int initLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        FrameLayout mainFrame = findViewById(R.id.mainContainer);
        //todo 底部导航栏
        BottomNavigationView bottomNavigationView = findView(R.id.bottom_nav);
        bottomNavigationView.setOnNavigationItemSelectedListener(listener);

        fragments = new Fragment[]{new MainFragment(), new VideoFragment(), new FilmFragment(), new UserFragment()};
        //设置fragment到布局
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.mainContainer, new MainFragment())
                .commit();
    }

    /**
     * (底部导航栏)
     */
    private final BottomNavigationView.OnNavigationItemSelectedListener listener = item -> {
       int id=item.getItemId();
        if (id == R.id.home) {
            replaceFragment(fragments[0]);
        } else if (id == R.id.video) {
            replaceFragment(fragments[1]);
        } else if (id == R.id.film) {
            replaceFragment(fragments[2]);
        } else if (id == R.id.user_center) {
            replaceFragment(fragments[3]);
        }
        return true;
    };

    /**
     * 切换Fragment
     */
    private void replaceFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.replace(R.id.mainContainer, fragment);
        transaction.commitAllowingStateLoss();
    }
    private void addFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.add(R.id.mainContainer, fragment);
        transaction.commitAllowingStateLoss();
    }
    private void showFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.show(fragment);
        transaction.commitAllowingStateLoss();
    }
    private void hideFragment(Fragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fm.beginTransaction();
        transaction.hide(fragment);
        transaction.commitAllowingStateLoss();
    }

    @Override
    public void onClick(View v) {
    }

}