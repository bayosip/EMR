package com.example.babyv20.atha.Controller.RecyclerList;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.babyv20.atha.Controller.Activities.Forms;
import com.example.babyv20.atha.Controller.Controller_Interface.OnItemClickCallBack;
import com.example.babyv20.atha.R;

public class ListViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private TextView header;
    private ImageView icon;
    private View container;
    private Context context;
    private OnItemClickCallBack callBack;


    public ListViewHolder(View itemView, Context c) {

        super(itemView);
        widgetSetup(itemView);
        this.context = c;
    }

    void widgetSetup(View view){

        icon = (ImageView) view.findViewById(R.id.imageView);
        header = (TextView)view.findViewById(R.id.textViewDemographicHeader);
        container = view.findViewById(R.id.headerLayout);
        container.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.imageView:
                callBack.onItemClick(getAdapterPosition());
                break;
            case R.id.headerLayout:
                callBack.onItemClick(getAdapterPosition());
                break;
            case R.id.textViewDemographicHeader:
                callBack.onItemClick(getAdapterPosition());
                break;
        }
    }

    public void setOnItemClickCall (final OnItemClickCallBack callBack ){
        this.callBack = callBack;
    }

    public TextView getHeader() {
        return header;
    }

    public ImageView getIcon() {
        return icon;
    }

    public View getContainer() {
        return container;
    }
}
