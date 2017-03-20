package com.rooksoto.parallel.activityhub.attendees;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.annotation.UiThread;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;
import com.rooksoto.parallel.utility.widgets.recyclerview.ExpandedRecyclerView.AttendeesExpandAdapter;
import com.rooksoto.parallel.utility.widgets.recyclerview.ExpandedRecyclerView.UserExtend;

import java.util.ArrayList;
import java.util.List;

public class FragmentAttendees extends Fragment implements BaseView {
    private FragmentAttendeesPresenter fragmentAttendeesPresenter = new FragmentAttendeesPresenter();

    private View view;
    private RecyclerView recyclerViewAttendees;
    private AttendeesAdapter attendeesAdapter;
    private AttendeesExpandAdapter attendeesExpandAdapter;
    private ImageButton imageButtonExit;
    private Handler handler = new Handler();

    private List<User> listofUsers = new ArrayList<>();
    private List<UserExtend> listofUserExtend = new ArrayList<>();
    private List<Answers> listofAnswers = new ArrayList<>();
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference attendeesRef;
    private DatabaseReference answerRef;

    private static final String TAG = "FragmentAttendees";


    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_attendees, container, false);
        listofUsers.clear();
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
                UserExtend tempUser;
                for (DataSnapshot user:dataSnapshot.getChildren()) {
                    if (!user.child("name").getValue().toString().equals("Test")){
                        listofAnswers.clear();
                        for (DataSnapshot answers:user.child("listofAnswers").getChildren()){
                            Answers tempAnswer = new Answers(
                                    (String) answers.child("question").getValue(),
                                    (String) answers.child("answer").getValue()
                            );
                            listofAnswers.add(tempAnswer);
                        }
                         tempUser = new UserExtend(
                                (String) user.child("name").getValue(),
                                (String) user.child("email").getValue(),
                                (String) user.child("pictureLink").getValue(),
                                    (String) user.getKey(),
                                 (List<Answers>) listofAnswers
                        );
                        listofUserExtend.add(tempUser);
                    }
                    attendeesExpandAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Error getting list of attendees");
            }
        });
        setExtendViews();
    }

    public void initialize () {
        //setViews();
        handler.postDelayed(new Runnable() {
            public void run() {
                setExtendViews();
            }
        }, 1000);
    }

    @Override
    public void setViews () {
//        attendeesAdapter = new AttendeesAdapter(listofUsers, "Attendees");
//        recyclerViewAttendees = (RecyclerView) view.findViewById(R.id.fragment_hub_attendees_recyclerview);
//        recyclerViewAttendees.setLayoutManager(new LinearLayoutManager(view.getContext()));
//        attendeesAdapter.setRecyclerView(recyclerViewAttendees);
//        recyclerViewAttendees.setAdapter(attendeesAdapter);
    }

    private void setExtendViews(){
        recyclerViewAttendees = (RecyclerView) view.findViewById(R.id.fragment_hub_attendees_recyclerview);
        attendeesExpandAdapter = new AttendeesExpandAdapter(getActivity(), listofUserExtend);

        attendeesExpandAdapter.setExpandCollapseListener(new ExpandableRecyclerAdapter.ExpandCollapseListener() {
            @UiThread
            @Override
            public void onParentExpanded(int parentPosition) {
                UserExtend userExtend = listofUserExtend.get(parentPosition);
            }

            @UiThread
            @Override
            public void onParentCollapsed(int parentPosition) {
                UserExtend userExtend = listofUserExtend.get(parentPosition);
            }
        });

        recyclerViewAttendees.setAdapter(attendeesExpandAdapter);
        recyclerViewAttendees.setLayoutManager(new LinearLayoutManager(getActivity()));
    }
}
