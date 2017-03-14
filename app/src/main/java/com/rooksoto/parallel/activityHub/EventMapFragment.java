package com.rooksoto.parallel.activityHub;

/**
 * Created by rook on 3/2/17.
 */

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.davemorrissey.labs.subscaleview.SubsamplingScaleImageView;
import com.rooksoto.parallel.R;

public class EventMapFragment extends Fragment {
    private View rootView;
    private SubsamplingScaleImageView imageView;

    @Override
    public void onCreate (@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_event_map, container, false);
//        PinView imageView = (PinView)rootView.findViewById(id.imageView);
//        imageView.setImage(ImageSource.asset("squirrel.jpg"));
//        imageView.setPin(new PointF(1718f, 581f));
        return rootView;
    }

    @Override
    public void onViewCreated (View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView = (SubsamplingScaleImageView)rootView.findViewById(R.id.imageView);
        imageView.setImage(ImageSource.resource(R.drawable.floorplan));
//        imageView.setImage(ImageSource.asset("map.png"))
//        imageView.setImage(ImageSource.uri("/sdcard/DCIM/DSCM00123.JPG"));
    }

    @Override
    public void onResume () {
        super.onResume();
    }

    @Override
    public void onStop () {
        super.onStop();
    }
}
