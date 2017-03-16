package com.rooksoto.parallel.activitylogin.wait;

import android.app.Activity;
import android.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.utility.CustomAlertDialog;

public class FragmentLoginWaitPresenter implements BasePresenter {
    private View view;
    private ViewPager viewPager;
    private SmartTabLayout smartTabLayout;

    @Override
    public void start () {
    }

    @Override
    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
    }

    public void setViewPager (View viewP, ViewPager viewPagerP, SmartTabLayout smartTabLayoutP) {
        this.view = viewP;
        this.viewPager = viewPagerP;
        this.smartTabLayout = smartTabLayoutP;

        FragmentPagerItems pages = new FragmentPagerItems(view.getContext());
        pages.add(FragmentPagerItem.of("Current Events", FragmentLoginWaitPageCurrent.class));
        pages.add(FragmentPagerItem.of("Future Events", FragmentLoginWaitPageFuture.class));
        pages.add(FragmentPagerItem.of("Favorites", FragmentLoginWaitPageFavorites.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(((Activity) view.getContext()).getFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new TabletTransformer());
        smartTabLayout.setViewPager(viewPager);
    }
}
