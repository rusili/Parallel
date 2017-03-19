package com.rooksoto.parallel.activityhub.eventmap;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by huilin on 3/18/17.
 */

class FragmentEventMapPresenter {
    private Listener listener;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference attendeesRef;
    private List<User> listofUsers;
    private AttendeesAdapter attendeesAdapter;


    FragmentEventMapPresenter(Listener listener) {
        this.listener = listener;
        listofUsers = new ArrayList<>();
    }

    void onViewCreated() {
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
                                (String) user. child("pictureLink").getValue()
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

        attendeesAdapter = new AttendeesAdapter(listofUsers, "Event");
        listener.setViews(attendeesAdapter);
    }


    interface Listener {

        void setViews(AttendeesAdapter attendeesAdapter);
    }
}
