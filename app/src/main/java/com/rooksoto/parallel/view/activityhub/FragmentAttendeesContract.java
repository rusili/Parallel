package com.rooksoto.parallel.view.activityhub;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;

/**
 * Created by huilin on 3/7/17.
 */

public interface FragmentAttendeesContract {

    interface View extends BaseView<Presenter> {
        void displayRv();
        void updateRv();
        void clickToSeeProfile();
    }

    interface Presenter extends BasePresenter {
        void showProfile();
        void updateRvAdapterEveryArrivalDeparture();
    }
}
