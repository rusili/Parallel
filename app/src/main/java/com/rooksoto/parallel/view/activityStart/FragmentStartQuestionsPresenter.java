package com.rooksoto.parallel.view.activityStart;


import android.content.Intent;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.view.View;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Questions;
import com.rooksoto.parallel.utility.AppContext;
import com.rooksoto.parallel.view.activityHub.ActivityHub;
import com.rooksoto.parallel.viewwidgets.swipestack.FixedSwipeStack;
import com.rooksoto.parallel.viewwidgets.swipestack.SwipeStackAdapter;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

/**
 * Created by rook on 3/6/17.
 */

public class FragmentStartQuestionsPresenter implements FragmentStartQuestionsContract.Presenter{
    private View mView;
    private FixedSwipeStack mSwipeStack;
    private List<Questions> listofQuestions;

    @Override
    public void start() {

    }

    @Override
    public void setSwipeStack(Fragment fragment) {
        listofQuestions = new ArrayList<>();
        listofQuestions.add(new Questions("Are you an IOS or Android developer?", R.drawable.ic_appleicon, R.drawable.ic_androidicon));
        listofQuestions.add(new Questions("Are you affiliated with C4Q?"));
        listofQuestions.add(new Questions("Do you have more than 3 years of programming experience?"));
        listofQuestions.add(new Questions("Are dogs better than cats?"));

        mSwipeStack = (FixedSwipeStack) mView.findViewById(R.id.fragment_start_questions_swipestack_holder);
        mSwipeStack.setAdapter(new SwipeStackAdapter(listofQuestions));
        mSwipeStack.setListener((SwipeStack.SwipeStackListener) fragment);
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        if (position == listofQuestions.size() - 1) {
            toActivityHub();
        } else {
            // Put question and LEFT answer into user profile
        }
    }

    @Override
    public void onViewSwipedToRight(int position) {
        if (position == listofQuestions.size() - 1) {
            toActivityHub();
        } else {
            // Put question and RIGHT answer into user profile
        }
    }

    @Override
    public void onStackEmpty() {
        // Do nothing
    }

    @Override
    public void toActivityHub() {
        new Handler().postDelayed(new Runnable() {
            public void run () {
                Intent intentToActivityHub = new Intent(mView.getContext(), ActivityHub.class);
                AppContext.getAppContext().startActivity(intentToActivityHub);
            }
        }, 500);
    }
}
