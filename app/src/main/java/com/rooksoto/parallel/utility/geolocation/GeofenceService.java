package com.rooksoto.parallel.utility.geolocation;

import android.app.IntentService;
import android.content.Intent;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingEvent;

import java.util.List;

/**
 * Created by rook on 3/2/17.
 */

public class GeofenceService extends IntentService {

    private static final String TAG = "GeofenceService";

    public GeofenceService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        GeofencingEvent event = GeofencingEvent.fromIntent(intent);
        if (event.hasError()) {
            Log.d(TAG, "onHandleIntent: There was an error getting geofence transition event.");
        } else {
            int transition = event.getGeofenceTransition();
            List<Geofence> geofences = event.getTriggeringGeofences();
            Geofence geofence = geofences.get(0);
            String requestID = geofence.getRequestId();

            if (transition == Geofence.GEOFENCE_TRANSITION_ENTER) {
                Log.d(TAG, "onHandleIntent: Entering Geofence - " + requestID);
//                sendEntranceMessage();
            } else if (transition == Geofence.GEOFENCE_TRANSITION_EXIT) {
                Log.d(TAG, "onHandleIntent: Exiting Geofence - " + requestID);
                sendExitMessage();
            }
        }
    }

    private void sendEntranceMessage() {
        Intent entryIntent = new Intent("geofence_entry");
        entryIntent.putExtra("message", "Successfully Entered");
        LocalBroadcastManager.getInstance(this).sendBroadcast(entryIntent);
    }

    private void sendExitMessage() {
        Intent exitIntent = new Intent("geofence_exit");
        exitIntent.putExtra("message", "Successfully Exited");
        LocalBroadcastManager.getInstance(this).sendBroadcast(exitIntent);
    }
}

// ***************** This is Google's Code *****************
// *****************    Implement This!    *****************

//public class GeofenceTransitionsIntentService extends IntentService {
//   ...
//    protected void onHandleIntent(Intent intent) {
//        GeofencingEvent geofencingEvent = GeofencingEvent.fromIntent(intent);
//        if (geofencingEvent.hasError()) {
//            String errorMessage = GeofenceErrorMessages.getErrorString(this,
//                    geofencingEvent.getErrorCode());
//            Log.e(TAG, errorMessage);
//            return;
//        }
//
//        // Get the transition type.
//        int geofenceTransition = geofencingEvent.getGeofenceTransition();
//
//        // Test that the reported transition was of interest.
//        if (geofenceTransition == Geofence.GEOFENCE_TRANSITION_ENTER ||
//                geofenceTransition == Geofence.GEOFENCE_TRANSITION_EXIT) {
//
//            // Get the geofences that were triggered. A single event can trigger
//            // multiple geofences.
//            List triggeringGeofences = geofencingEvent.getTriggeringGeofences();
//
//            // Get the transition details as a String.
//            String geofenceTransitionDetails = getGeofenceTransitionDetails(
//                    this,
//                    geofenceTransition,
//                    triggeringGeofences
//            );
//
//            // Send notification and log the transition details.
//            sendNotification(geofenceTransitionDetails);
//            Log.i(TAG, geofenceTransitionDetails);
//        } else {
//            // Log the error.
//            Log.e(TAG, getString(R.string.geofence_transition_invalid_type,
//                    geofenceTransition));
//        }
//    }

