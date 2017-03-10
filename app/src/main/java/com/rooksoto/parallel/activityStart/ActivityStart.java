package com.rooksoto.parallel.activityStart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityStart.enterid.FragmentStartEnterID;
import com.rooksoto.parallel.utility.CustomAlertDialog;

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
