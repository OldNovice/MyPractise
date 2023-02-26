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

import java.util.ArrayList;
import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.MyViewHolder> {

    private Context context;
    private List<Book> bookList;

    public BookAdapter(List<Book> bookList) {
        this.bookList = bookList;
        notifyDataSetChanged();
    }

     static class MyViewHolder extends RecyclerView.ViewHolder {
        CardView cardView;
        ImageView bookName;
        TextView bookPages;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            cardView= (CardView) itemView;
            bookName = itemView.findViewById(R.id.item_bookName);
            bookPages=itemView.findViewById(R.id.item_bookPages);
        }
    }
    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (context == null) {
            context=parent.getContext();
        }
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_book, null);
        return new MyViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Book book=bookList.get(position);
        Glide.with(context).load(book.getName()).into(holder.bookName);
        holder.bookPages.setText(book.getPages());
    }

    @Override
    public int getItemCount() {
        return bookList.size();
    }


}
