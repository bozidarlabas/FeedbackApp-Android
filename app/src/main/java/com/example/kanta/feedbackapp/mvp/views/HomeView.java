package com.example.kanta.feedbackapp.mvp.views;

import com.example.kanta.feedbackapp.mvp.models.ProjectModel;

import java.util.List;

/**
 * Created by kanta on 23.09.15..
 */
public interface HomeView {

    void showProjects(List<ProjectModel> projects);

    void addedProject();

   // void goToFeedback(ProjectItem project);

}
