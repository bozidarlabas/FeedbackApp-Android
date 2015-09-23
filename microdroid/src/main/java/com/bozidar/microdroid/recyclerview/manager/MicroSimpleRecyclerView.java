package com.bozidar.microdroid.recyclerview.manager;

import android.content.Context;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.bozidar.microdroid.R;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.api.MicroRecyclerAPI;
import com.bozidar.microdroid.recyclerview.model.MicroSimpleModel;

import java.util.List;

/**
 * Created by bozidar on 21.04.15..
 */
public class MicroSimpleRecyclerView implements MicroRecyclerAPI {

    private List<MicroSimpleModel> data;
    private RecyclerView recyclerView;
    private MicroRecyclerAdapter microRecyclerAdapter;
    private Context context;

    //TODO send parameters through constructor
    public MicroSimpleRecyclerView(RecyclerView recyclerView, List<MicroSimpleModel> data, Context context){
        this.data = data;
        this.context = context;
        this.recyclerView = recyclerView;
    }

    @Override
    public void createRecyclerView(){
        Log.d("Kreiraj", "kreiraj");
        microRecyclerAdapter = new MicroRecyclerAdapter();
        recyclerView.setAdapter(microRecyclerAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        //Check if user has implement onMicroItemClickListener in activity
        if(context instanceof MicroRecyclerAdapter.onMicroItemCLickListener)
            microRecyclerAdapter.setOnMicroCLickListener((MicroRecyclerAdapter.onMicroItemCLickListener) context);
    }

}
