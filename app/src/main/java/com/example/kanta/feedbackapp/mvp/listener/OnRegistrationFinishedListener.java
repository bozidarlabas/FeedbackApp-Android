package com.example.kanta.feedbackapp.mvp.listener;

/**
 * Created by kanta on 23.09.15..
 */
public interface OnRegistrationFinishedListener {

        public void onUsernameError();

        public void onPasswordError();

        public void onEmailError();

        public void onCityError();

        public void onCountryError();

        public void onGenderError();

        public void onBirthDateError();


        public void onSuccess(String serverResponse);

}
