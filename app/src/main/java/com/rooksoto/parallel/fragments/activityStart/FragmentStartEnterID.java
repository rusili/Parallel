package com.rooksoto.parallel.fragments.activityStart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rooksoto.parallel.R;

public class FragmentStartEnterID extends Fragment {
    private View mView;
    private TextView textViewEventID;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_enterid, container, false);
        initialize();
        return mView;
    }

    private void initialize(){
        textViewEventID = (TextView) mView.findViewById(R.id.fragment_start_enterid_eventid);
    }
}
