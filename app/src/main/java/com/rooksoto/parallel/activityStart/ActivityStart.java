package com.rooksoto.parallel.activityStart;

import android.Manifest;
import android.app.Fragment;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityStart.enterid.FragmentStartEnterID;
import com.rooksoto.parallel.activityStart.welcome.FragmentStartWelcome;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

import link.fls.swipestack.SwipeStack;

public class ActivityStart extends AppCompatActivity implements SwipeStack.SwipeStackListener, ActivityStartPresenter.Listener {
    private int containerID = R.id.activity_start_fragment_container;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    ParallelLocation locationService = null;
    private ActivityStartPresenter activityStartPresenter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        activityStartPresenter = new ActivityStartPresenter(this);
        checkForGoogleApiAvail();
        locationService = ParallelLocation.getInstance();
        locationService.connect(); // Also starts Location Monitoring!
//        locationService.startGeofenceMonitoring(this); // Hardcoded Geofence (Currently: C4Q HQ @ 100m)
        loadFragmentEnterID();
    }

    private void loadtestCamera () {
        getFragmentManager().beginTransaction()
                .replace(R.id.activity_start_fragment_container, Camera2BasicFragment.newInstance())
                .commit();
    }

    @RequiresApi (api = Build.VERSION_CODES.M)
    private void getLocationPermissions () {
        requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                9999);
    }

    private void loadFragmentEnterID () {
        FragmentStartEnterID mFragmentStartEnterID = new FragmentStartEnterID();
        getFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartEnterID)
                .commit();
    }

    private void loadFragmentWelcome () {
        FragmentStartWelcome mFragmentStartWelcome = new FragmentStartWelcome();
        getFragmentManager().beginTransaction()
                .replace(containerID, mFragmentStartWelcome)
                .commit();
    }

//    private void loadFragmentQuestions () {
//        FragmentStartQuestions mFragmentStartQuestions = new FragmentStartQuestions();
//        getSupportFragmentManager().beginTransaction()
//                .replace(containerID, mFragmentStartQuestions)
//                .commit();
//    }

    @Override
    public void onBackPressed () {
        Fragment currentFragment = getFragmentManager().findFragmentById(containerID);
        if (currentFragment instanceof FragmentStartWelcome) {
            mCustomAlertDialog.exit(this);
        } else {
            super.onBackPressed();
        }
    }

//    public void onClickToQuestions (View view) {
//        mCustomSoundEffects.setDefaultClick();
//        loadFragmentQuestions();
//    }
//
//    public void onClickToActivityHub (View view) {
//        mCustomSoundEffects.setDefaultClick();
//        Intent intent = new Intent(this, ActivityHub.class);
//        startActivity(intent);
//    }

    @Override
    public void onViewSwipedToLeft (int position) {

    }

    @Override
    public void onViewSwipedToRight (int position) {

    }

    @Override
    public void onStackEmpty () {

    }

//    public void onClicktoWelcome (View view) {
//        loadFragmentWelcome();
//    }

    @Override
    protected void onStop () {
        locationService.disconnect();
        super.onStop();
    }

    public void checkForGoogleApiAvail() {
        int hasGpsInstalled = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (hasGpsInstalled != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(this, hasGpsInstalled, 1).show();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            getLocationPermissions();
        }
    }
}