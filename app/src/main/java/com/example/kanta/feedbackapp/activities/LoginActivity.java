package com.example.kanta.feedbackapp.activities;

import android.content.Intent;
import android.util.Log;
import android.view.View;

import com.bozidar.microdroid.base.MicroActivity;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.view.LoginView;

import butterknife.OnClick;

public class LoginActivity extends MicroActivity implements LoginView {



    //Implemented methods

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {

    }

    @Override
    public void setPasswordError() {

    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {

    }

    @OnClick(R.id.btnRegistration)
    public void goToRegistration(View v){
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
    }
}
