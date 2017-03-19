package com.rooksoto.parallel.activityhub.eventmap;

import android.app.Fragment;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.PinView;

import java.util.ArrayList;

public class FragmentEventMap extends Fragment {
    private View rootView;
    private PinView imageView;
    private ArrayList<PointF> listOfCoord;
    private ImageButton imageButtonExit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_hub_event_map, container, false);
        imageView = (PinView) rootView.findViewById(R.id.imageView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        imageView.setImage(ImageSource.resource(R.drawable.floorplan));
        // TODO grab list of coordinates from firebase and display all
        //        imageView.setPin(new PointF(1718f, 581f));
    }

    @Override
    public void onResume() {
        super.onResume();
        final GestureDetector gestureDetector = new GestureDetector(getActivity(),
                new GestureDetector.SimpleOnGestureListener() {
                    @Override
                    public void onLongPress(MotionEvent e) {
                        super.onLongPress(e);
                        if (imageView.isReady()) {
                            PointF coordinate = imageView.viewToSourceCoord(e.getX(), e.getY());
                            imageView.setPin(coordinate);
                            // TODO push coordinates to firebase & refresh view
                        }
                    }
                });
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return gestureDetector.onTouchEvent(motionEvent);
            }
        });

    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
