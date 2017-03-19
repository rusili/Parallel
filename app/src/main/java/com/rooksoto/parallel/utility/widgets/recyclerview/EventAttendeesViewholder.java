package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.eventmap.FragmentEventMapPresenter;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by huilin on 3/19/17.
 */

class EventAttendeesViewholder extends RecyclerView.ViewHolder {
    private final FragmentEventMapPresenter eventMapPresenter;
    private View view;
    private TextView textViewName;
    private TextView textViewEmail;
    private ImageView imageViewProfile;
    private List<Answers> answersList;

    public EventAttendeesViewholder(View itemView) {
        super(itemView);
        view = itemView;
        textViewName = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_name);
        textViewEmail = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_email);
        imageViewProfile = (ImageView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_picture);
        eventMapPresenter = FragmentEventMapPresenter.getInstance(null);
    }

    public void bind(final User userParam) {
        textViewName.setText(userParam.getName());
        textViewEmail.setText(userParam.getEmail());
        if (userParam.getPictureLink() == null) {
            Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(imageViewProfile);
        } else {
            Picasso.with(AppContext.getAppContext()).load(Uri.parse(userParam.getPictureLink())).fit().into(imageViewProfile);
        }
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                eventMapPresenter.onUserSelected(userParam.getUid());
            }
        });
    }
}