package com.rooksoto.parallel.userHub;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;

/**
 * Created by huilin on 3/6/17.
 */

public class UserHubContract {

    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {
        void stopLocationServices();
    }
}
