package com.example.kanta.feedbackapp.mvp.models;

import com.google.gson.annotations.SerializedName;

/**
 * Created by boogiepop on 23.09.15..
 */
public class ProjectModel {

    public static final String ID = "id";
    public static final String NAME = "name";
    public static final String RATING = "rating";

    @SerializedName(ID)
    private int id;

    @SerializedName(NAME)
    private String name;

    @SerializedName(RATING)
    private String rating;




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

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
