package com.example.kanta.feedbackapp.activities;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kanta.feedbackapp.R;

public class SplashActivity extends MainSplashActivity {

    private static final int TIME = 1850;

    @Override
    public Class getNextClassActivity() {
        return LoginActivity.class;
    }

    @Override
    public int getSplashIntroTime() {
        return TIME;
    }

    @Override
    public int setLayoutResource() {
        return R.layout.activity_main_splash;
    }
}