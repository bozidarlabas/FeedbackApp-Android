package com.example.kanta.feedbackapp.mvp.view;

/**
 * Created by kanta on 24.09.15..
 */
public interface FeedbackView {

    public void onSuccess(String response);

    public void onFailure(String response);

}
