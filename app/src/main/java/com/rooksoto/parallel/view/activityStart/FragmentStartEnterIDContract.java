package com.rooksoto.parallel.view.activityStart;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;

/**
 * Created by rook on 3/6/17.
 */

public interface FragmentStartEnterIDContract {

    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {

        //FragmentStartEnterID
        boolean isOnline();

    }



}
