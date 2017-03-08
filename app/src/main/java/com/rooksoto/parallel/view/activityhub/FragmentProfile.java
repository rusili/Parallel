package com.rooksoto.parallel.view.activityhub;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

/**
 * Created by huilin on 3/4/17.
 */

public class FragmentProfile extends Fragment implements FragmentProfileContract.View{

    private FragmentProfileContract.Presenter profilePresenter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_hub_screen_page, container, false);

        return rootView;
    }

    @Override
    public void setPresenter(FragmentProfileContract.Presenter presenter) {
        profilePresenter = presenter;
    }
}
