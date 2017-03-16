package com.rooksoto.parallel.activitylogin;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHub;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomToast;

public class ActivityLoginPresenter implements BasePresenter {
    private Activity activity;
    private int containerID = R.id.activity_login_fragment_container;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private CustomToast mCustomToast = new CustomToast();
    private boolean logoVisible = false;
    private FragmentLoginLogin mFragmentLoginLogin;
    private boolean isNew = true;

    @Override
    public void start () {
    }

    @Override
    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
    }

    public void loadFragment (Fragment fragmentP, int animator1, int animator2, int containerID, String id) {
        activity.getFragmentManager().beginTransaction()
                .setCustomAnimations(animator1, animator2)
                .add(containerID, fragmentP, id)
                .addToBackStack(null)
                .commit();
    }

    public void startAnimations (final View view1, final View view2, Animation animation1P, Animation animation2P) {
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);

        animation1P.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {
            }

            @Override
            public void onAnimationEnd (Animation animation) {
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        view1.startAnimation(animation1P);
        view2.startAnimation(animation2P);
    }

    public void checkLogoVisibility (View viewP) {
        if (!logoVisible) {
            ImageView logoViewLeft = (ImageView) ((Activity) viewP.getContext()).findViewById(R.id.activity_login_logoleft);
            logoViewLeft.setVisibility(View.VISIBLE);
            Animation fadeInUp = AnimationUtils.loadAnimation(viewP.getContext(), R.anim.fadeinup);
            logoViewLeft.startAnimation(fadeInUp);

            ImageView logoViewRight = (ImageView) ((Activity) viewP.getContext()).findViewById(R.id.activity_login_logoright);
            logoViewRight.setVisibility(View.VISIBLE);
            Animation fadeInDown = AnimationUtils.loadAnimation(viewP.getContext(), R.anim.fadeindown);
            logoViewRight.startAnimation(fadeInDown);
            logoVisible = true;
        }
    }

    public void intentToActivityStart () {
        Intent fromActivityStartToActivityHub = new Intent(activity, ActivityHub.class);
        activity.startActivity(fromActivityStartToActivityHub);
        activity.finish();
    }

    public void giveActivity (Activity activityP, View rootView) {
        this.activity = activityP;
    }
}
