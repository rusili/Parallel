package com.rooksoto.parallel.activityhub;

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
import com.rooksoto.parallel.activityhub.attendees.FragmentAttendees;
import com.rooksoto.parallel.activityhub.chat.FragmentChat;
import com.rooksoto.parallel.activityhub.enterid.FragmentHubEnterID;
import com.rooksoto.parallel.activityhub.eventinfo.FragmentItinerary;
import com.rooksoto.parallel.activityhub.profile.FragmentProfile;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHub extends AppCompatActivity implements BaseView {
    private ActivityHubPresenter activityHubPresenter = new ActivityHubPresenter();

    private View view;

    private int containerID = R.id.content_frame;
    private static final String TAG = "ActivityHub";

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
        pages.add(FragmentPagerItem.of("Profile", FragmentProfile.class));
        pages.add(FragmentPagerItem.of("Event Info", FragmentItinerary.class));
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
        //loadFragmentEnterID();
        setupViewpager();
    }

    @Override
    public void setViews () {
        view = getWindow().getDecorView().getRootView();
    }

    private void loadFragmentEnterID () {
        SmartTabLayout smartTabLayout = (SmartTabLayout) findViewById(R.id.viewpagertab);
        smartTabLayout.setVisibility(View.INVISIBLE);

        getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.animator_fade_in, R.animator.animator_fade_out)
                    .add(containerID, new FragmentHubEnterID(), "Enter ID")
                    .commit();
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