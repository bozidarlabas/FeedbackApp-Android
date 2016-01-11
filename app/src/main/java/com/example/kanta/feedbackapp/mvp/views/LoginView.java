package com.example.kanta.feedbackapp.mvp.views;

/**
 * Created by kanta on 23.09.15..
 */
public interface LoginView {
    void setUsernameError();

    void setPasswordError();

    void navigateToHome();

    void setWrongAuthentication();
}