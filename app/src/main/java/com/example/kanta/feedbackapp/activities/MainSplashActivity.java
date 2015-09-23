package com.example.kanta.feedbackapp.activities;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.example.kanta.feedbackapp.R;

public abstract class MainSplashActivity extends AppCompatActivity{

    public abstract Class getNextClassActivity();

    public abstract int getSplashIntroTime();

    public abstract int setLayoutResource();

    public void delay() {
        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                MainSplashActivity.this.startActivity(new Intent(MainSplashActivity.this.getBaseContext(), MainSplashActivity.this.getNextClassActivity()));
                MainSplashActivity.this.finish();
            }
        }, getSplashIntroTime());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(setLayoutResource());
        delay();
    }
}

