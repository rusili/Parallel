package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.net.Uri;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AttendeesViewholder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewEmail;
    private ImageView imageViewProfile;
    private List <Answers> answersList;
    private User user;
    private ImageButton expandBtn;
    private FirebaseAuth firebaseAuth;


    private RecyclerView recyclerViewAnswers;
    private LinearLayout linearLayoutExpanding;

    public AttendeesViewholder (View itemView) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_name);
        textViewEmail = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_email);
        imageViewProfile = (ImageView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_picture);
        linearLayoutExpanding = (LinearLayout) itemView.findViewById(R.id.expanding_layout);
        expandBtn = (ImageButton) itemView.findViewById(R.id.expand_btn);

        recyclerViewAnswers = (RecyclerView) itemView.findViewById(R.id.fragment_hub_attendees_expand_recyclerview);
        recyclerViewAnswers.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
    }

    public void bind (User userParam) {
        // user has no answers;

        ProfileAdapter profileAdapter = new ProfileAdapter(answersList);
        recyclerViewAnswers.setAdapter(profileAdapter);

        textViewName.setText(userParam.getName());
        textViewEmail.setText(userParam.getEmail());
        if (userParam.getPictureLink() == null) {
            Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(imageViewProfile);
        } else {
            Picasso.with(AppContext.getAppContext()).load(Uri.parse(userParam.getPictureLink())).fit().into(imageViewProfile);
        }
    }

    public ImageButton getExpandBtn () {
        return expandBtn;
    }

    public LinearLayout getLinearLayoutExpanding () {
        return linearLayoutExpanding;
    }
}
