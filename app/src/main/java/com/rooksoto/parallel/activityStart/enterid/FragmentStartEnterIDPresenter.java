package com.rooksoto.parallel.activityStart.enterid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.CustomAlertDialog;

public class FragmentStartEnterIDPresenter implements BasePresenter {

    @Override
    public void start () {
    }

    public void onBackPressedOverride (View viewP) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog();
        customAlertDialog.exit(viewP.getContext());
    }

    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
        if (!isOnline(viewP)) {
            Toast.makeText(viewP.getContext(), "Cannot Connect - Please Check Internet Connection", Toast.LENGTH_SHORT).show();
        } else {
            ((Activity) viewP.getContext()).getFragmentManager().beginTransaction()
                    .setCustomAnimations(R.animator.animator_fade_in, R.animator.animator_fade_out)
                    .replace(containerID, fragmentP, id)
                    .commit();
        }
    }

    private boolean isOnline (View viewP) {
        ConnectivityManager connManager = (ConnectivityManager) viewP.getContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnectedOrConnecting()) {
            return true;
        } else {
            return false;
        }
    }

    // TODO: 3/12/17 - ROOK - Implement check for valid eventID. See comments. 
    void checkEventID (final String eventID, DatabaseReference reference) {
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.hasChild(eventID)) {
                    // Command the fragment to load the next screen, using eventID data
                } else {
                    // User typed invalid eventID... Handle here (Send to error page?)
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }
}
