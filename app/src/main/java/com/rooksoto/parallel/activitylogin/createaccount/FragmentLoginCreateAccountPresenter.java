package com.rooksoto.parallel.activitylogin.createaccount;

import android.app.Activity;
import android.app.Fragment;
import android.view.View;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;

public class FragmentLoginCreateAccountPresenter implements BasePresenter {
    @Override
    public void start () {
    }

    @Override
    public void onBackPressedOverride (View viewP) {}

    public void createNewAccount (String email, String username, String password) {
        // TODO: 3/8/17 Creates new Account
    }

    public void setOnClickReplace (Fragment fragment, View viewP, int containerID, String id) {
        ((Activity) viewP.getContext()).getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in_right, R.animator.animator_fade_out_right)
                .replace(containerID, fragment, id)
                .commit();
    }
}
