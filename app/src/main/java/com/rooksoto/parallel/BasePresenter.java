package com.rooksoto.parallel;

import android.app.Fragment;
import android.view.View;

public interface BasePresenter {

    void start ();

    void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id);
}