package com.rooksoto.parallel.activityHub;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

/**
 * Created by huilin on 3/4/17.
 */

public class FragmentEventInfo extends Fragment {
    private View mView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hub_eventinfo, container, false);
        return mView;
    }

    private void initialize(){

    }
}
