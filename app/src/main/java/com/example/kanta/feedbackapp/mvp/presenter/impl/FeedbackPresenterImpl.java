package com.example.kanta.feedbackapp.mvp.presenter.impl;

import com.example.kanta.feedbackapp.mvp.presenter.FeedbackPresenter;
import com.example.kanta.feedbackapp.mvp.view.FeedbackView;
import com.example.kanta.feedbackapp.service.RequestApi;
import com.example.kanta.feedbackapp.utils.Constants;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kanta on 24.09.15..
 */

public class FeedbackPresenterImpl implements FeedbackPresenter, Callback<String> {

    FeedbackView view;

    public FeedbackPresenterImpl(FeedbackView view){
        this.view = view;
    }

    @Override
    public void success(String s, Response response) {
        view.onSuccess(s);
    }

    @Override
    public void failure(RetrofitError error) {
        view.onFailure(error.toString());
    }

    @Override
    public void sendFeedback(String feedback, String rating, String lat, String lon, String username, String project_id) {
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.sendFeedback("feedback", feedback, rating, lat, lon, username, project_id, this);
    }
}
