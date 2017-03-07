package com.rooksoto.parallel.view.activityStart;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.view.activityHub.ActivityHub;
import com.rooksoto.parallel.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;
import com.rooksoto.parallel.viewwidgets.camera2.Camera2BasicFragment;

import link.fls.swipestack.SwipeStack;

public class ActivityStart extends AppCompatActivity implements SwipeStack.SwipeStackListener {
    private int containerID = R.id.activity_start_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    ParallelLocation locationService = null;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        locationService = ParallelLocation.getInstance();
        checkForGoogleApiAvail();
        locationService = ParallelLocation.getInstance();
        locationService.connect(); // Also starts Location Monitoring!
        locationService.startGeofenceMonitoring(this); // Hardcoded Geofence (Currently: C4Q HQ @ 100m)
        initialize();
        loadFragmentEnterID();
    }

    private void loadtestCamera () {
            getFragmentManager().beginTransaction()
                    .replace(R.id.activity_start_fragment_container, Camera2BasicFragment.newInstance())
                    .commit();
    }

    private void checkForGoogleApiAvail () {
        int hasGpsInstalled = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (hasGpsInstalled != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, hasGpsInstalled, 1).show();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            getLocationPermissions();
        }
    }

    @RequiresApi (api = Build.VERSION_CODES.M)
    private void getLocationPermissions () {
        requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                9999);
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentEnterID () {
        FragmentStartEnterID mFragmentStartEnterID = new FragmentStartEnterID();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartEnterID)
                .commit();
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

    public void onClickToActivityHub (View view) {
        mCustomSoundEffects.setDefaultClick();
        Intent intent = new Intent(this, ActivityHub.class);
        startActivity(intent);
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

    public void onClicktoWelcome (View view) {
        loadFragmentWelcome();
    }

    @Override
    protected void onStop () {
        locationService.disconnect();
        super.onStop();
    }
}
