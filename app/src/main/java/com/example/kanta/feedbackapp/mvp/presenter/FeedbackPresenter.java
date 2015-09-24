package com.example.kanta.feedbackapp.mvp.presenter;

/**
 * Created by kanta on 24.09.15..
 */
public interface FeedbackPresenter {
    public void sendFeedback(String feedback, String rating, String lat, String lon, String username, String multimediaUri, String project_id);
}
