package com.example.kanta.feedbackapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.slidingtab.manager.MicroTabManager;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.fragments.HomeFragment;
import com.example.kanta.feedbackapp.mvp.listeners.InnerFragmentsListener;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.utils.Constants;
import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;

import butterknife.InjectView;
import butterknife.OnClick;

public class HomeScreen extends MicroActivity implements InnerFragmentsListener {

    @InjectView(R.id.nav_view)
    NavigationView navigationView;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.pagermy)
    ViewPager viewPager;

    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;

    ActionBarDrawerToggle drawerToggle;

    @InjectView(R.id.multiple_actions)
    FloatingActionsMenu fab;

    @InjectView(R.id.action_c)
    FloatingActionButton fabItem1;

    @InjectView(R.id.action_b)
    FloatingActionButton fabItem2;

    @InjectView(R.id.action_a)
    FloatingActionButton fabItem3;

    HomeFragment allProjects;
    HomeFragment myProjects;

    int position = 0;

    private MicroRecyclerAdapter adapter;

    @Override
    public int setupToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_home_screen;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        if (adapter == null)
            adapter = new MicroRecyclerAdapter();

        setupTabs();
        setupDrawerContent();
    }

    /**
     * Setup tabs
     */

    private void setupTabs() {
        MicroTabManager microTabManager = new MicroTabManager(getSupportFragmentManager(), viewPager, tabLayout);
        allProjects = HomeFragment.getInstance(Constants.ALL_PROJECTS);
        allProjects.setListener(this);
        myProjects = HomeFragment.getInstance(Constants.MY_PROJECTS);
        myProjects.setListener(this);
        microTabManager.addTab(allProjects);
        microTabManager.addTab(myProjects);
        microTabManager.init();

        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                position = tab.getPosition();
                if (position == 0 && fabItem1.getVisibility() == View.VISIBLE) {
                    fabItem1.setVisibility(View.GONE);
                    fabItem3.setVisibility(View.GONE);
                } else if (position == 1 && fabItem1.getVisibility() != View.VISIBLE) {
                    fabItem1.setVisibility(View.VISIBLE);
                    fabItem3.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (drawerToggle.onOptionsItemSelected(item))
            return true;
        return super.onOptionsItemSelected(item);
    }

    /**
     * Method for checking clicked items in Navigation Drawer
     */
    private void setupDrawerContent() {
        if (navigationView != null) {
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    if (menuItem.getItemId() == R.id.nav_home) {
                        checkMeOut();
                    }

                    if (menuItem.getItemId() == R.id.nav_home2) {
                        gotoInterestActivity();
                    }

                    drawerLayout.closeDrawers();
                    return true;
                }
            });

            drawerToggle = new ActionBarDrawerToggle(HomeScreen.this, drawerLayout, R.string.hello_world, R.string.hello_world);
            drawerLayout.setDrawerListener(drawerToggle);
        }
    }

    public void gotoInterestActivity(){
        startActivity(new Intent(this, InterestsActivity.class));
    }

    @Override
    public void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        drawerToggle.syncState();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        drawerToggle.onConfigurationChanged(newConfig);
    }

    public void checkMeOut() {
        //Toast.makeText(this,"dsadsadsda",Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(Constants.SUCCESS_LOGIN, "");
        editor.apply();
        Intent i = new Intent(HomeScreen.this, LoginActivity.class);
        startActivity(i);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_details, menu);

        //action_search
        final android.support.v7.widget.SearchView searchView = (android.support.v7.widget.SearchView) menu.findItem(R.id.action_search).getActionView();

        searchView.setOnQueryTextListener(new android.support.v7.widget.SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                //goToNextActivity(query);
                //searchView.clearFocus();
                if (position == 0)
                    allProjectsSearch(query);
                else if (position == 1)
                    myProjectsSearch(query);

                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private void allProjectsSearch(String keyword){
        Log.d("asdsad", keyword);
        allProjects.searchAllProjects(keyword);
    }

    private void myProjectsSearch(String keyword){
        Log.d("mojjjjj", "mojasd");
        myProjects.searchMyProjects(keyword);
    }

    @Override
    public void addProject(ProjectModel project) {
        myProjects.addItemToList(project);
    }

    @OnClick(R.id.action_c)
    public void filterPublicProjects(View v) {

        myProjects.filterPublicProjects();
    }

    @OnClick(R.id.action_a)
    public void filterPrivateProjects(View v) {
        myProjects.filterPrivateProjects();
    }

    @OnClick(R.id.action_b)
    public void filterAllProjects(View v) {
        if(position == 0)
            allProjects.allProjects();
        else{
            Log.d("aasdadasdgggg", "aassad");
            myProjects.filterAllProjects();
        }

    }

}
