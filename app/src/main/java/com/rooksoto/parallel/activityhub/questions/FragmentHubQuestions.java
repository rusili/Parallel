package com.rooksoto.parallel.activityhub.questions;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.Log;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
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
    private List<ImageView> imageViewGradient = new ArrayList<>();
    private List<FrameLayout> listofFrameLayouts = new ArrayList<>();

    private int containerID = R.id.content_frame;
    private int counter = 0;
    private boolean altImage = false;
    private String[] welcomeText = new String[] {"Welcome", "to C4Q's", "3.3 Demo Day", "Enjoy"};
    private boolean started = false;

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
        startWelcomeAnimation();
    }

    @Override
    public void setViews () {
        imageViewLeftButton = (ImageView) view.findViewById(R.id.fragment_hub_questions_leftanswer);
        imageViewRightButton = (ImageView) view.findViewById(R.id.fragment_hub_questions_rightanswer);
        hTextViewLine0 = (HTextView) view.findViewById(R.id.fragment_hub_questions_line0);
        hTextViewLine0.setAnimateType(HTextViewType.SCALE);
        hTextViewLine0.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18);

        textViewLine.add(hTextViewLine0);
        imageViewGradient.add(new ImageView(view.getContext()));

        for (int i=1; i<listofQuestions.size(); i++){
            String viewID = "fragment_hub_questions_line" + i;
            String imageID = "fragment_hub_questions_gradient" + i;
            String layoutID = "fragment_hub_questions_container" + i;
            TextView tempView = (TextView) view.findViewById(getResources().getIdentifier(viewID, "id", view.getContext().getPackageName()));
            ImageView tempImage = (ImageView) view.findViewById(getResources().getIdentifier(imageID, "id", view.getContext().getPackageName()));
            FrameLayout tempLayout = (FrameLayout) view.findViewById(getResources().getIdentifier(layoutID, "id", view.getContext().getPackageName()));
            textViewLine.add(tempView);
            imageViewGradient.add(tempImage);
            listofFrameLayouts.add(tempLayout);
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
            Answers tempAnswer = listofAnswers.get(backwardsCounter);

            tempView.setText(tempAnswer.getQuestion());
            Animation fadeinuptext = AnimationUtils.loadAnimation(view.getContext(), R.anim.fadeinuptext);
            FrameLayout frameLayout = listofFrameLayouts.get(i-1);
            frameLayout.startAnimation(fadeinuptext);

            if (counter > 1 && i<counter) {
                colorGradient(i, backwardsCounter);
            }
            backwardsCounter--;
        }
    }

    private void colorGradient(int i, int backwardsCounter) {
        if (TextUtils.isDigitsOnly(listofAnswers.get(backwardsCounter).getAnswer())){
            Log.d("Answer: ", String.valueOf(Integer.parseInt(listofAnswers.get(backwardsCounter).getAnswer())));
            imageViewGradient.get(i).setImageResource(Integer.parseInt(listofAnswers.get(backwardsCounter).getAnswer()));
            imageViewGradient.get(i).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.textMain));
            imageViewGradient.get(i).setVisibility(View.VISIBLE);
        } else if (listofAnswers.get(backwardsCounter).getAnswer() == "False") {
            imageViewGradient.get(i).setImageDrawable(view.getResources().getDrawable(R.drawable.gradient));
            imageViewGradient.get(i).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.red_transparent));
            imageViewGradient.get(i).setVisibility(View.VISIBLE);
        } else if (listofAnswers.get(backwardsCounter).getAnswer() == "True") {
            imageViewGradient.get(i).setImageDrawable(view.getResources().getDrawable(R.drawable.gradient));
            imageViewGradient.get(i).setColorFilter(ContextCompat.getColor(view.getContext(), R.color.green_transparent));
            imageViewGradient.get(i).setVisibility(View.VISIBLE);
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
                    listofAnswers.add(new Answers(listofQuestions.get(counter).getQuestion(), String.valueOf(listofQuestions.get(counter).getLeftResID())));

                } else {
                    OnClickEffect.setBinaryLeft((ImageView) v);
                    listofAnswers.add(new Answers(listofQuestions.get(counter).getQuestion(), "False"));
                }
                break;
            case R.id.fragment_hub_questions_rightanswer:
                if (altImage){
                    OnClickEffect.setAltImage((ImageView) v);
                    listofAnswers.add(new Answers(listofQuestions.get(counter).getQuestion(), String.valueOf(listofQuestions.get(counter).getRightResID())));
                } else {
                    OnClickEffect.setBinaryRight((ImageView) v);
                    listofAnswers.add(new Answers(listofQuestions.get(counter).getQuestion(), "True"));
                }
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
        }, 250);
    }

    private Runnable runnableHTextView = new Runnable() {
        @Override
        public void run () {
            final HTextView textView = (HTextView) view.findViewById(R.id.fragment_hub_questions_line0);
            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 40);
            textView.setAnimateType(HTextViewType.SCALE);
            textView.animateText(welcomeText[counter]); // animate
            counter++;
            if (started) {
                startWelcomeAnimation();
            }
            if (counter == 1) {
                playAudio(R.raw.welcome);
            }
            if (counter == 4) {
                stopWelcomeAnimation();
                Handler handler = new Handler();
                handler.postDelayed(new Runnable() {
                    public void run () {
                        RelativeLayout relativeLayout = (RelativeLayout) view.findViewById(R.id.fragment_hub_questions_layout_buttons);
                        relativeLayout.setVisibility(View.VISIBLE);
                        counter = 0;
                        getQuestions();
                        setViews();
                        setOnClickListener();
                    }
                }, 1000);
            }
        }
    };

    public void startWelcomeAnimation () {
        started = true;
        handler.postDelayed(runnableHTextView, 1250);
    }
    public void stopWelcomeAnimation () {
        started = false;
        handler.removeCallbacks(runnableHTextView);
    }

    private void playAudio (int rawID) {
        MediaPlayer mediaPlayer = MediaPlayer.create(view.getContext(), rawID);
        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion (MediaPlayer mediaPlayer) {
                mediaPlayer.stop();
            }
        });
        mediaPlayer.start();
    }
}
