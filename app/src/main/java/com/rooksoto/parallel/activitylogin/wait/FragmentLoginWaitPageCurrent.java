package com.rooksoto.parallel.activitylogin.wait;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Events;
import com.rooksoto.parallel.utility.widgets.recyclerview.EventsAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentLoginWaitPageCurrent extends Fragment implements BaseView{
    private View mView;
    private RecyclerView mRecyclerView;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_wait_event, container, false);
        setDemoDayEvent();
        return mView;
    }

    private void setDemoDayEvent () {
        List <Events> eventsList = new ArrayList <>();
        eventsList.add(new Events());
        mRecyclerView = (RecyclerView) mView.findViewById(R.id.fragment_login_wait_event_current_recyclerview);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mView.getContext()));
        mRecyclerView.setAdapter(new EventsAdapter(eventsList));
    }

    @Override
    public void initialize () {

    }

    @Override
    public void setViews () {

    }
}
