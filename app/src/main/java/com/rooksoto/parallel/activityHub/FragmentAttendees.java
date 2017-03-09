package com.rooksoto.parallel.activityHub;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

/**
 * Created by huilin on 3/4/17.
 */

public class FragmentAttendees extends Fragment {
    private View mView;
    private RecyclerView mRecyclerView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hub_attendees, container, false);
        initialize();
        return mView;
    }

    private void initialize(){
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_hub_attendees_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRecyclerView.setAdapter(new AttendeesAdapter());
    }
}
