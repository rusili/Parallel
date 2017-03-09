package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Questions;

import java.util.ArrayList;
import java.util.List;

public class ProfileAdapter extends RecyclerView.Adapter {
    List <Questions> listofQuestions = new ArrayList <>();

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View profileView = inflater.inflate(R.layout.navigationdrawer_viewholder, parent, false);
        ProfileViewholder viewHolder = new ProfileViewholder(profileView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        ProfileViewholder profileViewholder = (ProfileViewholder) holder;
        profileViewholder.bind(position);
    }

    @Override
    public int getItemCount () {
        return listofQuestions.size();
    }
}
