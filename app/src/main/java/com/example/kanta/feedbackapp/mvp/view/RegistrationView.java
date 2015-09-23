package com.example.kanta.feedbackapp.mvp.view;

/**
 * Created by kanta on 23.09.15..
 */
public interface RegistrationView {
    public void showProgress();

    public void hideProgress();

    public void setUsernameError();

    public void setPasswordError();

    public void setEmailError();

    public void setCityError();

    public void setCountryError();

    public void setGenderError();

    public void setBirthDateError();

    public void navigateToHome();
}
