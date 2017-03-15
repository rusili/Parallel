package com.rooksoto.parallel.activityhub;

import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHubPresenter;
import com.rooksoto.parallel.activityhub.EventMapFragment;
import com.rooksoto.parallel.activityhub.attendees.FragmentAttendees;
import com.rooksoto.parallel.activityhub.chat.FragmentChat;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHub extends AppCompatActivity implements ActivityHubPresenter.Listener {
    private ActivityHubPresenter activityHubPresenter;

    private View view;

    private int containerID = R.id.viewpager;
    private static final String TAG = "ActivityHub";
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private FragmentPagerItems pages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        activityHubPresenter = new ActivityHubPresenter(this);
        initialize();
    }

    public void setupViewpager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Event Map", EventMapFragment.class));
        pages.add(FragmentPagerItem.of("Chat", FragmentChat.class));
        pages.add(FragmentPagerItem.of("Attendees", FragmentAttendees.class));
        pages.add(FragmentPagerItem.of("Camera", Camera2BasicFragment.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);

        final LayoutInflater inflater = LayoutInflater.from(this);
        final Resources res = view.getResources();

        viewPagerTab.setCustomTabView(new SmartTabLayout.TabProvider() {
              @Override
              public View createTabView(ViewGroup container, int position, PagerAdapter adapter) {
                  ImageView icon = (ImageView) inflater.inflate(R.layout.custom_tab_icon, container,
                          false);
                  switch (position) {
                      case 0:
                          icon.setImageDrawable(res.getDrawable(R.drawable.ic_map_black));
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
            }
        );

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new TabletTransformer());
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void initialize() {
        view = getWindow().getDecorView().getRootView();
        activityHubPresenter.onInitialize();
        setupViewpager();
    }

    @Override
    public void onBackPressed() {
        viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
    }

    @Override
    protected void onStop() {
        super.onStop();
        activityHubPresenter.onStop();
    }

    @Override
    public void checkLocationServices(ParallelLocation locationService) {
        locationService.startGeofenceMonitoring(view.getContext());

    }

    @Override
    public void disconnectLocationService(ParallelLocation locationService) {
        locationService.disconnect();
    }
}