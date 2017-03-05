package com.rooksoto.parallel.fragments.activityStart;

import android.media.MediaPlayer;
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

public class FragmentStartWelcome extends Fragment {
    private View mView;
    private String[] welcomeText = new String[] {"Welcome", "to", "C4Q's", "3.3 Demo Day", "Enjoy"};
    private int counter = 0;

    private boolean started = false;
    private Handler handler = new Handler();

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_welcome, container, false);
        initialize();
        return mView;
    }

    private void initialize () {
        start();
    }

    private void playWelcomeVoice () {
        MediaPlayer mediaPlayer = MediaPlayer.create(mView.getContext(), R.raw.welcome);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
            }
        });
        mediaPlayer.start();
    }

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
