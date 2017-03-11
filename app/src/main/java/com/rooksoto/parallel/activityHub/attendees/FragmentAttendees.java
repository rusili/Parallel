package com.rooksoto.parallel.activityHub.attendees;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

public class FragmentAttendees extends Fragment implements BaseView {
    private FragmentAttendeesPresenter fragmentAttendeesPresenter = new FragmentAttendeesPresenter();

    private View view;
    private RecyclerView recyclerView;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_attendees, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        recyclerView = (RecyclerView) view.findViewById(R.id.fragment_hub_attendees_recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new AttendeesAdapter());
    }

    @Override
    public void onBackPressed () {

    }
}
