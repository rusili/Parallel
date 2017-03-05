package com.rooksoto.parallel.viewwidgets.hubviewpager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.rooksoto.parallel.fragments.activityHub.FragmentAttendees;
import com.rooksoto.parallel.fragments.activityHub.FragmentChat;
import com.rooksoto.parallel.fragments.activityHub.FragmentEventInfo;

/**
 * Created by huilin on 3/4/17.
 */

public class HubPagerAdapter extends FragmentStatePagerAdapter {
    private static final int NUM_PAGES = 3;

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
