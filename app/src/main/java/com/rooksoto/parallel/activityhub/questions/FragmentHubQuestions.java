package com.rooksoto.parallel.activityhub.questions;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.BaseView;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.utility.widgets.swipestack.FixedSwipeStack;

import link.fls.swipestack.SwipeStack;

public class FragmentHubQuestions extends Fragment implements BaseView, SwipeStack.SwipeStackListener {
    private FragmentHubQuestionsPresenter fragmentHubQuestionsPresenter = new FragmentHubQuestionsPresenter();
    private FixedSwipeStack swipeStack;

    private int containerID = R.id.content_frame;

    private View view;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_hub_questions, container, false);
        initialize();
        return view;
    }

    public void initialize () {
        setViews();
    }

    @Override
    public void setViews () {
    }

    @Override
    public void onBackPressed () {
        fragmentHubQuestionsPresenter.onBackPressedOverride(view);
    }

    private void setSwipeStack () {
        //swipeStack = (FixedSwipeStack) view.findViewById(R.id.fragment_hub_questions_swipestack_holder);
        swipeStack.setListener(this);
        fragmentHubQuestionsPresenter.setSwipeStack(swipeStack, view);
    }

    @Override
    public void onViewSwipedToLeft (int position) {
        fragmentHubQuestionsPresenter.onViewSwipedToLeft(position);
    }

    @Override
    public void onViewSwipedToRight (int position) {
        fragmentHubQuestionsPresenter.onViewSwipedToRight(position);
    }

    @Override
    public void onStackEmpty () {
    }
}
