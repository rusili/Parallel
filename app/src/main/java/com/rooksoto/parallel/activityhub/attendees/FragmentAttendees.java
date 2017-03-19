package com.rooksoto.parallel.activityhub.attendees;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAttendees extends Fragment implements BaseView {
    private FragmentAttendeesPresenter fragmentAttendeesPresenter = new FragmentAttendeesPresenter();

    private View view;
    private RecyclerView recyclerViewAttendees;
    private AttendeesAdapter attendeesAdapter;
    private ImageButton imageButtonExit;

    private List<User> listofUsers = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference attendeesRef;

    private static final String TAG = "FragmentAttendees";


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_attendees, container, false);
        initialize();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(ParallelLocation.eventID);
        attendeesRef = reference.child("attendee_list");
        attendeesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user:dataSnapshot.getChildren()) {

                    if (!user.child("name").getValue().toString().equals("Test")){
                        listofUsers.add(new User(
                                (String) user.child("name").getValue(),
                                (String) user.child("email").getValue(),
                                (String) user.child("pictureLink").getValue()
                        ));
                    }
                    attendeesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Error getting list of attendees");
            }
        });

    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        attendeesAdapter = new AttendeesAdapter(listofUsers, "Attendees");
        recyclerViewAttendees = (RecyclerView) view.findViewById(R.id.fragment_hub_attendees_recyclerview);
        recyclerViewAttendees.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewAttendees.setAdapter(attendeesAdapter);

        imageButtonExit = (ImageButton) view.findViewById(R.id.activity_hub_action_bar_button);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAlertDialog customAlertDialog = new CustomAlertDialog();
                customAlertDialog.exit(getActivity());
            }
        });
    }
}
