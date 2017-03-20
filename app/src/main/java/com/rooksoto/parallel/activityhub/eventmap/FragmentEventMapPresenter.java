package com.rooksoto.parallel.activityhub.eventmap;

import android.graphics.PointF;
import android.support.annotation.NonNull;
import android.util.Log;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.objects.Pin;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by huilin on 3/18/17.
 */

public class FragmentEventMapPresenter {
    public static final String RECEIVED_PINS = "received_pins";
    public static final String SENT_PINS = "sent_pins";
    private Listener listener;
    private DatabaseReference attendeesRef;
    private List<User> listofUsers;
    private AttendeesAdapter attendeesAdapter;
    private FirebaseUser user;
    private static FragmentEventMapPresenter instance;
    private DatabaseReference userRef;

    public static FragmentEventMapPresenter getInstance(Listener listener) {
        if (instance == null) {
            instance = new FragmentEventMapPresenter(listener);
        }
        return instance;
    }

    private FragmentEventMapPresenter(Listener listener) {
        this.listener = listener;
        listofUsers = new ArrayList<>();
    }


    public void onCreation() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        attendeesRef = FirebaseDatabase.getInstance().getReference().child(ParallelLocation.eventID).child("attendee_list");
        userRef = FirebaseDatabase.getInstance().getReference().child(ParallelLocation.eventID).child("attendee_list").child(user.getUid());
    }

    void onViewCreated() {
        attendeesAdapter = new AttendeesAdapter(listofUsers, "Event");
        listener.setViews(attendeesAdapter);
    }

    public void onUserSelected(String uid) {
        listener.getCoordinates();
        Log.d(TAG, "onUserSelected: " + uid);
        attendeesRef.child(user.getUid()).child(SENT_PINS).push().setValue(new Pin(uid, listener.getCoordinates()));
        attendeesRef.child(uid).child(RECEIVED_PINS).push().setValue(new Pin(user.getUid(), listener.getCoordinates()));
        listener.closeSheet();
    }

    public void onStartup() {
        attendeesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listofUsers.clear();
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    if (!user.child("name").getValue().toString().equals("Test")) {
                        listofUsers.add(new User(
                                (String) user.child("name").getValue(), (String) user.child("email").getValue(), (String) user.child("pictureLink").getValue(), user.getKey()));
                    }
                    attendeesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Error getting list of attendees");
            }
        });

        userRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.d(TAG, "onDataChange: Pins have been added");
                Log.d(TAG, "onDataChange: " + dataSnapshot.hasChild(RECEIVED_PINS));
                if (dataSnapshot.hasChild(RECEIVED_PINS)) {
                    for (DataSnapshot item : dataSnapshot.child(RECEIVED_PINS).getChildren()) {
                        Pin pin = getPin(item);
                        listener.populatePin(RECEIVED_PINS, pin.getCoordinates());
                    }
                }
                Log.d(TAG, "onDataChange: " + dataSnapshot.hasChild(SENT_PINS));
                if (dataSnapshot.hasChild(SENT_PINS)) {
                    for (DataSnapshot item : dataSnapshot.child(SENT_PINS).getChildren()) {
                        Pin pin = getPin(item);
                        listener.populatePin(SENT_PINS, pin.getCoordinates());
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @NonNull
    private Pin getPin(DataSnapshot item) {
        PointF coordinates = new PointF((Float.valueOf(String.valueOf(item.child("coordinates").child("x").getValue()))), (Float.valueOf(String.valueOf(item.child("coordinates").child("y").getValue()))));
        return new Pin((String) item.child("uid").getValue(), coordinates);
    }


    interface Listener {

        void setViews(AttendeesAdapter attendeesAdapter);

        PointF getCoordinates();

        void populatePin(String tag, PointF coordinates);

        void closeSheet();
    }
}
