package com.example.kanta.feedbackapp.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bozidar.microdroid.base.MicroActivity;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.presenter.LoginPresenter;
import com.example.kanta.feedbackapp.mvp.presenter.RegistrationPresenter;
import com.example.kanta.feedbackapp.mvp.presenter.impl.RegistrationPresenterImpl;
import com.example.kanta.feedbackapp.mvp.view.RegistrationView;
import com.example.kanta.feedbackapp.utils.Constants;

import butterknife.InjectView;
import butterknife.OnClick;

public class RegistrationActivity extends MicroActivity implements RegistrationView{

    @InjectView(R.id.etemail)
    TextView etEmail;

    @InjectView(R.id.etusername)
    TextView etusername;

    @InjectView(R.id.etPassword)
    TextView etPassword;

    @InjectView(R.id.etcity)
    TextView etcity;

    @InjectView(R.id.etcountry)
    TextView etcountry;

    @InjectView(R.id.etgender)
    Spinner etgender;

    @InjectView(R.id.etbirthdate)
    Spinner etbirthdate;

    private RegistrationPresenter presenter;
    ArrayAdapter<String> yourAdapter;
    ArrayAdapter<String> yourAdapter2;

    //etgender.setAdapter(yourAdapter);
   // yourAdapter= new ArrayAdapter<String>(this, R.layout.spinner_item, value);



    @Override
    public int setupToolbar() {
        return 0;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_registration;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        presenter = new RegistrationPresenterImpl(this);

        String[] array1 =new String[] {"Gender", "Male", "Female"};
        String[] array2 = new String [] {"1983","1984", "1985", "1986", "1987", "1988", "1989", "1990", "1991", "1992"};
        yourAdapter= new ArrayAdapter<String>(this, R.layout.spinner_item, array1);
        yourAdapter.setDropDownViewResource(R.layout.spinnerbackground);
        etgender.setAdapter(yourAdapter);
        yourAdapter2= new ArrayAdapter<String>(this, R.layout.spinner_item, array2);
        yourAdapter2.setDropDownViewResource(R.layout.spinnerbackground);
        etbirthdate.setAdapter(yourAdapter2);
    }

    @OnClick(R.id.btnRegister)
    public void register(View v){



        String username = etusername.getText().toString();
        String email = etEmail.getText().toString();
        String password = etPassword.getText().toString();
        String city = etcity.getText().toString();
        String country = etcountry.getText().toString();
        String gender = etgender.getSelectedItem().toString();
        String gender1 = String.valueOf(gender.charAt(0)).toLowerCase();
        String birthDate = etbirthdate.getSelectedItem().toString();
        presenter.validateCredentials(username, password, email, city, country, gender1, birthDate);
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void setUsernameError() {
        etusername.setError("Username is empty");
    }

    @Override
    public void setPasswordError() {
        etPassword.setError("Password is empty");
    }

    @Override
    public void setEmailError() {
        etEmail.setError("Email is empty");
    }

    @Override
    public void setCityError() {
        etcity.setError("City is empty");
    }

    @Override
    public void setCountryError() {
        etcountry.setError("Country is empty");
    }

    @Override
    public void setGenderError() {

    }

    @Override
    public void setBirthDateError() {

    }

    @Override
    public void navigateToHome() {
        saveLoginToSharedPrefs();
        Intent i = new Intent(this, HomeScreen.class);
        startActivity(i);
    }

    @Override
    public void setUsernameExists() {
        Toast.makeText(this, "Username exists", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void setEmailExists() {
        Toast.makeText(this, "Email exists", Toast.LENGTH_SHORT).show();
    }

    public void saveLoginToSharedPrefs(){
        SharedPreferences.Editor editor =  getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE).edit();
        editor.putString(Constants.SUCCESS_LOGIN, etusername.getText().toString());
        editor.apply();
    }

}
