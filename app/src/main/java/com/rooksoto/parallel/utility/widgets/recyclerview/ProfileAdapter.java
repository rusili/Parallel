package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter {
    List <Answers> listofAnswers = new ArrayList <>();

    public ProfileAdapter (List <Answers> listofAnswersP) {
        this.listofAnswers = listofAnswersP;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View profileView = inflater.inflate(R.layout.fragment_hub_profile_questionsviewholder, parent, false);
        ProfileViewholder viewHolder = new ProfileViewholder(profileView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        ProfileViewholder profileViewholder = (ProfileViewholder) holder;
        profileViewholder.bind(position, listofAnswers.get(position));
    }

    @Override
    public int getItemCount () {
        return listofAnswers.size();
    }
}
