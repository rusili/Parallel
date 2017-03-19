package com.rooksoto.parallel.activityhub.eventmap;

import android.graphics.PointF;
import android.support.annotation.Nullable;
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
    private Listener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference attendeesRef;
    private List<User> listofUsers;
    private AttendeesAdapter attendeesAdapter;
    private FirebaseAuth firebaseAuth;
    private FirebaseUser user;
    private static FragmentEventMapPresenter instance;

    public static FragmentEventMapPresenter getInstance (@Nullable Listener listener) {
        if (instance == null) {
            instance = new FragmentEventMapPresenter(listener);
        }
        return instance;
    }

    private FragmentEventMapPresenter(Listener listener) {
        this.listener = listener;
        listofUsers = new ArrayList<>();
    }


    void onViewCreated() {
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(ParallelLocation.eventID);
        attendeesRef = reference.child("attendee_list");
        attendeesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot user : dataSnapshot.getChildren()) {
                    if (!user.child("name").getValue().toString().equals("Test")) {
                        listofUsers.add(new User(
                                (String) user.child("name").getValue(),
                                (String) user.child("email").getValue(),
                                user.getKey(),
                                (String) user.child("pictureLink").getValue())
                        );
                    }
                    attendeesAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Error getting list of attendees");
            }
        });

        attendeesAdapter = new AttendeesAdapter(listofUsers, "Event");
        listener.setViews(attendeesAdapter);
    }

    public void onUserSelected(String uid) {
        listener.getCoordinates();
        Log.d(TAG, "onUserSelected: " + uid);
        attendeesRef.child(user.getUid()).child("sentPins").push().setValue(new Pin(uid, listener.getCoordinates()));
        attendeesRef.child(uid).child("receivedPins").push().setValue(new Pin(user.getUid(), listener.getCoordinates()));
    }


    interface Listener {

        void setViews(AttendeesAdapter attendeesAdapter);

        PointF getCoordinates();
    }
}
