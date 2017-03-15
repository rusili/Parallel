package com.rooksoto.parallel.objects;

public class Questions {
    String question;
    String answer;
    int leftResID = 0;
    int rightResID = 0;

    public Questions (String questionParam) {
        this.question = questionParam;
    }

    public Questions (String questionParam, int leftResIDParam, int rightResIDParam) {
        this.question = questionParam;
        this.leftResID = leftResIDParam;
        this.rightResID = rightResIDParam;
    }

    public String getAnswer () {
        return answer;
    }

    public void setAnswer (String answer) {
        this.answer = answer;
    }

    public String getQuestion () {
        return question;
    }

    public int getLeftResID () {
        return leftResID;
    }

    public int getRightResID () {
        return rightResID;
    }
}
