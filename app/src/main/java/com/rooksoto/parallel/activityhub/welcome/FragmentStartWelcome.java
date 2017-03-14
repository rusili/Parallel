package com.rooksoto.parallel.activityhub.welcome;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.questions.FragmentHubQuestions;

public class FragmentStartWelcome extends Fragment implements BaseView {
    private FragmentStartWelcomePresenter fragmentStartWelcomePresenter = new FragmentStartWelcomePresenter();

    private View view;

    private int containerID = R.id.activity_start_fragment_container;
    private String[] welcomeText = new String[] {"Welcome", "to", "C4Q's", "3.3 Demo Day", "Enjoy"};

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_welcome, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        TextView textViewHostedBy = (TextView) view.findViewById(R.id.fragment_start_welcome_hostedby);
        TextView textViewHost = (TextView) view.findViewById(R.id.fragment_start_welcome_host);
        fragmentStartWelcomePresenter.setRunnableHTextView(view, welcomeText, textViewHostedBy, textViewHost);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                fragmentStartWelcomePresenter.setOnClickReplace(new FragmentHubQuestions(), view, containerID, "Questions");
            }
        });

        fragmentStartWelcomePresenter.start();
    }

    @Override
    public void onBackPressed () {
        fragmentStartWelcomePresenter.onBackPressedOverride(view);
    }

}
