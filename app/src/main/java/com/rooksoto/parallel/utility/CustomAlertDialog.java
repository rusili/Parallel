package com.rooksoto.parallel.utility;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activitylogin.ActivityLogin;
import com.rooksoto.parallel.utility.geolocation.ParallelLocation;

// Custom alert dialog allowing the user to use their own layout, title, text, and icon. Defaults to finish() onClick
// Parameters:  1) Context
// Optional:    1) Icon
//              2) View/Layout

public class CustomAlertDialog {
    private DatabaseReference userRef;

    private int containerID = R.id.content_frame;
    private FirebaseUser firebaseUser;

    public void exit (final Activity activity) {
        new AlertDialog.Builder(activity)
                .setIcon(R.drawable.lines)
                .setView(null)
                .setTitle("Exiting App")
                .setMessage("Are you sure you want to close Parallel?")
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        firebaseUser = FirebaseAuth.getInstance().getCurrentUser();
                        userRef = FirebaseDatabase.getInstance().getReference().child(ParallelLocation.eventID).child("attendee_list");
                        userRef.child(firebaseUser.getUid()).removeValue();
                        FirebaseAuth.getInstance().signOut();
                        Intent intent = new Intent(activity, ActivityLogin.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        intent.putExtra("EXIT", true);
                        activity.finishAffinity();
                        activity.startActivity(intent);
                    }
                })
                .setNegativeButton("No", null)
                .show();

    }

    public void exit (final Context contextParam, String messageP) {
        new AlertDialog.Builder(contextParam)
                .setIcon(R.drawable.lines)
                .setView(null)
                .setTitle("Exiting App")
                .setMessage(messageP)
                .setPositiveButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick (DialogInterface dialog, int which) {
                        ((Activity) contextParam).finish();
                    }
                })
                .setNegativeButton("No", null)
                .show();
    }
}
