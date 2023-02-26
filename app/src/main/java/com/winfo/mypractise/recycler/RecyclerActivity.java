package com.winfo.mypractise.recycler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smart.refresh.footer.BallPulseFooter;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.BezierRadarHeader;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.scwang.smart.refresh.layout.api.RefreshLayout;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity3;
import com.winfo.mypractise.javabean.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerActivity extends BaseActivity3 {

    private RecyclerView recyclerView;
    private SmartRefreshLayout refreshLayout;
    int page = 1;
    private int count = 20;
    private BookAdapter bookAdapter;
    private List<Book> bookLists = new ArrayList<>();
    private Book[] books = {
            new Book(R.mipmap.app_logo, "111"),
            new Book(R.mipmap.app_logo, "222"),
            new Book(R.mipmap.app_logo, "333"),
    };

    @Override
    protected int intiLayout() {
        return R.layout.activity_recycler;
    }

    @Override
    protected void initView() {
        initRefresh();
        recyclerView = findViewById(R.id.recyclerView);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {//启用 HomeAsUp 按钮（返回箭头）
            actionBar.setDisplayHomeAsUpEnabled(true);
        }
        Intent intent = getIntent();

        //设置标题
        CollapsingToolbarLayout layout = findViewById(R.id.collapsing_toolbar);
        layout.setTitle("catName");
        //设置内容
//        TextView contentView = (TextView) findViewById(R.id.cat_text_view);
//        contentView.setText(generateContent(catName));

    }

    @Override
    protected void initData() {
        initBooks();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        bookAdapter = new BookAdapter(bookLists);
        recyclerView.setAdapter(bookAdapter);
    }

    private void initBooks() {
        bookLists.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(books.length);
            bookLists.add(books[index]);
        }
    }

    @Override
    public void onClicks(View v) {

    }

    /**
     * 初始化刷新头
     *
     * @throws ClassicsHeader    经典刷新头
     * @throws ClassicsFooter    经典加载
     * @throws BezierRadarHeader 贝塞尔雷达 样式
     * @throws BallPulseFooter   球脉冲样式
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initRefresh() {
        refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle
//        .Scale));
        //刷新
        refreshLayout.setOnRefreshListener(refresh -> {
            refresh.finishRefresh(2000/*,false*/);//传入false表示刷新失败
            refreshBooks();
        });
        //加载
        refreshLayout.setOnLoadMoreListener(loadMore -> {
            loadMore.finishLoadMore(2000/*,false*/);//传入false表示加载失败
        });
    }

    private void refreshBooks() {
        new Thread(() -> {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            runOnUiThread(() -> {
                initBooks();
                bookAdapter.notifyDataSetChanged();
            });
        }).start();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home://点击 HomeAsUp 按钮时
                finish();//关闭当前活动（即返回上一个活动）
                return true;
        }
        return true;
    }
}