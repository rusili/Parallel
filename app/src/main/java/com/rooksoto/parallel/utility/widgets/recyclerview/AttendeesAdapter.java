package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.transition.TransitionManager;
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
    private int mExpandedPostion = -1;
    private RecyclerView recyclerView;

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
    public void onBindViewHolder (RecyclerView.ViewHolder holder, final int position) {
        final AttendeesViewholder attendeesViewholder = (AttendeesViewholder) holder;
        attendeesViewholder.bind(listofUsers.get(position));

        final boolean isExpanded = position == mExpandedPostion;
        attendeesViewholder.getLinearLayoutExpanding().setVisibility(isExpanded ? View.VISIBLE : View.GONE);
        attendeesViewholder.itemView.setActivated(isExpanded);
        attendeesViewholder.getExpandBtn().setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View view) {
                mExpandedPostion = isExpanded ? -1 : position;
                TransitionManager.beginDelayedTransition(recyclerView);
                notifyDataSetChanged();
                if (isExpanded) {
                    attendeesViewholder.getExpandBtn().setImageResource(R.drawable.ic_keyboard_arrow_down_black_24dp);
                } else {
                    attendeesViewholder.getExpandBtn().setImageResource(R.drawable.ic_keyboard_arrow_up_black_24dp);
                }
            }
        });
    }

    @Override
    public int getItemCount () {
        return listofUsers.size();
    }

    public void setRecyclerView (RecyclerView recyclerView) {
        this.recyclerView = recyclerView;
    }
}
