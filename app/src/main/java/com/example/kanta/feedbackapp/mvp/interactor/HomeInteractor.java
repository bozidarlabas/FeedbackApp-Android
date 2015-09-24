package com.example.kanta.feedbackapp.mvp.interactor;

import com.example.kanta.feedbackapp.mvp.listener.HomeListener;

/**
 * Created by kanta on 23.09.15..
 */
public interface HomeInteractor {
    void loadAllProjects(HomeListener listener);

    void loadMyProjects(HomeListener listener);
}
