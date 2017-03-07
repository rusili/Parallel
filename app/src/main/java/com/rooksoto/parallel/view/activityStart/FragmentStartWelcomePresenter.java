package com.rooksoto.parallel.view.activityStart;


import android.media.MediaPlayer;
import android.view.View;

import com.rooksoto.parallel.R;

/**
 * Created by rook on 3/6/17.
 */

public class FragmentStartWelcomePresenter implements FragmentStartWelcomeContract.Presenter {

    View view;

    @Override
    public void start() {

    }

    @Override
    public void playWelcomeVoice() {

        MediaPlayer mediaPlayer = MediaPlayer.create(view.getContext(), R.raw.welcome);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
            }
        });
        mediaPlayer.start();
    }
}
