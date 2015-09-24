package com.example.kanta.feedbackapp.fragments;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.bozidar.microdroid.slidingtab.fragment.MicroTabFrag;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.activities.SendFeedback;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.mvp.models.item.ProjectItem;
import com.example.kanta.feedbackapp.mvp.presenter.HomePresenter;
import com.example.kanta.feedbackapp.mvp.presenter.impl.HomePresenterImpl;
import com.example.kanta.feedbackapp.mvp.view.HomeView;
import com.example.kanta.feedbackapp.utils.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import javax.sql.RowSetReader;

import butterknife.InjectView;

public class HomeFragment extends MicroTabFrag implements MicroRecyclerAdapter.onMicroItemCLickListener, HomeView{


    private MicroRecyclerAdapter adapter;
    HomePresenter presenter;

    @InjectView(R.id.lista)
    RecyclerView pokemonList;

    @InjectView(R.id.progress_wheel)
    ProgressWheel progressWheel;

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
        if(projectType != null){
            switch(projectType){
                case Constants.ALL_PROJECTS:
                    presenter.loadAllProjects(getUsername());
                    break;
                case Constants.MY_PROJECTS:
                    presenter.loadMyProjects();
                    break;
            }
        }


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
    public void microItemClicked(View view, MicroItem item) {
        Intent i = new Intent(getMicroActivity(), SendFeedback.class);
        String clickedProjectId = String.valueOf(((ProjectItem) item).getProject().getId());
        Log.d("klikk", String.valueOf(((ProjectItem) item).getProject().getId()));
        i.putExtra("project_id", clickedProjectId);
        getMicroActivity().startActivity(i);
    }

    @Override
    public void showProjects(List<ProjectModel> projects) {
        progressWheel.setVisibility(View.INVISIBLE);
        setRecyclerView(projects);
    }

    public void setRecyclerView(List<ProjectModel> projects) {
        this.pokemonList.setLayoutManager(new LinearLayoutManager(getMicroActivity()));
        if (this.adapter == null) adapter = new MicroRecyclerAdapter();

        this.pokemonList.setAdapter(adapter);

        adapter.setOnMicroCLickListener(this);


        for (ProjectModel project : projects) {
            ProjectItem projectItem = new ProjectItem(project);
            adapter.addItem(projectItem);
        }
    }
    public String getUsername(){
        SharedPreferences prefs = getMicroActivity().getSharedPreferences(Constants.PREFS_NAME, getMicroActivity().MODE_PRIVATE);
        return prefs.getString(Constants.SUCCESS_LOGIN, "");
    }
}
