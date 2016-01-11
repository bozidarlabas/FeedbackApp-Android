package com.example.kanta.feedbackapp.mvp.interactors.impl;

import android.util.Log;

import com.example.kanta.feedbackapp.mvp.interactors.HomeInteractor;
import com.example.kanta.feedbackapp.mvp.listeners.HomeListener;
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
        api.fetchAllProjects("allprojects", username, this);
    }

    @Override
    public void loadMyProjects(HomeListener listener) {
        this.listener = listener;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.fetchMyrojects("myprojects", "jtrupina", this);
    }

    @Override
    public void storeNewProject(final HomeListener listener, String projectId, String username) {
        Log.d("saljem", username);
        Log.d("saljem", projectId);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.addNewProject("addproject", username, projectId, new Callback<String>() {
            @Override
            public void success(String s, Response response) {
                listener.projectAdded();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.d("neuspjeh", error.toString());
            }
        });
    }

    @Override
    public void getPublicProjects(String username) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
       api.getPublicProjects("publicprojects", username, this);
    }

    @Override
    public void getPrivateProjects(String username) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.getPrivateProjects("privateProjects", username, this);

    }

    @Override
    public void searchAllProjects(String username, String keyword) {
        Log.d("asdasdasd", username);
        Log.d("asdasdasd", keyword);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.getSearchAllProjects("searchallprojects", username, keyword, this);
    }

    @Override
    public void searchMyProjects(String username, String keyword, String filter) {
        Log.d("sasdasdasd", username);
        Log.d("sasdasdasd", keyword);
        Log.d("sasdasdasd", filter);
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.getSearchMyProjects(username, filter, keyword, this);
    }

    @Override
    public void loadAllProjectsTest(HomeListener listener, String username) {
        this.listener = listener;

        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.fetchAllProjectsTest("allprojectstest", username, this);
    }


    @Override
    public void success(List<ProjectModel> projectModels, Response response) {
        Log.d("poslano", "poslano");
        listener.onSuccess(projectModels);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("failure", error.toString());
    }
}
