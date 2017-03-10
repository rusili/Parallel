package com.rooksoto.parallel.activityStart;

import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityHub.ActivityHub;
import com.rooksoto.parallel.activityStart.enterid.FragmentStartEnterID;
import com.rooksoto.parallel.activityStart.questions.FragmentStartQuestions;
import com.rooksoto.parallel.activityStart.welcome.FragmentStartWelcome;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.widgets.camera2.Camera2BasicFragment;

import link.fls.swipestack.SwipeStack;

public class ActivityStart extends AppCompatActivity implements BaseView {
    private ActivityStartPresenter activityStartPresenter;

    private View view;

    private int containerID = R.id.activity_start_fragment_container;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
        loadFragmentEnterID();
    }

    public void initialize () {
        setViews();
        activityStartPresenter.setLocationServices(view);
    }

    @Override
    public void setViews () {
        view = getWindow().getDecorView().getRootView();
    }

    private void loadFragmentEnterID () {
        activityStartPresenter.loadFragment(new FragmentStartEnterID(), containerID, R.animator.animator_fade_in_left, R.animator.animator_fade_out_right, "EnterID");
    }

    @Override
    public void onBackPressed () {
        activityStartPresenter.onBackPressedOverride(view);
    }

    @Override
    protected void onStop () {
        super.onStop();
        activityStartPresenter.disconnectLocationServices();
    }
}
