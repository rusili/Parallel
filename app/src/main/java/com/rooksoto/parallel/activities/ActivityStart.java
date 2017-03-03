package com.rooksoto.parallel.activities;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.activityStart.FragmentStartQuestions;
import com.rooksoto.parallel.fragments.activityStart.FragmentStartWelcome;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

import link.fls.swipestack.SwipeStack;

public class ActivityStart extends AppCompatActivity implements SwipeStack.SwipeStackListener{
    private int containerID = R.id.activity_start_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
        loadFragmentWelcome();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentWelcome () {
        FragmentStartWelcome mFragmentStartWelcome = new FragmentStartWelcome();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartWelcome)
                .commit();
    }

    private void loadFragmentQuestions () {
        FragmentStartQuestions mFragmentStartQuestions = new FragmentStartQuestions();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartQuestions)
                .commit();
    }

    @Override
    public void onBackPressed () {
        Fragment currentFragment = getSupportFragmentManager().findFragmentById(containerID);
        if (currentFragment instanceof FragmentStartWelcome) {
            mCustomAlertDialog.exit(this);
        } else {
            super.onBackPressed();
        }
    }

    public void onClickToQuestions (View view) {
        mCustomSoundEffects.setDefaultClick();
        loadFragmentQuestions();
    }

    @Override
    public void onViewSwipedToLeft (int position) {

    }

    @Override
    public void onViewSwipedToRight (int position) {

    }

    @Override
    public void onStackEmpty () {

    }
}
