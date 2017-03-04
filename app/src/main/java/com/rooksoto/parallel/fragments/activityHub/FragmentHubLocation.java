package com.rooksoto.parallel.fragments.activityHub;

/**
 * Created by rook on 3/2/17.
 */

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.geolocation.ParallelLocation;

public class FragmentHubLocation extends Fragment {

    private View mView;
    private TextView tvLatitude;
    private TextView tvLongitude;

    private ParallelLocation locationService = null;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hub_location, container, false);

        tvLatitude = (TextView) mView.findViewById(R.id.tv_Latitude);
        tvLongitude = (TextView) mView.findViewById(R.id.tv_Longitude);

        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        locationService = ParallelLocation.getInstance();

        // Test Current Location Here
        tvLatitude.setText(String.valueOf(locationService.getLatitude()));
        tvLongitude.setText(String.valueOf(locationService.getLongitude()));

    }
}
