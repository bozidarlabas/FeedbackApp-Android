package com.example.kanta.feedbackapp.fragments;


import android.os.Bundle;
import android.view.View;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;
import com.example.kanta.feedbackapp.R;

public class HomeFragment extends MicroTabFrag implements MicroRecyclerAdapter.onMicroItemCLickListener{


    private MicroRecyclerAdapter adapter;

    @Override
    public void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            //Do something with bundle
        }
        if (adapter == null)
            adapter = new MicroRecyclerAdapter();
    }


    public static HomeFragment getInstance(String tabTitle) {
        HomeFragment fragment = new HomeFragment();
        /*Bundle args = new Bundle();
        args.putString("title", tabTitle);
        fragment.setArguments(args);*/
        return fragment;
    }

    @Override
    public String setTabTitle() {
        return "naslov";
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_home;
    }



    @Override
    public void microItemClicked(View view, MicroItem item) {
    }
}
