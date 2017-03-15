package com.rooksoto.parallel.activityhub.enterid;

import android.annotation.SuppressLint;
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
import com.rooksoto.parallel.activityhub.ActivityHubPresenter;
import com.rooksoto.parallel.activityhub.questions.FragmentHubQuestions;
import com.rooksoto.parallel.utility.OnClickEffect;

@SuppressLint("ValidFragment")
public class FragmentHubEnterID extends Fragment implements BaseView {
    private FragmentHubEnterIDPresenter fragmentHubEnterIDPresenter;
    private ActivityHubPresenter.Listener listener;

    private View view;
    private EditText textViewEventID;
    private Button buttonEnter;

    private int containerID = R.id.content_frame;
    private String eventID;

    @SuppressLint("ValidFragment")
    public FragmentHubEnterID(ActivityHubPresenter.Listener listener){
        fragmentHubEnterIDPresenter = new FragmentHubEnterIDPresenter(listener);
        this.listener = listener;
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_enterid, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        final FragmentHubQuestions fragmentHubQuestions = new FragmentHubQuestions(listener);

        textViewEventID = (EditText) view.findViewById(R.id.fragment_start_enterid_eventid);
        eventID = textViewEventID.getText().toString();

        buttonEnter = (Button) view.findViewById(R.id.enter_button);
        buttonEnter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                OnClickEffect.setButton(buttonEnter);
                // TODO: 3/15/2017 Parameter:
                //fragmentHubEnterIDPresenter.checkEventID(eventID);
                fragmentHubEnterIDPresenter.setOnClickReplace(fragmentHubQuestions, buttonEnter, containerID, "Questions");
            }
        });
    }

}
