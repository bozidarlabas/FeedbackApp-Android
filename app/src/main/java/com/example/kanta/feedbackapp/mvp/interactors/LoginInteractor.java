package com.example.kanta.feedbackapp.mvp.interactors;

import com.example.kanta.feedbackapp.mvp.listeners.OnLoginFinishedListener;

/**
 * Created by kanta on 23.09.15..
 */
public interface LoginInteractor {
    void login(String username, String password, OnLoginFinishedListener listener);
}


