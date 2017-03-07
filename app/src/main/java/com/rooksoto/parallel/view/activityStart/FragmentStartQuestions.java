package com.rooksoto.parallel.view.activityStart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;

import link.fls.swipestack.SwipeStack;

public class FragmentStartQuestions extends Fragment implements SwipeStack.SwipeStackListener, FragmentStartQuestionsContract.View {

    View mView;

    private FragmentStartQuestionsContract.Presenter presenter;

    @Nullable
    @Override
    public View onCreateView (LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_start_questions, container, false);
        initialize();
        return mView;
    }

    @Override
    public void setPresenter(FragmentStartQuestionsContract.Presenter presenter) {
        this.presenter = presenter;
    }

    private void initialize () {
        setSwipeStack();
    }

    private void setSwipeStack () {
        presenter.setSwipeStack(this);
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
