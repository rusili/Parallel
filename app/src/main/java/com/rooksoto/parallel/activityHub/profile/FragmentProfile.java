package com.rooksoto.parallel.activityHub.profile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;

public class FragmentProfile extends Fragment implements BaseView {
    private FragmentProfilePresenter fragmentProfilePresenter;

    private View view;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_profile, container, false);
        initialize();
        return view;
    }

    @Override
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
