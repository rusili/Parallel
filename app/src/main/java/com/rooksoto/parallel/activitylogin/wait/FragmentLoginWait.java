package com.rooksoto.parallel.activitylogin.wait;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;

public class FragmentLoginWait extends Fragment implements BaseView{
    private FragmentLoginWaitPresenter fragmentLoginWaitPresenter = new FragmentLoginWaitPresenter();
    private View mView;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_viewpager, container, false);
        initialize();
        return mView;
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        ViewPager viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) mView.findViewById(R.id.viewpagertab);

        fragmentLoginWaitPresenter.setViewPager(mView, viewPager, viewPagerTab);
    }
}
