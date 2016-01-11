package com.example.kanta.feedbackapp.mvp.presenter.impl;

import com.example.kanta.feedbackapp.mvp.interactors.LoginInteractor;
import com.example.kanta.feedbackapp.mvp.interactors.impl.LoginInteractorImpl;
import com.example.kanta.feedbackapp.mvp.listeners.OnLoginFinishedListener;
import com.example.kanta.feedbackapp.mvp.presenter.LoginPresenter;
import com.example.kanta.feedbackapp.mvp.views.LoginView;
import com.example.kanta.feedbackapp.utils.Constants;

/**
 * Created by kanta on 23.09.15..
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener {

    LoginView view;
    LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view){
        this.view = view;
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void login(String username, String password) {
        interactor.login(username, password, this);
    }

    @Override
    public void onSuccess(String serverResponse) {
        switch (serverResponse){
            case Constants.SUCCESS_LOGIN:
                view.navigateToHome();
                break;
            case Constants.FAILURE_LOGIN:
                view.setWrongAuthentication();
                break;
        }
    }

    @Override
    public void onUsernameError() {
        view.setUsernameError();
    }

    @Override
    public void onPasswordError() {
        view.setPasswordError();
    }
}
