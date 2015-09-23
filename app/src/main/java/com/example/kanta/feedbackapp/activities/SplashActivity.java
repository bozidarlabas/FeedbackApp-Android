package com.example.kanta.feedbackapp.activities;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.utils.Constants;

public class SplashActivity extends MainSplashActivity {

    private static final int TIME = 1850;

    @Override
    public Class getNextClassActivity() {
        if(isUserLogged())
            return HomeScreen.class;
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

    public boolean isUserLogged(){
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        String restoredText = prefs.getString(Constants.SUCCESS_LOGIN, "");
        return !restoredText.equals("");
    }
}