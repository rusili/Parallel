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
        if (answerP.getAnswer().equals("True")){
            imageViewLeft.setVisibility(View.GONE);
        } else if (answerP.getAnswer().equals("False")){
            imageViewRight.setVisibility(View.GONE);
        } else if (answerP.getAnswer().equals("2130837624")) { // Selected Android
            imageViewLeft.setVisibility(View.GONE);
            imageViewRight.setImageResource((R.drawable.ic_androidicon));
        } else if (answerP.getAnswer().equals("2130837625")) { // Selected iOS
            imageViewRight.setVisibility(View.GONE);
            imageViewLeft.setImageResource(R.drawable.ic_appleicon);
        }
    }
}