package com.winfo.mypractise.application;


import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import androidx.annotation.NonNull;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity;
import com.winfo.mypractise.fragment.FilmFragment;
import com.winfo.mypractise.fragment.MainFragment;
import com.winfo.mypractise.fragment.UserFragment;
import com.winfo.mypractise.fragment.VideoFragment;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends BaseActivity implements CompoundButton.OnCheckedChangeListener {

    private ViewPager mViewPager;
    private RadioGroup mRadioGroup;
    private RadioButton tab1,tab2,tab3,tab4;  //四个单选按钮
    private List<View> mViews;   //存放视图

    private MainFragment mainFragment;//主页
    private VideoFragment videoFragment;//
    private FilmFragment filmFragment;//
    private UserFragment userFragment;//
    String fragment_statue="";
private Fragment fragment;
    @Override
    protected int initLayout() {
        return R.layout.activity_main2;
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initView() {
       // initRadioGroup();
        tab1=findViewById(R.id.rb_msg);
        tab2=findViewById(R.id.rb_people);
        tab3=findViewById(R.id.rb_find);
        tab4=findViewById(R.id.rb_me);
        tab1.setOnCheckedChangeListener(this);
        tab2.setOnCheckedChangeListener(this);
        tab3.setOnCheckedChangeListener(this);
        tab4.setOnCheckedChangeListener(this);

    }

    @SuppressLint("InflateParams")
    private void initRadioGroup() {
        //初始化控件
       // mViewPager=findViewById(R.id.viewpager);
        mRadioGroup=findViewById(R.id.rg_tab);
        tab1=findViewById(R.id.rb_msg);
        tab2=findViewById(R.id.rb_people);
        tab3=findViewById(R.id.rb_find);
        tab4=findViewById(R.id.rb_me);

        mViews=new ArrayList<View>();//加载，添加视图
        mViews.add(LayoutInflater.from(this).inflate(R.layout.fragment_main,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.fragment_video,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.fragment_film,null));
        mViews.add(LayoutInflater.from(this).inflate(R.layout.fragment_user,null));

       // mViewPager.setAdapter(new MyViewPagerAdapter());//设置一个适配器
        //对viewpager监听，让分页和底部图标保持一起滑动
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override   //让viewpager滑动的时候，下面的图标跟着变动
            public void onPageSelected(int position) {
                switch (position) {
                    case 0:
                        tab1.setChecked(true);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        tab4.setChecked(false);
                        break;
                    case 1:
                        tab1.setChecked(false);
                        tab2.setChecked(true);
                        tab3.setChecked(false);
                        tab4.setChecked(false);
                        break;
                    case 2:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(true);
                        tab4.setChecked(false);
                        break;
                    case 3:
                        tab1.setChecked(false);
                        tab2.setChecked(false);
                        tab3.setChecked(false);
                        tab4.setChecked(true);
                        break;
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //对单选按钮进行监听，选中、未选中
        mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @SuppressLint("NonConstantResourceId")
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int i) {
                switch (i) {
                    case R.id.rb_msg:
                        mViewPager.setCurrentItem(0);
                        break;
                    case R.id.rb_people:
                        mViewPager.setCurrentItem(1);
                        break;
                    case R.id.rb_find:
                        mViewPager.setCurrentItem(2);
                        break;
                    case R.id.rb_me:
                        mViewPager.setCurrentItem(3);
                        break;
                }
            }
        });
    }

    @Override
    public void onClick(View v) {
    }



    @SuppressLint("NonConstantResourceId")
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        switch (buttonView.getId()) {
            case R.id.rb_msg:
                if (isChecked) {
                    fragment_statue = "1";
                    if (mainFragment == null) {
                        mainFragment = new MainFragment();
                    }
                }
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.add(R.id.fragments, mainFragment, "mainFragment");
                ft.addToBackStack(null);
                ft.commit();
                break;
            case R.id.rb_people:
                fragment_statue = "2";
                if (isChecked) {
                    if (videoFragment == null) {
                        videoFragment = new VideoFragment();
                    }
                }
                switchContent(mainFragment);
                break;
            case R.id.rb_find:
                if (isChecked) {
                    fragment_statue = "3";
                    if (filmFragment == null) {
                        filmFragment = new FilmFragment();
                    }
                }
                switchContent(filmFragment);
                break;
            case R.id.rb_me:
                if (isChecked) {
                    fragment_statue = "4";
                    if (userFragment == null) {
                        userFragment = new UserFragment();
                    }
                }
                switchContent(userFragment);
                break;
        }
    }
    //设置返回fragment状态
    private void setBootCheck(String value) {
        switch (value) {
            case "1":
                tab1.setChecked(true);
                break;
            case "2":
                tab2.setChecked(true);
                break;
            case "3":
                tab3.setChecked(true);
//                imgBtnLocation.setVisibility(View.VISIBLE);
//                zoomControlsView.setVisibility(View.VISIBLE);
//                mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
                break;
            case "4":
                tab4.setChecked(true);
                break;
            default:
                break;
        }
    }


    /**
     * 修改显示的内容 不会重新加载
     **/
    public void switchContent(Fragment to) {
        if (fragment != to) {
            FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
            if (!to.isAdded()) { // 先判断是否被add过
                if (to == mainFragment) {
                    transaction.hide(fragment).add(R.id.fragments, mainFragment, "mainFragment").commit();
                } else {
                    transaction.hide(fragment).add(R.id.fragments, to, "mainFragment").commit(); // 隐藏当前的fragment，add下一个到Activity中
                }
            } else {
                transaction.hide(fragment).show(to).commit(); // 隐藏当前的fragment，显示下一个
            }
            fragment = to;
        }
    }
}