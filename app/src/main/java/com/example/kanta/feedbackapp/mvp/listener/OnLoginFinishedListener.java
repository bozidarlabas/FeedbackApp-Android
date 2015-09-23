package com.example.kanta.feedbackapp.mvp.listener;

/**
 * Created by kanta on 23.09.15..
 */
public interface OnLoginFinishedListener {

    public void onUsernameError();

    public void onPasswordError();

    public void onSuccess(String serverResponse);
}
