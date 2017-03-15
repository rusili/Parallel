package com.rooksoto.parallel.activitylogin.splash;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;
import com.rooksoto.parallel.utility.CustomAlertDialog;

public class FragmentLoginSplash extends Fragment implements BaseView {
    private FragmentLoginSplashPresenter fragmentLoginSplashPresenter = new FragmentLoginSplashPresenter();

    private View view;
    private int containerID = R.id.activity_login_fragment_container;
    private ImageView parallelLineLeft;
    private ImageView parallelLineRight;
    private ImageView parallelWordLeft;
    private ImageView parallelWordRight;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_login_splash, container, false);
        initialize();
        return view;
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        parallelWordLeft = (ImageView) view.findViewById(R.id.fragment_login_splash_wordleft);
        parallelWordRight = (ImageView) view.findViewById(R.id.fragment_login_splash_wordright);
        parallelLineLeft = (ImageView) view.findViewById(R.id.fragment_login_splash_lineleft);
        parallelLineRight = (ImageView) view.findViewById(R.id.fragment_login_splash_lineright);
        parallelWordLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                //fragmentLoginSplashPresenter.setOnClickReplace(new FragmentLoginLogin().newInstance(true), parallelWordLeft, containerID, "Login");
            }
        });

        fragmentLoginSplashPresenter.setAnimationViews(parallelLineLeft, parallelLineRight);
        fragmentLoginSplashPresenter.setAnimatorSetViews(parallelWordLeft, parallelWordRight);

        splashLineAnimation();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                splashWordAnimation();
            }
        }, 1250);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run () {
                fragmentLoginSplashPresenter.setOnClickReplace(new FragmentLoginLogin().newInstance(true), parallelWordLeft, containerID, "Login");
            }
        }, 3000);

    }

    private void splashWordAnimation () {
        AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(),
                R.animator.animator_fade_in_rightsplash);
        AnimatorSet setRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(view.getContext(),
                R.animator.animator_fade_in_leftsplash);

        fragmentLoginSplashPresenter.startAnimatorSet(setLeftIn, setRightIn);
    }

    private void splashLineAnimation () {
        Animation accelerateUp = AnimationUtils.loadAnimation(view.getContext(), R.anim.accelerateup);
        Animation accelerateDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.acceleratedown);
        Animation slowDown = AnimationUtils.loadAnimation(view.getContext(), R.anim.slowdown);
        Animation slowUp = AnimationUtils.loadAnimation(view.getContext(), R.anim.slowup);

        fragmentLoginSplashPresenter.startOnAnimationsEnd(accelerateDown, accelerateUp, slowUp, slowDown);
    }
}
