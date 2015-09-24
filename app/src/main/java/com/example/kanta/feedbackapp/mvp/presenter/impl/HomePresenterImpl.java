package com.example.kanta.feedbackapp.mvp.presenter.impl;

import android.util.Log;

import com.example.kanta.feedbackapp.mvp.interactor.HomeInteractor;
import com.example.kanta.feedbackapp.mvp.interactor.impl.HomeInteractorImpl;
import com.example.kanta.feedbackapp.mvp.listener.HomeListener;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.mvp.presenter.HomePresenter;
import com.example.kanta.feedbackapp.mvp.view.HomeView;

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
    public void onSuccess(List<ProjectModel> projectModels) {
        view.showProjects(projectModels);

    }
}
