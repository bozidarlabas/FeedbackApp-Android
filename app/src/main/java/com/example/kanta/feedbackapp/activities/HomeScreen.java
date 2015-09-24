package com.example.kanta.feedbackapp.activities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.slidingtab.manager.MicroTabManager;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.fragments.HomeFragment;
import com.example.kanta.feedbackapp.mvp.models.ProjectModel;
import com.example.kanta.feedbackapp.mvp.models.item.ProjectItem;
import com.example.kanta.feedbackapp.mvp.view.HomeView;
import com.example.kanta.feedbackapp.utils.Constants;
import com.pnikosis.materialishprogress.ProgressWheel;

import java.util.List;

import butterknife.InjectView;

public class HomeScreen extends MicroActivity{

    @InjectView(R.id.nav_view)
    NavigationView navigationView;

    @InjectView(R.id.drawer_layout)
    DrawerLayout drawerLayout;

    @InjectView(R.id.pagermy)
    ViewPager viewPager;

    @InjectView(R.id.tabLayout)
    TabLayout tabLayout;


    private MicroRecyclerAdapter adapter;

    @Override
    public int setupToolbar() {
        return 0;
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
        microTabManager.addTab(HomeFragment.getInstance(Constants.ALL_PROJECTS));
        microTabManager.addTab(HomeFragment.getInstance(Constants.MY_PROJECTS));
        microTabManager.init();
    }

    /**
     * Method for checking clicked items in Navigation Drawer
     */
    private void setupDrawerContent() {
        if(navigationView != null){
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(MenuItem menuItem) {
                    menuItem.setChecked(true);
                    if(menuItem.getItemId() == R.id.nav_home){
                        checkMeOut();
                    }
                    drawerLayout.closeDrawers();
                    return true;
                }
            });
        }
    }

    public void checkMeOut(){
        //Toast.makeText(this,"dsadsadsda",Toast.LENGTH_SHORT).show();
        SharedPreferences.Editor editor =  getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(Constants.SUCCESS_LOGIN, "");
        editor.apply();
        Intent i = new Intent(HomeScreen.this, LoginActivity.class);
        startActivity(i);

    }




}
