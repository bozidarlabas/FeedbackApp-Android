package com.example.kanta.feedbackapp.mvp.interactors;

import com.example.kanta.feedbackapp.mvp.listeners.HomeListener;

/**
 * Created by kanta on 23.09.15..
 */
public interface HomeInteractor {
    void loadAllProjects(HomeListener listener, String username);

    void loadMyProjects(HomeListener listener);

    void storeNewProject(HomeListener listener, String projectId, String username);

    void getPublicProjects(String username);

    void getPrivateProjects(String username);

    void searchAllProjects(String username, String keyword);

    void searchMyProjects(String username, String keyword, String filter);

    void loadAllProjectsTest(HomeListener listener, String username);

}
