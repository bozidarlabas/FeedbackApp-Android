package com.bozidar.microdroid.base;

import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;

/**
 * Created by bozidar on 19.04.15..
 */

/**
 * A Fragment represents a behaviour or a portion of user interface in an Activity
 */
public abstract class MicroFragment extends Fragment {

    private Activity parentActivity;
    private Bundle savedInstanceState;

    /**
     * OnAttach is called after Fragment is associated with its Activity
     *
     * @param activity Reference to the Activity object which can be used as Context
     */
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.parentActivity = activity;
        Log.d("fragment", "onAttach");
    }

    /**
     * Don't use onCreate to access View hierarchy because Activity's onCreate
     * may/may not be finished. Create background threads here for long running operations
     *
     * @param savedInstanceState Data structure that stores key/value pairs before fragment was previously destroyed
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        Log.d("fragment", "onCreate");
    }

    /**
     * You are excpected to return a View Hierarchy for your fragment.
     *
     * @param inflater
     * @param container
     * @param savedInstanceState Data structure that stores key/value pairs before fragment was previously destroyed
     * @return
     */
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(setLayoutResource(), container, false);
        ButterKnife.inject(this, view);
        Log.d("fragment", "onCreateView");
        return view;
    }

    /**
     * Called immediately after onCreateView has returned view
     *
     * @param view               The View returned by OnCreateView
     * @param savedInstanceState if non-null, this fragment is being re-constructed from a previous saved state as given here
     */
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        init();
        Log.d("fragment", "onViewCreated");
    }

    /**
     * Called after Activity onCreate has completed execution
     * Use this method to access/modify UI elements
     *
     * @param savedInstanceState
     */
    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("fragment", "onActivityCreated");
    }

    /**
     * Called after Activity's onStart method
     */
    @Override
    public void onStart() {
        super.onStart();
        Log.d("fragment", "onStart");
    }

    /**
     * Called after Activity's onResume method
     */
    @Override
    public void onResume() {
        super.onResume();
        Log.d("fragment", "onResume");
    }

    /**
     * System calls this method as the first indication that the User is leaving the fragment
     */
    @Override
    public void onPause() {
        super.onPause();
        Log.d("fragment", "onPause");
    }

    /**
     * Use this to save information inside Bundle object
     *
     * @param outState Used to save data before fragments is stopped
     */
    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.d("fragment", "onSaveInstanceState");
    }

    /**
     * Called when the fragment is no longer started
     */
    @Override
    public void onStop() {
        super.onStop();
        Log.d("fragment", "onStop");
    }

    /**
     * Called after the Fragment view Hierarchy is no longer accessible
     */
    @Override
    public void onDestroyView() {
        super.onDestroyView();
        Log.d("fragment", "onDestroyView");
    }

    /**
     * Called after fragment is not used. It still exists as a Java object attached to the Activity.
     */
    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d("fragment", "onDestroy");
    }

    /**
     * Fragment is not tied to the Activity and does not have a View hierarchy
     */
    @Override
    public void onDetach() {
        super.onDetach();
        Log.d("fragment", "onDetach");
    }

    public MicroActivity getMicroActivity() {
        if (getActivity() != null)
            return (MicroActivity) getActivity();
        return (MicroActivity) parentActivity;
    }
    public Bundle getSavedInstanceState(){
        return this.savedInstanceState;
    }

    public abstract int setLayoutResource();

    public abstract void init();
}

//There is two ways of adding fragment to activity:
//  1. Through XML (using <fragment>)
//  2. Through Java
