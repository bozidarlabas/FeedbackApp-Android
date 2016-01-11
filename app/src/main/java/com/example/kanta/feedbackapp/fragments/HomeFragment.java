package com.example.kanta.feedbackapp.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.activities.SendFeedback;
import com.example.kanta.feedbackapp.mvp.listeners.InnerFragmentsListener;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.mvp.models.item.ProjectItem;
import com.example.kanta.feedbackapp.mvp.presenter.HomePresenter;
import com.example.kanta.feedbackapp.mvp.presenter.impl.HomePresenterImpl;
import com.example.kanta.feedbackapp.mvp.views.HomeView;
import com.example.kanta.feedbackapp.utils.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;
import java.util.List;
import butterknife.InjectView;

public class HomeFragment extends MicroTabFrag implements MicroRecyclerAdapter.onMicroItemCLickListener, HomeView {


    private MicroRecyclerAdapter adapter;
    HomePresenter presenter;

    @InjectView(R.id.lista)
    RecyclerView pokemonList;

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

    private InnerFragmentsListener listener;

    private String filteredProject = "allprojects";

    @Override
    public void init() {
        Bundle bundle = getArguments();
        if (bundle != null) {
            //Do something with bundle
        }
        if (adapter == null)
            adapter = new MicroRecyclerAdapter();
        presenter = new HomePresenterImpl(this);
    }

    public void setListener(InnerFragmentsListener listener) {
        this.listener = listener;
    }


    public static HomeFragment getInstance(String tabTitle) {

        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("title", tabTitle);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        String projectType = getArguments().getString("title");
        if (projectType != null) {
            switch (projectType) {
                case Constants.ALL_PROJECTS:
                    // fab.setVisibility(View.GONE);
                    allProjects();
                    break;
                case Constants.MY_PROJECTS:
                    presenter.loadMyProjects();
                    break;
            }
        }
    }

    public void allProjects(){
        presenter.loadAllProjects(getUsername());
    }

    @Override
    public String setTabTitle() {
        return getArguments().getString("title");
    }

    @Override
    public int setLayoutResource() {
        return R.layout.fragment_home;
    }

    @Override
    public void microItemClicked(View view, MicroItem item, int index) {

        String projectType = getArguments().getString("title");
        if (projectType != null) {
            switch (projectType) {
                case Constants.ALL_PROJECTS:

                    removeProjectFromPublicList(index);
                    ProjectModel model = ((ProjectItem) item).getProject();
                    listener.addProject(model);
                    presenter.addMyProjectOnServer(String.valueOf(model.getId()), getUsername());


                    //removeProjectFromPublicList(view);

                    break;
                case Constants.MY_PROJECTS:
                    goToFeedback(item);
                    break;
            }
        }
    }

    @Override
    public void showProjects(List<ProjectModel> projects) {
        adapter.removeItems();
        progressWheel.setVisibility(View.INVISIBLE);
        setRecyclerView(projects);
    }

    @Override
    public void addedProject() {
        Toast.makeText(getMicroActivity(), "Project added", Toast.LENGTH_SHORT).show();
    }

    public void setRecyclerView(List<ProjectModel> projects) {
        if (projects != null) {
            this.pokemonList.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
            if (this.adapter == null) adapter = new MicroRecyclerAdapter();

            this.pokemonList.setAdapter(adapter);

            adapter.setOnMicroCLickListener(this);


            for (ProjectModel project : projects) {
                ProjectItem projectItem = null;
                if (getArguments().getString("title").equals(Constants.MY_PROJECTS))
                    projectItem = new ProjectItem(project, true);
                else
                    projectItem = new ProjectItem(project, false);
                adapter.addItem(projectItem);
            }
        } else {
            adapter.removeItems();
            Toast.makeText(getMicroActivity(), "No results", Toast.LENGTH_SHORT).show();
        }

    }

    public String getUsername() {
        SharedPreferences prefs = getMicroActivity().getSharedPreferences(Constants.PREFS_NAME, getMicroActivity().MODE_PRIVATE);
        return prefs.getString(Constants.SUCCESS_LOGIN, "");
    }


    public void goToFeedback(MicroItem item) {

        Intent i = new Intent(getMicroActivity(), SendFeedback.class);
        String clickedProjectId = String.valueOf(((ProjectItem) item).getProject().getId());
        Log.d("klikk", String.valueOf(((ProjectItem) item).getProject().getId()));
        i.putExtra("project_id", clickedProjectId);
        getMicroActivity().startActivity(i);
    }

    public void removeProjectFromPublicList(int index) {
        adapter.removeItem(index);
    }

    public void addItemToList(ProjectModel model) {
        ProjectItem item = new ProjectItem(model, true);
        adapter.addItem(item);
    }


    public void searchAllProjects(String keyword) {
        presenter.getSearchedAllProjects(getUsername(), keyword);

    }

    public void searchMyProjects(String keyword) {
        presenter.getSearchedMyProjects(getUsername(), keyword, filteredProject);
    }

    public void  filterPublicProjects(){
        filteredProject = "myprojectspublic";
        presenter.getPublicProjects(getUsername());
    }

    public void  filterPrivateProjects(){
        filteredProject = "myprojectsprivate";
        presenter.getPrivateProjects(getUsername());
    }

    public void  filterAllProjects(){
        filteredProject = "allprojects";
        presenter.loadAllProjectsTest(getUsername());
    }






}
