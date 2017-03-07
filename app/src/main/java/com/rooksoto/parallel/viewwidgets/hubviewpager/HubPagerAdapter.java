package com.rooksoto.parallel.viewwidgets.hubviewpager;

import android.app.Fragment;
import android.app.FragmentManager;
import android.support.v13.app.FragmentStatePagerAdapter;

import com.rooksoto.parallel.view.activityHub.FragmentAttendees;
import com.rooksoto.parallel.view.activityHub.FragmentChat;
import com.rooksoto.parallel.view.activityHub.FragmentEventInfo;
import com.rooksoto.parallel.viewwidgets.camera2.Camera2BasicFragment;

public class HubPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 4;

    public HubPagerAdapter (FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem (int position) {
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragmentChat();
                break;
            case 1:
                fragment = new FragmentAttendees();
                break;
            case 2:
                fragment = Camera2BasicFragment.newInstance();
                break;
            case 3:
                fragment = new FragmentEventInfo();
                break;
        }
        return fragment;
    }

    @Override
    public int getCount () {
        return NUM_PAGES;
    }
}
