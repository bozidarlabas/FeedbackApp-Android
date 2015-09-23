package com.example.kanta.feedbackapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bozidar.microdroid.base.MicroActivity;
import com.example.kanta.feedbackapp.R;

public class HomeScreen extends MicroActivity {


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

    }
}
