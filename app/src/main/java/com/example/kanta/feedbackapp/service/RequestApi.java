package com.example.kanta.feedbackapp.service;

import com.example.kanta.feedbackapp.mvp.models.ProjectModel;

import java.util.List;

import retrofit.Callback;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.Multipart;
import retrofit.http.POST;
import retrofit.mime.TypedFile;

/**
 * Created by kanta on 23.09.15..
 */
public interface RequestApi {

    @FormUrlEncoded
    @POST("/WebDiP/2013_projekti/WebDiP2013_038/registration.php")
    public void sendRegistrationRequest(
            @Field("email") String email,
            @Field("username") String username,
            @Field("password") String password,
            @Field("city") String city,
            @Field("country") String country,
            @Field("gender") String gender,
            @Field("birthDate") String birthDate,
            @Field("registration") String registration,
            Callback<String> response);

    @FormUrlEncoded
    @POST("/WebDiP/2013_projekti/WebDiP2013_038/login.php")
    public void sendLoginRequest(
            @Field("password") String password,
            @Field("username") String username,
            Callback<String> response);

    @FormUrlEncoded
    @POST("/WebDiP/2013_projekti/WebDiP2013_038/projects.php")
    public void sendFeedback(
            @Field("sendfeedback") String sendFeedbak,
            @Field("feedback") String feedback,
            @Field("rating") String rating,
            @Field("lat") String latitude,
            @Field("long") String longitude,
            @Field("username") String username,
            @Field("project_id") String projectId,
           // @Field("attachment") TypedFile attachment,
            Callback<String> response);

    @FormUrlEncoded
    @POST("/WebDiP/2013_projekti/WebDiP2013_038/projects.php")
    public void fetchAllProjects(
            @Field("allprojects") String allProjects,
            @Field("username") String username,
            Callback<List<ProjectModel>> projects);

    @FormUrlEncoded
    @POST("/WebDiP/2013_projekti/WebDiP2013_038/projects.php")
    public void fetchMyrojects(
            @Field("myprojects") String allProjects,
            @Field("username") String username,
            Callback<List<ProjectModel>> projects);
}
