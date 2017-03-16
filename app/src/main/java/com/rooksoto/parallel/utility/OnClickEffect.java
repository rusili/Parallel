package com.rooksoto.parallel.utility;

import android.os.Handler;
import android.support.v4.content.ContextCompat;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

import com.rooksoto.parallel.R;

public class OnClickEffect {
    private static Handler handler = new Handler();

    public static void setBinaryLeft(final ImageView view){
        view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.red));
        Animation fadeout = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeoutonclick);
        view.startAnimation(fadeout);

        handler.postDelayed(new Runnable() {
            public void run() {
                view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.red_transparent));
            }
        }, 250);
    }

    public static void setBinaryRight(final ImageView view){
        view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.green));
        Animation fadeout = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeoutonclick);
        view.startAnimation(fadeout);

        handler.postDelayed(new Runnable() {
            public void run() {
                view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.green_transparent));
            }
        }, 250);
    }

    public static void setAltImage(final ImageView view){
        view.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.teal));
        Animation fadeout = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeoutonclick);
        view.startAnimation(fadeout);
    }

    public static void setButton(final Button button) {
        button.setTextColor(button.getContext().getResources().getColor(R.color.teal));

        handler.postDelayed(new Runnable() {
            public void run() {
                button.setTextColor(button.getContext().getResources().getColor(R.color.teal));
            }
        }, 150);
    }
}
