package com.example.kanta.feedbackapp.activities;

import android.content.SharedPreferences;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.bozidar.microdroid.base.MicroActivity;
import com.bozidar.microdroid.recyclerview.adapter.MicroRecyclerAdapter;
import com.bozidar.microdroid.recyclerview.item.MicroItem;
import com.example.kanta.feedbackapp.R;
import com.example.kanta.feedbackapp.mvp.models.InterestModel;
import com.example.kanta.feedbackapp.mvp.models.item.InterestItem;
import com.example.kanta.feedbackapp.service.RequestApi;
import com.example.kanta.feedbackapp.utils.Constants;

import java.util.List;

import butterknife.InjectView;
import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class InterestsActivity extends MicroActivity implements MicroRecyclerAdapter.onMicroItemCLickListener {

    private MicroRecyclerAdapter adapter;

    @InjectView(R.id.listainteres)
    RecyclerView pokemonList;

    @Override
    public int setupToolbar() {
        return R.id.toolbar;
    }

    @Override
    public int setupLayoutRes() {
        return R.layout.activity_interests;
    }

    @Override
    public int setupMenuRes() {
        return 0;
    }

    @Override
    public void init() {
        if (adapter == null)
            adapter = new MicroRecyclerAdapter();

        loadAllINterests(getUsername());
    }


    public void loadAllINterests(String username) {
        Log.d("saljem", "saljem");
        RestAdapter restAdapter = new RestAdapter.Builder().setEndpoint(Constants.SERVER_ENDPOINT).build();
        RequestApi api = restAdapter.create(RequestApi.class);
        api.fetchAllInterests("interests", username, new Callback<List<InterestModel>>() {
            @Override
            public void success(List<InterestModel> interestModels, Response response) {
                setRecyclerView(interestModels);
            }

            @Override
            public void failure(RetrofitError error) {

            }
        });
    }

    public String getUsername() {
        SharedPreferences prefs = getSharedPreferences(Constants.PREFS_NAME, MODE_PRIVATE);
        return prefs.getString(Constants.SUCCESS_LOGIN, "");
    }

    public void setRecyclerView(List<InterestModel> interests) {
        if (interests != null) {
            this.pokemonList.setLayoutManager(new LinearLayoutManager(this));
            if (this.adapter == null) adapter = new MicroRecyclerAdapter();

            this.pokemonList.setAdapter(adapter);

            adapter.setOnMicroCLickListener(this);


            for (InterestModel interest : interests) {
                InterestItem interestItem  = new InterestItem(interest);
                adapter.addItem(interestItem);

            }
        }else{
            Toast.makeText(this, "No data", Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void microItemClicked(View view, MicroItem item, int index) {

    }
}




