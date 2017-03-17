package com.rooksoto.parallel.activitylogout;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.rooksoto.parallel.R;

/**
 * Created by rook on 3/16/17.
 */

public class ActivityLogout extends AppCompatActivity{

    TextView tvLogoutText;
    TextView tvLogoutTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logout);

        tvLogoutText = (TextView) findViewById(R.id.tv_logout_warning_text);
        tvLogoutTimer = (TextView) findViewById(R.id.tv_logout_warning_timer);

    }

    @Override
    protected void onResume() {
        super.onResume();
        startCountDownTimer();
    }

    private void startCountDownTimer() {
        new CountDownTimer(300000, 1000) {

            public void onTick(long millisUntilFinished) {
                tvLogoutTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
                tvLogoutTimer.setText("" +
                        millisUntilFinished / 60 +
                        "m" +
                        " " +
                        millisUntilFinished % 60 +
                        "s");
            }

            public void onFinish() {
                tvLogoutTimer.setText("Exiting...");
                finishAffinity();
            }
        }.start();
    }
}
