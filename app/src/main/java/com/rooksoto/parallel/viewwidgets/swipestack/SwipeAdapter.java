package com.rooksoto.parallel.viewwidgets.swipestack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.network.objects.Questions;

import java.util.List;

public class SwipeAdapter extends BaseAdapter {
    private List <Questions> listOfQuestions;

    public SwipeAdapter (List <Questions> listParam) {
        this.listOfQuestions = listParam;
    }

    @Override
    public Object getItem (int position) {
        return listOfQuestions.get(position);
    }

    @Override
    public long getItemId (int position) {
        return position;
    }

    @Override
    public View getView (int position, View viewParam, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View questionView = inflater.inflate(R.layout.fragment_start_questions_swipestack, parent, false);
        // bind

        return questionView;
    }

    @Override
    public int getCount () {
        return listOfQuestions.size();
    }
}
