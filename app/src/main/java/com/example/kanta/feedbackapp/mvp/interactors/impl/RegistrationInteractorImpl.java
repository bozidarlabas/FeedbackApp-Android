package com.example.kanta.feedbackapp.mvp.interactors.impl;

import android.text.TextUtils;
import android.util.Log;

import com.example.kanta.feedbackapp.mvp.interactors.RegisterInteractor;
import com.example.kanta.feedbackapp.mvp.listeners.OnRegistrationFinishedListener;
import com.example.kanta.feedbackapp.service.RequestApi;
import com.example.kanta.feedbackapp.utils.Constants;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by kanta on 23.09.15..
 */
public class RegistrationInteractorImpl implements RegisterInteractor, Callback<String> {

    OnRegistrationFinishedListener listener;

    @Override
    public void login(String username, String password, String email, String city, String country, String gender, String birthDate, OnRegistrationFinishedListener listener) {
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
        if (TextUtils.isEmpty(email)){
            listener.onEmailError();
            error = true;
        }
        if (TextUtils.isEmpty(city)){
            listener.onCityError();
            error = true;
        }
        if (TextUtils.isEmpty(country)){
            listener.onCountryError();
            error = true;
        }
        if (gender == "g"){
            listener.onGenderError();
            error = true;
        }
        if (birthDate.matches("Year")){
            listener.onBirthDateError();
            error = true;
        }
        if (!error){
            RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
            RequestApi api = restAdapter.create(RequestApi.class);
            api.sendRegistrationRequest(email, username, password, city, country, gender, birthDate, "registration", this);
        }
    }

    @Override
    public void success(String s, Response response) {
        Log.d("odgovor", s);
        listener.onSuccess(s);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.d("failure", error.toString());
    }
}
