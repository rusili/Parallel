package com.rooksoto.parallel.activityStart.welcome;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;

public class FragmentStartWelcome extends Fragment implements BaseView {
    private FragmentStartWelcomePresenter fragmentStartWelcomePresenter;

    private View view;

    private String[] welcomeText = new String[] {"Welcome", "to", "C4Q's", "3.3 Demo Day", "Enjoy"};

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_welcome, container, false);
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
    }

    @Override
    public void onBackPressed () {
        fragmentStartWelcomePresenter.onBackPressedOverride(view);
    }

}
