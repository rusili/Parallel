package com.rooksoto.parallel.view.activityLogin;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rooksoto.parallel.R;

public class FragmentLoginSplash extends Fragment {
    private View mView;
    private ImageView parallelLineLeft;
    private ImageView parallelLineRight;
    private ImageView parallelLineLeft2;
    private ImageView parallelLineRight2;
    private ImageView parallelWordLeft;
    private ImageView parallelWordRight;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_splash, container, false);
        splashLineAnimation();

        return mView;
    }

    private void splashPostLineAnimation () {
        parallelLineLeft2 = (ImageView) mView.findViewById(R.id.fragment_login_splash_lineleft2);
        parallelLineRight2 = (ImageView) mView.findViewById(R.id.fragment_login_splash_lineright2);
        parallelLineLeft2.setVisibility(View.VISIBLE);
        parallelLineRight2.setVisibility(View.VISIBLE);

        final AnimatorSet setFadeDown = (AnimatorSet) AnimatorInflater.loadAnimator(mView.getContext(),
                R.animator.animator_fade_out_down);
        setFadeDown.setTarget(parallelLineLeft2);
        setFadeDown.start();

        final AnimatorSet setFadeUp = (AnimatorSet) AnimatorInflater.loadAnimator(mView.getContext(),
                R.animator.animator_fade_out_up);
        setFadeUp.setTarget(parallelLineRight2);
        setFadeUp.start();
    }

    private void splashWordAnimation () {
        parallelWordLeft = (ImageView) mView.findViewById(R.id.fragment_login_splash_wordleft);
        parallelWordRight = (ImageView) mView.findViewById(R.id.fragment_login_splash_wordright);
        parallelWordLeft.setVisibility(View.VISIBLE);
        parallelWordRight.setVisibility(View.VISIBLE);

        AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(mView.getContext(),
                R.animator.animator_fade_in_left);
        setLeftIn.setTarget(parallelWordLeft);
        setLeftIn.start();

        AnimatorSet setRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(mView.getContext(),
                R.animator.animator_fade_in_right);
        setRightIn.setTarget(parallelWordRight);
        setRightIn.start();
    }

    private void splashLineAnimation () {
        parallelLineLeft = (ImageView) mView.findViewById(R.id.fragment_login_splash_lineleft);
        parallelLineRight = (ImageView) mView.findViewById(R.id.fragment_login_splash_lineright);
        parallelLineLeft.setVisibility(View.VISIBLE);
        parallelLineRight.setVisibility(View.VISIBLE);

        Animation accelerateUp = AnimationUtils.loadAnimation(mView.getContext(), R.anim.accelerateup);
        accelerateUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {
            }

            @Override
            public void onAnimationEnd (Animation animation) {
                Animation slowDown = AnimationUtils.loadAnimation(mView.getContext(), R.anim.slowdown);
                slowDown.setAnimationListener(new Animation.AnimationListener() {
                    @Override
                    public void onAnimationStart (Animation animation) {
                    }

                    @Override
                    public void onAnimationEnd (Animation animation) {
                        splashWordAnimation();
                    }

                    @Override
                    public void onAnimationRepeat (Animation animation) {
                    }
                });
                parallelLineRight.startAnimation(slowDown);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        parallelLineRight.startAnimation(accelerateUp);

        Animation accelerateDown = AnimationUtils.loadAnimation(mView.getContext(), R.anim.acceleratedown);
        accelerateDown.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {
            }

            @Override
            public void onAnimationEnd (Animation animation) {
                Animation slowUp = AnimationUtils.loadAnimation(mView.getContext(), R.anim.slowup);
                parallelLineLeft.startAnimation(slowUp);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        parallelLineLeft.startAnimation(accelerateDown);
    }
}
