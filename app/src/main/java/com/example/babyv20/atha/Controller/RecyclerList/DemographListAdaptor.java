package com.example.babyv20.atha.Controller.RecyclerList;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.babyv20.atha.Controller.Controller_Interface.OnItemClickCallBack;
import com.example.babyv20.atha.Controller.RecyclerList.ListItem;
import com.example.babyv20.atha.Controller.RecyclerList.ListViewHolder;
import com.example.babyv20.atha.R;

import java.util.List;

public class DemographListAdaptor extends RecyclerView.Adapter<ListViewHolder>  {

    private List<ListItem> list;
    private LayoutInflater inflater;
    private Context context;
    private ListViewHolder holder;
    private OnItemClickCallBack callBack;

    public DemographListAdaptor(List<ListItem> list, Context context, OnItemClickCallBack callBack){
        this.list = list;
        inflater = LayoutInflater.from(context);
        this.context =context;
        this.callBack = callBack;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.parent_list_layout, parent, false);
        holder = new ListViewHolder(view, context);
        holder.setOnItemClickCall(callBack);
        return holder;
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {

        ListItem item = list.get(position);
        holder.getHeader().setText(item.getTitle());
//        holder.getIcon().setImageResource(item.getResID());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }


}
