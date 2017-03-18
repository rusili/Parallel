package com.rooksoto.parallel.activityhub.profile;

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
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.widgets.recyclerview.ProfileAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentProfile extends Fragment implements BaseView {
    private FragmentProfilePresenter fragmentProfilePresenter;
    private RecyclerView recyclerViewProfile;
    List<Answers> listofAnswers = new ArrayList<>();

    private View view;
    private ImageButton imageButtonExit;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_profile, container, false);
        initialize();
        return view;
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        tempRV();
        imageButtonExit = (ImageButton) view.findViewById(R.id.activity_hub_action_bar_button);
        ProfileAdapter profileAdapter = new ProfileAdapter(listofAnswers);
        recyclerViewProfile = (RecyclerView) view.findViewById(R.id.fragment_hub_profile_recyclerview);
        recyclerViewProfile.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerViewProfile.setAdapter(profileAdapter);
        setOnClickListeners();
    }

    private void setOnClickListeners() {
        imageButtonExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CustomAlertDialog customAlertDialog = new CustomAlertDialog();
                customAlertDialog.exit(getActivity());
            }
        });
    }

    private void tempRV(){
        listofAnswers.add(new Answers("Placeholder", "True"));
    }
}
