package com.rooksoto.parallel.utility.widgets.hubviewpager;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

/**
 * Created by huilin on 3/4/17.
 */

public class HubSwipePageFragment extends Fragment {

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_hub_eventinfo, container, false);

        return rootView;
    }
}

