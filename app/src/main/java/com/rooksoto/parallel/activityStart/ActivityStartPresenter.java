package com.rooksoto.parallel.activityStart;

/**
 * Created by huilin on 3/13/17.
 */

class ActivityStartPresenter {
    private Listener listener;

    public ActivityStartPresenter(Listener listener) {
        this.listener = listener;
    }

    public void onCreate() {
    }

    interface Listener{
    }

}
