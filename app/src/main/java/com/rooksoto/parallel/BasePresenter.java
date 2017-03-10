package com.rooksoto.parallel;

import android.app.Fragment;
import android.view.View;

public interface BasePresenter {

    void start ();

    void onBackPressedOverride (View viewP);

    void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id);
}