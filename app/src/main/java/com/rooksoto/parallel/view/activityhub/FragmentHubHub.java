package com.rooksoto.parallel.view.activityhub;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

public class FragmentHubHub extends Fragment implements FragmentHubHubContract.View{
    private View mView;
    private FragmentHubHubContract.Presenter hubHubPresenter;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_hub_hub, container, false);
        return mView;
    }

    @Override
    public void setPresenter(FragmentHubHubContract.Presenter presenter) {
        hubHubPresenter = presenter;
    }
}
