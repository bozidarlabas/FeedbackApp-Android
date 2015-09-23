package com.bozidar.microdroid.base;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;

import butterknife.ButterKnife;


public abstract class MicroActivity extends AppCompatActivity {

    private int layoutResource;
    private Fragment currentFragment;
    private int fragmentContainer;
    protected Bundle savedInstanceState;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.savedInstanceState = savedInstanceState;
        this.layoutResource = setupLayoutRes();
        setContentView(this.layoutResource);
        setToolbar();
        ButterKnife.inject(this);
        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if(setupMenuRes() != 0)
            getMenuInflater().inflate(setupMenuRes(), menu);
        return true;
    }

    public void setFragment(int container, Fragment fragment) {
        if (this.savedInstanceState == null) {
            this.currentFragment = fragment;
            this.fragmentContainer = container;
            getSupportFragmentManager().beginTransaction().replace(container, fragment).commit();
        }
    }

    /**
     * Set a Toolbar act as the ActionBar for this Activity window
     */
    public void setToolbar() {
        if (setupToolbar() != 0) {
            Toolbar toolbar = (Toolbar) findViewById(setupToolbar());
            setSupportActionBar(toolbar);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
            this.toolbar = toolbar;
        }
    }


    /**
     * Get a Toolbar instance which is shown on current Activity
     *
     * @return instance of toolbar
     */
    protected Toolbar getToolbar() {
        return this.toolbar;
    }

    /**
     * Setup Toolbar instance
     *
     * @return
     */
    public abstract int setupToolbar();

    /**
     * Setup resource id for child of this abstract class
     *
     * @return layout resource id
     */
    public abstract int setupLayoutRes();

    /**
     * Setup menu resource id for child of this abstract class
     *
     * @return menu resource id
     */
    public abstract int setupMenuRes();


    /**
     * This is called with activity creation (for example, here is fragment created)
     */
    public abstract void init();

    public Bundle getSavedInstanceState() {
        return this.savedInstanceState;
    }
}

//FragmentManager
// mantains references to all fragments inside Activtiy
//Use findFragmentById() or findFragmentByTag() to get reference to a particular Fragment

//FragmentTransactions
//Used for adding, removing and replacing Fragments
