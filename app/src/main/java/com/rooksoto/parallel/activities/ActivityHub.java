package com.rooksoto.parallel.activities;

import android.Manifest;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.fragments.activityHub.FragmentChat;
import com.rooksoto.parallel.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;
import com.rooksoto.parallel.viewwidgets.hubviewpager.HubPagerAdapter;

public class ActivityHub extends AppCompatActivity {
    private int containerID = R.id.activity_hub_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private static final String TAG = "ActivityHub";
    ParallelLocation locationService = null;
    private ViewPager viewPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        initialize();
//        loadFragmentChat();

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new HubPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();

        checkForGoogleApiAvail();
        if(Build.VERSION.SDK_INT >= 23) {
            getLocationPermissions();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        startLocationServices();
    }

    private void checkForGoogleApiAvail() {
        int hasGpsInstalled = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(this);
        if (hasGpsInstalled != ConnectionResult.SUCCESS) {
            Log.d(TAG, "Google API Not Available on This Device");
            GoogleApiAvailability.getInstance().getErrorDialog(this, hasGpsInstalled, 1).show();
        } else {
            Log.d(TAG, "Google API is Available");
        }
    }

    private void startLocationServices() {
        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(getApplicationContext());
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void getLocationPermissions() {
        requestPermissions(new String[] {
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION},
        9999);

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

    @Override
    public void onBackPressed () {
        mCustomAlertDialog.exit(this);
        //super.onBackPressed();
        if (viewPager.getCurrentItem() == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed();
        } else {
            // Otherwise, select the previous step.
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }
}