package com.rooksoto.parallel.activityHub.eventinfo;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;

/**
 * Created by huilin on 3/4/17.
 */

public class FragmentEventInfo extends Fragment implements BaseView {
    private FragmentEventInfoPresenter fragmentEventInfoPresenter;

    private View view;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_eventinfo, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {

    }

    @Override
    public void onBackPressed () {

    }
}
