package com.rooksoto.parallel.utility.widgets.swipestack;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;

import com.hanks.htextview.HTextView;
import com.hanks.htextview.HTextViewType;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Questions;

import java.util.List;

public class SwipeStackAdapter extends BaseAdapter {
    private View view;
    private List <Questions> listOfQuestions;

    public SwipeStackAdapter (List <Questions> listParam) {
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
        view = inflater.inflate(R.layout.fragment_hub_questions_swipestack, parent, false);
        bind(position);

        return view;
    }

    private void bind (int position) {
        Questions question = listOfQuestions.get(position);
        HTextView hTextViewQuestion = (HTextView) view.findViewById(R.id.fragment_hub_questions_htextview_question);
        hTextViewQuestion.setAnimateType(HTextViewType.SCALE);
        hTextViewQuestion.animateText(question.getQuestion());

        if (question.getLeftResID() != 0) {
            ImageView imageViewLeft = (ImageView) view.findViewById(R.id.fragment_hub_questions_swipestack_leftanswer);
            ImageView imageViewRight = (ImageView) view.findViewById(R.id.fragment_hub_questions_swipestack_rightanswer);
            imageViewLeft.setImageResource(question.getLeftResID());
            imageViewRight.setImageResource(question.getRightResID());
        }

    }

    @Override
    public int getCount () {
        return listOfQuestions.size();
    }
}
