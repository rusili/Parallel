package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.User;

import java.util.ArrayList;
import java.util.List;

public class AttendeesAdapter extends RecyclerView.Adapter {
    private List <User> listofUsers = new ArrayList <>();

    public AttendeesAdapter (List<User> listofUsersP) {
        this.listofUsers = listofUsersP;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View attendeesView = inflater.inflate(R.layout.fragment_hub_attendees_viewholder, parent, false);
        AttendeesViewholder viewHolder = new AttendeesViewholder(attendeesView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        AttendeesViewholder attendeesViewholder = (AttendeesViewholder) holder;
        attendeesViewholder.bind(listofUsers.get(position));
    }

    @Override
    public int getItemCount () {
        return listofUsers.size();
    }
}
