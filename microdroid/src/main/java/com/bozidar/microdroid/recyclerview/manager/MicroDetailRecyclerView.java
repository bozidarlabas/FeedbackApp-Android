package com.bozidar.microdroid.recyclerview.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.bozidar.microdroid.R;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.api.MicroRecyclerAPI;
import com.bozidar.microdroid.recyclerview.model.MicroSimpleModel;

import java.util.List;

/**
 * Created by bozidar on 09.05.15..
 */
public class MicroDetailRecyclerView implements MicroRecyclerAPI {
    private int recyclerViewLayoutRes;
    private List<MicroSimpleModel> data;
    private RecyclerView recyclerView;
    private MicroRecyclerAdapter microRecyclerAdapter;
    private Context context;

    //TODO send parameters through constructor
    public MicroDetailRecyclerView(RecyclerView recyclerView, List<MicroSimpleModel> data, Context context){
        this.data = data;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public void createRecyclerView(){
        microRecyclerAdapter = new MicroRecyclerAdapter();
        recyclerView.setAdapter(microRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //check if user has implement onMicroItemClickListener in activity
        if(context instanceof MicroRecyclerAdapter.onMicroItemCLickListener)
            microRecyclerAdapter.setOnMicroCLickListener((MicroRecyclerAdapter.onMicroItemCLickListener) context);
    }
}
