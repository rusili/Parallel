package com.rooksoto.parallel.activityStart.enterid;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityStart.welcome.FragmentStartWelcome;

public class FragmentStartEnterID extends Fragment implements BaseView {
    private FragmentStartEnterIDPresenter fragmentStartEnterIDPresenter = new FragmentStartEnterIDPresenter();

    private View view;
    private EditText textViewEventID;
    private Button buttonEnter;

    private int containerID = R.id.activity_start_fragment_container;
    private String eventID;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_enterid, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        textViewEventID = (EditText) view.findViewById(R.id.fragment_start_enterid_eventid);
        eventID = textViewEventID.getText().toString();

        buttonEnter = (Button) view.findViewById(R.id.enter_button);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                fragmentStartEnterIDPresenter.checkEventID(eventID);
                fragmentStartEnterIDPresenter.setOnClickReplace(new FragmentStartWelcome(), buttonEnter, containerID, "Questions");
            }
        });
    }

    @Override
    public void onBackPressed () {
        fragmentStartEnterIDPresenter.onBackPressedOverride(view);
    }

}
