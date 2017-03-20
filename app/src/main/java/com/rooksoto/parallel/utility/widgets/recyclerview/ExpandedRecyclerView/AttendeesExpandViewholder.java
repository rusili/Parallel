package com.rooksoto.parallel.utility.widgets.recyclerview.ExpandedRecyclerView;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Build;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ParentViewHolder;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AttendeesExpandViewholder extends ParentViewHolder {
    private TextView textViewName;
    private TextView textViewEmail;
    private ImageView imageViewProfile;
    private List<Answers> listofAnswers = new ArrayList<>();
    private User user;
    private FirebaseAuth firebaseAuth;
    private FirebaseDatabase database;
    private DatabaseReference reference;
    private DatabaseReference answerRef;

    private static final float INITIAL_POSITION = 0.0f;
    private static final float ROTATED_POSITION = 180f;

    public AttendeesExpandViewholder (View itemView) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_name);
        textViewEmail = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_email);
        imageViewProfile = (ImageView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_picture);
    }

    public void bind (UserExtend userParam) {
        textViewName.setText(userParam.getName());
        textViewEmail.setText(userParam.getEmail());

        if (userParam.getPictureLink() == null) {
            Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(imageViewProfile);
        } else {
            Picasso.with(AppContext.getAppContext()).load(Uri.parse(userParam.getPictureLink())).fit().into(imageViewProfile);
        }
    }

    @SuppressLint ("NewApi")
    @Override
    public void setExpanded(boolean expanded) {
        super.setExpanded(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            if (expanded) {
            } else {
            }
        }
    }

    @Override
    public void onExpansionToggled(boolean expanded) {
        super.onExpansionToggled(expanded);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
            RotateAnimation rotateAnimation;
            if (expanded) { // rotate clockwise
                rotateAnimation = new RotateAnimation(ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            } else { // rotate counterclockwise
                rotateAnimation = new RotateAnimation(-1 * ROTATED_POSITION,
                        INITIAL_POSITION,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f,
                        RotateAnimation.RELATIVE_TO_SELF, 0.5f);
            }
            rotateAnimation.setDuration(200);
            rotateAnimation.setFillAfter(true);
        }
    }
}
