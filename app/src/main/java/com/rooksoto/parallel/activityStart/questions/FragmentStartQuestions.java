package com.rooksoto.parallel.activityStart.questions;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.widgets.swipestack.FixedSwipeStack;

import link.fls.swipestack.SwipeStack;

public class FragmentStartQuestions extends Fragment implements BaseView, SwipeStack.SwipeStackListener {
    private FragmentStartQuestionsPresenter fragmentStartQuestionsPresenter = new FragmentStartQuestionsPresenter();
    private FixedSwipeStack swipeStack;

    private View view;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_questions, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
        setSwipeStack();
        setOnClickListeners();
    }

    @Override
    public void onBackPressed () {
        fragmentStartQuestionsPresenter.onBackPressedOverride(view);
    }

    private void setSwipeStack () {
        swipeStack = (FixedSwipeStack) view.findViewById(R.id.fragment_start_questions_swipestack_holder);
        swipeStack.setListener(this);
        fragmentStartQuestionsPresenter.setSwipeStack(swipeStack, view);
    }

    @Override
    public void onViewSwipedToLeft (int position) {
        fragmentStartQuestionsPresenter.onViewSwipedToLeft(position);
    }

    @Override
    public void onViewSwipedToRight (int position) {
        fragmentStartQuestionsPresenter.onViewSwipedToRight(position);
    }

    @Override
    public void onStackEmpty () {
    }

    private void setOnClickListeners(){
        ImageView imageViewLeft = (ImageView) view.findViewById(R.id.fragment_start_questions_swipestack_leftanswer);
        imageViewLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick (View v) {
                onViewSwipedToLeft(swipeStack.getCurrentPosition());
            }
        });
    }
}
