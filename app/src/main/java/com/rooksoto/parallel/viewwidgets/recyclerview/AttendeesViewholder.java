package com.rooksoto.parallel.viewwidgets.recyclerview;

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Answers;
import com.rooksoto.parallel.network.objects.User;
import com.rooksoto.parallel.utility.AppContext;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AttendeesViewholder extends RecyclerView.ViewHolder {
    private TextView textViewName;
    private TextView textViewEmail;
    private ImageView imageViewProfile;
    private List<Answers> answersList;

    public AttendeesViewholder (View itemView) {
        super(itemView);
        textViewName = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_name);
        textViewEmail = (TextView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_email);
        imageViewProfile = (ImageView) itemView.findViewById(R.id.fragment_hub_attendees_viewholder_picture);
    }

    public void bind(User userParam){
        textViewName.setText(userParam.getName());
        textViewEmail.setText(userParam.getEmail());
        if (userParam.getPictureLink() == null) {
            Picasso.with(AppContext.getAppContext()).load(R.drawable.bruttino_large).fit().into(imageViewProfile);
        } else {
            Picasso.with(AppContext.getAppContext()).load(Uri.parse(userParam.getPictureLink())).fit().into(imageViewProfile);
        }
    }
}
