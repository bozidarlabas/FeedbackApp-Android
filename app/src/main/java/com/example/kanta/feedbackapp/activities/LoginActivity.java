package com.example.kanta.feedbackapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.bozidar.microdroid.base.MicroActivity;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.presenter.LoginPresenter;
import com.example.kanta.feedbackapp.mvp.presenter.impl.LoginPresenterImpl;
import com.example.kanta.feedbackapp.mvp.view.LoginView;
import com.example.kanta.feedbackapp.utils.Constants;

import butterknife.InjectView;
import butterknife.OnClick;

public class LoginActivity extends MicroActivity implements LoginView {

    @InjectView(R.id.etUsername)
    EditText etUsername;

    @InjectView(R.id.etPassword)
    EditText etPassword;

    LoginPresenter presenter;

    //Implemented methods

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {
        etUsername.setError("Username is empty");
    }

    @Override
    public void setPasswordError() {
        etPassword.setError("Password is empty");
    }

    @Override
    public void showErrorDialog() {

    }

    @Override
    public void navigateToHome() {
        saveLoginToSharedPrefs();
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
    }

    @Override
    public void setWrongAuthentication() {
        Toast.makeText(this, "Login failed, wrong username or password", Toast.LENGTH_SHORT).show();
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
        presenter = new LoginPresenterImpl(this);
    }

    @OnClick(R.id.btnLogin)
    public void login(){
        String username = etUsername.getText().toString();
        String password = etPassword.getText().toString();
        presenter.validateCredentials(username, password);
    }

    @OnClick(R.id.btnRegistration)
    public void goToRegistration(View v){
        Intent i = new Intent(this, RegistrationActivity.class);
        startActivity(i);
        overridePendingTransition(R.anim.anim_to_right, R.anim.anim_to_left);
    }


    public void saveLoginToSharedPrefs(){
        SharedPreferences.Editor editor =  getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(Constants.SUCCESS_LOGIN, etUsername.getText().toString());
        editor.apply();
    }
}
