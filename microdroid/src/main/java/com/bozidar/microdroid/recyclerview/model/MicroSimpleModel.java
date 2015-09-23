package com.bozidar.microdroid.recyclerview.model;

/**
 * Created by bozidar on 21.04.15..
 */
public abstract class MicroSimpleModel {


    private String text;

    private int imageResource;

    public MicroSimpleModel(){}

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = setTitle();
    }

    public int getImageResource() {
        return imageResource;
    }

    public void setImageResource(int imageResource) {
        this.imageResource = setImgResource();
    }



    public abstract String setTitle();

    public abstract int setImgResource();


}
