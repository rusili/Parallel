package com.rooksoto.parallel.activitylogin.splash;

import android.animation.AnimatorSet;
import android.app.Activity;
import android.app.Fragment;
import android.view.View;
import android.view.animation.Animation;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;

public class FragmentLoginSplashPresenter implements BasePresenter {
    private View viewAnimatorSet1;
    private View viewAnimatorSet2;
    private View viewAnimation1;
    private View viewAnimation2;

    @Override
    public void start () {
    }

    // Animations:
    public void setAnimatorSetViews (View view1P, View view2P) {
        this.viewAnimatorSet1 = view1P;
        this.viewAnimatorSet2 = view2P;

    }

    public void setAnimationViews (View view1P, View view2P) {
        this.viewAnimation1 = view1P;
        this.viewAnimation2 = view2P;
    }

    public void startOnAnimationsEnd (Animation animation1P, Animation animation2P, final Animation animation11P, final Animation animation22P) {
        animation1P.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {
            }

            @Override
            public void onAnimationEnd (Animation animation) {
                viewAnimation1.startAnimation(animation11P);
                viewAnimation2.startAnimation(animation22P);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        viewAnimation1.setVisibility(View.VISIBLE);
        viewAnimation2.setVisibility(View.VISIBLE);

        viewAnimation1.startAnimation(animation1P);
        viewAnimation2.startAnimation(animation2P);
    }

    public void startAnimatorSet (AnimatorSet animationSet1P, AnimatorSet animationSet2P) {
        animationSet1P.setTarget(viewAnimatorSet1);
        animationSet2P.setTarget(viewAnimatorSet2);

        viewAnimatorSet1.setVisibility(View.VISIBLE);
        viewAnimatorSet2.setVisibility(View.VISIBLE);

        animationSet1P.start();
        animationSet2P.start();
    }

    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
        ((Activity) viewP.getContext()).getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in, R.animator.animator_fade_out_right)
                .replace(containerID, fragmentP, id)
                .commit();
    }
}
