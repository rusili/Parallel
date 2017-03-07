package com.rooksoto.parallel.view.activityStart;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.rooksoto.parallel.utility.AppContext;

/**
 * Created by rook on 3/6/17.
 */

public class FragmentStartEnterIDPresenter implements FragmentStartEnterIDContract.Presenter{
    @Override
    public void start() {

    }

    @Override
    public boolean isOnline() {
        ConnectivityManager connManager = (ConnectivityManager) AppContext.getAppContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connManager.getActiveNetworkInfo();

        if(networkInfo != null && networkInfo.isConnectedOrConnecting()){
            return true;
        }
        else{
            return false;
        }
    }
}
