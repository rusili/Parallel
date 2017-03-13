package com.rooksoto.parallel.activityHub;

import com.rooksoto.parallel.utility.geolocation.ParallelLocation;

public class ActivityHubPresenter {
    private ParallelLocation locationService = null;
    private Listener listener;

    public ActivityHubPresenter(Listener listener) {
        this.listener = listener;
    }

    public void onInitialize() {
        locationService = ParallelLocation.getInstance();
        locationService.connect();
        listener.checkLocationServices(locationService);
    }


    public void onStop() {
        listener.disconnectLocationService(locationService);
    }

    interface Listener {

        void checkLocationServices(ParallelLocation locationService);

        void disconnectLocationService(ParallelLocation locationService);
    }
}
