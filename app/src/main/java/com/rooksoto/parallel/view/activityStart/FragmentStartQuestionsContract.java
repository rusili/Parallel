package com.rooksoto.parallel.view.activityStart;

import android.support.v4.app.Fragment;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;

/**
 * Created by rook on 3/6/17.
 */

public interface FragmentStartQuestionsContract {

    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {

        //FragmentStartQuestions
        void setSwipeStack(Fragment fragment);
        void onViewSwipedToLeft(int position);
        void onViewSwipedToRight(int position);
        void onStackEmpty();
        void toActivityHub();

    }



}
