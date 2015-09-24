package com.example.kanta.feedbackapp.mvp.models.item;

import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;

import java.util.HashMap;

import butterknife.ButterKnife;
import butterknife.InjectView;

/**
 * Created by kanta on 23.09.15..
 */
public class ProjectItem implements MicroItem {

    @InjectView(R.id.tvListName)
    TextView tvProjectName;

    @InjectView(R.id.ratingBar)
    RatingBar ratingBar;

    private ProjectModel project;

    public ProjectItem(ProjectModel project){
        this.project = project;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.project_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        this.tvProjectName.setText(project.getName());
        this.ratingBar.setRating(Float.parseFloat(project.getRating()));
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.inject(this, view);
    }

    public ProjectModel getProject() {
        return project;
    }
}
