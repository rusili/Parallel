package com.rooksoto.parallel.activityHub;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityHub.attendees.FragmentAttendees;
import com.rooksoto.parallel.activityHub.chat.FragmentChat;
import com.rooksoto.parallel.activityHub.eventinfo.FragmentEventInfo;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHub extends AppCompatActivity implements BaseView {
    private static final String TAG = "ActivityHub";
    private ActivityHubPresenter activityHubPresenter;
    private View view;
    private int containerID = R.id.viewpager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);

        initialize();
    }

    private void setupViewpager () {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Event Info", FragmentEventInfo.class));
        pages.add(FragmentPagerItem.of("Chat", FragmentChat.class));
        pages.add(FragmentPagerItem.of("Attendees", FragmentAttendees.class));
        pages.add(FragmentPagerItem.of("Camera", Camera2BasicFragment.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);

        activityHubPresenter.setUpViewPager(viewPager, viewPagerTab, adapter);
    }

    @Override
    protected void onStart () {
        super.onStart();
    }

    public void initialize () {
        setViews();
        activityHubPresenter.checkLocationServices(view);
        setupViewpager();
    }

    @Override
    public void setViews () {
        view = getWindow().getDecorView().getRootView();
    }

    @Override
    public void onBackPressed () {
        activityHubPresenter.onBackPressedOverride(view);
    }

    @Override
    protected void onStop () {
        super.onStop();
        activityHubPresenter.onStopOverride();
    }
}