package com.rooksoto.parallel.activitylogin;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.createaccount.FragmentLoginCreateAccount;
import com.rooksoto.parallel.activitylogin.login.FragmentLoginLogin;
import com.rooksoto.parallel.activitylogin.splash.FragmentLoginSplash;
import com.rooksoto.parallel.activitylogin.wait.FragmentLoginWait;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomToast;

public class ActivityLogin extends AppCompatActivity implements BaseView{
    private ActivityLoginPresenter activityLoginPresenter = new ActivityLoginPresenter();
    private int containerID = R.id.activity_login_fragment_container;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private CustomToast mCustomToast = new CustomToast();
    private ImageView logoViewLeft;
    private ImageView logoViewRight;
    private boolean logoVisible = false;
    private FragmentLoginLogin mFragmentLoginLogin;
    private boolean isNew = true;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();
    }

    public void initialize () {
        activityLoginPresenter.giveActivity(this);
        setViews();
    }

    @Override
    public void setViews () {
        loadFragmentSplash();
    }

    private void loadFragmentSplash () {
        FragmentLoginSplash mFragmentLoginSplash = new FragmentLoginSplash();
        activityLoginPresenter.loadFragment(mFragmentLoginSplash,
                R.animator.animator_nothing, R.animator.animator_fade_out, containerID, "Splash");
    }

    private void loadFragmentLogin () {
        mFragmentLoginLogin = FragmentLoginLogin.newInstance(isNew);
        activityLoginPresenter.loadFragment(mFragmentLoginLogin, R.animator.animator_fade_in, R.animator.animator_fade_out_right, containerID, "Login");
        activityLoginPresenter.checkLogoVisibility(logoVisible, logoViewLeft, logoViewRight);

        isNew = false;
    }

    private void loadFragmentCreateAccount () {
        FragmentLoginCreateAccount mFragmentLoginCreateAccount = new FragmentLoginCreateAccount();
        activityLoginPresenter.loadFragment(mFragmentLoginCreateAccount,
                R.animator.animator_fade_in_right, R.animator.animator_fade_out_right, containerID, "Create");
    }

    private void loadFragmentWait () {
        Animation fadeOutDown = AnimationUtils.loadAnimation(this, R.anim.fadeoutdown);
        Animation fadeOutUp = AnimationUtils.loadAnimation(this, R.anim.fadeoutup);
        activityLoginPresenter.startAnimations(logoViewLeft, logoViewRight, fadeOutDown, fadeOutUp);

        FragmentLoginWait mFragmentLoginWait = new FragmentLoginWait();
        activityLoginPresenter.loadFragment(mFragmentLoginWait,
                R.animator.animator_fade_in_right, R.animator.animator_fade_out_right, containerID, "Wait");
    }

    @Override
    public void onBackPressed () {
        activityLoginPresenter.onBackPressedOverride();
    }

    public void onClickToActivityStart (View view) {
        activityLoginPresenter.intentToActivityStart();
    }
}
