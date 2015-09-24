package com.example.kanta.feedbackapp.mvp.presenter.impl;

import com.example.kanta.feedbackapp.mvp.presenter.FeedbackPresenter;

import retrofit.Callback;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kanta on 24.09.15..
 */
public class FeedbackPresenterImpl implements FeedbackPresenter, Callback<String> {



    @Override
    public void success(String s, Response response) {

    }

    @Override
    public void failure(RetrofitError error) {

    }

    @Override
    public void sendFeedback(String feedback, String rating, String lat, String lon, String username, String project_id) {

    }
}
