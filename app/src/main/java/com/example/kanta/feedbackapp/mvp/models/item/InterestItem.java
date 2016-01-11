package com.example.kanta.feedbackapp.mvp.models.item;

import android.view.View;
import android.widget.TextView;

import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.models.InterestModel;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kanta on 25.09.15..
 */
public class InterestItem implements MicroItem {

    @InjectView(R.id.tvListName)
    TextView tvProjectName;

    InterestModel model;

    public InterestItem(InterestModel model){
        this.model = model;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.itemlayout2;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        this.tvProjectName.setText(model.getName());
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.inject(this, view);
    }
}
