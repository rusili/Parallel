package com.rooksoto.parallel.view.activityLogin;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.eftimoff.viewpagertransformers.TabletTransformer;
import com.ogaclejapan.smarttablayout.SmartTabLayout;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItem;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItemAdapter;
import com.ogaclejapan.smarttablayout.utils.v13.FragmentPagerItems;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.CustomSoundEffects;
import com.rooksoto.parallel.utility.CustomToast;
import com.rooksoto.parallel.view.activityHub.ActivityHub;
import com.rooksoto.parallel.view.activityStart.ActivityStart;

public class ActivityLogin extends AppCompatActivity {
    private int containerID = R.id.activity_login_fragment_container;
    private CustomSoundEffects mCustomSoundEffects;
    private CustomAlertDialog mCustomAlertDialog = new CustomAlertDialog();
    private CustomToast mCustomToast = new CustomToast();
    private ImageView logoViewLeft;
    private ImageView logoViewRight;
    private boolean logoVisible = false;
    private FragmentLoginLogin mFragmentLoginLogin;
    private boolean isNew = true;
    private ViewPager viewPager;

    @Override
    protected void onCreate (Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initialize();

        loadFragmentSplash();
    }

    private void initialize () {
        mCustomSoundEffects = new CustomSoundEffects(getWindow().getDecorView().getRootView());
    }

    private void loadFragmentSplash () {
        FragmentLoginSplash mFragmentLoginSplash = new FragmentLoginSplash();
        getFragmentManager().beginTransaction()
                .add(containerID, mFragmentLoginSplash, "Splash")
                .commit();
    }

    private void loadFragmentLogin () {
        mFragmentLoginLogin = FragmentLoginLogin.newInstance(isNew);
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in_left, R.animator.animator_fade_out_right)
                .replace(containerID, mFragmentLoginLogin, "Login")
                .commit();

        if (!logoVisible) {
            logoViewLeft = (ImageView) findViewById(R.id.activity_login_logoleft);
            logoViewLeft.setVisibility(View.VISIBLE);
            Animation fadeInUp = AnimationUtils.loadAnimation(this, R.anim.fadeinup);
            logoViewLeft.startAnimation(fadeInUp);

            logoViewRight = (ImageView) findViewById(R.id.activity_login_logoright);
            logoViewRight.setVisibility(View.VISIBLE);
            Animation fadeInDown = AnimationUtils.loadAnimation(this, R.anim.fadeindown);
            logoViewRight.startAnimation(fadeInDown);
            logoVisible = true;
        }
        isNew = false;
    }

    private void loadFragmentCreateAccount () {
        FragmentLoginCreateAccount mFragmentLoginCreateAccount = new FragmentLoginCreateAccount();
        getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in_right, R.animator.animator_fade_out_right)
                .replace(containerID, mFragmentLoginCreateAccount)
                .commit();
    }

    private void loadFragmentWait () {
        Animation fadeOutDown = AnimationUtils.loadAnimation(this, R.anim.fadeoutdown);
        logoViewLeft.startAnimation(fadeOutDown);
        Animation fadeOutUp = AnimationUtils.loadAnimation(this, R.anim.fadeoutup);
        fadeOutUp.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {}

            @Override
            public void onAnimationEnd (Animation animation) {
                logoViewLeft.setVisibility(View.INVISIBLE);
                logoViewRight.setVisibility(View.INVISIBLE);
                setContentView(R.layout.activity_login_viewpager);
                setupViewpager();
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        logoViewRight.startAnimation(fadeOutUp);
    }

    private void setupViewpager () {
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        SmartTabLayout viewPagerTab = (SmartTabLayout) findViewById(R.id.viewpagertab);

        FragmentPagerItems pages = new FragmentPagerItems(this);
        pages.add(FragmentPagerItem.of("Current Events", FragmentLoginWait.class));
        pages.add(FragmentPagerItem.of("Future Events", FragmentLoginWait.class));
        pages.add(FragmentPagerItem.of("Favorites", FragmentLoginWait.class));

        FragmentPagerItemAdapter adapter = new FragmentPagerItemAdapter(getFragmentManager(), pages);
        viewPager.setAdapter(adapter);
        viewPager.setPageTransformer(true, new TabletTransformer());
        viewPagerTab.setViewPager(viewPager);
    }

    public void onClickToLogin (View view) {
        mCustomSoundEffects.setDefaultClick();
        loadFragmentLogin();
    }

    public void onClicktoCreateAccount (View view) {
        mCustomSoundEffects.setDefaultClick();
        loadFragmentCreateAccount();
    }

    public void onClicktoWait (View view) {
        mCustomSoundEffects.setDefaultClick();
        loadFragmentWait();
        mCustomToast.show(getWindow().getDecorView().getRootView(), "Login successful");
    }

    public void onClicktoActivityHub (View view) {
        mCustomSoundEffects.setDefaultClick();
        Intent fromActivityStartToActivityHub = new Intent(this, ActivityHub.class);
        startActivity(fromActivityStartToActivityHub);
        finish();
    }

    @Override
    public void onBackPressed () {
        Fragment currentFragment = getFragmentManager().findFragmentById(containerID);
        if (currentFragment instanceof FragmentLoginLogin || currentFragment instanceof FragmentLoginWait) {
            mCustomAlertDialog.exit(this);
        } else if (currentFragment instanceof FragmentLoginCreateAccount) {
            loadFragmentLogin();
        } else {
            super.onBackPressed();
        }
    }

    public void onClickToActivityStart (View view) {
        finish();
        Intent intentToActivityStart = new Intent(this, ActivityStart.class);
        startActivity(intentToActivityStart);
    }
}
