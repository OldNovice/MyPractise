package com.winfo.mypractise.recycler;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.AppBarLayout;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.scwang.smart.refresh.layout.constant.SpinnerStyle;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity3;

import java.util.ArrayList;
import java.util.List;

public class RecyclerActivity extends BaseActivity3 {
    private AppBarLayout appbar;
    private Toolbar toolbar;
    private ListAdapter listAdapter;
    private RecyclerView recyclerView;
    private List<String> dataList;
    int page = 1;
    private int count = 20;

    @Override
    protected int intiLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {
        initRefresh();
        appbar = findViewById(R.id.appbar);
        toolbar = findViewById(R.id.toolbar);
        recyclerView=findViewById(R.id.recyclerView);

    }

    @Override
    protected void initData() {
        dataList = new ArrayList<>();
        for (int i = 1; i <= count ; i++){
            dataList.add(i+1+"");
        }

        listAdapter = new ListAdapter(this);
        recyclerView.setAdapter(listAdapter);
        listAdapter.setList(dataList);
    }

    @Override
    protected void initClickEvent() {

    }

    @Override
    public void onClicks(View v) {

    }

    /**
     * 初始化刷新头
     *@throws  ClassicsHeader 经典刷新头
     *@throws  ClassicsFooter   经典加载
     * @throws BezierRadarHeader 贝塞尔雷达 样式
     * @throws BallPulseFooter   球脉冲样式
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initRefresh() {
        RefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));
        //刷新
        refreshLayout.setOnRefreshListener(refresh -> {
            refresh.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            dataList.clear();
            for (int i = 0; i <=count ; i++) {
                dataList.add(i+1+"");
            }
        });
        //加载
        refreshLayout.setOnLoadMoreListener(loadMore -> {
            loadMore.finishLoadMore(2000/*,false*/);//传入false表示加载失败
            count =+ 10;
            page++;
            for (int i = 1; i <= count ; i++){
                dataList.add(i+1+"");
            }
            listAdapter.notifyDataSetChanged();
        });
    }
}