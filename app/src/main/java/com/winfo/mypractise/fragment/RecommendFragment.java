package com.winfo.mypractise.fragment;

import android.annotation.SuppressLint;
import android.view.View;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.winfo.mypractise.R;
import com.winfo.mypractise.baseclass.BaseFragment;
import com.winfo.mypractise.javabean.Book;
import com.winfo.mypractise.recycler.Book2Adapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RecommendFragment extends BaseFragment {

    private RecyclerView recyclerView;
    private Book2Adapter bookAdapter;
    private final List<Book> bookLists = new ArrayList<>();
    public Book[] books = {
            new Book(R.mipmap.app_logo, "Java"),
            new Book(R.mipmap.wm_1, "iOS"),
            new Book(R.mipmap.app_logo, "Android"),
    };

    @Override
    protected int initLayout() {
        return R.layout.fragment_home;
    }


    @Override
    protected void initView(View view) {
        recyclerView = view.findViewById(R.id.recycler_view);
        initRecycler();
    }

    /**
     * 有删除键----对应布局文件：activity_recycler2
     */
    @SuppressLint("NotifyDataSetChanged")
    private void initRecycler() {
        recyclerView.setLayoutManager(new LinearLayoutManager(requireActivity(), LinearLayoutManager.VERTICAL, false));
        bookAdapter = new Book2Adapter(requireContext());
        bookAdapter.setBookList(bookLists);
        recyclerView.setAdapter(bookAdapter);
        bookAdapter.setOnItemClickListener((view1, position) -> {
            bookLists.remove(position);
            bookAdapter.notifyDataSetChanged();
        });

        //添加数据
        bookLists.clear();
        for (int i = 0; i < 20; i++) {
            Random random = new Random();
            int index = random.nextInt(books.length);
            bookLists.add(books[index]);
        }
    }


    @Override
    protected void initEvent() {

    }

    @Override
    public void onClick(View v) {

    }

}
