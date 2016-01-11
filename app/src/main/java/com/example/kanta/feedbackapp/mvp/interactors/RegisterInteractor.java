package com.example.kanta.feedbackapp.mvp.interactors;

import com.example.kanta.feedbackapp.mvp.listeners.OnRegistrationFinishedListener;

/**
 * Created by kanta on 23.09.15..
 */
public interface RegisterInteractor {
    public void login(String username, String password, String email, String city, String country, String gender, String birthDate, OnRegistrationFinishedListener listener);
}
