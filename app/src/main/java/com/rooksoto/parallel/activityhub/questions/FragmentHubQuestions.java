package com.rooksoto.parallel.activityhub.questions;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.activityhub.ActivityHubPresenter;
import com.rooksoto.parallel.objects.Answers;
import com.rooksoto.parallel.objects.Questions;
import com.rooksoto.parallel.utility.OnClickEffect;

import java.util.ArrayList;
import java.util.List;

@SuppressLint("ValidFragment")
public class FragmentHubQuestions extends Fragment implements BaseView, View.OnClickListener{
    private FragmentHubQuestionsPresenter fragmentHubQuestionsPresenter;
    private List<Questions> listofQuestions;
    private List<Answers> listofAnswers = new ArrayList<>();
    private static android.os.Handler handler = new android.os.Handler();

    private View view;
    private List<TextView> textViewLine = new ArrayList<>();
    private HTextView hTextViewLine0;
    private ImageView imageViewLeftButton;
    private ImageView imageViewRightButton;

    private int containerID = R.id.content_frame;
    private int counter = 0;
    private boolean altImage = false;

    @SuppressLint("ValidFragment")
    public FragmentHubQuestions(ActivityHubPresenter.Listener listener){
        fragmentHubQuestionsPresenter = new FragmentHubQuestionsPresenter(listener);
    }

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_questions, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        getQuestions();
        setViews();
        setOnClickListener();
    }

    @Override
    public void setViews () {
        imageViewLeftButton = (ImageView) view.findViewById(R.id.fragment_hub_questions_leftanswer);
        imageViewRightButton = (ImageView) view.findViewById(R.id.fragment_hub_questions_rightanswer);
        hTextViewLine0 = (HTextView) view.findViewById(R.id.fragment_hub_questions_line0);
        hTextViewLine0.setAnimateType(HTextViewType.SCALE);

        textViewLine.add(hTextViewLine0);

        for (int i=1; i<listofQuestions.size(); i++){
            String viewID = "fragment_hub_questions_line" + i;
            TextView tempView = (TextView) view.findViewById(getResources().getIdentifier(viewID, "id", view.getContext().getPackageName()));
            textViewLine.add(tempView);
        }
        showNextQuestion();
    }

    private void setOnClickListener() {
        imageViewLeftButton.setOnClickListener(this);
        imageViewRightButton.setOnClickListener(this);
    }

    private void getQuestions(){
        listofQuestions = fragmentHubQuestionsPresenter.getQuestions();
    }

    private void showNextQuestion() {
        int backwardsCounter = counter;

        showDelayedButtonClick(backwardsCounter);

        hTextViewLine0.animateText(listofQuestions.get(backwardsCounter).getQuestion());
        backwardsCounter--;

        for (int i=1; i<=counter; i++){
            TextView tempView = textViewLine.get(i);
            Questions tempQuestion = listofQuestions.get(backwardsCounter);

            tempView.setText(tempQuestion.getQuestion());
            Animation fadeinuptext = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeinuptext);
            tempView.startAnimation(fadeinuptext);

            backwardsCounter--;
        }
    }

    private void showDelayedButtonClick(int backwardsCounter){
        final int finalBackwardsCounter = backwardsCounter;
        handler.postDelayed(new Runnable() {
            public void run() {
                showAnswers(finalBackwardsCounter);
            }
        }, 250);
    }

    private void showAnswers(int backwardsCounter){
        if(listofQuestions.get(backwardsCounter).getLeftResID()!=0){
            altImage = true;
            imageViewLeftButton.setImageResource(listofQuestions.get(backwardsCounter).getLeftResID());
            imageViewRightButton.setImageResource(listofQuestions.get(backwardsCounter).getRightResID());
            imageViewLeftButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.textMain));
            imageViewRightButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.textMain));
        } else {
            imageViewLeftButton.setImageDrawable(view.getResources().getDrawable(R.drawable.xbutton));
            imageViewRightButton.setImageDrawable(view.getResources().getDrawable(R.drawable.checkbutton));
            imageViewLeftButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.red_transparent));
            imageViewRightButton.setColorFilter(ContextCompat.getColor(view.getContext(), R.color.green_transparent));
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fragment_hub_questions_leftanswer:
                if (altImage){
                    OnClickEffect.setAltImage((ImageView) v);
                } else {
                    OnClickEffect.setBinaryLeft((ImageView) v);
                }
                listofAnswers.add(new Answers(listofQuestions.get(counter).getQuestion(), "False"));
                break;
            case R.id.fragment_hub_questions_rightanswer:
                if (altImage){
                    OnClickEffect.setAltImage((ImageView) v);
                } else {
                    OnClickEffect.setBinaryRight((ImageView) v);
                }
                listofAnswers.add(new Answers(listofQuestions.get(counter).getQuestion(), "True"));
                break;
            default:
                break;
        }
        if (counter == (listofQuestions.size()-1)){
            showDelayedButtonClick(counter);
            handler.postDelayed(new Runnable() {
                public void run() {
                    toViewPagerHub();
                }
            }, 250);

        } else {
            counter++;
            altImage = false;
            showNextQuestion();
        }
    }

    private void toViewPagerHub(){
        Animation fadeoutup = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeoutuptransition);
        view.startAnimation(fadeoutup);

        final Fragment currentFrag = this;
        handler.postDelayed(new Runnable() {
            public void run() {
                getFragmentManager().beginTransaction().remove(currentFrag).commit();
                fragmentHubQuestionsPresenter.toViewPager();
            }
        }, 500);
    }
}
