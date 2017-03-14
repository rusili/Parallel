package com.rooksoto.parallel.activityhub.questions;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Handler;
import android.view.View;

import com.rooksoto.parallel.BasePresenter;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHub;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.Questions;
import com.rooksoto.parallel.utility.CustomAlertDialog;
import com.rooksoto.parallel.utility.widgets.swipestack.FixedSwipeStack;
import com.rooksoto.parallel.utility.widgets.swipestack.SwipeStackAdapter;

import java.util.ArrayList;
import java.util.List;

public class FragmentHubQuestionsPresenter implements BasePresenter {
    private View view;

    private List <Questions> listofQuestions = new ArrayList <>();
    private List <Answers> listofAnswers = new ArrayList <>();

    @Override
    public void start () {
    }

    public void onBackPressedOverride (View viewP) {
        this.view = viewP;
        CustomAlertDialog customAlertDialog = new CustomAlertDialog();
        customAlertDialog.exit(viewP.getContext());
    }

    public void setSwipeStack (FixedSwipeStack swipeStack, View viewP) {
        this.view = viewP;
        listofQuestions.add(new Questions("Please answer the next few questions:"));

        listofQuestions.add(new Questions("Are you an IOS or Android developer?", R.drawable.ic_appleicon, R.drawable.ic_androidicon));
        listofQuestions.add(new Questions("Are you an experienced programmer?"));
        listofQuestions.add(new Questions("Are you affiliated with C4Q?"));
        listofQuestions.add(new Questions("Are dogs better than cats?"));

        swipeStack.setAdapter(new SwipeStackAdapter(listofQuestions));
    }

    public void setOnClickReplace (Fragment fragmentP, View viewP, int containerID, String id) {
        ((Activity) viewP.getContext()).getFragmentManager().beginTransaction()
                .setCustomAnimations(R.animator.animator_fade_in, R.animator.animator_fade_out_right)
                .replace(containerID, fragmentP, id)
                .commit();
    }

    public void onViewSwipedToLeft (int position) {
        if (position == listofQuestions.size() - 1) {
            toActivityHub();
        } else if (position == 0) {
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "IOS"));
        } else {
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "False"));
        }
    }

    public void onViewSwipedToRight (int position) {
        if (position == listofQuestions.size() - 1) {
            toActivityHub();
        } else if (position == 0) {
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "Android"));
        } else {
            listofAnswers.add(new Answers(listofQuestions.get(position).getQuestion(), "True"));
        }
    }

    private void toActivityHub () {
        saveUserInfo();
        new Handler().postDelayed(new Runnable() {
            public void run () {
                Intent intentToActivityHub = new Intent(((Activity) view.getContext()), ActivityHub.class);
                view.getContext().startActivity(intentToActivityHub);
            }
        }, 500);

    }

    private void saveUserInfo () {
        // get currentUserInfo;
        //User newUser = new User();
    }

    public void onStackEmpty () {

    }
}
