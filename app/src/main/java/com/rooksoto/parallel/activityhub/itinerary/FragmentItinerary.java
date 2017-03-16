package com.rooksoto.parallel.activityhub.itinerary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Session;
import com.rooksoto.parallel.utility.widgets.recyclerview.ItineraryAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FragmentItinerary extends Fragment implements BaseView {
    private FragmentItineraryPresenter fragmentItineraryPresenter = new FragmentItineraryPresenter();
    private RecyclerView recyclerViewItinerary;
    private List<Session> listofSessions = new ArrayList <>();

    private View view;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_itinerary, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        tempRV();
        recyclerViewItinerary = (RecyclerView) view.findViewById(R.id.fragment_hub_itinerary_recyclerview);
        recyclerViewItinerary.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewItinerary.setAdapter(new ItineraryAdapter(listofSessions));
    }

    private void tempRV(){
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        dateFormat.format(date);
        listofSessions.add(new Session("Introduction", "Google main stage", date, date));
    }

}
