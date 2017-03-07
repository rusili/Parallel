package com.rooksoto.parallel.view.activityStart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.viewwidgets.swipestack.FixedSwipeStack;
import com.rooksoto.parallel.viewwidgets.swipestack.SwipeStackAdapter;

import link.fls.swipestack.SwipeStack;

public class FragmentStartQuestions extends Fragment implements SwipeStack.SwipeStackListener, FragmentStartQuestionsContract.View {

    private View view;
    private FixedSwipeStack swipeStack;
    private FragmentStartQuestionsContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_start_questions, container, false);
        initialize();
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void setPresenter(FragmentStartQuestionsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize () {
        presenter = new FragmentStartQuestionsPresenter(view);
        setPresenter(presenter);
        setSwipeStack();
    }

    private void setSwipeStack () {
        presenter.getQuestions();
        swipeStack = (FixedSwipeStack) view.findViewById(R.id.fragment_start_questions_swipestack_holder);
        swipeStack.setAdapter(new SwipeStackAdapter(presenter.getQuestions()));
    }

    @Override
    public void onViewSwipedToLeft (int position) {
        presenter.onViewSwipedToLeft(position);
    }

    @Override
    public void onViewSwipedToRight (int position) {
        presenter.onViewSwipedToRight(position);
    }

    @Override
    public void onStackEmpty () {}

    private void toActivityHub () {

    }
}
