package com.rooksoto.parallel.activityhub.itinerary;

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
import com.rooksoto.parallel.objects.Session;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.recyclerview.ItineraryAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentItinerary extends Fragment implements BaseView {
    private FragmentItineraryPresenter fragmentItineraryPresenter = new FragmentItineraryPresenter();
    private List<Session> listofSessions = new ArrayList <>();

    private View view;
    private RecyclerView recyclerViewItinerary;
    private ImageButton imageButtonExit;

    private ItineraryAdapter itineraryAdapter;

    private FirebaseDatabase database;
    private DatabaseReference itineraryRef;

    private static final String TAG = "FragmentItinerary";


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_itinerary, container, false);
        listofSessions.clear();
        initialize();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        database = FirebaseDatabase.getInstance();
        itineraryRef = database.getReference().child(ParallelLocation.eventID).child("itinerary");

        itineraryRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot item:dataSnapshot.getChildren()) {
                    listofSessions.add(new Session(
                            (String) item.child("name").getValue(),
                            (String) item.child("location").getValue(),
                            (String) item.child("start_time").getValue())
                    );
                    itineraryAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Error reading itinerary data");
            }
        });
    }

    public void initialize () {
        setViews();
    }




    @Override
    public void setViews () {
        itineraryAdapter = new ItineraryAdapter(listofSessions);
        recyclerViewItinerary = (RecyclerView) view.findViewById(R.id.fragment_hub_itinerary_recyclerview);
        recyclerViewItinerary.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewItinerary.setAdapter(itineraryAdapter);
    }

    private void tempRV(){
//        DateFormat dateFormat = new SimpleDateFormat("HH:mm");
//        Date date = new Date();
//        dateFormat.format(date);
//        listofSessions.add(new Session("Introduction", "Google main stage", date, date));

//        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//        Calendar calendar = Calendar.getInstance();
//        calendar.set(Calendar.YEAR, 2017);
//        calendar.set(Calendar.MONTH, 3);
//        calendar.set(Calendar.DATE, 22);
//        calendar.set(Calendar.HOUR,18);
//        calendar.set(Calendar.MINUTE,00);
//        calendar.set(Calendar.SECOND,00);
//        Date date = calendar.getTime();
//        dateFormat.format(date);
//        listofSessions.add(new Session("Arrive at Google", "Google", date, date));
//        calendar.set(Calendar.HOUR,18);
//        calendar.set(Calendar.MINUTE,10);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Shirts & Snacks", "Google", date, date));
//        calendar.set(Calendar.HOUR,18);
//        calendar.set(Calendar.MINUTE,20);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Group Photos", "Google", date, date));
//        calendar.set(Calendar.HOUR,18);
//        calendar.set(Calendar.MINUTE,30);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Doors Open", "Google", date, date));
//        calendar.set(Calendar.HOUR,19);
//        calendar.set(Calendar.MINUTE,00);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Opening Remarks", "Google", date, date));
//        calendar.set(Calendar.HOUR,19);
//        calendar.set(Calendar.MINUTE,22);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Demos Begin", "Google", date, date));
//        calendar.set(Calendar.HOUR,21);
//        calendar.set(Calendar.MINUTE,00);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Reception & Networking", "Google", date, date));
//        calendar.set(Calendar.HOUR,22);
//        calendar.set(Calendar.MINUTE,00);
//        date = calendar.getTime();
//        listofSessions.add(new Session("Closing & Cleanup", "Google", date, date));
    }

}
