package com.rooksoto.parallel.fragments.activityStart;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Questions;
import com.rooksoto.parallel.viewwidgets.swipestack.SwipeAdapter;

import java.util.ArrayList;
import java.util.List;

import link.fls.swipestack.SwipeStack;

public class FragmentStartQuestions extends Fragment {
    private View mView;

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
        List <Questions> tempList = new ArrayList <>();
        tempList.add(new Questions());
        tempList.add(new Questions());
        tempList.add(new Questions());
        tempList.add(new Questions());

        SwipeStack swipeStack = (SwipeStack) mView.findViewById(R.id.fragment_start_questions_swipestack_holder);
        swipeStack.setAdapter(new SwipeAdapter(tempList));
    }
}
