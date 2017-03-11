package com.rooksoto.parallel.activityStart;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityStart.enterid.FragmentStartEnterID;

public class ActivityStart extends AppCompatActivity implements BaseView {
    private ActivityStartPresenter activityStartPresenter = new ActivityStartPresenter();

    private View view;

    private int containerID = R.id.activity_start_fragment_container;

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
        activityStartPresenter.loadFragment(new FragmentStartEnterID(), R.animator.animator_fade_in, R.animator.animator_fade_out, containerID, "EnterID");
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
