package com.rooksoto.parallel.userHub;

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

// THIS FRAGMENT IS NOW USELESS

public class FragmentHubLocation extends Fragment {

    private View mView;
    private TextView tvLatitude;
    private TextView tvLongitude;

    private ParallelLocation locationService = null;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        locationService = ParallelLocation.getInstance();
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hub_location, container, false);

        tvLatitude = (TextView) mView.findViewById(R.id.tv_Latitude);
        tvLongitude = (TextView) mView.findViewById(R.id.tv_Longitude);

        return mView;
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onResume () {
        super.onResume();
        tvLatitude.setText(String.valueOf(locationService.getLatitude()));
        tvLongitude.setText(String.valueOf(locationService.getLongitude()));
    }

    @Override
    public void onStop () {
        super.onStop();
        locationService.disconnect();
    }
}
