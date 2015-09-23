package com.example.kanta.feedbackapp.mvp.interactor.impl;

import android.text.TextUtils;
import android.util.Log;

import com.example.kanta.feedbackapp.mvp.interactor.LoginInteractor;
import com.example.kanta.feedbackapp.mvp.listener.OnLoginFinishedListener;
import com.example.kanta.feedbackapp.service.RequestApi;
import com.example.kanta.feedbackapp.utils.Constants;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kanta on 23.09.15..
 */
public class LoginInteractorImpl implements LoginInteractor, Callback<String> {

    private OnLoginFinishedListener listener;

    @Override
    public void login(String username, String password, OnLoginFinishedListener listener) {
        boolean error = false;
        this.listener = listener;
        if (TextUtils.isEmpty(username)){
            listener.onUsernameError();
            error = true;
        }
        if (TextUtils.isEmpty(password)){
            listener.onPasswordError();
            error = true;
        }

        if (!error){
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
            RequestApi api = restAdapter.create(RequestApi.class);
            api.sendLoginRequest(password, username, this);
        }
    }

    @Override
    public void success(String s, Response response) {
        listener.onSuccess(s);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("failure", error.toString());

    }
}
