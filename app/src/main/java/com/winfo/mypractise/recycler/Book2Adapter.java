package com.winfo.mypractise.recycler;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.winfo.mypractise.R;
import com.winfo.mypractise.javabean.Book;

import java.util.List;

public class Book2Adapter extends RecyclerView.Adapter<Book2Adapter.MyViewHolder> {

    private Context context;
    private List<Book> bookList;
    private OnItemClickListener mOnItemClickListener;

    public Book2Adapter(Context context) {
        this.context = context;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

    static class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView bookName;
        TextView bookPages,delete;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            bookName = itemView.findViewById(R.id.item_bookName);
            bookPages = itemView.findViewById(R.id.item_bookPages);
            delete=itemView.findViewById(R.id.delete);
        }
    }

    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context = parent.getContext();
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_book2, null);
        return new MyViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book = bookList.get(position);
        Glide.with(context).load(book.getName()).into(holder.bookName);
        holder.bookPages.setText(book.getPages());
        holder.delete.setOnClickListener(v -> {
            if (mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        mOnItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(View view, int position);

    }
}