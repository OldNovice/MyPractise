package com.winfo.mypractise.fragment;

import android.annotation.SuppressLint;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseFragment;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FilmFragment extends BaseFragment {

    TabLayout tabLayout;
    ViewPager viewPager;
    ImageView back, _add;
    TextView textView;

    @Override
    protected int initLayout() {
        return R.layout.fragment_film;
    }

    @SuppressLint("InflateParams")
    @Override
    protected void initView(View view) {
        tabLayout = view.findViewById(R.id.tab_layout);
        viewPager = view.findViewById(R.id.view_pager);
        back = view.findViewById(R.id.title_drawer);
        back.setVisibility(View.GONE);
        _add= view.findViewById(R.id.title_menu);
        _add.setVisibility(View.GONE);
        textView=view.findViewById(R.id.title_text);
        textView.setText("放映厅");
        List<Fragment> viewList = new ArrayList<>();
        String[] tabName = {"推荐", "电影", "电视剧", "少儿", "纪录片", "综艺", "动漫"};
        //绑定--添加到集合中
        viewList.add(new RecommendFragment());
        //组装
        ViewPageAdapter myAdapter = new ViewPageAdapter(requireFragmentManager(), viewList, Arrays.asList(tabName));
        viewPager.setAdapter(myAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    protected void initEvent() { }

    @Override
    public void onClick(View v) { }

    /**
     * 适配器
     */
    static class ViewPageAdapter extends FragmentPagerAdapter {

        //存放Fragment碎片集合（list）
        List<Fragment> fragmentList;
        List<String> tabList;

        public ViewPageAdapter(FragmentManager fm, List<Fragment> fragmentList, List<String> tabList) {
            super(fm);
            this.fragmentList = fragmentList;
            this.tabList = tabList;
        }

        @Override
        public int getCount() {
            return fragmentList.size();
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            return fragmentList.get(position);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabList.get(position);
        }
    }
}