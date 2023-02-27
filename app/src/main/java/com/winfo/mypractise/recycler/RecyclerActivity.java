package com.winfo.mypractise.recycler;

import android.annotation.SuppressLint;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.appbar.CollapsingToolbarLayout;
import com.scwang.smart.refresh.footer.ClassicsFooter;
import com.scwang.smart.refresh.header.ClassicsHeader;
import com.scwang.smart.refresh.layout.SmartRefreshLayout;
import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseActivity3;
import com.winfo.mypractise.diyview.SlideRecyclerView;
import com.winfo.mypractise.javabean.Book;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecyclerActivity extends BaseActivity3 {

    private RecyclerView recyclerView;
    private BookAdapter bookAdapter;
    private SlideRecyclerView slideRecyclerView;
    private final List<Book> bookLists = new ArrayList<>();
    private final Book[] books = {
            new Book(R.mipmap.app_logo, "Java"),
            new Book(R.mipmap.wm_1, "iOS"),
            new Book(R.mipmap.app_logo, "Android"),
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
        //设置标题
        CollapsingToolbarLayout layout = findViewById(R.id.collapsing_toolbar);
        layout.setTitle("列表布局");
        slideRecyclerView=findViewById(R.id.slide_recycler);
    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    protected void initData() {
        initBooks();
        //initSlideRecyclerView();
        initRecyclerView();
    }
    /**没有删除键----对应布局文件：activity_recycler*/
    private void initRecyclerView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 1);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        recyclerView.setLayoutManager(gridLayoutManager);
        bookAdapter = new BookAdapter(this);
        bookAdapter.setBookList(bookLists);
        recyclerView.setAdapter(bookAdapter);
    }
    /**有删除键----对应布局文件：activity_recycler2*/
    @SuppressLint("NotifyDataSetChanged")
    private void initSlideRecyclerView() {
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        slideRecyclerView.setLayoutManager(linearLayoutManager);
        bookAdapter = new BookAdapter(this);
        bookAdapter.setBookList(bookLists);
        slideRecyclerView.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener((view, position) -> {
            bookLists.remove(position);
            bookAdapter.notifyDataSetChanged();
        });
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
     * 初始化刷新头{
     *     ClassicsHeader:经典刷新头--
     *     ClassicsFooter:经典加载--
     *     BezierRadarHeader:贝塞尔雷达样式--
     *     BallPulseFooter:球脉冲样式--
     * }
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initRefresh() {
        SmartRefreshLayout refreshLayout = findViewById(R.id.refreshLayout);
        refreshLayout.setRefreshHeader(new ClassicsHeader(this));
        refreshLayout.setRefreshFooter(new ClassicsFooter(this));

//        refreshLayout.setRefreshHeader(new BezierRadarHeader(this).setEnableHorizontalDrag(true));
//        refreshLayout.setRefreshFooter(new BallPulseFooter(this).setSpinnerStyle(SpinnerStyle.Scale));

        //刷新
        refreshLayout.setOnRefreshListener(refresh -> {
            refresh.finishRefresh(1000);//传入false表示刷新失败
            refreshBooks();
        });
        //加载
        refreshLayout.setOnLoadMoreListener(loadMore -> {
            loadMore.finishLoadMore(1000);//传入false表示加载失败
        });
    }
    /**刷新数据*/
    @SuppressLint("NotifyDataSetChanged")
    private void refreshBooks() {
        new Thread(() -> {
            try {
                Thread.sleep(1000);
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
        if (item.getItemId() == android.R.id.home) {//点击 HomeAsUp 按钮时
            finish();//关闭当前活动（即返回上一个活动）
            return true;
        }
        return true;
    }
}