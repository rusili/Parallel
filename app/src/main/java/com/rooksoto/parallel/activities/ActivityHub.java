package com.rooksoto.parallel.activities;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.rooksoto.parallel.R;
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
        setContentView(R.layout.activity_hub_viewpager);
        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(this);
        initialize();
        setupViewpager();
//        loadFragmentHubLocation();
    }

    private void setupViewpager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        pagerAdapter = new HubPagerAdapter(getSupportFragmentManager());
        viewPager.setAdapter(pagerAdapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    @Override
    public void onBackPressed () {
        mCustomAlertDialog.exit(this);
        if (viewPager.getCurrentItem() == 0) {
            super.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onStop() {
        locationService.disconnect();
        super.onStop();
    }
}