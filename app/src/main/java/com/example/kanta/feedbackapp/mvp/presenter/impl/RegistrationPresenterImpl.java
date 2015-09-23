package com.example.kanta.feedbackapp.mvp.presenter.impl;

import com.example.kanta.feedbackapp.mvp.interactor.RegisterInteractor;
import com.example.kanta.feedbackapp.mvp.interactor.impl.RegistrationInteractorImpl;
import com.example.kanta.feedbackapp.mvp.listener.OnRegistrationFinishedListener;
import com.example.kanta.feedbackapp.mvp.presenter.RegistrationPresenter;
import com.example.kanta.feedbackapp.mvp.view.RegistrationView;
import com.example.kanta.feedbackapp.utils.Constants;

/**
 * Created by kanta on 23.09.15..
 */
public class RegistrationPresenterImpl implements RegistrationPresenter, OnRegistrationFinishedListener {

    RegistrationView view;
    RegisterInteractor interactor;

    public RegistrationPresenterImpl(RegistrationView view){
        this.view = view;
        interactor = new RegistrationInteractorImpl();
    }

    @Override
    public void validateCredentials(String username, String password, String email, String city, String country, String gender, String birthdate) {
        //view.showProgress();
        interactor.login(username, password, email, city, country, gender, birthdate, this);
    }

    @Override public void onUsernameError() {
        view.setUsernameError();
        view.hideProgress();
    }

    @Override public void onPasswordError() {
        view.setPasswordError();
        view.hideProgress();
    }

    @Override
    public void onEmailError() {
        view.setEmailError();
        view.hideProgress();
    }

    @Override
    public void onCityError() {
        view.setCityError();
        view.hideProgress();
    }

    @Override
    public void onCountryError() {
        view.setCountryError();
        view.hideProgress();
    }

    @Override
    public void onGenderError() {
        view.setGenderError();
        view.hideProgress();
    }

    @Override
    public void onBirthDateError() {
        view.setBirthDateError();
        view.hideProgress();
    }

    @Override public void onSuccess(String serverResponse) {
        switch (serverResponse){
            case Constants.FAILURE_EMAIL:
                view.setEmailExists();
                break;
            case Constants.FAILURE_USERNAME:
                view.setUsernameExists();
                break;
            case Constants.FAILURE_EMAIL_USERNAME:
                break;
            case Constants.SUCCESS_REGISTRATION:
                view.navigateToHome();
                break;
        }
    }
}
