package com.rooksoto.parallel.activityhub.profile;

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
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.Questions;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.rooksoto.parallel.utility.widgets.recyclerview.ProfileAdapter;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class FragmentProfile extends Fragment implements BaseView {
    private FragmentProfilePresenter fragmentProfilePresenter;
    private RecyclerView recyclerViewProfile;
    List<Answers> listofAnswers = new ArrayList<>();

    private FirebaseUser user;

    private View view;
    private ImageButton imageButtonExit;

    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference answerRef;

    ProfileAdapter profileAdapter;

    private ImageView ivProfilePic;
    private TextView tvName;
    private TextView tvEmail;

    private static final String TAG = "FragmentProfile";

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_profile, container, false);
        initialize();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(ParallelLocation.eventID);

        tvName.setText(user.getDisplayName());
        tvEmail.setText(user.getEmail());
        Picasso.with(getActivity()).load(user.getPhotoUrl()).into(ivProfilePic);


        // Get Answer List from Firebase
        answerRef = reference.child("attendee_list").child(user.getUid()).child("listofAnswers");

        answerRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot question:dataSnapshot.getChildren()) {
                    listofAnswers.add(new Answers(
                            (String) question.child("answer").getValue(),
                            (String) question.child("question").getValue()
                    ));
                    profileAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Failed to get list of answers from dataSnapshot");
            }
        });
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {

        ivProfilePic = (ImageView) view.findViewById(R.id.iv_profile_pic);
        tvName = (TextView) view.findViewById(R.id.tv_name);
        tvEmail = (TextView) view.findViewById(R.id.tv_email);

        imageButtonExit = (ImageButton) view.findViewById(R.id.activity_hub_action_bar_button);
        profileAdapter = new ProfileAdapter(listofAnswers);
        recyclerViewProfile = (RecyclerView) view.findViewById(R.id.rv_profile_answers);
        recyclerViewProfile.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewProfile.setAdapter(profileAdapter);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAlertDialog customAlertDialog = new CustomAlertDialog();
                customAlertDialog.exit(view.getContext());
            }
        });
    }

}
