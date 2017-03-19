package com.rooksoto.parallel.utility.geolocation;

import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.Geofence;
import com.google.android.gms.location.GeofencingRequest;
import com.google.android.gms.location.LocationListener;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.model.EventLocation;
import com.rooksoto.parallel.utility.AppContext;
import com.rooksoto.parallel.utility.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class ParallelLocation {

    private static final String TAG = "ParallelLocation";

    private static GoogleApiClient googleApiClient;
    private static ParallelLocation instance;
    private static double parallelLatitude;
    private static double parallelLongitude;
    private static EventLocation eventLocation;

    // Inits to default eventID, at US White House, 100m radius
    public static String eventID = "/default";
    public static double eventLatitude = 38.8976763;
    public static double eventLongitude = -77.0365298;

    public static ParallelLocation getInstance () {
        if (instance == null) {
            instance = new ParallelLocation();
        }
        return instance;
    }

    public static float eventGeofenceRadius = 100;

    private ParallelLocation () {

        // [BLOCK]
        // I'm getting event/geofence location information from Firebase here

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference eventReference = database.getReference(
                eventID + "/event_location/"
        );

        Log.d(TAG, "Initial Geofence: " + eventLatitude + ", " + eventLongitude + ", " + eventGeofenceRadius);

        eventReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                HashMap<String, Object> locationMap = (HashMap<String, Object>) dataSnapshot.getValue();
                eventLongitude = Double.valueOf(locationMap.get("latitude").toString());
                eventLongitude = Double.valueOf(locationMap.get("longitude").toString());
                eventGeofenceRadius = Float.valueOf(locationMap.get("radius_meters").toString());
                Log.d(TAG, "Event Geofence: " + eventLatitude + ", " + eventLongitude + ", " + eventGeofenceRadius);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.d(TAG, "onCancelled: Error getting location data from Firebase");
            }
        });
        // [/Block]

        final Context context = AppContext.getAppContext();
        googleApiClient = new GoogleApiClient.Builder(context)
                .addApi(LocationServices.API)
                .addConnectionCallbacks(new GoogleApiClient.ConnectionCallbacks() {
                    @Override
                    public void onConnected (@Nullable Bundle bundle) {
                        Log.d(TAG, "Successfully Connected to GPS-L");
                        startLocationMonitoring();
                        startGeofenceMonitoring(context);
                    }

                    @Override
                    public void onConnectionSuspended (int i) {
                        Log.d(TAG, "Connection to GPS-L Suspended");
                    }
                })
                .addOnConnectionFailedListener(new GoogleApiClient.OnConnectionFailedListener() {
                    @Override
                    public void onConnectionFailed (@NonNull ConnectionResult connectionResult) {
                        Log.d(TAG, "Failed to Connect to GPS-L");
                    }
                })
                .build();
        googleApiClient.connect();
    }

    public void connect () {
        googleApiClient.reconnect();
    }

    public void disconnect () {
        googleApiClient.disconnect();
    }

    public void startLocationMonitoring () {
        Log.d(TAG, "startLocationMonitoring: Called Successfully");
        try {
            LocationRequest locationRequest = LocationRequest.create()
                    .setInterval(10000)
                    .setFastestInterval(5000)
                    .setPriority(LocationRequest.PRIORITY_HIGH_ACCURACY);
            LocationServices.FusedLocationApi.requestLocationUpdates(
                    googleApiClient,
                    locationRequest,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged (Location location) {
                            Log.d(TAG, "onLocationChanged: Current Lat: " + location.getLatitude());
                            parallelLatitude = location.getLatitude();
                            Log.d(TAG, "onLocationChanged: Current Long: " + location.getLongitude());
                            parallelLongitude = location.getLongitude();
                        }
                    });
        } catch (SecurityException e) {
            Log.d(TAG, "Security Exception: " + e.getMessage());
        }
    }

    public void startGeofenceMonitoring (Context context) {

        Log.d(TAG, "startGeofenceMonitoring: Called");

        try {
            Geofence geofence = new Geofence.Builder()
                    .setRequestId(Constants.GOFENCE_ID)
                    .setCircularRegion(
                            eventLatitude,
                            eventLongitude,
                            eventGeofenceRadius)
                    .setExpirationDuration(Geofence.NEVER_EXPIRE)
                    .setNotificationResponsiveness(1000)
                    .setTransitionTypes(Geofence.GEOFENCE_TRANSITION_ENTER | Geofence.GEOFENCE_TRANSITION_EXIT)
                    .build();
            GeofencingRequest geofencingRequest = new GeofencingRequest.Builder()
                    .setInitialTrigger(GeofencingRequest.INITIAL_TRIGGER_ENTER)
                    .addGeofence(geofence)
                    .build();
            Intent intent = new Intent(context, GeofenceService.class);
            PendingIntent pendingIntent = PendingIntent.getService(
                    context,
                    0,
                    intent,
                    PendingIntent.FLAG_UPDATE_CURRENT
            );

            if (!googleApiClient.isConnected()) {
                Log.d(TAG, "startGeofenceMonitoring: Not Connected!");
            } else {
                LocationServices.GeofencingApi.addGeofences(googleApiClient, geofencingRequest, pendingIntent)
                        .setResultCallback(new ResultCallback <Status>() {
                            @Override
                            public void onResult (@NonNull Status status) {
                                if (status.isSuccess()) {
                                    Log.d(TAG, "onResult: Geofence added successfully!");
                                } else {
                                    Log.d(TAG, "onResult: Failed to add geofence.");
                                }
                            }
                        });
            }
        } catch (SecurityException e) {
            Log.d(TAG, "startGeofenceMonitoring: SecurityException " + e.getMessage());
        }
    }

    public void stopGeofenceMonitoring () {
        Log.d(TAG, "stopGeofenceMonitoring: Called");
        ArrayList <String> geofenceIds = new ArrayList <>();
        geofenceIds.add(Constants.GOFENCE_ID);
    }

    public double getLatitude () {
        return parallelLatitude;
    }

    public double getLongitude () {
        return parallelLongitude;
    }
}
