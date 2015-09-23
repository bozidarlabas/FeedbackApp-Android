package com.example.kanta.feedbackapp.mvp.presenter;

/**
 * Created by kanta on 23.09.15..
 */
public interface RegistrationPresenter {

    public void validateCredentials(String username, String password, String email, String city, String country, String gender, String birthdate);
}
