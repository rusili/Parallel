package com.rooksoto.parallel.utility.widgets.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;

public class ProfileViewholder extends RecyclerView.ViewHolder {
    TextView textViewQuestion;
    ImageView imageViewLeft;
    ImageView imageViewRight;

    public ProfileViewholder (View itemView) {
        super(itemView);
        textViewQuestion = (TextView) itemView.findViewById(R.id.fragment_hub_profile_textview_question);
        imageViewLeft = (ImageView) itemView.findViewById(R.id.fragment_hub_profile_leftanswer);
        imageViewRight = (ImageView) itemView.findViewById(R.id.fragment_hub_profile_rightanswer);

    }

    public void bind (int position, Answers answerP) {
        textViewQuestion.setText("" + answerP.getQuestion());
        if (answerP.getAnswer() == "True"){
            imageViewLeft.setVisibility(View.GONE);
        } else if (answerP.getAnswer() == "False"){
            imageViewRight.setVisibility(View.GONE);
        }
    }
}