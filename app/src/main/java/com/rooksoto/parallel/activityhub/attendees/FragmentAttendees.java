package com.rooksoto.parallel.activityhub.attendees;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.User;
import com.rooksoto.parallel.utility.widgets.recyclerview.AttendeesAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentAttendees extends Fragment implements BaseView {
    private FragmentAttendeesPresenter fragmentAttendeesPresenter = new FragmentAttendeesPresenter();

    private View view;
    private RecyclerView recyclerViewAttendees;
    private AttendeesAdapter attendeesAdapter;

    private List<User> listofUsers = new ArrayList<>();

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
        tempRV();
        recyclerViewAttendees = (RecyclerView) view.findViewById(R.id.fragment_hub_attendees_recyclerview);
        recyclerViewAttendees.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewAttendees.setAdapter(attendeesAdapter);
    }

    private void tempRV(){
        listofUsers.add(new User("Temp", "Temp@Temp.com"));
        listofUsers.add(new User("Temp", "Temp@Temp.com"));
        attendeesAdapter = new AttendeesAdapter(listofUsers);
    }
}
