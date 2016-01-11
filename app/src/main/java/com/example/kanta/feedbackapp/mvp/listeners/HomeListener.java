package com.example.kanta.feedbackapp.mvp.listeners;

import com.example.kanta.feedbackapp.mvp.models.ProjectModel;

import java.util.List;

/**
 * Created by kanta on 23.09.15..
 */
public interface HomeListener {
    void onSuccess(List<ProjectModel> projectModels);

    void projectAdded();
}
