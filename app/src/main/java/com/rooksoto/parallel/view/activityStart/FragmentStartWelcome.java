package com.rooksoto.parallel.view.activityStart;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.rooksoto.parallel.R;

public class FragmentStartWelcome extends Fragment implements FragmentStartWelcomeContract.View{

    private View mView;
    private String[] welcomeText = new String[] {"Welcome", "to", "C4Q's", "3.3 Demo Day", "Enjoy"};
    private int counter = 0;

    private boolean started = false;
    private Handler handler = new Handler();

    private FragmentStartWelcomeContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_welcome, container, false);
        initialize();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(FragmentStartWelcomeContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize () {
        presenter = new FragmentStartWelcomePresenter(mView);
        setPresenter(presenter);
        start();
    }

    private void playWelcomeVoice () {
        presenter.playWelcomeVoice();
    }

    // This method is related to view. Keeping in fragment.
    private Runnable runnableHTextView = new Runnable() {
        @Override
        public void run () {
            final HTextView textView = (HTextView) mView.findViewById(R.id.fragment_start_welcome_htextview);
            textView.setAnimateType(HTextViewType.SCALE);
            textView.animateText(welcomeText[counter]); // animate
            counter++;
            if (started) {
                start();
            }
            if (counter == 1) {
                playWelcomeVoice();
            }
            if (counter == 5) {
                stop();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run () {
                        runHostAnimation();
                    }
                }, 1000);
            }
        }
    };

    // This method is related to view. Keeping in fragment.
    private void runHostAnimation () {
        TextView textViewHostedBy = (TextView) mView.findViewById(R.id.fragment_start_welcome_hostedby);
        final TextView textViewHost = (TextView) mView.findViewById(R.id.fragment_start_welcome_host);
        textViewHostedBy.setVisibility(View.VISIBLE);

        Animation fadeInLeft = AnimationUtils.loadAnimation(mView.getContext(), R.anim.fadeinleft);
        fadeInLeft.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {
            }

            @Override
            public void onAnimationEnd (Animation animation) {
                textViewHost.setVisibility(View.VISIBLE);
                Animation fadeIn = AnimationUtils.loadAnimation(mView.getContext(), R.anim.fadein);
                textViewHost.startAnimation(fadeIn);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        textViewHostedBy.startAnimation(fadeInLeft);

    }

    public void stop () {
        started = false;
        handler.removeCallbacks(runnableHTextView);
    }

    public void start () {
        started = true;
        handler.postDelayed(runnableHTextView, 1500);
    }
}
