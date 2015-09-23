package com.bozidar.microdroid.slidingtab.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by bozidar on 24.04.15..
 */
public class MicroPagerAdapter extends FragmentPagerAdapter {


    String[] tabs;

    private final List<MicroTabFrag> fragments;

    public MicroPagerAdapter(FragmentManager fm) {
        super(fm);
        this.fragments = new ArrayList<>();
        //tabs = context.getResources().getStringArray(R.array.tabs);
    }

    public <MicroTab extends MicroTabFrag> void addTab(MicroTab tab){
        this.fragments.add(tab);
        this.notifyDataSetChanged();
    }

    @Override
    public CharSequence getPageTitle(int position) {

        return fragments.get(position).setTabTitle();
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    /**
     * Returns the total number of items in the data set hold by the adapter
     *
     * @return The total number of items in this adapter
     */
    @Override
    public int getCount() {
        return fragments.size();
    }
}