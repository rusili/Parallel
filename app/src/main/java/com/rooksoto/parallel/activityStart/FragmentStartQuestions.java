package com.rooksoto.parallel.activityStart;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.Questions;
import com.rooksoto.parallel.activityHub.ActivityHub;
import com.rooksoto.parallel.utility.widgets.swipestack.FixedSwipeStack;
import com.rooksoto.parallel.utility.widgets.swipestack.SwipeStackAdapter;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class FragmentStartQuestions extends Fragment implements SwipeStack.SwipeStackListener {
    private View mView;
    private FixedSwipeStack mSwipeStack;
    private List <Questions> listofQuestions;
    private List <Answers> listofAnswers = new ArrayList <>();

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_questions, container, false);
        initialize();
        return mView;
    }

    private void initialize () {
        setSwipeStack();
    }

    private void setSwipeStack () {
        listofQuestions = new ArrayList <>();
        listofQuestions.add(new Questions("Are you an IOS or Android developer?", R.drawable.ic_appleicon, R.drawable.ic_androidicon));
        listofQuestions.add(new Questions("Are you affiliated with C4Q?"));
        listofQuestions.add(new Questions("Do you have more than 3 years of programming experience?"));
        listofQuestions.add(new Questions("Are dogs better than cats?"));

        mSwipeStack = (FixedSwipeStack) mView.findViewById(R.id.fragment_start_questions_swipestack_holder);
        mSwipeStack.setAdapter(new SwipeStackAdapter(listofQuestions));
        mSwipeStack.setListener(this);
    }

    @Override
    public void onViewSwipedToLeft (int position) {
        if (position == listofQuestions.size() - 1) {
            toActivityHub();
        } else if (position==0){
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "IOS"));
        } else {
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "False"));
        }
    }

    @Override
    public void onViewSwipedToRight (int position) {
        if (position == listofQuestions.size() - 1) {
            toActivityHub();
        } else if (position==0){
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "Android"));
        } else {
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "True"));
        }
    }

    @Override
    public void onStackEmpty () {}

    private void toActivityHub () {
        saveUserInfo();
        new Handler().postDelayed(new Runnable() {
            public void run () {
                Intent intentToActivityHub = new Intent(mView.getContext(), ActivityHub.class);
                startActivity(intentToActivityHub);
            }
        }, 500);

    }

    private void saveUserInfo () {
        // get currentUserInfo;
        //User newUser = new User();
    }
}
