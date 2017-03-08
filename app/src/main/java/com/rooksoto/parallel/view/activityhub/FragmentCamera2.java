package com.rooksoto.parallel.view.activityhub;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

/**
 * Created by huilin on 3/4/17.
 */

public class FragmentCamera2 extends Fragment implements FragmentCamera2Contract.View {

    private FragmentCamera2Contract.Presenter cameraPresenter;

    @Override
    public View onCreateView (LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
        ViewGroup rootView = (ViewGroup) inflater.inflate(
                R.layout.fragment_hub_screen_page, container, false);

        return rootView;
    }


    @Override
    public void setPresenter(FragmentCamera2Contract.Presenter presenter) {
        cameraPresenter = presenter;
    }
}
