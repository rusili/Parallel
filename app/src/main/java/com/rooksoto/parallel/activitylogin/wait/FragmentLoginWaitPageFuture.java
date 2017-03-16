package com.rooksoto.parallel.activitylogin.wait;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;

public class FragmentLoginWaitPageFuture extends Fragment implements BaseView{
    private View mView;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_wait_event, container, false);
        return mView;
    }

    @Override
    public void initialize () {

    }

    @Override
    public void setViews () {

    }

}
