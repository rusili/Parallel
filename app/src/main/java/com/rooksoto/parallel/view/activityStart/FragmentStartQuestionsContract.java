package com.rooksoto.parallel.view.activityStart;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.network.objects.Questions;

import java.util.List;

/**
 * Created by rook on 3/6/17.
 */

interface FragmentStartQuestionsContract {

    interface View extends BaseView<Presenter> {


    }

    interface Presenter extends BasePresenter {

        //FragmentStartQuestions
        List<Questions> getQuestions();
        void onViewSwipedToLeft(int position);
        void onViewSwipedToRight(int position);
        void onStackEmpty();
        void toActivityHub();

    }



}
