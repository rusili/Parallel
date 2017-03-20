package com.rooksoto.parallel.activityhub.itinerary;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Session;
import com.rooksoto.parallel.utility.widgets.recyclerview.ItineraryAdapter;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class FragmentItinerary extends Fragment implements BaseView {
    private FragmentItineraryPresenter fragmentItineraryPresenter = new FragmentItineraryPresenter();
    private List<Session> listofSessions = new ArrayList <>();

    private View view;
    private RecyclerView recyclerViewItinerary;
    private ImageButton imageButtonExit;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_itinerary, container, false);
        listofSessions.clear();
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
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2017);
        calendar.set(Calendar.MONTH, 3);
        calendar.set(Calendar.DATE, 22);
        calendar.set(Calendar.HOUR,18);
        calendar.set(Calendar.MINUTE,00);
        calendar.set(Calendar.SECOND,00);
        Date date = calendar.getTime();
        dateFormat.format(date);
        listofSessions.add(new Session("Arrive at Google", "Google", date, date));
        calendar.set(Calendar.HOUR,18);
        calendar.set(Calendar.MINUTE,10);
        date = calendar.getTime();
        listofSessions.add(new Session("Shirts & Snacks", "Google", date, date));
        calendar.set(Calendar.HOUR,18);
        calendar.set(Calendar.MINUTE,20);
        date = calendar.getTime();
        listofSessions.add(new Session("Group Photos", "Google", date, date));
        calendar.set(Calendar.HOUR,18);
        calendar.set(Calendar.MINUTE,30);
        date = calendar.getTime();
        listofSessions.add(new Session("Doors Open", "Google", date, date));
        calendar.set(Calendar.HOUR,19);
        calendar.set(Calendar.MINUTE,00);
        date = calendar.getTime();
        listofSessions.add(new Session("Opening Remarks", "Google", date, date));
        calendar.set(Calendar.HOUR,19);
        calendar.set(Calendar.MINUTE,22);
        date = calendar.getTime();
        listofSessions.add(new Session("Demos Begin", "Google", date, date));
        calendar.set(Calendar.HOUR,21);
        calendar.set(Calendar.MINUTE,00);
        date = calendar.getTime();
        listofSessions.add(new Session("Reception & Networking", "Google", date, date));
        calendar.set(Calendar.HOUR,22);
        calendar.set(Calendar.MINUTE,00);
        date = calendar.getTime();
        listofSessions.add(new Session("Closing & Cleanup", "Google", date, date));
    }

}
