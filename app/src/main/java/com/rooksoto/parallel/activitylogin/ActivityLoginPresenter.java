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
import com.rooksoto.parallel.activityHub.ActivityHub;
import com.rooksoto.parallel.activityStart.ActivityStart;
import com.rooksoto.parallel.activitylogin.createaccount.FragmentLoginCreateAccount;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;
import com.rooksoto.parallel.activitylogin.wait.FragmentLoginWait;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomToast;

public class ActivityLoginPresenter implements BasePresenter{
    private Activity activity;
    private int containerID = R.id.activity_login_fragment_container;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private CustomToast mCustomToast = new CustomToast();
    private ImageView logoViewLeft;
    private ImageView logoViewRight;
    private boolean logoVisible = false;
    private FragmentLoginLogin mFragmentLoginLogin;
    private boolean isNew = true;

    @Override
    public void start () {
    }

    @Override
    public void onBackPressed () {
    }

    public void onBackPressedOverride () {
        Fragment currentFragment = activity.getFragmentManager().findFragmentById(containerID);
        if (currentFragment instanceof FragmentLoginLogin || currentFragment instanceof FragmentLoginWait) {
            mCustomAlertDialog.exit(activity);
        } else if (currentFragment instanceof FragmentLoginCreateAccount) {
        } else {
            activity.onBackPressed();
        }
    }

    public void onClickToActivityStart (View view) {
        activity.finish();
        Intent intentToActivityStart = new Intent(activity, ActivityStart.class);
        activity.startActivity(intentToActivityStart);
    }

    public void loadFragment (Fragment fragmentP, int animator1, int animator2, int containerID, String id) {
        activity.getFragmentManager().beginTransaction()
                .setCustomAnimations(animator1, animator2)
                .add(containerID, fragmentP, id)
                .commit();
    }

    public void startAnimations (final View view1, final View view2, Animation animation1P, Animation animation2P){
        view1.setVisibility(View.VISIBLE);
        view2.setVisibility(View.VISIBLE);

        animation1P.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {}
            @Override
            public void onAnimationEnd (Animation animation) {
                view1.setVisibility(View.INVISIBLE);
                view2.setVisibility(View.INVISIBLE);
            }
            @Override
            public void onAnimationRepeat (Animation animation) {}
        });
        view1.startAnimation(animation1P);
        view2.startAnimation(animation2P);
    }

    public void checkLogoVisibility (boolean logoVisibleP, ImageView logoViewLeft, ImageView logoViewRight) {
        if (!logoVisible) {
            logoViewLeft = (ImageView) activity.findViewById(R.id.activity_login_logoleft);
            logoViewLeft.setVisibility(View.VISIBLE);
            Animation fadeInUp = AnimationUtils.loadAnimation(activity, R.anim.fadeinup);
            logoViewLeft.startAnimation(fadeInUp);

            logoViewRight = (ImageView) activity.findViewById(R.id.activity_login_logoright);
            logoViewRight.setVisibility(View.VISIBLE);
            Animation fadeInDown = AnimationUtils.loadAnimation(activity, R.anim.fadeindown);
            logoViewRight.startAnimation(fadeInDown);
            logoVisible = true;
        }
    }

    public void intentToActivityStart (){
        Intent fromActivityStartToActivityHub = new Intent(activity, ActivityHub.class);
        activity.startActivity(fromActivityStartToActivityHub);
        activity.finish();
    }

    public void giveActivity (Activity activityP) {
        this.activity = activityP;
    }
}
