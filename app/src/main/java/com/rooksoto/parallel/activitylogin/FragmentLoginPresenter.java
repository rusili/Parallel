package com.rooksoto.parallel.activitylogin;

import android.view.View;
import android.view.animation.Animation;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.activitylogin.models.AnimationPackage;

public class FragmentLoginPresenter implements BasePresenter {


    @Override
    public void start () {

    }


    public void setAnimations(AnimationPackage animationPackage){
        animationPackage.getView1().setVisibility(View.VISIBLE);
        animationPackage.getView2().setVisibility(View.VISIBLE);
        animationPackage.getAnimatorSet1().setTarget(animationPackage.getView1());
        animationPackage.getAnimatorSet1().start();
        animationPackage.getAnimatorSet2().setTarget(animationPackage.getView2());
        animationPackage.getAnimatorSet2().start();
    }


    public void setOnEndAnimations (final AnimationPackage animationPackage) {
        animationPackage.getView1().setVisibility(View.VISIBLE);
        animationPackage.getView2().setVisibility(View.VISIBLE);
        animationPackage.getAnimation1().setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {}
            @Override
            public void onAnimationEnd (Animation animation) {
                animationPackage.getView1().startAnimation(animationPackage.getAnimation11());
                animationPackage.getView2().startAnimation(animationPackage.getAnimation22());
            }
            @Override
            public void onAnimationRepeat (Animation animation) {}
        });
        animationPackage.getView1().startAnimation(animationPackage.getAnimation1());
        animationPackage.getView2().startAnimation(animationPackage.getAnimation2());
    }

    @Override
    public void onBackPressed () {

    }

    public void createNewAccount (String email, String username, String password) {
        // TODO: 3/8/17 Creates new Account
    }
}
