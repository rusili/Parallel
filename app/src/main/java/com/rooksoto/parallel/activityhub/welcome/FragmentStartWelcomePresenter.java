package com.rooksoto.parallel.activityhub.welcome;

import android.app.Activity;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;

public class FragmentStartWelcomePresenter implements BasePresenter {
    private View view;
    private View viewAnimation1;
    private View viewAnimation2;

    private String[] arrayString;
    private int counter = 0;
    private boolean started = false;
    private Handler handler = new Handler();

    private Runnable runnableHTextView = new Runnable() {
        @Override
        public void run () {
            final HTextView textView = (HTextView) view.findViewById(R.id.fragment_start_welcome_htextview);
            textView.setAnimateType(HTextViewType.SCALE);
            textView.animateText(arrayString[counter]); // animate
            counter++;
            if (started) {
                start();
            }
            if (counter == 1) {
                playAudio(R.raw.welcome);
            }
            if (counter == 5) {
                stop();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run () {
                        viewAnimation1.setVisibility(View.VISIBLE);
                        viewAnimation2.setVisibility(View.VISIBLE);
                        Animation fadeinleft = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeinleft);
                        Animation fadein = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadein);
                        startOnAnimationsEnd(fadeinleft, fadein);
                    }
                }, 1000);
            }
        }
    };

    @Override
    public void start () {
        started = true;
        handler.postDelayed(runnableHTextView, 1500);
    }

    public void onBackPressedOverride (View viewP) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog();
        customAlertDialog.exit(viewP.getContext());
    }

    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
        ((Activity) viewP.getContext()).getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in, R.animator.animator_fade_out_right)
                .replace(containerID, fragmentP, id)
                .commit();
    }

    private void playAudio (int rawID) {
        MediaPlayer mediaPlayer = MediaPlayer.create(view.getContext(), rawID);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
            }
        });
        mediaPlayer.start();
    }

    public void stop () {
        started = false;
        handler.removeCallbacks(runnableHTextView);
    }

    public void setRunnableHTextView (View viewP, String[] arrayStringP, View viewAnimation1, View viewAnimation2) {
        this.view = viewP;
        this.arrayString = arrayStringP;
        this.viewAnimation1 = viewAnimation1;
        this.viewAnimation2 = viewAnimation2;
    }

    private void startOnAnimationsEnd (Animation animation1P, final Animation animation2P) {
        animation1P.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart (Animation animation) {
            }

            @Override
            public void onAnimationEnd (Animation animation) {
                viewAnimation2.startAnimation(animation2P);
            }

            @Override
            public void onAnimationRepeat (Animation animation) {
            }
        });
        viewAnimation1.startAnimation(animation1P);
    }

}
