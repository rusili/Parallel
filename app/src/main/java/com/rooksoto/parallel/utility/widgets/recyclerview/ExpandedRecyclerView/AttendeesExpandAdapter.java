package com.rooksoto.parallel.utility.widgets.recyclerview.ExpandedRecyclerView;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bignerdranch.expandablerecyclerview.ExpandableRecyclerAdapter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;

import java.util.List;

public class AttendeesExpandAdapter extends ExpandableRecyclerAdapter<UserExtend, Answers, AttendeesExpandViewholder, AnswersExpandViewholder> {
    private LayoutInflater layoutInflater;
    private List<UserExtend> listofUserExtend;
    /**
     * Primary constructor. Sets up {@link #mParentList} and {@link #mFlatItemList}.
     * <p>
     * Any changes to {@link #mParentList} should be made on the original instance, and notified via
     * {@link #notifyParentInserted(int)}
     * {@link #notifyParentRemoved(int)}
     * {@link #notifyParentChanged(int)}
     * {@link #notifyParentRangeInserted(int, int)}
     * {@link #notifyChildInserted(int, int)}
     * {@link #notifyChildRemoved(int, int)}
     * {@link #notifyChildChanged(int, int)}
     * methods and not the notify methods of RecyclerView.Adapter.
     *
     * @param parentList List of all parents to be displayed in the RecyclerView that this
     *                   adapter is linked to
     */
    public AttendeesExpandAdapter (Context contextP, @NonNull List<UserExtend> parentList) {
        super(parentList);
        this.listofUserExtend = parentList;
        layoutInflater = LayoutInflater.from(contextP);
    }

    @NonNull
    @Override
    public AttendeesExpandViewholder onCreateParentViewHolder (@NonNull ViewGroup parentViewGroup, int viewType) {
        View attendeeView = layoutInflater.inflate(R.layout.fragment_hub_attendees_viewholder, parentViewGroup, false);
        return new AttendeesExpandViewholder(attendeeView);
    }

    @NonNull
    @Override
    public AnswersExpandViewholder onCreateChildViewHolder (@NonNull ViewGroup childViewGroup, int viewType) {
        View answerView = layoutInflater.inflate(R.layout.fragment_hub_profile_questionsviewholder, childViewGroup, false);
        return new AnswersExpandViewholder(answerView);
    }

    @Override
    public void onBindParentViewHolder (@NonNull AttendeesExpandViewholder parentViewHolder, int parentPosition, @NonNull UserExtend parent) {
        parentViewHolder.bind(parent);
    }

    @Override
    public void onBindChildViewHolder (@NonNull AnswersExpandViewholder childViewHolder, int parentPosition, int childPosition, @NonNull Answers child) {
        childViewHolder.bind(child);
    }
}
