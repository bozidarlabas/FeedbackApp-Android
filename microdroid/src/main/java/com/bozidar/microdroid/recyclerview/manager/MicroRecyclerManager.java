package com.bozidar.microdroid.recyclerview.manager;

import android.content.Context;
import android.support.v7.widget.RecyclerView;

import com.bozidar.microdroid.recyclerview.api.MicroRecyclerAPI;
import com.bozidar.microdroid.recyclerview.model.MicroSimpleModel;

import java.util.List;

/**
 * Builder pattern
 * This class represents manager for creating recycler view
 * Created by bozidar on 21.04.15..
 */

public class MicroRecyclerManager <E> {
    private final List<MicroSimpleModel> data;
    private final Context context;
    private final RecyclerView recyclerView;


    public static class Builder{
        //Required parameter
        private final Context context;

        //Other parameters
        private List<MicroSimpleModel> data;
        private RecyclerView recyclerView;


        public Builder(Context context){
            this.context = context;
        }

        public Builder data(List<MicroSimpleModel> data){
            this.data = data;
            return this;
        }

        public Builder recyclerView(RecyclerView recyclerView){
            this.recyclerView = recyclerView;
            return this;
        }

        public MicroRecyclerManager build(){
            return new MicroRecyclerManager(this);
        }
    }

    private MicroRecyclerManager(Builder builder){
        this.data = builder.data;
        this.context = builder.context;
        this.recyclerView = builder.recyclerView;
    }


    public MicroRecyclerAPI buildSimpleRecyclerView() {
        return new MicroSimpleRecyclerView(recyclerView, data, context);
    }

    //TODO return new MicroAdvancedRecyclerView
    public MicroRecyclerAPI buildAdvancedREcyclerView() {
        return new MicroDetailRecyclerView(recyclerView, data, context);
    }

}
