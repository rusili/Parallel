package com.rooksoto.parallel.activitylogin.wait;


import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.R;

public class FragmentLoginWait extends Fragment {
    private View mView;
    private ViewPager viewPager;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_viewpager, container, false);
        setupViewpager();
        return mView;
    }

    private void setupViewpager () {
        viewPager = (ViewPager) mView.findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) mView.findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(mView.getContext());
        pages.add(FragmentPagerItem.of("Current Events", FragmentLoginWaitPageCurrent.class));
        pages.add(FragmentPagerItem.of("Future Events", FragmentLoginWaitPageFuture.class));
        pages.add(FragmentPagerItem.of("Favorites", FragmentLoginWaitPageFavorites.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new TabletTransformer());
        viewPagerTab.setViewPager(viewPager);
    }
}
