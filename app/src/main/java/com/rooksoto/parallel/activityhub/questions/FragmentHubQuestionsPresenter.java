package com.rooksoto.parallel.activityhub.questions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHub;
import com.rooksoto.parallel.activityhub.ActivityHubPresenter;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.Questions;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.widgets.swipestack.FixedSwipeStack;
import com.rooksoto.parallel.utility.widgets.swipestack.SwipeStackAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHubQuestionsPresenter implements BasePresenter {
    private ActivityHubPresenter.Listener listener;
    private View view;

    private List <Questions> listofQuestions = new ArrayList <>();
    private List <Answers> listofAnswers = new ArrayList <>();

    public FragmentHubQuestionsPresenter(ActivityHubPresenter.Listener listener){
        this.listener = listener;
    }

    @Override
    public void start () {
    }

    public void onBackPressedOverride (View viewP) {
        CustomAlertDialog customAlertDialog = new CustomAlertDialog();
        customAlertDialog.exit(viewP.getContext());
    }

    public void setTempList() {
        listofQuestions.add(new Questions("Please answer the next few questions:"));

        listofQuestions.add(new Questions("Are you an IOS or Android developer?", R.drawable.ic_appleicon, R.drawable.ic_androidicon));
        listofQuestions.add(new Questions("Are you an experienced programmer?"));
        listofQuestions.add(new Questions("Are you affiliated with C4Q?"));
        listofQuestions.add(new Questions("Are dogs better than cats?"));
    }

    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
    }

    public void toViewPager(){
        listener.setupViewpager();
    }

    private void saveUserInfo () {
        // get currentUserInfo;
        //User newUser = new User();
    }

    public List<Questions> getQuestions() {
        getQuestionsfromFirebase();
        setTempList();
        return listofQuestions;
    }

    public void getQuestionsfromFirebase() {
        // // TODO: 3/14/2017 get List of Questions from Firebase
    }
}
