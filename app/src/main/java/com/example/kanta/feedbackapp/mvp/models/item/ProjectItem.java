package com.example.kanta.feedbackapp.mvp.models.item;

import android.view.View;
import android.widget.ImageView;
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

    @InjectView(R.id.checkGroupPicture)
    ImageView imageView;

    private ProjectModel project;

    private boolean isMyProjectsFragment = false;

    public ProjectItem(ProjectModel project, boolean isMyProjectsFragment){
        this.project = project;
        this.isMyProjectsFragment = isMyProjectsFragment;
    }

    @Override
    public int getLayoutResource() {
        return R.layout.project_item;
    }

    @Override
    public void displayItem(View view, int position, HashMap<String, Integer> color) {
        this.tvProjectName.setText(project.getName());
        this.ratingBar.setRating(Float.parseFloat(project.getRating()));
        if(isMyProjectsFragment){
            imageView.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public void setItemView(View view) {
        ButterKnife.inject(this, view);
    }

    public ProjectModel getProject() {
        return project;
    }

    public ImageView getImageView() {
        return imageView;
    }

    public void setImageView(ImageView imageView) {
        this.imageView = imageView;
    }
}
