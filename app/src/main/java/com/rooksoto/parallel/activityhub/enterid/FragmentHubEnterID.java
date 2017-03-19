package com.rooksoto.parallel.activityhub.enterid;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHubPresenter;
import com.rooksoto.parallel.utility.OnClickEffect;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;

@SuppressLint("ValidFragment")
public class FragmentHubEnterID extends Fragment implements BaseView {
    private FragmentHubEnterIDPresenter fragmentHubEnterIDPresenter;
    private ActivityHubPresenter.Listener listener;

    private View view;
    private EditText textViewEventID;
    private Button buttonEnter;

    private int containerID = R.id.content_frame;

    ParallelLocation location;
    FirebaseDatabase database;
    DatabaseReference reference;

    private static final String TAG = "FragmentHubEnterID";

    @Override
    public void onStart() {
        super.onStart();
        location = ParallelLocation.getInstance();
        database = FirebaseDatabase.getInstance();
    }

    @SuppressLint("ValidFragment")
    public FragmentHubEnterID(ActivityHubPresenter.Listener listener){
        fragmentHubEnterIDPresenter = new FragmentHubEnterIDPresenter(listener);
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_enterid, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {

        textViewEventID = (EditText) view.findViewById(R.id.fragment_start_enterid_eventid);

        buttonEnter = (Button) view.findViewById(R.id.enter_button);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                OnClickEffect.setButton(buttonEnter);
                if (textViewEventID.getText().toString().equals("")) {
                    ParallelLocation.eventID = "empty";
                } else {
                    ParallelLocation.eventID = textViewEventID.getText().toString();
                }
                Log.d(TAG, "onClick: eventID Current Value is: " + ParallelLocation.eventID);
                reference = database.getReference();
                fragmentHubEnterIDPresenter.checkEventID(ParallelLocation.eventID, reference);
                // TODO: 3/16/17 Start the questions fragment only if eventID is valid
            }
        });
    }

}
