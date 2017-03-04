package com.rooksoto.parallel.fragments.activityStart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.SoundPoolPlayer;

public class FragmentStartWelcome extends Fragment {
    private View mView;
    private SoundPoolPlayer mSoundPoolPlayer;
    private FrameLayout mFrameLayoutLeft;
    private FrameLayout mFrameLayoutRight;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_welcome, container, false);
        initialize();
        return mView;
    }

    private void initialize(){
        welcomeAnimation();
    }

    private void playWelcomeVoice () {
        mSoundPoolPlayer = new SoundPoolPlayer(mView.getContext());
        mSoundPoolPlayer.playShortResource(R.raw.welcome);
        mSoundPoolPlayer.release();
    }

    private void welcomeAnimation () {
//        mFrameLayoutLeft = (FrameLayout) mView.findViewById(R.id.fragment_start_welcome_top);
//        mFrameLayoutRight = (FrameLayout) mView.findViewById(R.id.fragment_start_welcome_righthalf);
//
//        Animation leftSide = AnimationUtils.loadAnimation(mView.getContext(), R.anim.falldown);
//        Animation rightSide = AnimationUtils.loadAnimation(mView.getContext(), R.anim.fallup);
//        mFrameLayoutLeft.startAnimation(leftSide);
//        mFrameLayoutRight.setLayoutAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart (Animation animation) {}
//            @Override
//            public void onAnimationEnd (Animation animation) {
//                showWelcome();
//                playWelcomeVoice();
//            }
//            @Override
//            public void onAnimationRepeat (Animation animation) {}
//        });
//        mFrameLayoutRight.startAnimation(rightSide);
    }

    private void showWelcome () {
        //?
    }
}
