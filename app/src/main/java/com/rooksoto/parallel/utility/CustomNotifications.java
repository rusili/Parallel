package com.rooksoto.parallel.utility;

import android.app.NotificationManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.graphics.Color;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.IBinder;
import android.support.v7.app.NotificationCompat;
import android.widget.RemoteViews;

import com.rooksoto.parallel.R;

// Custom notifications on a small remote view. Includes vibration, lights, & sound. Bound to the KillService.class
// Parameters:  1) Parent context
// Needs:       1) Custom layout: notification_bar
//              2) KillService.class

public class CustomNotifications {
    private Context mContext;

    public CustomNotifications (Context contextParam) {
        this.mContext = contextParam;
    }

    public void show_Station () {

    }

    private void show () {
        // Using RemoteViews to bind custom layouts into Notification
        final RemoteViews smallView = new RemoteViews(mContext.getPackageName(), R.layout.notification_layout);
        final Uri defaultSoundURI = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        ServiceConnection mConnection = new ServiceConnection() {
            public void onServiceConnected (ComponentName className, IBinder binder) {
                ((KillService.KillBinder) binder).service.startService(new Intent(mContext, KillService.class));
                android.app.Notification mBuilder = new NotificationCompat.Builder(mContext)
                        .setSmallIcon(R.drawable.ic_notification)
                        .setContentTitle("Parallel Event")
                        .setOngoing(true)
                        .setContent(smallView)
                        .setVibrate(new long[] {500, 1000})
                        .setLights(Color.MAGENTA, 2000, 2000)
                        .setSound(defaultSoundURI)
                        .build();

                NotificationManager noteMan = (NotificationManager) mContext.getSystemService(Context.NOTIFICATION_SERVICE);
                noteMan.notify(555, mBuilder);
            }

            @Override
            public void onServiceDisconnected (ComponentName name) {
            }
        };
        mContext.bindService(new Intent(mContext, KillService.class), mConnection, Context.BIND_AUTO_CREATE);
    }
}