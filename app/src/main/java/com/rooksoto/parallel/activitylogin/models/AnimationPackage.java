package com.rooksoto.parallel.activitylogin.models;

import android.animation.AnimatorSet;
import android.view.View;
import android.view.animation.Animation;

public class AnimationPackage {
    private View layoutView;
    private View view1;
    private View view2;
    private Animation animation1;
    private Animation animation2;
    private Animation animation11;
    private Animation animation22;
    private AnimatorSet animatorSet1;
    private AnimatorSet animatorSet2;

    public AnimationPackage(View layoutView, View view1, View view2){
        this.layoutView = layoutView;
        this.view1 = view1;
        this.view2 = view2;
    }

    public void setAnimation(Animation animation1, Animation animation2){
        this.animation1 = animation1;
        this.animation2 = animation2;
    }

    public void setOnAnimationEnd (Animation animation1, Animation animation2, Animation animation11, Animation animation22) {
        this.animation1 = animation1;
        this.animation2 = animation2;
        this.animation11 = animation11;
        this.animation22 = animation22;
    }

    public void setAnimationSet(AnimatorSet animatorSet1, AnimatorSet animatorSet2){
        this.animatorSet1 = animatorSet1;
        this.animatorSet2 = animatorSet2;
    }

    public View getLayoutView () {
        return layoutView;
    }

    public View getView1 () {
        return view1;
    }

    public View getView2 () {
        return view2;
    }

    public Animation getAnimation1 () {
        return animation1;
    }

    public Animation getAnimation2 () {
        return animation2;
    }

    public Animation getAnimation11 () {
        return animation11;
    }

    public Animation getAnimation22 () {
        return animation22;
    }

    public AnimatorSet getAnimatorSet1 () {
        return animatorSet1;
    }

    public AnimatorSet getAnimatorSet2 () {
        return animatorSet2;
    }


}
