package com.example.kanta.feedbackapp.mvp.interactor.impl;

import android.util.Log;

import com.example.kanta.feedbackapp.mvp.interactor.HomeInteractor;
import com.example.kanta.feedbackapp.mvp.listener.HomeListener;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.service.RequestApi;
import com.example.kanta.feedbackapp.utils.Constants;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kanta on 23.09.15..
 */
public class HomeInteractorImpl implements HomeInteractor, Callback<List<ProjectModel>> {

    HomeListener listener;


    @Override
    public void loadAllProjects(HomeListener listener, String username) {
        this.listener = listener;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.fetchAllProjects("allprojects",username, this);
    }

    @Override
    public void loadMyProjects(HomeListener listener) {
        this.listener = listener;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.fetchMyrojects("myprojects", "jtrupina", this);
    }

    @Override
    public void success(List<ProjectModel> projectModels, Response response) {
        Log.d("uspjeh", projectModels.get(0).getName());
        listener.onSuccess(projectModels);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("failure", error.toString());
    }
}
