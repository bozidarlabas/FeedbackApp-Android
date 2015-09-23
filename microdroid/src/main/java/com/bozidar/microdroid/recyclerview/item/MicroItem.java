package com.bozidar.microdroid.recyclerview.item;

import android.view.View;

import java.util.HashMap;

/**
 * Created by Bozidar on 12.7.2015..
 */
public interface MicroItem {

    public int getLayoutResource();

    public void displayItem(View view, int position, HashMap<String, Integer> color);

    public void setItemView(View view);


}
