package com.example.kanta.feedbackapp.mvp.presenter.impl;

import com.example.kanta.feedbackapp.mvp.interactor.LoginInteractor;
import com.example.kanta.feedbackapp.mvp.interactor.RegisterInteractor;
import com.example.kanta.feedbackapp.mvp.interactor.impl.LoginInteractorImpl;
import com.example.kanta.feedbackapp.mvp.interactor.impl.RegistrationInteractorImpl;
import com.example.kanta.feedbackapp.mvp.listener.OnLoginFinishedListener;
import com.example.kanta.feedbackapp.mvp.presenter.LoginPresenter;
import com.example.kanta.feedbackapp.mvp.view.LoginView;
import com.example.kanta.feedbackapp.utils.Constants;

/**
 * Created by kanta on 23.09.15..
 */
public class LoginPresenterImpl implements LoginPresenter, OnLoginFinishedListener{

    LoginView view;
    LoginInteractor interactor;

    public LoginPresenterImpl(LoginView view){
        this.view = view;
        this.interactor = new LoginInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password) {
        interactor.login(username, password, this);
    }

    @Override
    public void onUsernameError() {
        view.setUsernameError();
    }

    @Override
    public void onPasswordError() {
        view.setPasswordError();
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
        //prijelaz u main screen
    }
}
