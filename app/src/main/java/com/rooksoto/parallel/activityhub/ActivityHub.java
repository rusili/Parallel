package com.rooksoto.parallel.activityhub;

import android.app.Fragment;
import android.content.Context;
import android.content.res.Resources;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.eftimoff.viewpagertransformers.DepthPageTransformer;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.attendees.FragmentAttendees;
import com.rooksoto.parallel.activityhub.chat.FragmentChat;
import com.rooksoto.parallel.activityhub.enterid.FragmentHubEnterID;
import com.rooksoto.parallel.activityhub.eventmap.FragmentEventMap;
import com.rooksoto.parallel.activityhub.itinerary.FragmentItinerary;
import com.rooksoto.parallel.activityhub.profile.FragmentProfile;
import com.rooksoto.parallel.activityhub.questions.FragmentHubQuestions;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

public class ActivityHub extends AppCompatActivity implements ActivityHubPresenter.Listener {
    private ActivityHubPresenter activityHubPresenter;

    private View view;

    private int containerID = R.id.content_frame;
    private static final String TAG = "ActivityHub";
    private ViewPager viewPager;
    private SmartTabLayout viewPagerTab;
    private FragmentPagerItems pages;

    private FirebaseAuth firebaseAuth;
    private FirebaseUser firebaseUser;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference userKey;

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
        viewPagerTab.setVisibility(View.VISIBLE);

        pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Itinerary", FragmentItinerary.class));
        pages.add(FragmentPagerItem.of("Profile", FragmentProfile.class));
        pages.add(FragmentPagerItem.of("Attendees", FragmentAttendees.class));
        pages.add(FragmentPagerItem.of("Chat", FragmentChat.class));
        pages.add(FragmentPagerItem.of("Map", FragmentEventMap.class));
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
                                                      icon.setImageDrawable(res.getDrawable(R.drawable.ic_today));
                                                      break;
                                                  case 1:
                                                      icon.setImageDrawable(res.getDrawable(R.drawable.ic_person_outline_black_24dp));
                                                      break;
                                                  case 2:
                                                      icon.setImageDrawable(res.getDrawable(R.drawable.ic_people_outline));
                                                      break;
                                                  case 3:
                                                      icon.setImageDrawable(res.getDrawable(R.drawable.ic_chat_bubble_outline));
                                                      break;
                                                  case 4:
                                                      icon.setImageDrawable(res.getDrawable(R.drawable.ic_map_black));
                                                      break;
                                                  case 5:
                                                      icon.setImageDrawable(res.getDrawable(R.drawable.ic_photo_camera_black_24dp));
                                                      break;
                                                  default:
                                                      throw new IllegalStateException("Invalid position: " + position);
                                              }
                                              return icon;
                                          }
                                      }
        );

        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new DepthPageTransformer());
        viewPagerTab.setViewPager(viewPager);
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    public void initialize() {
        view = getWindow().getDecorView().getRootView();
        activityHubPresenter.onInitialize();
        loadFragmentEnterID();
        //activityHubPresenter.toViewPager();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseUser = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference();
        userKey = reference.child(ParallelLocation.eventID).child("attendee_list");
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
    public void onBackPressed() {
        Fragment currentFrag = getFragmentManager().findFragmentById(containerID);
        if (currentFrag instanceof FragmentHubEnterID || currentFrag instanceof FragmentHubQuestions) {
            CustomAlertDialog customAlertDialog = new CustomAlertDialog();
            customAlertDialog.exit(this);
        } else {
            viewPager.setCurrentItem(viewPager.getCurrentItem() - 1);
        }
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

    @Override
    public void activateParallelEvent(String enteredEventID) {
        ParallelLocation.eventID = enteredEventID;
        final FragmentHubQuestions fragmentHubQuestions = new FragmentHubQuestions(this);
        if (!isOnline()) {
            Toast.makeText(this, "Cannot Connect - Please Check Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            Fragment currentFrag = getFragmentManager().findFragmentById(containerID);
            getFragmentManager().beginTransaction().remove(currentFrag).commit();

            getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.animator_fade_in, R.animator.animator_fade_out)
                    .replace(containerID, fragmentHubQuestions, "Questions")
                    .commit();
        }
    }

    @Override
    public void showEventIdError(String enteredEventID) {
        Toast.makeText(this,
                "The event \"" + enteredEventID + "\" does not exist! Please enter a valid Event ID.",
                Toast.LENGTH_SHORT)
                .show()
        ;
    }

    private boolean isOnline () {
        ConnectivityManager connManager = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        userKey.child(firebaseUser.getUid()).removeValue();
        firebaseAuth.signOut();

    }
    public void sendCoordinates(View view) {

    }
}
