package com.rooksoto.parallel.activitylogin;

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
import com.rooksoto.parallel.activitylogin.models.AnimationPackage;

public class FragmentLoginSplash extends Fragment implements BaseView{
    private View mView;
    private FragmentLoginPresenter fragmentLoginPresenter;
    private ImageView parallelLineLeft;
    private ImageView parallelLineRight;
    private ImageView parallelWordLeft;
    private ImageView parallelWordRight;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_login_splash, container, false);
        initialize();
        return mView;
    }

    @Override
    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        parallelWordLeft = (ImageView) mView.findViewById(R.id.fragment_login_splash_wordleft);
        parallelWordRight = (ImageView) mView.findViewById(R.id.fragment_login_splash_wordright);
        parallelLineLeft = (ImageView) mView.findViewById(R.id.fragment_login_splash_lineleft);
        parallelLineRight = (ImageView) mView.findViewById(R.id.fragment_login_splash_lineright);

        splashLineAnimation();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                splashWordAnimation();
            }
        }, 1250);
    }

    private void splashWordAnimation () {
        AnimatorSet setLeftIn = (AnimatorSet) AnimatorInflater.loadAnimator(mView.getContext(),
                R.animator.animator_fade_in_rightsplash);
        AnimatorSet setRightIn = (AnimatorSet) AnimatorInflater.loadAnimator(mView.getContext(),
                R.animator.animator_fade_in_leftsplash);

        AnimationPackage animationPackage1 = new AnimationPackage(mView, parallelWordLeft, parallelWordRight);
        animationPackage1.setAnimationSet(setLeftIn, setRightIn);

        fragmentLoginPresenter.setAnimations(animationPackage1);
    }

    private void splashLineAnimation () {
        Animation accelerateUp = AnimationUtils.loadAnimation(mView.getContext(), R.anim.accelerateup);
        Animation accelerateDown = AnimationUtils.loadAnimation(mView.getContext(), R.anim.acceleratedown);
        Animation slowDown = AnimationUtils.loadAnimation(mView.getContext(), R.anim.slowdown);
        Animation slowUp = AnimationUtils.loadAnimation(mView.getContext(), R.anim.slowup);

        AnimationPackage animationPackage2 = new AnimationPackage(mView, parallelLineLeft, parallelLineRight);
        animationPackage2.setOnAnimationEnd(accelerateDown, accelerateUp, slowUp, slowDown);

        fragmentLoginPresenter.setOnEndAnimations(animationPackage2);
    }
}
