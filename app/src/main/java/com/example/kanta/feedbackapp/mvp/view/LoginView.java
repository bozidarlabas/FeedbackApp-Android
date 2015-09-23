package com.example.kanta.feedbackapp.mvp.view;

/**
 * Created by kanta on 23.09.15..
 */
public interface LoginView {
    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();

    public void showErrorDialog();

    public void navigateToHome();

    public void setWrongAuthentication();
}