package com.bozidar.microdroid.recyclerview.holder;

import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bozidar.microdroid.R;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;

/**
 * Created by bozidar on 20.04.15..
 */

public class MicroViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

    private MicroRecyclerAdapter.onMicroItemCLickListener listener;
    private MicroItem item;
    private View view;



    public MicroViewHolder(View view, MicroRecyclerAdapter.onMicroItemCLickListener listener, MicroItem item) {
        super(view);
        item.setItemView(view);
        this.listener = listener;
        this.item = item;
        this.view = view;

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if(listener != null)
            listener.microItemClicked(view, item,getLayoutPosition());
            Log.i("ADAPATER POUVIJA", getLayoutPosition()+"");

    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public MicroItem getItem() {
        return item;
    }

    public void setItem(MicroItem item) {
        this.item = item;
    }
}