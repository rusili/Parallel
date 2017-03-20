package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.AppContext;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AttendeesViewholder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewEmail;
    private ImageView imageViewProfile;
    private List <Answers> listofAnswers = new ArrayList <>();
    private User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference answerRef;

    private RecyclerView recyclerViewAnswers;
    private ProfileAdapter profileAdapter;

    public AttendeesViewholder (View itemView) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_name);
        textViewEmail = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_email);
        imageViewProfile = (ImageView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_picture);

        recyclerViewAnswers = (RecyclerView) itemView.findViewById(R.id.fragment_hub_attendees_expand_recyclerview);
        recyclerViewAnswers.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        recyclerViewAnswers.setAdapter(profileAdapter);
    }

    public void bind (User userParam) {
        firebaseAuth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();
        reference = database.getReference().child(ParallelLocation.eventID);

        answerRef = reference.child("attendee_list").child(userParam.getUid()).child("listofAnswers");
        answerRef.addValueEventListener (new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot question:dataSnapshot.getChildren()) {
                    listofAnswers.add(new Answers(
                            (String) question.child("question").getValue(),
                            (String) question.child("answer").getValue()
                    ));
                    profileAdapter.notifyDataSetChanged();
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {}
        });

        profileAdapter = new ProfileAdapter(listofAnswers);
        recyclerViewAnswers.setAdapter(profileAdapter);

        textViewName.setText(userParam.getName());
        textViewEmail.setText(userParam.getEmail());
        if (userParam.getPictureLink() == null) {
            Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(imageViewProfile);
        } else {
            Picasso.with(AppContext.getAppContext()).load(Uri.parse(userParam.getPictureLink())).fit().into(imageViewProfile);
        }
    }

}
