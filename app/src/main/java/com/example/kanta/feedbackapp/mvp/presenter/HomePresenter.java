package com.example.kanta.feedbackapp.mvp.presenter;

/**
 * Created by kanta on 23.09.15..
 */
public interface HomePresenter {
    void loadAllProjects(String username);

    void loadMyProjects();

    void addMyProjectOnServer(String projectId, String username);

    void getPublicProjects(String username);

    void getPrivateProjects(String username);

    void getSearchedAllProjects(String username, String keyword);

    void getSearchedMyProjects(String username, String keyword, String filter);

    void loadAllProjectsTest(String username);
}
