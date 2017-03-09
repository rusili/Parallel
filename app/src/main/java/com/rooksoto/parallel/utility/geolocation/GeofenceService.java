package com.rooksoto.parallel.utility.geolocation;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

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
            Log.d(TAG, "onHandleIntent: There was an error getting geofence transition event.");
        } else {
            int transition = event.getGeofenceTransition();
            List <Geofence> geofences = event.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            String requestID = geofence.getRequestId();

            if (transition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Log.d(TAG, "onHandleIntent: Entering Geofence - " + requestID);

            } else if (transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                Log.d(TAG, "onHandleIntent: Exiting Geofence = " + requestID);
            }
        }
    }
}
