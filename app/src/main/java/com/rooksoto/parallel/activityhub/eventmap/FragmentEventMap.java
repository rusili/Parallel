package com.rooksoto.parallel.activityhub.eventmap;

import android.app.Fragment;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.davemorrissey.labs.subscaleview.ImageSource;
import com.flipboard.bottomsheet.BottomSheetLayout;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.PinView;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

public class FragmentEventMap extends Fragment implements FragmentEventMapPresenter.Listener {
    private View rootView;
    private PinView imageView;
    private RecyclerView recyclerViewAttendees;
    private BottomSheetLayout bottomSheet;
    private View sheetView;
    private FragmentEventMapPresenter fragEventMapPresenter;
    private PointF coordinatePressed;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        fragEventMapPresenter = FragmentEventMapPresenter.getInstance(this);
        fragEventMapPresenter.onCreation();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_hub_event_map, container, false);
        imageView = (PinView) rootView.findViewById(R.id.imageView);
        bottomSheet = (BottomSheetLayout) rootView.findViewById(R.id.bottomsheet);
        sheetView = inflater.inflate(R.layout.attendee_bottom_sheet, bottomSheet, false);

        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fragEventMapPresenter.onViewCreated();
        imageView.setImage(ImageSource.resource(R.drawable.floorplan));
    }

    @Override
    public void onStart() {
        super.onStart();
        fragEventMapPresenter.onStartup();
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
                            coordinatePressed = imageView.viewToSourceCoord(e.getX(), e.getY());
                            imageView.setPin(coordinatePressed);
                            bottomSheet.showWithSheetView(sheetView);
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

    @Override
    public void setViews(AttendeesAdapter attendeesAdapter) {
        // get bottom sheet view
        recyclerViewAttendees = (RecyclerView) sheetView.findViewById(R.id.fragment_hub_attendees_recyclerview);
        recyclerViewAttendees.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerViewAttendees.setAdapter(attendeesAdapter);
    }

    @Override
    public PointF getCoordinates() {
        return coordinatePressed;
    }

    @Override
    public void populatePin(String tag, PointF coordinates) {
        imageView.addPin(tag, coordinates);
    }

    @Override
    public void closeSheet() {
        bottomSheet.dismissSheet();
    }
}
