package com.winfo.mypractise.recycler;


import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.winfo.mypractise.R;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListAdapter.MyViewHolder> {

    private final Context context;
    private List<String> list;

    public ListAdapter(Context context) {
        this.context = context;
        list = new ArrayList<>();
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setList(List<String> list) {
        this.list = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    @SuppressLint("InflateParams")
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View inflate = LayoutInflater.from(context).inflate(R.layout.item_list, null);
        return new MyViewHolder(inflate);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.item_tv.setText(position+1+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {

        private final TextView item_tv;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_tv = itemView.findViewById(R.id.item_text);
        }
    }
}
