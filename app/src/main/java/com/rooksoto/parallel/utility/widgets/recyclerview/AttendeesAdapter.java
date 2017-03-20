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
    private String purpose = "";
    private List<User> listofUsers = new ArrayList<>();
    private View view;

    public AttendeesAdapter(List<User> listofUsersP, String purpose) {
        this.listofUsers = listofUsersP;
        this.purpose = purpose;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        switch (purpose) {
            case "Attendees":
                view = inflater.inflate(R.layout.fragment_hub_attendees_viewholder, parent, false);
                AttendeesViewholder viewHolder = new AttendeesViewholder(view);
                return viewHolder;
            case "Event":
                view = inflater.inflate(R.layout.event_map_attendees_viewholder, parent, false);
                EventAttendeesViewholder eventViewHolder = new EventAttendeesViewholder(view);
                return eventViewHolder;
        }
        return null;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        switch (purpose) {
            case "Attendees":
                AttendeesViewholder attendeesViewholder = (AttendeesViewholder) holder;
                attendeesViewholder.bind(listofUsers.get(position));
                break;
            case "Event":
                EventAttendeesViewholder eventViewHolder = (EventAttendeesViewholder) holder;
                eventViewHolder.bind(listofUsers.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return listofUsers.size();
    }
}