package com.rooksoto.parallel.activityHub;

import android.app.Fragment;
import android.content.res.Resources;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityHub.attendees.FragmentAttendees;
import com.rooksoto.parallel.activityHub.chat.FragmentChat;
import com.rooksoto.parallel.activityHub.eventinfo.FragmentItinerary;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHubPresenter implements BasePresenter {
    private ParallelLocation locationService = null;

    private View view;
    private ViewPager viewPager;

    @Override
    public void start () {

    }

    @Override
    public void onBackPressedOverride (View viewP) {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    @Override
    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {

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

    public void setUpViewPager (ViewPager viewPagerP, SmartTabLayout viewPagerTab, FragmentPagerItemAdapter adapter) {
        this.viewPager = viewPagerP;

        FragmentPagerItems pages = new FragmentPagerItems(view.getContext());
        pages.add(FragmentPagerItem.of("Event Info", FragmentItinerary.class));
        pages.add(FragmentPagerItem.of("Chat", FragmentChat.class));
        pages.add(FragmentPagerItem.of("Attendees", FragmentAttendees.class));
        pages.add(FragmentPagerItem.of("Camera", Camera2BasicFragment.class));

        final LayoutInflater inflater = LayoutInflater.from(view.getContext());
        final Resources res = view.getResources();

        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
            @Override
            public View createTabView (ViewGroup container, int position, PagerAdapter adapter) {
                ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon, container,
                        false);
                switch (position) {
                    case 0:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_outline_black_24dp));
                        break;
                    case 1:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_today));
                        break;
                    case 2:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_chat_bubble_outline));
                        break;
                    case 3:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_people_outline));
                        break;
                    case 4:
                        icon.setImageDrawable(res.getDrawable(R.drawable.ic_panorama_wide_angle));
                        break;
                    default:
                        throw new IllegalStateException("Invalid position: " + position);
                }
                return icon;
            }
        });

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new TabletTransformer());
        viewPagerTab.setViewPager(viewPager);
    }
}
