package com.rooksoto.parallel.utility;

import android.app.Application;
import android.content.Context;

public class AppContext extends Application {

    // This only keeps a reference to the APPLICATION CONTEXT
    private static Context appContext;

    public static Context getAppContext() {
        return appContext;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        appContext = this;
    }
}
