package com.rooksoto.parallel.activityStart;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;

public class ActivityStartPresenter implements BasePresenter {
    private ParallelLocation locationServices = null;
    private View view;

    @Override
    public void start () {
    }

    public void onBackPressedOverride (View viewP) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog();
        customAlertDialog.exit(viewP.getContext());
    }

    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
    }

    public void loadFragment (Fragment fragmentP, int animator1, int animator2, int containerID, String id) {
        ((Activity) view.getContext()).getFragmentManager().beginTransaction()
                .setCustomAnimations(animator1, animator2)
                .add(containerID, fragmentP, id)
                .addToBackStack(null)
                .commit();
    }

    public void setLocationServices (View viewP) {
        this.view = viewP;

        locationServices = ParallelLocation.getInstance();
        checkForGoogleApiAvail();
        locationServices = ParallelLocation.getInstance();
        locationServices.connect(); // Also starts Location Monitoring!
        locationServices.startGeofenceMonitoring(viewP.getContext()); // Hardcoded Geofence (Currently: C4Q HQ @ 100m)
    }

    private void checkForGoogleApiAvail () {
        int hasGpsInstalled = GoogleApiAvailability.getInstance().isGooglePlayServicesAvailable(view.getContext());
        if (hasGpsInstalled != ConnectionResult.SUCCESS) {
            GoogleApiAvailability.getInstance().getErrorDialog(((Activity) view.getContext()), hasGpsInstalled, 1).show();
        }
        if (Build.VERSION.SDK_INT >= 23) {
            getLocationPermissions();
        }
    }

    @RequiresApi (api = Build.VERSION_CODES.M)
    private void getLocationPermissions () {
        ((Activity) view.getContext()).requestPermissions(new String[] {
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION},
                9999);
    }

    public void disconnectLocationServices () {
        locationServices.disconnect();
    }

}
