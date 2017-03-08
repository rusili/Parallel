package com.rooksoto.parallel.view.activityhub;

import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;

import com.rooksoto.parallel.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.AppContext;

/**
 * Created by huilin on 3/6/17.
 */

public class ActivityHubPresenter implements ActivityHubContract.Presenter {
    ParallelLocation locationService;

    public void startLocationServices() {
        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(AppContext.getAppContext());
    }

    @Override
    public void stopLocationServices() {
        locationService.disconnect();
    }

    public void changeBackPressResult(ViewPager viewPager, FragmentActivity fragmentActivity) {
        if (viewPager.getCurrentItem() == 0) {
            fragmentActivity.onBackPressed();
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    public void start() {

    }
}
