package com.example.kanta.feedbackapp.mvp.interactor;

import com.example.kanta.feedbackapp.mvp.listener.OnLoginFinishedListener;
import com.example.kanta.feedbackapp.mvp.listener.OnRegistrationFinishedListener;

/**
 * Created by kanta on 23.09.15..
 */
public interface LoginInteractor {
    public void login(String username, String password, OnLoginFinishedListener listener);
}
