package com.example.kanta.feedbackapp.mvp.presenter.impl;

import com.example.kanta.feedbackapp.mvp.interactors.HomeInteractor;
import com.example.kanta.feedbackapp.mvp.interactors.impl.HomeInteractorImpl;
import com.example.kanta.feedbackapp.mvp.listeners.HomeListener;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.mvp.presenter.HomePresenter;
import com.example.kanta.feedbackapp.mvp.views.HomeView;

import java.util.List;

/**
 * Created by kanta on 23.09.15..
 */
public class HomePresenterImpl implements HomePresenter, HomeListener {

    HomeView view;
    HomeInteractor interactor;

    public HomePresenterImpl(HomeView view){
        this.view = view;
        interactor = new HomeInteractorImpl();
    }

    @Override
    public void loadAllProjects(String username) {
        interactor.loadAllProjects(this, username);
    }

    @Override
    public void loadMyProjects() {
        interactor.loadMyProjects(this);
    }

    @Override
    public void addMyProjectOnServer(String projectId, String username) {
        interactor.storeNewProject(this, projectId, username);
    }

    @Override
    public void getPublicProjects(String username) {
        interactor.getPublicProjects(username);
    }

    @Override
    public void getPrivateProjects(String username) {
        interactor.getPrivateProjects(username);
    }

    @Override
    public void getSearchedAllProjects(String username, String keyword) {
        interactor.searchAllProjects(username, keyword);
    }

    @Override
    public void getSearchedMyProjects(String username, String keyword, String filter) {
        interactor.searchMyProjects(username, keyword, filter);
    }

    @Override
    public void loadAllProjectsTest(String username) {
        interactor.loadAllProjectsTest(this, username);
    }


    @Override
    public void onSuccess(List<ProjectModel> projectModels) {
        view.showProjects(projectModels);

    }

    @Override
    public void projectAdded() {
        view.addedProject();
    }


}
