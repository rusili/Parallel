package com.rooksoto.parallel.userHub;

import com.rooksoto.parallel.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.AppContext;

/**
 * Created by huilin on 3/6/17.
 */

public class UserHubPresenter implements UserHubContract.Presenter {
    ParallelLocation locationService;


    @Override
    public void start() {
        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(AppContext.getAppContext());
    }

    @Override
    public void stopLocationServices() {
        locationService.disconnect();
    }
}
