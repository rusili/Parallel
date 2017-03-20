package com.rooksoto.parallel.utility.widgets.recyclerview.ExpandedRecyclerView;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.expandablerecyclerview.ChildViewHolder;
import com.rooksoto.parallel.R;
import com.rooksoto.parallel.objects.Answers;

public class AnswersExpandViewholder extends ChildViewHolder {
    TextView textViewQuestion;
    ImageView imageViewLeft;
    ImageView imageViewRight;

    public AnswersExpandViewholder (View itemView) {
        super(itemView);
        textViewQuestion = (TextView) itemView.findViewById(R.id.fragment_hub_profile_textview_question);
        imageViewLeft = (ImageView) itemView.findViewById(R.id.fragment_hub_profile_leftanswer);
        imageViewRight = (ImageView) itemView.findViewById(R.id.fragment_hub_profile_rightanswer);
    }

    public void bind (Answers child) {
        textViewQuestion.setText(child.getQuestion());
        if (child.getAnswer().equals("True")){
            imageViewLeft.setVisibility(View.INVISIBLE);
        } else if (child.getAnswer().equals("False")){
            imageViewRight.setVisibility(View.INVISIBLE);
        } else if (child.getAnswer().equals("2130837625")) { // Selected Android
            imageViewLeft.setVisibility(View.INVISIBLE);
            imageViewRight.setImageResource((R.drawable.ic_androidicon));
            imageViewRight.setColorFilter(itemView.getResources().getColor(R.color.teal));
        } else if (child.getAnswer().equals("2130837626")) { // Selected iOS
            imageViewRight.setVisibility(View.INVISIBLE);
            imageViewLeft.setImageResource(R.drawable.ic_appleicon);
            imageViewRight.setColorFilter(itemView.getResources().getColor(R.color.teal));
        }
    }
}