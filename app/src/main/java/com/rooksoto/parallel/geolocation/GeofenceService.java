package com.rooksoto.parallel.geolocation;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by rook on 3/2/17.
 */

public class GeofenceService extends IntentService {

    private static final String TAG = "GeofenceService";

    public GeofenceService () {
        super(TAG);
    }

    @Override
    protected void onHandleIntent (Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if (event.hasError()) {
            // TODO: 3/2/17 - Handle Error
        } else {
            int transition = event.getGeofenceTransition();
            List <Geofence> geofences = event.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            String requestID = geofence.getRequestId();

            if (transition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Log.d(TAG, "onHandleIntent: Entering Geofence - " + requestID);
                Toast toast = Toast.makeText(
                        getApplicationContext(),
                        "You have entered the geofence",
                        Toast.LENGTH_SHORT);
                toast.show();
            } else if (transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                Log.d(TAG, "onHandleIntent: Exiting Geofence = " + requestID);
                Toast toast = Toast.makeText(
                        getApplicationContext(),
                        "You have exited the geofence",
                        Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}
