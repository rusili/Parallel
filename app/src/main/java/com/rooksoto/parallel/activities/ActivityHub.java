package com.rooksoto.parallel.activities;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.activityHub.FragmentHubLocation;
import com.rooksoto.parallel.geolocation.ParallelLocation;
import com.rooksoto.parallel.fragments.activityHub.FragmentChat;
import com.rooksoto.parallel.utility.AppContext;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;

public class ActivityHub extends AppCompatActivity {
    private int containerID = R.id.activity_hub_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    private ParallelLocation locationService = null;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(this);
        initialize();
        loadFragmentChat();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentChat () {
        FragmentChat fragmentChat = new FragmentChat();
        getSupportFragmentManager().beginTransaction()
                .replace(containerID, fragmentChat)
                .commit();
    }

    private void loadFragmentHubLocation() {
        FragmentHubLocation fragmentHubLocation = new FragmentHubLocation();
        getSupportFragmentManager()
                .beginTransaction()
                .replace(containerID, fragmentHubLocation)
                .commit();
    }

    @Override
    public void onBackPressed () {
        mCustomAlertDialog.exit(this);
        //super.onBackPressed();
    }

    @Override
    protected void onStop() {
        locationService.disconnect();
        super.onStop();
    }
}