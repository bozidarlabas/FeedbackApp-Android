package com.example.kanta.feedbackapp.mvp.presenter.impl;

import android.util.Log;

import com.example.kanta.feedbackapp.mvp.presenter.FeedbackPresenter;
import com.example.kanta.feedbackapp.mvp.view.FeedbackView;
import com.example.kanta.feedbackapp.service.RequestApi;
import com.example.kanta.feedbackapp.utils.Constants;

import java.io.File;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;
import retrofit.mime.TypedFile;

/**
 * Created by kanta on 24.09.15..
 */

public class FeedbackPresenterImpl implements FeedbackPresenter, Callback<String> {

    FeedbackView view;
    RequestApi api;

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
    public void sendFeedback(String feedback, String rating, String lat, String lon, String username, String multimediaUri, String project_id) {
        Log.d("urriii", multimediaUri);
        TypedFile typedFile = new TypedFile("multipart/form-data", new File(multimediaUri));
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).setLogLevel(RestAdapter.LogLevel.FULL).build();
        api = restAdapter.create(RequestApi.class);
        api.sendFeedback("feedback", feedback, rating, lat, lon, username, project_id, typedFile, this);
    }
}
