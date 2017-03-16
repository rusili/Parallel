package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Session;

import java.util.ArrayList;
import java.util.List;

public class ItineraryAdapter extends RecyclerView.Adapter {
    List <Session> listofSessions = new ArrayList <>();

    public ItineraryAdapter (List <Session> listofSessionsP) {
        this.listofSessions = listofSessionsP;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder (ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View sessionView = inflater.inflate(R.layout.fragment_hub_eventinfo_sessions_viewholder, parent, false);
        ItineraryViewholder viewHolder = new ItineraryViewholder(sessionView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder (RecyclerView.ViewHolder holder, int position) {
        ItineraryViewholder itineraryViewholder = (ItineraryViewholder) holder;
        itineraryViewholder.bind(position, listofSessions.get(position));
    }

    @Override
    public int getItemCount () {
        return listofSessions.size();
    }
}
