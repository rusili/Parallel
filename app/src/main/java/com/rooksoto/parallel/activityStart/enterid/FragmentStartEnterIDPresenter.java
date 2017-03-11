package com.rooksoto.parallel.activityStart.enterid;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.view.View;
import android.widget.Toast;

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

    public void checkEventID (String eventID) {
        // TODO: 3/10/17 Check Event ID
    }
}
