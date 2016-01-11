package com.example.kanta.feedbackapp.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by kanta on 25.09.15..
 */
public class InterestModel {

    public static final String ID = "id";
    public static final String NAME = "name";

    @SerializedName(ID)
    private int id;

    @SerializedName(NAME)
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
