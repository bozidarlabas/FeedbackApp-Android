package com.example.kanta.feedbackapp.mvp.view;

import com.example.kanta.feedbackapp.mvp.models.ProjectModel;

import java.util.List;

/**
 * Created by kanta on 23.09.15..
 */
public interface HomeView {

    void showProjects(List<ProjectModel> projects);

   // void goToFeedback(ProjectItem project);

}
