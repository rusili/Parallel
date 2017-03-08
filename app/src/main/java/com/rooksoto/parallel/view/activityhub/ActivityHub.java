package com.rooksoto.parallel.view.activityhub;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.viewwidgets.camera2.Camera2BasicFragment;

public class ActivityHub extends AppCompatActivity {

    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private ViewPager viewPager;
    private ActivityHubPresenter activityHubPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        activityHubPresenter = new ActivityHubPresenter();
        activityHubPresenter.startLocationServices();
        setupViewpager();
    }

    private void setupViewpager() {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        final SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Event Info", FragmentEventInfo.class));
        pages.add(FragmentPagerItem.of("Chat", FragmentChat.class));
        pages.add(FragmentPagerItem.of("Attendees", FragmentAttendees.class));
        pages.add(FragmentPagerItem.of("Camera", Camera2BasicFragment.class));


        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new TabletTransformer());
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }


    @Override
    public void onBackPressed() {
        mCustomAlertDialog.exit(this);
        activityHubPresenter.changeBackPressResult(viewPager, this);
    }

    @Override
    protected void onStop() {
        activityHubPresenter.stopLocationServices();
        super.onStop();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (fragment instanceof FragmentEventInfo) {
            ((FragmentEventInfo) fragment).setPresenter(new FragmentEventInfoPresenter());
        }
        if (fragment instanceof FragmentChat) {
            ((FragmentChat) fragment).setPresenter(new FragmentChatPresenter());
        }
        if (fragment instanceof FragmentAttendees) {
            ((FragmentAttendees) fragment).setPresenter(new FragmentAttendeesPresenter());
        }
    }
}