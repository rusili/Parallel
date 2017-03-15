package com.rooksoto.parallel.activityhub;

import android.app.Fragment;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.attendees.FragmentAttendees;
import com.rooksoto.parallel.activityhub.chat.FragmentChat;
import com.rooksoto.parallel.activityhub.eventinfo.FragmentItinerary;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHubPresenter implements BasePresenter {
    private ParallelLocation locationService = null;
    private static Listener listener;

    private View view;

    public ActivityHubPresenter(Listener listener){
        this.listener = listener;
    }

    @Override
    public void start () {

    }

    @Override
    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {

    }

    public void toViewPager(){
        listener.setupViewpager();
    }

    public static void toViewPager2(){
        listener.setupViewpager();
    }

    public void onStopOverride () {
        locationService.disconnect();
    }

    public void checkLocationServices (View viewP) {
        this.view = viewP;

        locationService = ParallelLocation.getInstance();
        locationService.connect();
        locationService.startGeofenceMonitoring(view.getContext());
    }

    public interface Listener {
        void setupViewpager();
    }
}
