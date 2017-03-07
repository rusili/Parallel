package com.rooksoto.parallel.view.activityStart;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;

/**
 * Created by rook on 3/6/17.
 */

interface FragmentStartWelcomeContract {

    interface View extends BaseView<Presenter> {

    }

    interface Presenter extends BasePresenter {

        //FragmentStartWelcome
        void playWelcomeVoice();

    }



}
