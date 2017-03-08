package com.rooksoto.parallel.view.activityStart;


import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Questions;
import com.rooksoto.parallel.utility.AppContext;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rook on 3/6/17.
 */

class FragmentStartQuestionsPresenter implements FragmentStartQuestionsContract.Presenter{

    View view;
    private List<Questions> listOfQuestions;

    FragmentStartQuestionsPresenter(View view) {
        this.view = view;
    }

    @Override
    public void start() {

    }

    @Override
    public List<Questions> getQuestions() {
        listOfQuestions = new ArrayList<>();

        // Fill "listOfQuestions" with Firebase Questionnaire objects
        listOfQuestions.add(new Questions("Are you an IOS or Android developer?", R.drawable.ic_appleicon, R.drawable.ic_androidicon));
        listOfQuestions.add(new Questions("Are you affiliated with C4Q?"));
        listOfQuestions.add(new Questions("Do you have more than 3 years of programming experience?"));
        listOfQuestions.add(new Questions("Are dogs better than cats?"));
        return listOfQuestions;
    }

    @Override
    public void onViewSwipedToLeft(int position) {
        if (position == listOfQuestions.size() - 1) {
            toActivityHub();
        } else {
            // Put question and LEFT answer into user profile
        }
    }

    @Override
    public void onViewSwipedToRight(int position) {
        if (position == listOfQuestions.size() - 1) {
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
                Intent intentToActivityHub = new Intent(view.getContext(), Activity.class);
                AppContext.getAppContext().startActivity(intentToActivityHub);
            }
        }, 500);
    }
}
