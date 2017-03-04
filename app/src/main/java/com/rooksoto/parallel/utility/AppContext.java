package com.rooksoto.parallel.utility;

import android.app.Application;
import android.content.Context;

/**
 * Created by rook on 3/2/17.
 */

public class AppContext extends Application {

    // This only keeps a reference to the APPLICATION CONTEXT
    private static Context appContext;

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }

    public static Context getAppContext() {
        return appContext;
    }
}
