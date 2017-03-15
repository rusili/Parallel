package com.rooksoto.parallel.activityhub;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.eftimoff.viewpagertransformers.DepthPageTransformer;
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
import com.rooksoto.parallel.activityhub.questions.FragmentHubQuestions;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHub extends AppCompatActivity implements BaseView, ActivityHubPresenter.Listener{
    private ActivityHubPresenter activityHubPresenter;

    private View view;
    private ViewPager viewPager;

    private int containerID = R.id.content_frame;
    private static final String TAG = "ActivityHub";

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hub);
        activityHubPresenter = new ActivityHubPresenter(this);

        initialize();
    }

    public void setupViewpager () {
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Profile", FragmentProfile.class));
        pages.add(FragmentPagerItem.of("Event Info", FragmentItinerary.class));
        pages.add(FragmentPagerItem.of("Chat", FragmentChat.class));
        pages.add(FragmentPagerItem.of("Attendees", FragmentAttendees.class));
        pages.add(FragmentPagerItem.of("Camera", Camera2BasicFragment.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);

        setUpViewPager(viewPager, viewPagerTab, adapter);
    }

    private void setUpViewPager (ViewPager viewPagerP, SmartTabLayout viewPagerTab, FragmentPagerItemAdapter adapter) {
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
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected void onStart () {
        super.onStart();
    }

    public void initialize () {
        setViews();
        activityHubPresenter.checkLocationServices(view);
        loadFragmentEnterID();
        //setupViewpager();
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
                    .add(containerID, new FragmentHubEnterID(this), "Enter ID")
                    .commit();
    }

    @Override
    public void onBackPressed () {
        Fragment currentFrag = getFragmentManager().findFragmentById(containerID);
        if (currentFrag instanceof FragmentHubEnterID || currentFrag instanceof FragmentHubQuestions){
            CustomAlertDialog customAlertDialog = new CustomAlertDialog();
            customAlertDialog.exit(this);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
    }

    @Override
    protected void onStop () {
        super.onStop();
        activityHubPresenter.onStopOverride();
    }
}